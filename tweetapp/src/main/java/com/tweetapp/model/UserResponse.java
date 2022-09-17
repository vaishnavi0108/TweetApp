package com.tweetapp.model;

import java.io.Serializable;
import java.util.List;

import com.tweetapp.model.UsersDto;

import lombok.Getter;
import lombok.Setter;


public class UserResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5432683200958075030L;

	private List<UsersDto> usersDto;

	private String statusMessage;

	public List<UsersDto> getUsersDto() {
		return usersDto;
	}

	public void setUsersDto(List<UsersDto> usersDto) {
		this.usersDto = usersDto;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
