package com.beshambher.socialmedia.controller.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beshambher.socialmedia.domain.response.CommentResponse;
import com.beshambher.socialmedia.entity.post.Comment;
import com.beshambher.socialmedia.service.post.CommentService;

@RestController
@RequestMapping("/social")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping("/post/{id}/comments")
	public Page<CommentResponse> getComments(@PathVariable String id, @RequestParam(required = false) String orderby,
			@RequestParam(required = false) String sortby, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		return commentService.getCommentsByPost(id, orderby, sortby, page, size);
	}

	@GetMapping("/user/comments")
	public Page<CommentResponse> getUserComments(@RequestParam(required = false) String orderby,
			@RequestParam(required = false) String sortby, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		return commentService.getUserComments(orderby, sortby, page, size);
	}

	@PostMapping("/comments")
	public ResponseEntity<Comment> makeComment(@RequestBody(required = true) Comment comment) throws Exception {
		comment = commentService.create(comment);
		return ResponseEntity.noContent().header("location", "/comments/" + comment.getId()).build();
	}

	@PutMapping("/comments/{id}")
	public ResponseEntity<Comment> updateComment(@PathVariable String id, @RequestBody(required = true) Comment comment)
			throws Exception {
		comment = commentService.update(comment, id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/comments/{id}")
	public ResponseEntity<Comment> getComments(@PathVariable String id) throws Exception {
		commentService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
