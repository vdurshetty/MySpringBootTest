package com.venu;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.venu.model.User;
import com.venu.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
public class UserControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private RestApiController restApiController;
	
	@Before
	public void init() {
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(restApiController)
				  .addFilters(new CorsFilter())
				  .build();
		
	}
	
	@Test
	public void testGetAllUsers() throws Exception {
		
		List<User> users = Arrays.asList( new User(11, "Ganesh", 24,34.45f),
					new User(22,"venuTest",45,33.45f));
		
		//When
		Mockito.when(userService.findAllUsers()).thenReturn(users);
		
		mockMvc.perform(get("http://localhost:8080/api/user/"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$",hasSize(2)))
				.andExpect(jsonPath("$[0].id",is(11)))
				.andExpect(jsonPath("$[0].name",is("Ganesh")));
				
		verify(userService,times(1)).findAllUsers();
		verifyNoMoreInteractions(userService);
				
		
	}
	
	  @Test
	    public void test_get_by_id_success() throws Exception {
	        User user = new User(1, "Daenerys Targaryen",34,234.3f);
	        when(userService.findById(1)).thenReturn(user);
	        mockMvc.perform(get("http://localhost:8080/api/user/{id}", 1))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(jsonPath("$.id", is(1)))
	                .andExpect(jsonPath("$.name", is("Daenerys Targaryen")));

	        verify(userService, times(1)).findById(1);
	        verifyNoMoreInteractions(userService);
	    }

}
