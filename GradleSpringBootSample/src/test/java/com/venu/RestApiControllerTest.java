package com.venu;

	import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.venu.model.User;
	  
	 
	public class RestApiControllerTest {
	  
	    public final String REST_SERVICE_URI = "http://localhost:8080/api";
	      
	    /* GET */
	    @SuppressWarnings("unchecked")
	    public void listAllUsers(){
	        System.out.println("Testing listAllUsers API-----------");
	          
	        RestTemplate restTemplate = new RestTemplate();
	        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/user/", List.class);
	          
	        if(usersMap!=null){
	            for(LinkedHashMap<String, Object> map : usersMap){
	                System.out.println("User : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Salary="+map.get("salary"));;
	            }
	        }else{
	            System.out.println("No user exist----------");
	        }
	    }
	      
	    /* GET */
	    @Test
	    public void getUser(){
	       // RestTemplate restTemplate = new RestTemplate();
	        /*
	        User tUser = new User();
	        tUser.setId(2);
	        tUser.setName("Tom");
	        tUser.setAge(40);
	        tUser.setSalary(50000);
	        User user = restTemplate.getForObject(REST_SERVICE_URI+"/user/2", User.class);
	        assertThat(user).isEqualTo(tUser);
	        */
	    }
	      
	    /* POST */
	    public  void createUser() {
	        System.out.println("Testing create User API----------");
	        RestTemplate restTemplate = new RestTemplate();
	        User user = new User(0,"Sarah123",51,134);
	        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/user/", user, User.class);
	        //System.out.println("Location : "+uri.toASCIIString());
	        assertThat(uri.toString().substring(0, uri.toString().lastIndexOf("/"))).isEqualTo("http://localhost:8080/api/user");
	    }
	  
	    /* PUT */
	    @Test
	    public void updateUser() {
	        System.out.println("Testing update User API----------");
	       /* RestTemplate restTemplate = new RestTemplate();
	        User user  = new User(1,"Tomy",33, 70000);
	        restTemplate.put(REST_SERVICE_URI+"/user/1", user);
	        User uUser = restTemplate.getForObject(REST_SERVICE_URI+"/user/1", User.class);
	        assertThat(user).isEqualTo(uUser);
	        */
	    }
	  
	    /* DELETE */
	    public void deleteUser() {
	        System.out.println("Testing delete User API----------");
	        RestTemplate restTemplate = new RestTemplate();
	        restTemplate.delete(REST_SERVICE_URI+"/user/3");
	    }
	  
	  
	    /* DELETE */
	    public  void deleteAllUsers() {
	        System.out.println("Testing all delete Users API----------");
	        RestTemplate restTemplate = new RestTemplate();
	        restTemplate.delete(REST_SERVICE_URI+"/user/");
	    }
	  
	    public void main(String args[]){
	    	
	    	RestApiControllerTest testing = new RestApiControllerTest();
	    	
	       // testing.createUser();
	    	testing.listAllUsers();
	    	testing.getUser();
	    	testing.createUser();
	    	testing.listAllUsers();
	    	testing.updateUser();
	    	testing.listAllUsers();
	    	testing.deleteUser();
	    	testing.listAllUsers();
	    	testing.deleteAllUsers();
	    	testing.listAllUsers();
	    }
	}