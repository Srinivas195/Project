package com.cg.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class BootAuthenticationEntryPoint implements AuthenticationEntryPoint {

//	@Override
//	public void commence(HttpServletRequest request, HttpServletResponse response,
//			AuthenticationException authException) throws IOException, ServletException {
//       response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied !!!");		
//	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.core.AuthenticationException authException)
			throws IOException, ServletException {
	       response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied !!!");		

		
	}



}