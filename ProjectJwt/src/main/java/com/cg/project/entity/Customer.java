package com.cg.project.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

//@Data
@SuppressWarnings("unused")
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int customerId;

	@NotNull
	@Size(min = 3, message = " should have atleast 3 characters")
	@Pattern(regexp = "^[a-zA-Z ]{4,25}$", message = "Please Enter Alphabets for firstname")
	@Column(name = "first_name")
	private String firstName;

	@NotNull
	@Size(min = 1, message = " should have atleast 3 characters")
	@Pattern(regexp = "^[a-zA-Z ]{1,25}$", message = "Please Enter Alphabets for lastname")
	@Column(name = "last_name")
	private String lastName;

	@NotNull
//	@Pattern(regexp = "^[a-zA-Z ]{4,25}$", message = "Please Enter Alphabets ")
	@Column(name = "gender")
	private String gender;

	@NotNull
	@Column(name = "dob")
	private String dob;

	@NotNull
	@Email(message = "please enter valid email")
	@Column(name = "email_id")
	private String emailId;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Please Enter Alphanumeric")
	@Column(name = "address")
	private String address;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z ]{4,25}$", message = "Please Enter Alphabets for branch")
	@Column(name = "branch")
	private String branch;

	@NotNull
	@Size(min = 11, message = "Please enter 11 digits aadhar")
//	@Pattern(regexp = "[0-9]{11}", message = "Enter valid aadhar")
	@Column(name = "aadhar_no")
	private String aadharNo;

	@NotNull
	@Size(min = 10, message = "Please enter 10 digits Phone number")
	@Pattern(regexp = "[0-9]{10}", message = "Enter valid phone number")
	@Column(name = "phone_no")
	private String phoneNo;

	@NotNull
	@Size(min = 11, message = "Please enter 11 digits account number")
	@Pattern(regexp = "[0-9]{11}", message = "Enter valid account number")
	@Column(name = "account_no")
	private String accountNo;

	@NotNull
	 @Pattern(regexp = "[0-9]{5,7}" , message = "Enter valid amount ")
	@Column(name = "opening_balance")
	private String openingBalance;

	@NotNull
	@Size(min = 4, message = "Please enter 4 digits pin")
	@Pattern(regexp = "[0-9]{4}", message = "Enter valid pin number")
	@Column(name = "pin")
	private String pin;

	@NotNull
	 @Pattern(regexp = "^[a-z0-9_-]{3,15}$" , message = "Enter correct username")
	@Column(name = "username")
	private String username;

	@NotNull
	@Pattern(regexp = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,})" , message = "Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character")
	@Column(name = "password")
	private String password;



	
//	@NotNull
	@Column(name = "role")
	private String role;

//	"((?=.[a-z])(?=.\\d)(?=.[A-Z])(?=.[@#$%!]).{8,})"

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Beneficiary> beneficiaryList;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<TransactionDetails> transcationDetailsList;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Request> requestList;
	
	
	public Customer() {
		
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(String openingBalance) {
		this.openingBalance = openingBalance;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnoreProperties
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnoreProperties
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Beneficiary> getBeneficiaryList() {
		return beneficiaryList;
	}

	public void setBeneficiaryList(List<Beneficiary> beneficiaryList) {
		this.beneficiaryList = beneficiaryList;
	}

	public List<TransactionDetails> getTranscationDetailsList() {
		return transcationDetailsList;
	}

	public void setTranscationDetailsList(List<TransactionDetails> transcationDetailsList) {
		this.transcationDetailsList = transcationDetailsList;
	}

	public List<Request> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<Request> requestList) {
		this.requestList = requestList;
	}

	
	
	
}
