package com.venu.validator;

import org.springframework.stereotype.Component;

import com.venu.util.CustomErrorType;

@Component
public interface Validator<T> {
	
	void validate(T input) throws CustomErrorType;

}
