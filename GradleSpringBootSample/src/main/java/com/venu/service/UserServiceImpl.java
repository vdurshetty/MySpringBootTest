////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2018, Venugopal Durshetty. All rights reserved.
////////////////////////////////////////////////////////////////////////////////
package com.venu.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.venu.model.User;
import com.venu.util.CustomErrorType;
import com.venu.validator.Validator;

@Service("userService")
public class UserServiceImpl implements UserService {
   
    
	@Autowired
	@Qualifier("validateUser")
	private Validator<User> validateUser;
	
	
	private static final AtomicLong COUNTER = new AtomicLong();
    private List<User> users;

    
    public UserServiceImpl() {
    	//validator =  new ValidateUser();
      users = populateDummyUsers();
    }
    
    public String sayHello(String msg) {
         return "Hello World, " + msg;
    }

    public List<User> findAllUsers() {
         return users;
    }
    
    public User findById(long id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
           }
        }
        return null;
    }
    
    public User findByName(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
               return user;
            }
        }
        return null;
    }
    
    public void saveUser(User user) throws CustomErrorType {
    	validateUser.validate(user);
        user.setId(COUNTER.incrementAndGet());
        users.add(user);
    }
    
    public void updateUser(User user) {
	    int index = users.indexOf(user);
	    users.set(index, user);
	}
	
	public void deleteUserById(long id) {
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
		    User user = iterator.next();
		    if (user.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isUserExist(User user) {
		return findByName(user.getName()) != null;
	}
	
	public void deleteAllUsers() {
		users.clear();
	}

	private static List<User> populateDummyUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User(COUNTER.incrementAndGet(), "Sam", 30, 70000));
		users.add(new User(COUNTER.incrementAndGet(), "Tom", 40, 50000));
		users.add(new User(COUNTER.incrementAndGet(), "Jerome", 45, 30000));
		users.add(new User(COUNTER.incrementAndGet(), "Silvia", 50, 40000));
		return users;
	}

}
