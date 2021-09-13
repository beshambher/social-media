package com.beshambher.socialmedia.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beshambher.socialmedia.entity.post.PostLike;
import com.beshambher.socialmedia.entity.post.PostUserId;

@Repository
public interface PostLikeRepository extends CrudRepository<PostLike, PostUserId> {

	@Query("SELECT pl FROM PostLike pl WHERE pl.id.user=?1")
	PostLike findByUsername(String username);

	@Query("SELECT pl FROM PostLike pl WHERE pl.id.user=?1 AND pl.id.post=?2")
	PostLike findByUserAndPost(String user, String post);

}
