package com.tweetapp.model;

import java.io.Serializable;

import com.tweetapp.model.UsersDto;

import lombok.Getter;
import lombok.Setter;

public class UserRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8300957195016445421L;
	
	UsersDto userDto;

	public UsersDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UsersDto userDto) {
		this.userDto = userDto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
