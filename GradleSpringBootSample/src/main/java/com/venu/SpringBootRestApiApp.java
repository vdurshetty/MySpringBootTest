////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2018, Venugopal Durshetty. All rights reserved.
////////////////////////////////////////////////////////////////////////////////
package com.venu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
 
 
@SpringBootApplication(scanBasePackages = {"com.venu"})// same as @Configuration @EnableAutoConfiguration @ComponentScan
public class SpringBootRestApiApp extends SpringBootServletInitializer {
 
    public static void main(String[] args) {
    	System.out.println("In Main.....");
        SpringApplication.run(SpringBootRestApiApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootRestApiApp.class);
    }
    
}

