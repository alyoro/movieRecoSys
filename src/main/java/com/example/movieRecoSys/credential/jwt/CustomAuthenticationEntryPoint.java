package com.example.movieRecoSys.credential.jwt;

import org.springframework.security.web.AuthenticationEntryPoint;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         org.springframework.security.core.AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json");
        response.getOutputStream().print("{\"error\":\"Unauthorized.. Please authenticate..\"}");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    }
}