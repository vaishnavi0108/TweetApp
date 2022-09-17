package com.tweetapp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dto.Reply;
import com.tweetapp.dto.TweetsDto;
import com.tweetapp.entities.TweetsEntity;
import com.tweetapp.repo.TweetsRepo;
import com.tweetapp.request.TweetRequest;
import com.tweetapp.response.TweetResponse;
import com.tweetapp.service.TweetsService;

@Service
public class TweetsServiceImpl implements TweetsService {

	@Autowired
	TweetsRepo tweetsRepo;
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
	SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");

	@Override
	public TweetResponse getAllTweets() {
		// TODO Auto-generated method stub
		TweetResponse response = new TweetResponse();
		List<TweetsEntity> tweets = tweetsRepo.findAll();
		List<TweetsDto> tweetsDto = new ArrayList<>();
		tweets.forEach(entity -> {
			TweetsDto tweet = new TweetsDto();
			tweet.setTweet(entity.getTweet());
			tweet.setTweetId(entity.getTweetId());
			tweet.setUserTweetId(entity.getUserTweetId());
			tweet.setLike(entity.getLike());
			tweet.setReply(entity.getReply());
			tweet.setDateOfPost(simpleDateFormat.format(entity.getDateOfPost()));
			tweet.setTimeOfPost(localDateFormat.format(entity.getDateOfPost()));
			tweetsDto.add(tweet);
		});
		response.setTweetsDto(tweetsDto);
		return response;
	}

	@Override
	public TweetResponse getAllTweetsByUserName(String userName) {
		// TODO Auto-generated method stub
		TweetResponse response = new TweetResponse();
		List<TweetsDto> tweetsDto = new ArrayList<>();
		try {
			List<TweetsEntity> tweets = tweetsRepo.findByUserName(userName);
			tweets.forEach(entities->{
				TweetsDto dto = new TweetsDto();
				dto.setTweet(entities.getTweet());
				dto.setLike(entities.getLike());
				dto.setReply(entities.getReply());
				dto.setTweetId(entities.getTweetId());
				dto.setUserTweetId(entities.getUserTweetId());
				dto.setDateOfPost(simpleDateFormat.format(entities.getDateOfPost()));
				dto.setTimeOfPost(localDateFormat.format(entities.getDateOfPost()));
				tweetsDto.add(dto);
			});
			response.setStatusMessage("SUCCESS");
			response.setTweetsDto(tweetsDto);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
		return response;
	}

	@Override
	public TweetResponse addTweet(TweetRequest request, String userName) {
		// TODO Auto-generated method stub
		TweetResponse response = new TweetResponse();
		TweetsDto tweet = request.getTweet();
		TweetsEntity entity = new TweetsEntity();
		List<Reply> reply = new ArrayList<>();
		TweetsEntity maxEntity = tweetsRepo.findTopByOrderByTweetIdDesc();
		if(maxEntity == null)
		{
			entity.setTweetId(1l);
		}
		else
		{
			entity.setTweetId(maxEntity.getTweetId()+1);
		}
		entity.setTweet(tweet.getTweet());
		
		entity.setUserTweetId(userName);
		entity.setLike(0l);
		entity.setReply(reply);
		entity.setDateOfPost(new Date());
		try {
			tweetsRepo.save(entity);
			response.setStatusMessage("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatusMessage("FAILURE");
		}
		return response;
	}

	@Override
	public TweetResponse deleteTweet(String userName, Long tweetId) {
		// TODO Auto-generated method stub
		TweetResponse response = new TweetResponse();
		try {
			tweetsRepo.deleteByTweetId(tweetId);
			response.setStatusMessage("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatusMessage("FAILED");
		}
		return response;
	}

	@Override
	public TweetResponse replyToTweet(TweetRequest request) {
		// TODO Auto-generated method stub
		TweetResponse response = new TweetResponse();
		TweetsDto dto = request.getTweet();
		List<Reply> replies = new ArrayList<>();
		try {
			TweetsEntity entity = tweetsRepo.findByTweetId(dto.getTweetId());
			replies.addAll(entity.getReply());
			List<Reply> newReplies = dto.getReply();
			newReplies.forEach(reply->{
				reply.setDateReplied(new Date());
			});
			replies.addAll(newReplies);
			entity.setReply(replies);
			tweetsRepo.save(entity);
			response.setStatusMessage("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatusMessage("FAILED");
		}
		return response;
	}

	@Override
	public TweetResponse likeATweet(TweetRequest request) {
		// TODO Auto-generated method stub
		TweetResponse response = new TweetResponse();
		TweetsDto dto = request.getTweet();
		try {
			TweetsEntity entity = tweetsRepo.findByTweetId(dto.getTweetId());
			entity.setLike(entity.getLike()+1);
			tweetsRepo.save(entity);
			response.setStatusMessage("SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatusMessage("FAILED");
		}
		return response;
	}

	@Override
	public TweetResponse updateTweet(TweetRequest request) {
		// TODO Auto-generated method stub
		TweetResponse response = new TweetResponse();
		TweetsDto dto = request.getTweet();
		try {
			TweetsEntity entity = tweetsRepo.findByTweetId(dto.getTweetId());
			entity.setTweet(dto.getTweet());
			tweetsRepo.save(entity);
			response.setStatusMessage("SUCCESS");	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatusMessage("FAILED");
		}
		return response;
	}

}
