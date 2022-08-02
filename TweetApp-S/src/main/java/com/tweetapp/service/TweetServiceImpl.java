package com.tweetapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.entity.Tweet;
import com.tweetapp.entity.User;
import com.tweetapp.exception.NoSuchUserException;
import com.tweetapp.exception.TweetNotFoundException;
import com.tweetapp.repository.TweetRepository;
import com.tweetapp.repository.UserRepository;

@Service
public class TweetServiceImpl implements TweetService {
	
	private static final Logger logger = LoggerFactory.getLogger(TweetServiceImpl.class);
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	TweetRepository tweetRepo;

	@Override
	public Tweet postTweetByUser(String username,Tweet tweet) throws NoSuchUserException {
		logger.debug("-----Start TweetServiceImpl -> postTweetByUser(username,tweet)-----");
		Optional<User> user = userRepo.findById(username);
		if(user.isEmpty()) {
			logger.error("No such user exists");
			throw new NoSuchUserException("No such user exists");
		}
		tweet.setPostedDate(LocalDateTime.now());
		tweet.setUser(user.get());
		tweet.setLikedBy(new HashSet<>());
		tweet.setReplies(new ArrayList<>());
		Tweet savedTweet = tweetRepo.save(tweet);
		logger.info("Posted Tweet : " + savedTweet);
		logger.debug("-----End TweetServiceImpl -> postTweetByUser(username,tweet)-----");
		return savedTweet;
	}

	@Override
	public List<Tweet> getAllTweets() {
		logger.debug("-----TweetServiceImpl -> getAllTweets()-----");
		return tweetRepo.findAll();
	}
	
	@Override
	public List<Tweet> getAllTweetsByUser(String username) throws NoSuchUserException {
		logger.debug("-----Start TweetServiceImpl -> getAllTweetsByUser(username)-----");
		Optional<User> user = userRepo.findById(username);
		if(user.isEmpty()) {
			logger.error("No such user exists");
			throw new NoSuchUserException("No such user exists");
		}
		logger.debug("-----End TweetServiceImpl -> getAllTweetsByUser(username)-----");
		return tweetRepo.findByUserUsername(username);
	}
	
	@Override
	public Tweet updateTweet(String username,Tweet tweet) throws NoSuchUserException {
		logger.debug("-----Start TweetServiceImpl -> updateTweet(username,tweet)-----");
		Optional<User> user = userRepo.findById(username);
		if(user.isEmpty()) {
			logger.error("No such user exists");
			throw new NoSuchUserException("No such user exists");
		}
		tweet.setPostedDate(LocalDateTime.now());
		Tweet updatedTweet = tweetRepo.save(tweet);
		logger.info("Updated Tweet : " + updatedTweet);
		logger.debug("-----End TweetServiceImpl -> updateTweet(username,tweet)-----");
		return updatedTweet;
	}

	@Override
	public Tweet updateTweetByUser(Tweet tweet,String tweetId) throws TweetNotFoundException {
		logger.debug("-----Start TweetServiceImpl -> updateTweetByUser(tweet)-----");
		Optional<Tweet> oldTweet = tweetRepo.findById(tweetId);
		if(oldTweet.isEmpty()) {
			throw new TweetNotFoundException("Tweet not found");
		}
		tweet.setId(tweetId);
		tweet.setPostedDate(LocalDateTime.now());
		tweet.setTweetTag(tweet.getTweetTag()!=null?tweet.getTweetTag():oldTweet.get().getTweetTag());
		tweet.setUser(oldTweet.get().getUser());
		tweet.setLikes(oldTweet.get().getLikes());
		tweet.setLikedBy(oldTweet.get().getLikedBy());
		tweet.setReplies(oldTweet.get().getReplies());
		logger.debug("-----End TweetServiceImpl -> updateTweetByUser(tweet)-----");
		return tweetRepo.save(tweet);
	}

	@Override
	public String deleteTweetById(String tweetId) throws TweetNotFoundException {
		logger.debug("-----Start TweetServiceImpl -> deleteTweetById(tweetId)-----");
		Optional<Tweet> tweet = tweetRepo.findById(tweetId);
		if(tweet.isEmpty()) {
			logger.error("Tweet not found");
			throw new TweetNotFoundException("Tweet not found");
		}
		tweetRepo.deleteById(tweetId);
		logger.debug("-----End TweetServiceImpl -> deleteTweetById(tweetId)-----");
		return "Tweet Deleted Successfully";
	}

	@Override
	public String likeTweetById(String tweetId,String username) throws TweetNotFoundException, NoSuchUserException {
		logger.debug("-----Start TweetServiceImpl -> likeTweetById(tweetId,username)-----");
		Optional<Tweet> tweet = tweetRepo.findById(tweetId);
		if(tweet.isEmpty()) {
			logger.error("Tweet not found");
			throw new TweetNotFoundException("Tweet not found");
		}
		Optional<User> user = userRepo.findById(username);
		if(user.isEmpty()) {
			logger.error("No such user exists");
			throw new NoSuchUserException("No such user exists");
		}
		Set<String> usersLiked = tweet.get().getLikedBy();
		usersLiked.add(username);
		tweet.get().setLikes(usersLiked.size());
		logger.info("Tweet likes : " + tweet.get().getLikes());
		tweetRepo.save(tweet.get());
		logger.debug("-----End TweetServiceImpl -> likeTweetById(tweetId,username)-----");
		return "Tweet liked";
	}

	@Override
	public Tweet replyTweetById(String parentTweetId, Tweet tweet,String username) throws TweetNotFoundException, NoSuchUserException {
		logger.debug("-----Start TweetServiceImpl -> replyTweetById(parentTweetId,tweet,username)-----");
		Optional<Tweet> parentTweet = tweetRepo.findById(parentTweetId);
		if(parentTweet.isEmpty()) {
			logger.error("Tweet not found");
			throw new TweetNotFoundException("Tweet Not Found");
		}
		List<Tweet> replies = parentTweet.get().getReplies();
		if(replies.size()==0) tweet.setId("1");
		else {
			int id = Integer.parseInt(replies.get(replies.size()-1).getId());
			tweet.setId(String.valueOf(++id));
		}
		tweet.setPostedDate(LocalDateTime.now());
		tweet.setLikedBy(new HashSet<>());
		Optional<User> user = userRepo.findById(username);
		if(user.isEmpty()) {
			logger.error("No such user exists");
			throw new NoSuchUserException("No such user exists");
		}
		tweet.setUser(user.get());
		replies.add(tweet);
		parentTweet.get().setReplies(replies);
		logger.info("Tweet replies : " + parentTweet.get().getReplies());
		logger.debug("-----End TweetServiceImpl -> replyTweetById(parentTweetId,tweet,username)-----");
		return tweetRepo.save(parentTweet.get());
	}

}
