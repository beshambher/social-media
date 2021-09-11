package com.beshambher.socialmedia.service.post.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.beshambher.socialmedia.constants.sorting.PostSorting;
import com.beshambher.socialmedia.entity.post.Comment;
import com.beshambher.socialmedia.repository.CommentRepository;
import com.beshambher.socialmedia.service.post.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Override
	public String defaultOrder() {
		return PostSorting.createdAt.toString();
	}

	@Override
	public String defaultSort() {
		return "desc";
	}

	@Override
	public Page<Comment> getCommentsByPost(String postID) {
		return null;
	}

	@Override
	public <C extends Comment> C create(C comment) {
		comment.setUser(getUser());
		return commentRepository.save(comment);
	}

	@Override
	public <C extends Comment> C update(C comment) {
		return commentRepository.save(comment);
	}

	@Override
	public void deleteById(String id) {
		commentRepository.deleteById(id);
	}

}
