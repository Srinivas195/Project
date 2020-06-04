package com.cg.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;


@SuppressWarnings("serial")
//@Data
public class Transfer implements Serializable {
	
	@NotNull
	@Column
	private String recieverName;
	
	@NotNull
	@Column
//	@Size(min = 11, message = "Please enter 11 digits account number")
//	@Pattern(regexp = "[0-9]", message = "Enter valid account number")
	private String accNo;
	
	@NotNull
	@Column
	private String amount;
	
	@NotNull
	@Column
	private String pin;

	
	public Transfer( ){
		
	}
	
	
	
	public String getRecieverName() {
		return recieverName;
	}

	public void setRecieverName(String recieverName) {
		this.recieverName = recieverName;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	
	
	
	
}
