package com.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		String redirectUrl = "/default";

        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"))) {
            redirectUrl = "/indexAD";
        } else if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("USER"))) {
            redirectUrl = "/indexLogged";
        }

        response.sendRedirect(redirectUrl);
		
	}

}
