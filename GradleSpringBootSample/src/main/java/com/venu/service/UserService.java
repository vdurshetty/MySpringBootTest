////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2018, Venugopal Durshetty. All rights reserved.
////////////////////////////////////////////////////////////////////////////////
package com.venu.service;


import java.util.List;

import com.venu.model.User;
import com.venu.util.CustomErrorType;


public interface UserService {

    String sayHello(String msg);
    
    User findById(long id);
    
    User findByName(String name);
    
    void saveUser(User user) throws CustomErrorType;
    
    void updateUser(User user);
    
    void deleteUserById(long id);
    
    List<User> findAllUsers();
    
    void deleteAllUsers();
    
    boolean isUserExist(User user);
    
}
