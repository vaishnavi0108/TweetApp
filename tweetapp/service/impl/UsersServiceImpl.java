package com.tweetapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tweetapp.dto.UsersDto;
import com.tweetapp.entities.UsersEntity;
import com.tweetapp.repo.UsersRepo;
import com.tweetapp.request.UserRequest;
import com.tweetapp.response.UserResponse;
import com.tweetapp.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersRepo usersRepo;

	@Override
	public UserResponse getAllUsers() {
		// TODO Auto-generated method stub
		UserResponse response = new UserResponse();
		try {
			List<UsersEntity> users = usersRepo.findAll();
			List<UsersDto> usersDto = new ArrayList<>();
			users.forEach(entity -> {
				UsersDto dto = new UsersDto();
				dto.setContactNumber(entity.getContactNumber());
				dto.setEmailId(entity.getEmailId());
				dto.setFirstName(entity.getFirstName());
				dto.setLastName(entity.getLastName());
				dto.setLoggedIn(entity.getLoggedIn());
				dto.setLoginId(entity.getLoginId());
				dto.setPassword(entity.getPassword());
				usersDto.add(dto);
			});
			response.setUsersDto(usersDto);
			response.setStatusMessage("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatusMessage("FAILURE");
		}

		return response;
	}

	@Override
	public UserResponse register(UserRequest request) {
		// TODO Auto-generated method stub
		UserResponse response = new UserResponse();
		UsersEntity entity = new UsersEntity();
		UsersDto userDto = request.getUserDto();
		try {
			UsersEntity user = usersRepo.findByLoginId(userDto.getLoginId());
			List<String> roles = new ArrayList<>();
			roles.add("user");
			if (user == null) {
				entity.setContactNumber(userDto.getContactNumber());
				entity.setEmailId(userDto.getEmailId());
				entity.setFirstName(userDto.getFirstName());
				entity.setLastName(userDto.getLastName());
				entity.setLoggedIn(false);
				entity.setLoginId(userDto.getLoginId());
				entity.setPassword(passwordEncoder().encode(userDto.getPassword()));
				entity.setRoles(roles);
				entity.setObjectId(new ObjectId());
				usersRepo.save(entity);
				response.setStatusMessage("Success");
			} else {
				response.setStatusMessage("User Already Exists");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatusMessage("FAILURE");
		}

		return response;
	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public UserResponse forgetPassword(UserRequest request) {
		// TODO Auto-generated method stub
		UserResponse response = new UserResponse();
		UsersDto userDto = request.getUserDto();
		try {
			UsersEntity user = usersRepo.findByLoginId(userDto.getLoginId());
			if (user != null) {
				user.setPassword(passwordEncoder().encode(userDto.getPassword()));
				usersRepo.save(user);
				response.setStatusMessage("SUCCESS");
			} else {
				response.setStatusMessage("USER NOT FOUND");
			}
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatusMessage("FAILED");
		}
		return response;
	}

	@Override
	public UserResponse searchUsers(String username) {
		// TODO Auto-generated method stub
		UserResponse response = new UserResponse();
		List<UsersDto> users = new ArrayList<UsersDto>();
		try {
			List<UsersEntity> entities = usersRepo.searchByIds(username);
			entities.forEach(e -> {
				UsersDto dto = new UsersDto();
				dto.setLoginId(e.getLoginId());
				dto.setFirstName(e.getFirstName());
				dto.setLastName(e.getLastName());
				users.add(dto);
			});
			response.setStatusMessage("SUCCESS");
			response.setUsersDto(users);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public UserResponse getAllLoggedInUsers() {
		// TODO Auto-generated method stub
		UserResponse response = new UserResponse();
		try {
			List<UsersEntity> entities = usersRepo.findAllLoggedIn();
			// entities.stream().map(UsersDto::new).Collecters.toList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
