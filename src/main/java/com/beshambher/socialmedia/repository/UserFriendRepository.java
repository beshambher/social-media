package com.beshambher.socialmedia.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beshambher.socialmedia.entity.user.UserFriend;
import com.beshambher.socialmedia.entity.user.UserFriendId;

@Repository
public interface UserFriendRepository extends CrudRepository<UserFriend, UserFriendId> {

	@Query("SELECT uf FROM UserFriend uf WHERE uf.id.user=?1 OR uf.id.friend=?1")
	UserFriend findByUsername(String username);

	@Query("SELECT uf FROM UserFriend uf WHERE (uf.id.user=?1 AND uf.id.friend=?2) OR (uf.id.user=?2 AND uf.id.friend=?1)")
	UserFriend findByUserAndFriend(String user, String friend);

}
