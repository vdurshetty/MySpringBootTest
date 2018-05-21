package com.venu.validator;

import org.springframework.stereotype.Component;

import com.venu.model.Emp;
import com.venu.util.CustomErrorType;

@Component("validateEmp")
public class ValidateEmp implements Validator<Emp> {

	@Override
	public void validate(Emp input) throws CustomErrorType {
		
		if (input.getEname().length() < 3) {
			throw new CustomErrorType("Employee Name cannot be less than 3 chars");
		}

			
	}

}
