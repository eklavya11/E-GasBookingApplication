package com.gasbooking.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
public class Admin {
	@Id
	@GeneratedValue
	private Integer adminId;
	@NotBlank(message = "UserName is mandatory")
	private String username;
	@Pattern(regexp = "[a-zA-Z0-9]{8,}",message = "Password must be 8 characters, no special characters are allowed")
	private String password;
	@Pattern(regexp="(^$|[0-9]{10})",message="Mobile number should be 10 digits")
	private String mobileNumber;
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Invalid email.")
	private String email;
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(Integer adminId, String username, String password, String mobileNumber, String email) {
		super();
		this.adminId = adminId;
		this.username = username;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}