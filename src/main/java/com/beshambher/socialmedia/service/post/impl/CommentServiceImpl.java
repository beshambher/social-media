package com.beshambher.socialmedia.service.post.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.beshambher.socialmedia.constants.sorting.PostSorting;
import com.beshambher.socialmedia.domain.response.CommentResponse;
import com.beshambher.socialmedia.entity.post.Comment;
import com.beshambher.socialmedia.entity.post.Post;
import com.beshambher.socialmedia.repository.CommentRepository;
import com.beshambher.socialmedia.repository.PostRepository;
import com.beshambher.socialmedia.service.post.CommentService;

import javassist.NotFoundException;

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
				.map(c -> new CommentResponse(c));
	}

	@Override
	public Page<CommentResponse> getUserComments(String orderby, String sortby, Integer page, Integer size) {
		return commentRepository.findByUser(getUsername(), getPage(orderby, sortby, page, size))
				.map(c -> new CommentResponse(c));
	}

	@Override
	public Comment create(Comment comment) throws Exception {
		String id = comment.getPost().getId();
		Post post = postRepository.findById(id).orElse(null);
		if (post == null) {
			throw new NotFoundException("Post not found with id: " + id);
		}
		comment.setUser(getUser());
		comment = commentRepository.save(comment);
		post.setCommentsCount(post.getCommentsCount() + 1);
		post = postRepository.save(post);
		return comment;
	}

	@Override
	public Comment update(Comment comment, String id) throws Exception {
		Comment updatedComment = findById(id);
		comment.setComment(comment.getComment());
		return commentRepository.save(updatedComment);
	}

	@Override
	public void deleteById(String id) throws Exception {
		Comment comment = findById(id);
		commentRepository.delete(comment);
	}

	@Override
	public Comment findById(String id) throws Exception {
		Comment comment = null;
		if (isAdmin()) {
			comment = commentRepository.findById(id).orElse(null);
		} else {
			comment = commentRepository.findByUserAndId(getUsername(), id);
		}
		if (comment == null) {
			throw new NotFoundException("Comment not found with id: " + id);
		}
		return comment;
	}

}
