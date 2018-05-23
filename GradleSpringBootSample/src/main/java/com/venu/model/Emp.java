package com.venu.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModelProperty;

@Component
public class Emp {
	
	@NotNull (message = "Employee ID is required and cannot be blank")
	@ApiModelProperty (value = "The Employee Idd", position = 1)
	private Long eid;
	
	@NotBlank
	@Pattern(
	        regexp = "^[a-zA-Z]+$" ,
	        message = "Ename can only have charecters"
	    )
	@Size(max = 20, message = "Ename can be max of 20 chars")
	@ApiModelProperty(value = "Employee Name", position = 2)
	private String ename;
	
	@ApiModelProperty(value = "Employee Salary", position = 3)
	private float sal;
	
	@Valid
	@NotNull (message = "Address is required !")
	@ApiModelProperty(value = "Employee Address", position = 4)
	private Address address;
	
	public Emp() {
		super();
	}
	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public float getSal() {
		return sal;
	}
	public void setSal(float sal) {
		this.sal = sal;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	

}
