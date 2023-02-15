package com.gasbooking.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
public class Bank  {
	@Id
	@GeneratedValue
	private Integer bankId;
	@NotBlank(message = "Bank Name is mandatory")
	private String bankName;
	@NotBlank(message = "Bank Address is mandatory")
	private String bankAddress;
	
	private int accountNo;
	@Pattern(regexp = "[a-zA-Z0-9]{12}",message = "ifscNo must be 12 alphanumeric characters")
	private String ifscNo;
	@Pattern(regexp = "[a-zA-Z0-9]{10}",message = "pan must be 10 alphanumeric characters, space is not allowed")
	private String pan;
	
	public Bank(Integer bankId, String bankName, String address, int accountNo, String ifscNo, String pan) {
		super();
		this.bankId = bankId;
		this.bankName = bankName;
		this.bankAddress = address;
		this.accountNo = accountNo;
		this.ifscNo = ifscNo;
		this.pan = pan;
	}
	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public String getIfscNo() {
		return ifscNo;
	}
	public void setIfscNo(String ifscNo) {
		this.ifscNo = ifscNo;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public Integer getBankId() {
		return bankId;
	}
	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String address) {
		this.bankAddress = address;
	}
	
}