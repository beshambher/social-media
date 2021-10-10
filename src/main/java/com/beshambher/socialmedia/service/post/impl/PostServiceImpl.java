package com.beshambher.socialmedia.service.post.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.beshambher.socialmedia.constants.sorting.PostSorting;
import com.beshambher.socialmedia.domain.response.PostResponse;
import com.beshambher.socialmedia.entity.post.Post;
import com.beshambher.socialmedia.entity.post.PostLike;
import com.beshambher.socialmedia.repository.PostLikeRepository;
import com.beshambher.socialmedia.repository.PostRepository;
import com.beshambher.socialmedia.service.post.PostService;

import javassist.NotFoundException;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PostLikeRepository postLikeRepository;

	@Override
	public String defaultOrder() {
		return PostSorting.createdAt.toString();
	}

	@Override
	public String defaultSort() {
		return "desc";
	}

	@Override
	@Transactional
	public Page<PostResponse> getPosts(String orderBy, String sortBy, Integer page, Integer pageSize) {
		return postRepository.findByUsername(getUsername(), getPage(orderBy, sortBy, page, pageSize))
				.map(p -> new PostResponse(p));
	}

	@Override
	@Transactional
	public Page<PostResponse> getPostsWithFriends(String orderBy, String sortBy, Integer page, Integer pageSize) {
		if (!StringUtils.hasLength(orderBy)) {
			orderBy = PostSorting.created_at.toString();
		}
		return postRepository.findByUserWithFriends(getUsername(), getPage(orderBy, sortBy, page, pageSize))
				.map(p -> new PostResponse(p));
	}

	@Override
	@Transactional
	public Post create(Post post) {
		post.setLikes(0);
		post.setUser(getUser());
		post.setCommentsCount(0);
		return postRepository.save(post);
	}

	@Override
	@Transactional
	public Post update(Post post, String id) throws Exception {
		Post updatedPost = findById(id);
		updatedPost.setBody(post.getBody());
		return postRepository.save(updatedPost);
	}

	@Override
	@Transactional
	public void deleteById(String id) throws Exception {
		Post post = findById(id);
		postRepository.delete(post);
	}

	@Override
	@Transactional
	public Post toggleLike(String id) throws Exception {
		Post post = postRepository.findById(id).orElse(null);
		if (post == null) {
			throw new NotFoundException("Post not found with id: " + id);
		}
		PostLike postLike = postLikeRepository.findByUserAndPost(getUsername(), post.getId());
		if (postLike == null) {
			postLike = new PostLike(getUser(), post);
			postLike = postLikeRepository.save(postLike);
			post.setLikes(post.getLikes() + 1);
			post = postRepository.save(post);
		} else {
			postLikeRepository.delete(postLike);
			post.setLikes(post.getLikes() - 1);
			post = postRepository.save(post);
		}
		return post;
	}

	@Override
	public Post findById(String id) throws Exception {
		Post post = null;
		if (isAdmin()) {
			post = postRepository.findById(id).orElse(null);
		} else {
			post = postRepository.findByUsernameAndId(getUsername(), id);
		}
		if (post == null) {
			throw new NotFoundException("Post not found with id: " + id);
		}
		return post;
	}

}
