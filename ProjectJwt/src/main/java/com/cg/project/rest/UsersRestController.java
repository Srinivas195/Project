package com.cg.project.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.project.config.JwtUtil;
import com.cg.project.entity.Customer;
import com.cg.project.exception.EmailNotFoundException;
import com.cg.project.exception.MessageException;
import com.cg.project.exception.UserNotFoundException;
import com.cg.project.response.Response;
import com.cg.project.response.TokenResponse;
import com.cg.project.service.CustomerService;
import com.cg.project.service.UsersService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class UsersRestController {
	
	
	private CustomerService customerService;

	private UsersService usersService;
	@Autowired
	public UsersRestController(UsersService theUsersService, CustomerService thecustomerService) {
		this.usersService = theUsersService;
		this.customerService = thecustomerService;
	}
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	


	
	@PostMapping("/login")
	public TokenResponse<Customer> login(@RequestBody Customer register) throws Exception{
	
		Customer customer =usersService.findByemail(register.getEmailId());
		if(customer == null) {
			return new TokenResponse<Customer>(true, "Invalid Email",null,null);

		}
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(register.getEmailId(),register.getPassword()));
		} catch(DisabledException de) {
//			throw new Exception("User disabled",de);

			throw new EmailNotFoundException("User disabled");
			
		} catch(BadCredentialsException bce) {
			
			return new TokenResponse<Customer>(true, "Invalid Password",null,null);

		}// End of try catch
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(register.getEmailId());
	
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return new TokenResponse<Customer>(false, "Login Successful",jwt,customer);
	}// End of login()
	
	
	//expose "/Assets" and return the list
	@GetMapping("/getusers")
	public Response<List<Customer>> findAll() throws UserNotFoundException {
		List<Customer> users = usersService.findAll();
		if (users != null) {
			return new Response<>(false, "records found", users);
		} else {
			throw new UserNotFoundException();
		}
	}
	
	// add mapping for GET /Assets/{assetId}
	@GetMapping("/getusers/{id}")
	public Response<Customer> getRequestForm(@PathVariable int id) throws UserNotFoundException {
		Customer theRequestForm = usersService.findById(id);
		
		if (theRequestForm != null) {
			return new Response<>(false, "records found", theRequestForm);
		} else {
			throw new UserNotFoundException();
		}
	}
	
	// add for POST /Assets
	
	@PostMapping("/addusers")
	public Response<Customer> addRequestForm(@Valid  @RequestBody Customer theRequestForm) {
		
//		theRequestForm.setRequestID(0);
		Customer res = customerService.findByEmail(theRequestForm.getEmailId());
		Customer res1 = customerService.findByPhone(theRequestForm.getPhoneNo());
		Customer res2 = customerService.findByAadhar(theRequestForm.getAadharNo());
		Customer res3 = customerService.findByAccno(theRequestForm.getAccountNo());

		if( res !=null) {
			return new Response<Customer>(true,"This Email already Exist",null);
			
		}else if( res1 !=null) {
			return new Response<Customer>(true,"This Phone Number already Exist",null);

		}else if( res2 !=null){
			return new Response<Customer>(true,"This Aadhar Number already Exist",null);

		}else if( res3 !=null){
			return new Response<Customer>(true,"This Account already Exist",null);

		}
		
		theRequestForm.setCustomerId(0);
		
		
		usersService.save(theRequestForm);
		if (theRequestForm != null) {

			return new Response<>(false, "successfully saved", theRequestForm);

		} else {
			return new Response<>(true, "save failed", null);
		}
	}
	
	@PostMapping("/register")
	public Response<Customer> addRequestForm1(@RequestBody Customer theRequestForm) {
		
//		theRequestForm.setRequestID(0);
		
		usersService.save(theRequestForm);
		if (theRequestForm != null) {

			return new Response<>(false, "successfully saved", theRequestForm);

		} else {
			return new Response<>(true, "save failed", null);
		}
	}
	
	// add mapping for PUT /Assets - update
	
	@PutMapping("/users")
	public Response<Customer> updateRequestForm(@RequestBody Customer theRequestForm) {
		customerService.update(theRequestForm);
		if (theRequestForm != null) {

			return new Response<>(false, "successfully saved", theRequestForm);

		} else {
			return new Response<>(true, "save failed", null);
		}
	}
	
		
	
	 
	 @DeleteMapping("/deleteCustomer/{id}")
		public Response<Customer> deleteBeneficiary(@PathVariable int id) {

			Customer beneficiary = usersService.findById(id);

			if (beneficiary != null) {
				usersService.deleteById(id);
				return new Response<Customer>(false, "Beneficiary deleted successfully", beneficiary);
			} else {
				throw new MessageException("Not deleted");
			}
		}

	
	
}
