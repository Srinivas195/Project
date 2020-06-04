package com.cg.project;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.cg.project.entity.Customer;

@SuppressWarnings("serial")
@Component
public class UserDetailsImpl implements UserDetails {

	private Customer usersInfo;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(usersInfo.getRole());
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		
		return usersInfo.getPassword();
	}

	@Override
	public String getUsername() {
		
		return usersInfo.getEmailId();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
    
	public Customer getUsersInfo() {
		return usersInfo;
	}
	
	public void setUsersInfo(Customer usersInfo2) {
		this.usersInfo = usersInfo2;
	}


}
