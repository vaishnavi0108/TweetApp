package com.tweetapp.service;

import com.tweetapp.model.UsersDto;
import com.tweetapp.model.UserRequest;
import com.tweetapp.model.UserResponse;

public interface UsersService {

	UserResponse getAllUsers();

	UserResponse register(UserRequest request);

	UserResponse forgetPassword(UserRequest request);

	UserResponse searchUsers(String username);

}
