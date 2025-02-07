package com.istanbul_tech.homework.exception.entry.points;

import com.istanbul_tech.homework.dto.body.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        ErrorResponse errorResponse = new ErrorResponse(
                Instant.now().toString(),
                HttpServletResponse.SC_FORBIDDEN,
                "Forbidden",
                "You are not authorized to access this resource",
                "Forbidden",
                request.getRequestURI()
        );
        response.getWriter().write(errorResponse.toString());
    }
}
