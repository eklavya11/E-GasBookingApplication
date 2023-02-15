package com.gasbooking.app.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.PastOrPresent;
@Entity
public class SurrenderCylinder {
	@Id
	@GeneratedValue
	private Integer surrenderId;
	@PastOrPresent(message="Date should be in the past or present")
	private LocalDate surrenderDate;
	
	private String status;
	@OneToOne
	private Customer customer;
	
	public SurrenderCylinder() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public SurrenderCylinder(Integer surrenderId, LocalDate surrenderDate, String status, Customer customer) {
		super();
		this.surrenderId = surrenderId;
		this.surrenderDate = surrenderDate;
		this.status = status;
		this.customer = customer;
	}

     
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Integer getSurrenderId() {
		return surrenderId;
	}

	public void setSurrenderId(Integer surrenderId) {
		this.surrenderId = surrenderId;
	}

	public LocalDate getSurrenderDate() {
		return surrenderDate;
	}

	public void setSurrenderDate(LocalDate surrenderDate) {
		this.surrenderDate = surrenderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
}
