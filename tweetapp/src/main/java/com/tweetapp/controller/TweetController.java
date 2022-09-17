package com.tweetapp.controller;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import com.tweetapp.kafka.Producer;
import com.tweetapp.model.TweetRequest;
import com.tweetapp.model.TweetResponse;
import com.tweetapp.service.TweetsService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1.0/tweets")

public class TweetController {

	@Autowired
	TweetsService tweetsService;
	
//	public Producer producer;
//
//    @Autowired
//    TweetController(Producer producer) {
//        this.producer = producer;
//    }

		
	private final Logger logger = LoggerFactory.getLogger(TweetController.class);


	@GetMapping(value = "/all")
	public TweetResponse getAllTweets() {
		TweetResponse response = tweetsService.getAllTweets();
		logger.info("Tweet Controller"+"in get All Tweets() call" + response.getStatusMessage());
		return response;
	}

	@GetMapping(value = "/{username}")
	public TweetResponse getAllTweetsUser(@PathVariable("username") String userName) {
		TweetResponse response = tweetsService.getAllTweetsByUserName(userName);
		logger.info("Tweet Controller"+"in get All Tweets() call" + response.getStatusMessage());
		return response;

	}

	@PostMapping(value = "/{username}/add")
	public TweetResponse addTweet(@RequestBody TweetRequest request, @PathVariable("username") String userName) {
		TweetResponse response = tweetsService.addTweet(request, userName);
		logger.info("Tweet Controller"+"in addTweet() call" + response.getStatusMessage());
		return response;
	}

	@RequestMapping(path = "/{username}/delete/{id}", method = RequestMethod.DELETE)
	public TweetResponse deleteTweet(@PathVariable("username") String userName, @PathVariable("id") Long tweetId) {
		TweetResponse response = tweetsService.deleteTweet(userName, tweetId);
		logger.info("Tweet Controller"+"in deleteTweet() call" + response.getStatusMessage());
		return response;

	}

	@PostMapping(value = "/reply")
	public TweetResponse replyToTweet(@RequestBody TweetRequest request) {
		TweetResponse response = tweetsService.replyToTweet(request);
		logger.info("Tweet Controller"+"in replyToTweet() call" + response.getStatusMessage());
		return response;
	}

	@RequestMapping(value = "/like", method = RequestMethod.POST)
	public TweetResponse likeATweet(@RequestBody TweetRequest request) {
		TweetResponse response = tweetsService.likeATweet(request);
		logger.info("Tweet Controller"+"in likeATweet() call" + response.getStatusMessage());
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public TweetResponse updateTweet(@RequestBody TweetRequest request) {
		TweetResponse response = tweetsService.updateTweet(request);;
		logger.info("Tweet Controller"+"in updateTweet() call" + response.getStatusMessage());
		return response;
	}

}
