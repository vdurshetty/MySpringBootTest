////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2018, Venugopal Durshetty. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package com.venu.model;

import io.swagger.annotations.ApiModelProperty;

public class User {
	
	@ApiModelProperty (value = "The id", position = 1)
	private long id;
	@ApiModelProperty(value = "User Name", position = 2)
	private String name;
	@ApiModelProperty(value = "User Age", position = 3)
	private int age;
	@ApiModelProperty(value = "User Salary", position = 4)
	private double salary;

	public User() {
		id = 0;
	}
	
	public User(long id, String name, int age, double salary) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age
				+ ", salary=" + salary + "]";
	}
}
