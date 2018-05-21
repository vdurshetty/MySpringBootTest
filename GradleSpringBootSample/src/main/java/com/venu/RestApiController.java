////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2018, Venugopal Durshetty. All rights reserved.
////////////////////////////////////////////////////////////////////////////////
package com.venu;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.venu.model.User;
import com.venu.service.UserService;
import com.venu.service.resp.ErrorResponse;
import com.venu.service.resp.ServiceResponse;
import com.venu.util.CustomErrorType;
 

 
@RestController
@RequestMapping("/api")
public class RestApiController {
 
    public static final Logger LOGGER = LoggerFactory.getLogger(RestApiController.class);
 
    @Autowired
    private UserService userService; //Service which will do all data retrieval/manipulation work
    
    @Autowired
    private ServiceResponse response;
 
    // -------------------Retrieve All Users---------------------------------------------
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
         return "Hello Venu";
    }
 
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<?> listAllUsers() {
        List<User> users = userService.findAllUsers();
        //users = new ArrayList<User>();
        System.out.println("Users Count is : " + users.size());
        if (users.isEmpty()) {
            return new ResponseEntity<String>("No Data Found", HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
            
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
 
    // -------------------Retrieve Single User------------------------------------------
 
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id {}" + id);
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id {} not found." + id);
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("User with id " + id 
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
 
    // -------------------Create a User-------------------------------------------
 
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) throws Exception{
        System.out.println("Creating User : {}" + user);
        response.setResId(UUID.randomUUID().toString());
 
        if (userService.isUserExist(user)) {
            response.setMessage("Unable to create. A User with name " +  user.getName() + " already exist.");
            return new ResponseEntity<ServiceResponse>(response, HttpStatus.CONFLICT);
        }

        userService.saveUser(user);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
        
        response.setMessage("Record Successfully Created!!!!");
        return new ResponseEntity<ServiceResponse>(response, headers, HttpStatus.CREATED);
    }
 
    // ------------------- Update a User ------------------------------------------------
 
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating User with id {}" + id);
 
        User currentUser = userService.findById(id);
 
        if (currentUser == null) {
            System.out.println("Unable to update. User with id {} not found." + id);
            return new ResponseEntity<String>("Unable to upate. User with id " + id + " not found.",HttpStatus.NOT_FOUND);
        }
 
        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());
        currentUser.setSalary(user.getSalary());
 
        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
 
    // ------------------- Delete a User-----------------------------------------
 
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id {}" + id);
 
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id {} not found." + id);
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
 
    // ------------------- Delete All Users-----------------------------
 
    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        System.out.println("Deleting All Users");
 
        userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
 
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ CustomErrorType.class })
    @ResponseBody
    public ErrorResponse handleFailures(Exception e)
    {
        ErrorResponse error = new ErrorResponse();
        error.setDesc(e.getLocalizedMessage());
        error.setMessage(e.getMessage());
        error.setErrorId(UUID.randomUUID().toString());
        error.setErrorNumber("16");
        if (e.getCause()!=null) {
        	error.setDetails(e.getCause().toString());
        }
        return error;
    }
    
}

