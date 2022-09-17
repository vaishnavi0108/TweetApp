package com.tweetapp.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
public class TweetsDto {
	private String tweet;

	private String userTweetId;

	private Long tweetId;
	
	private Long like;
	
	private List<Reply> reply;
	
	private String dateOfPost;
	
	private String timeOfPost;

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public String getUserTweetId() {
		return userTweetId;
	}

	public void setUserTweetId(String userTweetId) {
		this.userTweetId = userTweetId;
	}

	public Long getTweetId() {
		return tweetId;
	}

	public void setTweetId(Long tweetId) {
		this.tweetId = tweetId;
	}

	public Long getLike() {
		return like;
	}

	public void setLike(Long like) {
		this.like = like;
	}

	public List<Reply> getReply() {
		return reply;
	}

	public void setReply(List<Reply> reply) {
		this.reply = reply;
	}

	public String getDateOfPost() {
		return dateOfPost;
	}

	public void setDateOfPost(String dateOfPost) {
		this.dateOfPost = dateOfPost;
	}

	public String getTimeOfPost() {
		return timeOfPost;
	}

	public void setTimeOfPost(String timeOfPost) {
		this.timeOfPost = timeOfPost;
	}
	
	
	
	
}
