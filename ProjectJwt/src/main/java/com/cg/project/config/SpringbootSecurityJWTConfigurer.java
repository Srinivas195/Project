package com.cg.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cg.project.BootAuthenticationEntryPoint;
import com.cg.project.filter.JwtRequestFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringbootSecurityJWTConfigurer extends WebSecurityConfigurerAdapter {

	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
	@Autowired
	private BootAuthenticationEntryPoint  bootAuthenticationEntryPoint;
	
	@Autowired 
	private UserDetailsService userDetailsService;
	
	@Autowired 
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService);
		
	} // End of configureGlobal()
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable()
		
		http.cors().and().csrf().disable()
		    .authorizeRequests().antMatchers("/api/login", "/api/register").permitAll()
		    .and()
		    .authorizeRequests().antMatchers("/api/requests/{pageNumber}/{itemsPerPage}","/api/requests/{pageNumber}/{itemsPerPage}/{fieldName}","/api/transactions/{pageNo}/{itemsPerPage}","/api/transactions/{pageNo}/{itemsPerPage}/{fieldName}","/api/getusers" , "/api/customers/{pageNo}/{itemsPerPage}","/api/addusers" ,"/api/customers/{pageNo}/{itemsPerPage}/{fieldName}","/api/getpost","/api/postnews","/api/deleteCustomer/{id}","/api/getTransactions", "/api/get-customers" , "api/customer/{customerId}","/api/add-customer","/api/update-customer").hasRole("ADMIN")
		    .and()
		    .authorizeRequests().antMatchers("/api/get-beneficiaries","/api/getpost","/api/addRequest/{id}","/api/myrequest/{id}","/api/request/{id}","/api/beneficiary/{id}","/api/benupdate","/api/delete-beneficiary/{id}","/api/addBeneficiary/{id}","/api/atm/{id}","/api/transfer/{id}","/api/myTransaction/{id}","/api/myBeneficiary/{id}","/api/credit/{id}").hasRole("USER")
		    .anyRequest().authenticated()
		    .and()
		    .exceptionHandling().authenticationEntryPoint(bootAuthenticationEntryPoint)
		    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
		    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		    
	}// End of configure()
	
	
	
}// end of class
