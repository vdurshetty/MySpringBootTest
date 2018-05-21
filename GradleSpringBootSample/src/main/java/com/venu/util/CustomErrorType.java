////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2018, Venugopal Durshetty. All rights reserved.
////////////////////////////////////////////////////////////////////////////////
package com.venu.util;

public class CustomErrorType extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorMessage;
	
 
    public CustomErrorType(String errorMessage) {
    	super(errorMessage);
        this.errorMessage = errorMessage;
    }
 
    public String getErrorMessage() {
        return errorMessage;
    }
 
}


