package com.cg.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@SuppressWarnings("serial")
@Data

public class Atm implements Serializable {
	
	@NotNull
	@Pattern(regexp = "[0-9]{3}" , message = "Enter valid amount ")
	private String amount;
	
	@NotNull
	@Size(min = 3, message = " should have atleast 3 characters")
	@Pattern(regexp = "^[a-zA-Z ]{4,25}$", message = "Please Enter Alphabets")
	private String type;
	
	@NotNull
	@Size(min = 4, message = "Please enter 4 digits pin")
	@Pattern(regexp = "[0-9]{4}", message = "Enter valid pin number")
	private String pin;

}


