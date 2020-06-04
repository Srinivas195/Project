package com.cg.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

//@Data
@Entity
@Table(name = "post")
public class Postnews {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="post_id")
//	@NotNull
	private Integer postId;

	@Size(min = 3, message = " should have atleast 3 characters")
	@Pattern(regexp = "[a-zA-Z ]{4,25}", message = "Please type Alphabets for Headline")
	@Column(name = "news_headline")
	@NotNull
	private String headLine;
	

	@Size(min = 3, message = "should have atleast 3 characters")
//	@Pattern(regexp = "^[a-zA-Z ]{4,25}$", message = "Please type Alphabets to fill Details")
	@Column(name = "details")
	@NotNull
	private String details;

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getHeadLine() {
		return headLine;
	}

	public void setHeadLine(String headLine) {
		this.headLine = headLine;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}


	
}
