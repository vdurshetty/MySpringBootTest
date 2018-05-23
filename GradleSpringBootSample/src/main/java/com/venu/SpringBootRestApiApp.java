////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2018, Venugopal Durshetty. All rights reserved.
////////////////////////////////////////////////////////////////////////////////
package com.venu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
 
 
@SpringBootApplication(scanBasePackages = {"com.venu"})// same as @Configuration @EnableAutoConfiguration @ComponentScan
//@PropertySource("file:config/application.properties")
@PropertySources({
	@PropertySource("file:config/application.properties"),
	@PropertySource("file:config/job1.properties")
})
public class SpringBootRestApiApp { //extends SpringBootServletInitializer {
 
    public static void main(String[] args) {
    	System.out.println("In Main.....");
        SpringApplication.run(SpringBootRestApiApp.class, args);
    }

    
}

