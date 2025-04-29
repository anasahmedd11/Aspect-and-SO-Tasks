package com.example.demo.Security;

import com.example.demo.Entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

//1.User logs in → You generate a JWT using generateJwtToken() → it’s signed with  key.
//2.User sends JWT on future requests (in the Authorization header).
//3.Your backend uses the same key to validate the JWT and extract the user.

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    //Injects values from application.properties: jwt.secret: A base64-encoded secret key and jwt.expiration.ms: Token lifetime
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration.ms}")
    private int jwtExpirationMs;

    //Secret key used to sign and verify JWT tokens. It's used internally to:
    //1.Sign the token when it's generated (so no one can tamper with it).
    //2.Verify the token when a request comes in (to ensure it was not forged).
    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String generateJwtToken(Authentication authentication) {

        //Gets the authenticated user (after successful login).
        User userPrincipal = (User) authentication.getPrincipal();

        //Builds a JWT: Subject → username, Issued at → now, Expiry → now + jwtExpirationMs, Signature algorithm → HS512 using your secret key
        //Add role if desired
        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), Jwts.SIG.HS256)
                .compact();
    }

    //Parses and validates the JWT using your key.
    //Extracts the subject (username) from the token payload.
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload().getSubject();
    }

    //Attempts to parse and verify the JWT.
    //If any parsing, signature, or expiry issue occurs, logs the error and returns false.
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().verifyWith(key()).build().parseSignedClaims(authToken);
            return true;
        } catch (Exception e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        }
        return false;
    }
} 