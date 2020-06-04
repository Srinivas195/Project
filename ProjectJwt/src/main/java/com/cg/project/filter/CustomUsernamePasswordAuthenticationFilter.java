package com.cg.project.filter;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cg.project.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private Customer usersInfo;
	
	@Override
	protected String obtainUsername(HttpServletRequest request) {
		if(request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
			usersInfo = null;
			try {
				Customer reg = getUsersInfo(request);
				return reg.getEmailId();
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
		}
		return super.obtainUsername(request);
	}
	
	@Override
	protected String obtainPassword(HttpServletRequest request) {
		if(request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
			
			try {
				Customer reg = getUsersInfo(request);
				return reg.getPassword();
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
          return super.obtainPassword(request);
	}
	
	private Customer getUsersInfo(HttpServletRequest request) throws IOException {
		
		if(usersInfo == null) {
			ObjectMapper mapper = new ObjectMapper();
			String json = "";
			BufferedReader reader = request.getReader();
			while (reader.ready()) {
				json = json + reader.readLine();
			}
			usersInfo = mapper.readValue(json, Customer.class);
		}
		return usersInfo;
	}
	
	
}

