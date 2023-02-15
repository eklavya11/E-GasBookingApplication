package com.gasbooking.app.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;



@Entity
public class Customer  {
	@Id
	@GeneratedValue
	private Integer customerId;
	@OneToOne
	private Bank bank;
	@OneToOne
	private SurrenderCylinder surrender;
	@OneToOne
	private Address address;
	@NotBlank(message = "Name is mandatory")
	private String username;
	@Pattern(regexp = "[a-zA-Z0-9]{8,}",message = "pwd must be 8 chars, no special chars are alllowed")
	private String password;
	@Pattern(regexp="(^$|[0-9]{10})",message = "mobile number must be of 10 digits only")
	private String mobileNumber;
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Invalid email.")
	private String email;
	
	@OneToMany(fetch = FetchType.EAGER)
	List<GasBooking> gasbookings = new ArrayList<>();
	 
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public Customer(Integer customerId, Bank bank, SurrenderCylinder surrender, Address address, String username,
			String password, String mobileNumber, String email, List<GasBooking> gasbookings) {
		super();
		this.customerId = customerId;
		this.bank = bank;
		this.surrender = surrender;
		this.address = address;
		this.username = username;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.gasbookings = gasbookings;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public SurrenderCylinder getSurrender() {
		return surrender;
	}

	public void setSurrender(SurrenderCylinder surrender) {
		this.surrender = surrender;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public List<GasBooking> getGasbookings() {
		return gasbookings;
	}

	public void setGasbookings(List<GasBooking> gasbookings) {
		this.gasbookings = gasbookings;
	}
	
	
	
	
	
	
	
}