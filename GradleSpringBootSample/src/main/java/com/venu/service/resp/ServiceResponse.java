package com.venu.service.resp;

import org.springframework.stereotype.Component;

@Component
public class ServiceResponse {
	
	private String resId;
	private String message;
	public ServiceResponse() {
		super();
	}
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
