package com.beshambher.socialmedia.service.post.impl;

import com.beshambher.socialmedia.constants.sorting.PostSorting;
import com.beshambher.socialmedia.dto.response.CommentResponse;
import com.beshambher.socialmedia.entity.post.Comment;
import com.beshambher.socialmedia.entity.post.Post;
import com.beshambher.socialmedia.exception.NotFoundException;
import com.beshambher.socialmedia.repository.CommentRepository;
import com.beshambher.socialmedia.repository.PostRepository;
import com.beshambher.socialmedia.service.post.CommentService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

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
	public Page<CommentResponse> getCommentsByPost(String postId, String orderby, String sortby, Integer page,
			Integer size) {
		return commentRepository.findByPost(postId, getPage(orderby, sortby, page, size))
				.map(CommentResponse::new);
	}

	@Override
	public Page<CommentResponse> getUserComments(String orderby, String sortby, Integer page, Integer size) {
		return commentRepository.findByUser(getUsername(), getPage(orderby, sortby, page, size))
				.map(CommentResponse::new);
	}

	@Override
	@Transactional
	public Comment create(Comment comment) throws Exception {
		Post post = getPost(comment);
		comment.setUser(getUser());
		comment = commentRepository.save(comment);
		post.setCommentsCount(post.getCommentsCount() + 1);
		postRepository.save(post);
		return comment;
	}

	@Override
	public Comment update(Comment comment, String id) throws Exception {
		Comment updatedComment = findById(id);
		comment.setComment(comment.getComment());
		return commentRepository.save(updatedComment);
	}

	@Override
	@Transactional
	public void deleteById(String id) {
		Comment comment = findById(id);
		Post post = getPost(comment);
		commentRepository.delete(comment);
		post.setCommentsCount(post.getCommentsCount() - 1);
		postRepository.save(post);
	}

	@Override
	public Comment findById(String id) throws NotFoundException {
		Comment comment;
		if (isAdmin()) {
			comment = commentRepository.findById(id).orElse(null);
		} else {
			comment = commentRepository.findByUserAndId(getUser(), id);
		}
		if (comment == null) {
			throw new NotFoundException("Comment not found with id: " + id);
		}
		return comment;
	}

	private Post getPost(Comment comment) throws NotFoundException {
		String id = comment.getPost().getId();
		Post post = postRepository.findById(id).orElse(null);
		if (post == null) {
			throw new NotFoundException("Post not found with id: " + id);
		}
		return post;
	}
}
