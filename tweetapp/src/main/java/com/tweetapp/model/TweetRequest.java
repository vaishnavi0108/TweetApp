package com.tweetapp.model;

import com.tweetapp.model.TweetsDto;

import lombok.Getter;
import lombok.Setter;

public class TweetRequest {

	private TweetsDto tweet;

	public TweetsDto getTweet() {
		return tweet;
	}

	public void setTweet(TweetsDto tweet) {
		this.tweet = tweet;
	}
	
	
}
