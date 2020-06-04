package com.cg.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

//@Data
@Entity
@Table(name = "beneficiary")
public class Beneficiary {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="beneficiary_id")
//	@NotNull
	private Integer beneficiaryId;

	@Size(min = 3, message = " should have atleast 3 characters")
	@Pattern(regexp = "^[a-zA-Z ]{4,25}$", message = "Please Enter only alphabets for firstname")
	@Column(name = "first_name")
	@NotNull
	private String firstName;

	
	@Size(min = 3, message = " should have atleast 3 characters")
	@Pattern(regexp = "^[a-zA-Z ]{4,25}$", message = "Please Enter alphabets for lastname")
	@Column(name = "last_name")
	@NotNull
	private String lastName;
	
	@Size(min = 11, message = "Please enter 11 digits account number")
	@Pattern(regexp = "[0-9]{11}", message = "Enter valid account number")
	@Column(name = "account_no", unique = true)
	@NotNull
	private String accountNo;

	@Email(message = "please enter valid email")
	@Column(unique = true)
	@NotNull
	private String email;

	@Size(min = 10, message = "Please enter 10 digits Phone")
	@Pattern(regexp = "[0-9]{10}", message = "Enter valid Phone ")
	@Column(name = "phone_no", unique = true)
	@NotNull
	private String phoneNo;


	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonBackReference
	private Customer customer;

	
	public Beneficiary() {
		
	}

	public Integer getBeneficiaryId() {
		return beneficiaryId;
	}


	public void setBeneficiaryId(Integer beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getAccountNo() {
		return accountNo;
	}


	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	
	

}
