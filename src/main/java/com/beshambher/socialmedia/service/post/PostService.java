package com.beshambher.socialmedia.service.post;

import org.springframework.data.domain.Page;

import com.beshambher.socialmedia.dto.response.PostResponse;
import com.beshambher.socialmedia.entity.post.Post;
import com.beshambher.socialmedia.service.CrudService;

public interface PostService extends CrudService<Post> {

	public Page<PostResponse> getPosts(String orderBy, String sortBy, Integer page, Integer pageSize);

	public Page<PostResponse> getPostsWithFriends(String orderBy, String sortBy, Integer page, Integer pageSize);

	public Post toggleLike(String id) throws Exception;

}
