package com.cg.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "request")
public class Request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "request_id")
	private int requestId;
	
	@Column(name= "request")
	private String request;
	
	@Column(name= "address")
	private String address;
	
	@Column(name= "phone_number")
	private long phoneNumber;
	
	@Column(name= "leaves")
	private int leaves;
	
	@Column(name= "status")
	private String status;
	
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonBackReference
	private Customer customer;
	
	
}