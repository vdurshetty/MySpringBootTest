package com.venu.validator;

import org.springframework.stereotype.Component;

import com.venu.model.User;
import com.venu.util.CustomErrorType;

@Component("validateUser")
public class ValidateUser implements Validator<User>{

	@Override
	public void validate(User input) throws CustomErrorType {
		
		if (input.getName().length() < 3) {
			throw new CustomErrorType("User Name cannot be less than 3 chars");
		}
		
	}
	
	

}
