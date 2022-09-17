package com.tweetapp.repository;

import java.util.List;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.TweetsEntity;
@Repository
public interface TweetsRepo extends MongoRepository<TweetsEntity, ObjectId> {



	@Query("{'userTweetId' : ?0}")
	List<TweetsEntity> findByUserName(String userName);

	@DeleteQuery(value="{'tweetId' : ?0}")
	void deleteByTweetId(Long tweetId);



	@Query("{'tweetId' : ?0}")
	TweetsEntity findByTweetId(Long tweetId);
	
	TweetsEntity findTopByOrderByTweetIdDesc();

}
