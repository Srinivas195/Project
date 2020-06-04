package com.cg.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class Credit implements Serializable {

	@Pattern(regexp = "[0-9]{5}", message = "Enter valid amount ")
	@Column(name = "opening_balance")
	private String amount;

}