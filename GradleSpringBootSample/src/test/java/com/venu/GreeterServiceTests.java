package com.venu;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.venu.service.UserService;
import com.venu.service.UserServiceImpl;

public class GreeterServiceTests {

  @Test
  public void sayHello_whenInvokedWithDuke_thenSaysHelloWorldDuke() {

    // Given
    UserService userService = new  UserServiceImpl();

    // When
    String greeting = userService.sayHello("Duke");

    // Then
    assertThat(greeting).isEqualTo("Hello World, Duke");

  }

}