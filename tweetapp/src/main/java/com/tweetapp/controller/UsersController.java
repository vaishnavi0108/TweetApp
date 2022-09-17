package com.tweetapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.model.UserRequest;
import com.tweetapp.model.UserResponse;
import com.tweetapp.service.UsersService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1.0/tweets")
public class UsersController {
	

	@Autowired
	UsersService usersService;
	
	private final Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	
	@RequestMapping(path = "/users/all" , method = RequestMethod.GET)
	public UserResponse getAllUsers() {
		UserResponse response = usersService.getAllUsers();
		logger.info("Users Controller"+"in getAllUsers() call" + response.getStatusMessage());
		return response;
	}

	@RequestMapping(path = "/register" , method = RequestMethod.POST)
	public UserResponse register(@RequestBody UserRequest request) {
		UserResponse response = usersService.register(request);
		logger.info("Users Controller"+"in register() call" + response.getStatusMessage());
		return response;
	}

	@RequestMapping(path = "/forgetPassword" , method = RequestMethod.POST)
	public UserResponse forgetPassword(@RequestBody UserRequest request) {
		UserResponse response = usersService.forgetPassword(request);
		logger.info("Users Controller"+"in forgetPassword() call" + response.getStatusMessage());
		return response;
	}

	@RequestMapping(path = "/user/search/{username}" , method = RequestMethod.GET)
	public UserResponse searchUsers(@PathVariable(name = "username") String username) {
		UserResponse response = usersService.searchUsers(username);
		logger.info("Users Controller"+"in searchUsers() call" + response.getStatusMessage());
		return response;
	}

	
	
}
