package com.example.demo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aspects {

    // To Convert request body Java objects into a JSON string for logging.
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before("execution(* com.example.demo.UserService.*(..))")
    public void beforeMyServiceMethods(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Aspect: Before executing method " + methodName);

        //Retrieving method arguments passed to the controller method like @RequestBody objects and @PathVariable
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg != null) {
                try {
                    String json = objectMapper.writeValueAsString(arg); //Converting object to a JSON string.
                    System.out.println("Request Body: " + json);
                } catch (Exception e) {
                    System.out.println("Error logging request body: " + e.getMessage());
                }
            }
        }
    }

}
