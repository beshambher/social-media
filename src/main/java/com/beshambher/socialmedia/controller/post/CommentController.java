package com.beshambher.socialmedia.controller.post;

import com.beshambher.socialmedia.dto.request.CommentRequest;
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

import com.beshambher.socialmedia.dto.response.CommentResponse;
import com.beshambher.socialmedia.entity.post.Comment;
import com.beshambher.socialmedia.service.post.CommentService;

@RestController
@RequestMapping("/social")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping("/posts/{id}/comments")
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
	public ResponseEntity<Comment> createComment(@RequestBody CommentRequest commentRequest) {
		var comment = commentService.addComment(commentRequest);
		return ResponseEntity.noContent().header("location", "/comments/" + comment.getId()).build();
	}

	@PutMapping("/comments/{id}")
	public ResponseEntity<Comment> updateComment(@PathVariable String id, @RequestBody Comment comment)
			throws Exception {
		commentService.update(comment, id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/comments/{id}")
	public ResponseEntity<Comment> deleteComment(@PathVariable String id) throws Exception {
		commentService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
