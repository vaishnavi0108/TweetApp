package com.tweetapp.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Reply {

	private String userId;

	private String replied;

	private Date dateReplied;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReplied() {
		return replied;
	}

	public void setReplied(String replied) {
		this.replied = replied;
	}

	public Date getDateReplied() {
		return dateReplied;
	}

	public void setDateReplied(Date dateReplied) {
		this.dateReplied = dateReplied;
	}
	
	
}
