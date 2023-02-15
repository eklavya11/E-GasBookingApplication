package com.gasbooking.app.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class GasBooking {
	@Id
	@GeneratedValue
	private Integer gasBookingId;
	@ManyToOne
	private Customer customer;
	//@JsonFormat(pattern = "YYYY-MM-dd")
	@PastOrPresent(message="Date should be in the past or present")
    private LocalDate bookingDate;
	@ManyToOne
	private Cylinder cylinder;
	private boolean paymentStatus;
	private boolean deliveryStatus;
	private float bill;
	
	public GasBooking() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public GasBooking(Integer gasBookingId, Customer customer, LocalDate bookingDate, Cylinder cylinder,
			boolean paymentStatus, boolean deliveryStatus, float bill) {
		super();
		this.gasBookingId = gasBookingId;
		this.customer = customer;
		this.bookingDate = bookingDate;
		this.cylinder = cylinder;
		this.paymentStatus = paymentStatus;
		this.deliveryStatus = deliveryStatus;
		this.bill = bill;
	}

    

	public boolean isDeliveryStatus() {
		return deliveryStatus;
	}



	public void setDeliveryStatus(boolean deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}



	public Integer getGasBookingId() {
		return gasBookingId;
	}

	public void setGasBookingId(Integer gasBookingId) {
		this.gasBookingId = gasBookingId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Cylinder getCylinder() {
		return cylinder;
	}

	public void setCylinder(Cylinder cylinder) {
		this.cylinder = cylinder;
	}

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public float getBill() {
		return bill;
	}

	public void setBill(float bill) {
		this.bill = bill;
	}
	
	
	
}