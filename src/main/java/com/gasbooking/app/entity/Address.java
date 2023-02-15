package com.gasbooking.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Address {
	@Id
	@GeneratedValue
	private Integer addressId;
	@NotBlank(message = "Society is mandatory")
	private String society;
	private int flatNo;
	//@NotBlank(message ="Area is mandatory")
	private String area;
	private int pincode;
    
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Address(Integer addressId, String society, int flatNo, String area, int pincode) {
		super();
		this.addressId = addressId;
		this.society = society;
		this.flatNo = flatNo;
		this.area = area;
		this.pincode = pincode;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getSociety() {
		return society;
	}
	public void setSociety(String society) {
		this.society = society;
	}
	public int getFlatNo() {
		return flatNo;
	}
	public void setFlatNo(int flatNo) {
		this.flatNo = flatNo;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
    
    
    
    
    
}
