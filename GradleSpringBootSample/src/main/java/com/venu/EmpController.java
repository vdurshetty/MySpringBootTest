////////////////////////////////////////////////////////////////////////////////
//
//Copyright (c) 2018, Venugopal Durshetty. All rights reserved.
////////////////////////////////////////////////////////////////////////////////
package com.venu;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.venu.model.Emp;
import com.venu.service.EmpService;
import com.venu.service.resp.ErrorResponse;
import com.venu.service.resp.ServiceResponse;
import com.venu.validator.Validator;



@RestController
@RequestMapping("/api")
public class EmpController {

	
	@Autowired
	private EmpService empService;
	
	@Autowired
	private Validator<Emp> validator;
	
	
	
	
	
	

	@RequestMapping(value = "/hai", method = RequestMethod.GET)
	public String hello() {
			return "Hai Venu";
	}

	
		@ResponseBody
	  @RequestMapping(value = "/emps", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	  public List<Emp> listAllEmps() throws Exception {
	        System.out.println("Fetching All Employees");
	        List<Emp> emps = empService.getAllEmps();
	        return emps;
	  }
	 
	    // -------------------Retrieve Single User------------------------------------------
	 
	  	@ResponseBody
	    @RequestMapping(value = "/emp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	    public Emp getEmp(long id) throws Exception{
	        System.out.println("Fetching User with id {}" + id);
	        Emp emp = empService.getEmp(id);
	        if (emp == null) {
	            System.out.println("User with id {} not found." + id);
	            throw new Exception("No Employees Available with ID :" + id);
	        }
	        return emp;
	    }
	 
	    // -------------------Create a User-------------------------------------------
	 
	    @ResponseBody
	    @RequestMapping(value = "/emp", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ServiceResponse createEmp(@Valid @RequestBody Emp emp) throws Exception{
	        System.out.println("Creating Emp : {}" + emp);
	        validator.validate(emp);
	        
	        ServiceResponse res = new ServiceResponse();
	        res.setResId(UUID.randomUUID().toString());
	        
	        if (empService.addEmp(emp)) {
	        	res.setMessage("Employee Successfully Added!");;
	        } 
	        return res;
	    }
	    
	    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	    @ExceptionHandler({ Exception.class })
	    @ResponseBody
	    public ErrorResponse handleFailures(Exception e)
	    {
	    	e.printStackTrace();
	        ErrorResponse error = new ErrorResponse();
	        error.setDesc(e.getLocalizedMessage());
	        error.setMessage(e.getMessage());
	        error.setErrorId(UUID.randomUUID().toString());
	        error.setErrorNumber("168");
	        if (e.getCause()!=null) {
	        	error.setDetails(e.getCause().toString());
	        }
	        return error;
	    }
}

