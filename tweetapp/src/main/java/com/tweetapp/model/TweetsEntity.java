package com.tweetapp.model;

import java.util.Date;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tweetapp.model.Reply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "tweets")
@AllArgsConstructor
@NoArgsConstructor
public class TweetsEntity {
	@Id
	@JsonProperty("_id")
	private ObjectId _id;
	
	@JsonProperty("tweet")
	private String tweet;
	
	@JsonProperty("userTweetId")
	private String userTweetId;
	

	@JsonProperty("tweetId")
	private Long tweetId;
	
	@JsonProperty("like")
	private Long like;
	
	@JsonProperty("reply")
	private List<Reply> reply;
	
	@JsonProperty("dateOfPost")
	private Date dateOfPost;

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

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

	public Date getDateOfPost() {
		return dateOfPost;
	}

	public void setDateOfPost(Date dateOfPost) {
		this.dateOfPost = dateOfPost;
	}
	
	

}
