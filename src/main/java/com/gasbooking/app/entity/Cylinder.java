package com.gasbooking.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Cylinder {
	@Id
	@GeneratedValue
	private Integer cylinderId;
	@Pattern(regexp = "[DOMESTIC|COMMERCIAL]",message = "Cylinder type must be DOMESTIC|COMMERCIAL")
	private CylinderType type;
	@Min(value=500,message = "Price value should be greater than 500")
	@Max(value=10000,message = "Price value should be less than 1000")
	@NotBlank(message="price should not be empty")
	private float price;
	@Min(value=5,message = "Price value should be greater than 5")
	@Max(value=50,message = "Price value should be less than 50")
	@NotNull(message="weight should not be empty")
	private Integer weight;
	
	public Cylinder() {
		super();
	}

	public Cylinder(Integer cylinderId, CylinderType type, float price, Integer weight) {
		super();
		this.cylinderId = cylinderId;
		this.type = type;
		this.price = price;
		this.weight = weight;
	}

	public Integer getCylinderId() {
		return cylinderId;
	}
	public CylinderType getType() {
		return type;
	}

	public void setType(CylinderType type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
}
