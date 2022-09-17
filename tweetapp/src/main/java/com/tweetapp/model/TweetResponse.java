package com.tweetapp.model;

import java.io.Serializable;
import java.util.List;

import com.tweetapp.model.TweetsDto;

import lombok.Getter;
import lombok.Setter;

public class TweetResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3452495145437447139L;
	private List<TweetsDto> tweetsDto;
	private String statusMessage;
	public List<TweetsDto> getTweetsDto() {
		return tweetsDto;
	}
	public void setTweetsDto(List<TweetsDto> tweetsDto) {
		this.tweetsDto = tweetsDto;
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
