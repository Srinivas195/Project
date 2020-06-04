package com.cg.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cg.project.service.UsersService;


@Component
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsersService registerService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  
		UserDetailsImpl userDetailsImpl = new UserDetailsImpl();
		
		userDetailsImpl.setUsersInfo(registerService.findByemail(username));
		
//		userDetailsImpl.setUsersInfo(registerService.);
		
		return userDetailsImpl;
	}

}
