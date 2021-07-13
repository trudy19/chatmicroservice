package com.example.testwebsocket.repositories;

import com.example.testwebsocket.models.FriendLink;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendLinkRepository extends MongoRepository<FriendLink, String> {

    List<FriendLink> findAllByUserId0OrUserId1(String UserId0, String UserId1);

}
