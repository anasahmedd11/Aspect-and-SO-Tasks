package com.example.demo.Security;

import com.example.demo.Services.UserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    //Utility class for validating and extracting data from JWT tokens.
    @Autowired
    private JwtUtil jwtUtil;

    //Custom implementation of UserDetailsService to load user details from the database by username.
    @Autowired
    private UserDetailService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            //Extracts the JWT token from the Authorization header in each HTTP request.
            String jwt = parseJwt(request);

            //If the JWT is present and valid, the username is extracted from the JWT.
            if (jwt != null && jwtUtil.validateJwtToken(jwt)) {
                String username = jwtUtil.getUserNameFromJwtToken(jwt);

                //Loads the user from the database using the extracted username.
                //Then Builds an Authentication object containing: user’s details and their roles/authorities
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null,
                                userDetails.getAuthorities());

                //Creates an object (WebAuthenticationDetails) that holds extra info about the current request, as: IP address and Session ID
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //Stores the Authentication object in the security context, which is a thread-local storage that holds authentication info for the current request.
                //Without this line, even if the token is valid, Spring Security would still treat the request as unauthenticated.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e.getMessage());
        }

        //Passes the request and response to the next filter in the filter chain.
        //Filter chain is like a pipeline, every filter must decide either to:
        //1.Allow the request to move forward (doFilter(...))
        //2.Stop it (e.g., if authentication fails, throw an exception or return a 403/401)
        //If you don’t call this method, the rest of the request processing (like accessing controllers or other filters)
        //will never happen, and the request may appear stuck or return no response.
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
} 