package com.venu.model;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModelProperty;

@Component
public class Address {
	
	@NotBlank (message = "Door Number is required and cannot be empty")
	@ApiModelProperty (value = "Door Number", position = 1)
	private String doorNo;
	
	@ApiModelProperty(value = "Street Name", position = 2)
	private String street;
	
	@NotBlank (message = "Suberb is required and cannot be empty")
	@ApiModelProperty(value = "Suburb Name", position = 3)
	private String suburb;
	
	@ApiModelProperty(value = "Pin Code", position = 4)
	private String pin;

	public Address() {
		super();
	}
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getSuburb() {
		return suburb;
	}
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	
	

}
