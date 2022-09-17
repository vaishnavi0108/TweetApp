package com.tweetapp.repository;

import java.util.List;



import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tweetapp.model.UsersEntity;

@Repository
public interface UsersRepo extends MongoRepository<UsersEntity, ObjectId> {

	@Query("{'loginId':?0}")
	UsersEntity findByLoginId(String loginId);

	@Query("{'loginId':{'$regex':?0,$options:'i'}}")
	List<UsersEntity> searchByIds(String username);

	@Query("{'loggedIn':true}")
	List<UsersEntity> findAllLoggedIn();

}
