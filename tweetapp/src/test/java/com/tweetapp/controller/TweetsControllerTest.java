package com.tweetapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tweetapp.config.TweetConfigTest;
import com.tweetapp.model.Reply;
import com.tweetapp.model.TweetsDto;
import com.tweetapp.model.TweetRequest;
import com.tweetapp.model.TweetResponse;
import com.tweetapp.service.TweetsService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TweetController.class)
@ContextConfiguration(classes = TweetConfigTest.class)
public class TweetsControllerTest {

	@SpyBean
	TweetController tweetController;

	@MockBean
	TweetsService tweetsService;


	private final Logger logger = LoggerFactory.getLogger(TweetController.class);

	@Test
	public void getAllTweetsTest() {
		TweetResponse tweetResponse = new TweetResponse();
		tweetResponse.setStatusMessage("SUCCESS");
		Mockito.when(tweetsService.getAllTweets()).thenReturn(tweetResponse);
		TweetResponse actualResponse = tweetController.getAllTweets();
		assertEquals(tweetResponse.getStatusMessage(), actualResponse.getStatusMessage());
	}

	@Test
	public void getAllTweetsUser() {
		TweetResponse tweetResponse = new TweetResponse();
		tweetResponse.setStatusMessage("SUCCESS");
		Mockito.when(tweetsService.getAllTweetsByUserName("finny")).thenReturn(tweetResponse);
		TweetResponse actualResponse = tweetController.getAllTweetsUser("finny");
		assertEquals(tweetResponse.getStatusMessage(), actualResponse.getStatusMessage());
	}

	@Test
	public void addTweet() {
		TweetResponse tweetResponse = new TweetResponse();
		TweetRequest request = new TweetRequest();
		TweetsDto tweet = new TweetsDto();
		tweet.setTweet("Hello");
		request.setTweet(tweet);
		tweetResponse.setStatusMessage("SUCCESS");
		Mockito.when(tweetsService.addTweet(request, "finny")).thenReturn(tweetResponse);
		TweetResponse actualResponse = tweetController.addTweet(request, "finny");
		assertEquals(tweetResponse.getStatusMessage(), actualResponse.getStatusMessage());
	}

	@Test
	public void deleteTweet() {
		TweetResponse tweetResponse = new TweetResponse();
		tweetResponse.setStatusMessage("SUCCESS");
		Mockito.when(tweetsService.deleteTweet("finny", 1l)).thenReturn(tweetResponse);
		TweetResponse actualResponse = tweetController.deleteTweet("finny", 1l);
		assertEquals(tweetResponse.getStatusMessage(), actualResponse.getStatusMessage());
	}

	@Test
	public void replyToTweet() {
		TweetResponse tweetResponse = new TweetResponse();
		TweetRequest request = new TweetRequest();
		TweetsDto tweet = new TweetsDto();
		List<Reply> replies = new ArrayList<>();
		Reply reply = new Reply();
		reply.setUserId("finny");
		reply.setReplied("Hi");
		reply.setDateReplied(new Date());
		replies.add(reply);
		tweet.setTweet("Hello");
		tweet.setReply(replies);
		request.setTweet(tweet);
		tweetResponse.setStatusMessage("SUCCESS");
		Mockito.when(tweetsService.replyToTweet(request)).thenReturn(tweetResponse);
		TweetResponse actualResponse = tweetController.replyToTweet(request);
		assertEquals(tweetResponse.getStatusMessage(), actualResponse.getStatusMessage());
	}

	@Test
	public void likeATweet() {
		TweetResponse tweetResponse = new TweetResponse();
		TweetRequest request = new TweetRequest();
		tweetResponse.setStatusMessage("SUCCESS");
		Mockito.when(tweetsService.likeATweet(request)).thenReturn(tweetResponse);
		TweetResponse actualResponse = tweetController.likeATweet(request);
		assertEquals(tweetResponse.getStatusMessage(), actualResponse.getStatusMessage());
	}

	@Test
	public void updateTweet() {
		TweetResponse tweetResponse = new TweetResponse();
		TweetRequest request = new TweetRequest();
		TweetsDto tweet = new TweetsDto();
		tweet.setTweet("Hello");
		request.setTweet(tweet);
		tweetResponse.setStatusMessage("SUCCESS");
		Mockito.when(tweetsService.updateTweet(request)).thenReturn(tweetResponse);
		TweetResponse actualResponse = tweetController.updateTweet(request);
		assertEquals(tweetResponse.getStatusMessage(), actualResponse.getStatusMessage());
	}

}





//package com.tweetapp.controller;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.mock.mockito.SpyBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.tweetapp.config.TweetConfigTest;
////import com.tweetapp.kafka.Producer;
//import com.tweetapp.model.TweetRequest;
//import com.tweetapp.model.TweetResponse;
//import com.tweetapp.service.TweetsService;
//
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(value = TweetController.class)
//@ContextConfiguration(classes = TweetConfigTest.class)
//public class TweetsControllerTest {
//
//	@SpyBean
//	TweetController tweetController;
//
//	@MockBean
//	TweetsService tweetsService;
//	
//	//@MockBean
//	//Producer producer;
//	
//	
//
//	@Test
//	public void getAllTweetsTest() {
//		TweetResponse tweetResponse = new TweetResponse();
//		tweetResponse.setStatusMessage("SUCCESS");
//		Mockito.when(tweetsService.getAllTweets()).thenReturn(tweetResponse);
//		TweetResponse actualResponse = tweetController.getAllTweets();
//		assertEquals(tweetResponse.getStatusMessage(),actualResponse.getStatusMessage());
//	}
//	
//	@Test
//	public void getAllTweetsUser() {
//		TweetResponse tweetResponse = new TweetResponse();
//		tweetResponse.setStatusMessage("SUCCESS");
//		Mockito.when(tweetsService.getAllTweetsByUserName("Vai")).thenReturn(tweetResponse);
//		TweetResponse actualResponse = tweetController.getAllTweetsUser("Vai");
//		assertEquals(tweetResponse.getStatusMessage(),actualResponse.getStatusMessage());
//	}
//	
//	@Test
//	public void addTweet() {
//		TweetResponse tweetResponse = new TweetResponse();
//		TweetResponse actualResponse = new TweetResponse();
//		TweetRequest request = new TweetRequest();
//		tweetResponse.setStatusMessage("SUCCESS");
//		actualResponse.setStatusMessage("SUCCESS");
//		Mockito.when(tweetsService.addTweet(request,"Vai")).thenReturn(tweetResponse);
//		
//	//	Mockito.doNothing().when(producer).sendMessage(Mockito.anyString());
////		tweetController.producer = producer;
//		
////		TweetResponse actualResponse = tweetController.addTweet(request,"Vai");
//		assertEquals(tweetResponse.getStatusMessage(),actualResponse.getStatusMessage());
//	}
//	
//	@Test
//	public void deleteTweet() {
//		TweetResponse tweetResponse = new TweetResponse();
//		tweetResponse.setStatusMessage("SUCCESS");
//		Mockito.when(tweetsService.deleteTweet("Vai",1l)).thenReturn(tweetResponse);
//		TweetResponse actualResponse = tweetController.deleteTweet("Vai",1l);
//		assertEquals(tweetResponse.getStatusMessage(),actualResponse.getStatusMessage());
//	}
//	
//	@Test
//	public void replyToTweet() {
//		TweetResponse tweetResponse = new TweetResponse();
//		TweetResponse actualResponse = new TweetResponse();
//		TweetRequest request = new TweetRequest();
//		tweetResponse.setStatusMessage("SUCCESS");
//		actualResponse.setStatusMessage("SUCCESS");
//		Mockito.when(tweetsService.replyToTweet(request)).thenReturn(tweetResponse);
//		//Mockito.doNothing().when(producer).sendMessage(Mockito.anyString());
////		TweetResponse actualResponse = tweetController.replyToTweet(request);
//		assertEquals(tweetResponse.getStatusMessage(),actualResponse.getStatusMessage());
//	}
//	
//	@Test
//	public void likeATweet() {
//		TweetResponse tweetResponse = new TweetResponse();
//		TweetRequest request = new TweetRequest();
//		tweetResponse.setStatusMessage("SUCCESS");
//		Mockito.when(tweetsService.likeATweet(request)).thenReturn(tweetResponse);
//		TweetResponse actualResponse = tweetController.likeATweet(request);
//		assertEquals(tweetResponse.getStatusMessage(),actualResponse.getStatusMessage());
//	}
//	
//	@Test
//	public void updateTweet() {
//		TweetResponse tweetResponse = new TweetResponse();
//		TweetResponse actualResponse = new TweetResponse();
//		TweetRequest request = new TweetRequest();
//		tweetResponse.setStatusMessage("SUCCESS");
//		actualResponse.setStatusMessage("SUCCESS");
//		Mockito.when(tweetsService.updateTweet(request)).thenReturn(tweetResponse);
//	//	Mockito.doNothing().when(producer).sendMessage(Mockito.anyString());
////		TweetResponse actualResponse = tweetController.updateTweet(request);
//		assertEquals(tweetResponse.getStatusMessage(),actualResponse.getStatusMessage());
//	}
//
//}
