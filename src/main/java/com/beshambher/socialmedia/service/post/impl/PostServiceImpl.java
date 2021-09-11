package com.beshambher.socialmedia.service.post.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.beshambher.socialmedia.constants.sorting.PostSorting;
import com.beshambher.socialmedia.domain.response.PostResponse;
import com.beshambher.socialmedia.entity.post.Post;
import com.beshambher.socialmedia.repository.PostRepository;
import com.beshambher.socialmedia.service.post.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public String defaultOrder() {
		return PostSorting.createdAt.toString();
	}

	@Override
	public String defaultSort() {
		return "desc";
	}

	@Override
	public Page<PostResponse> getPosts(String orderBy, String sortBy, Integer page, Integer pageSize) {
		return postRepository.findByUsername(getUsername(), getPage(orderBy, sortBy, page, pageSize))
				.map(p -> new PostResponse(p));
	}

	@Override
	public Page<PostResponse> getPostsWithFriends(String orderBy, String sortBy, Integer page, Integer pageSize) {
		if (!StringUtils.hasLength(orderBy)) {
			orderBy = PostSorting.created_at.toString();
		}
		return postRepository.findByUserWithFriends(getUsername(), getPage(orderBy, sortBy, page, pageSize))
				.map(p -> new PostResponse(p));
	}

	@Override
	public <P extends Post> P create(P post) {
		post.setLikes(0);
		post.setUser(getUser());
		post.setCommentsCount(0);
		return postRepository.save(post);
	}

	@Override
	public <P extends Post> P update(P post) {
		return postRepository.save(post);
	}

	@Override
	public void deleteById(String id) {
		postRepository.deleteById(id);
	}

}
