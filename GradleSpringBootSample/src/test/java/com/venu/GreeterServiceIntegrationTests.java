package com.venu;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.venu.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GreeterServiceIntegrationTests {

  @Autowired
  @Qualifier("userService")
  private UserService userService;
  @Test
  public void sayHello_whenInvokedWithDuke_thenSaysHelloWorldDuke() {

    // When
    String greeting =  userService.sayHello("Duke");

    // Then
    assertThat(greeting).isEqualTo("Hello World, Duke");

  }
  
  
  @Test
  public void testGetUser() {
	  
	  System.out.println(userService.findAllUsers().toString());
	  
  }

}