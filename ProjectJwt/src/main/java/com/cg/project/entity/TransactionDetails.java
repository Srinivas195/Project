package com.cg.project.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "transactions")
public class TransactionDetails implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trans_id")
	private Integer transId;

	@Column(name = "date_and_time")
	private Date date;

//	@Pattern(regexp = "((?=.[a-z])(?=.\\\\d)(?=.[A-Z])(?=.[@#$%!]).{8,})", message = "Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character")
	@Column
	private String remarks;

	@Column
//	@Pattern(regexp = "^[a-zA-Z ]{4,25}$", message = "Please Enter Alphabets")
	private String credit;

	@Column
//	@Pattern(regexp = "^[a-zA-Z ]{4,25}$", message = "Please Enter Alphabets")
	private String debit;

	@Column
//	@Pattern(regexp = "[0-9]{5}", message = "Enter valid amount ")
	private String balance;

	@Column
	private String id;

	@ManyToOne()
	@JoinColumn(name = "customer_id")
	@JsonBackReference
	private Customer customer;

}
