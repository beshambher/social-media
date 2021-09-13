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

import com.beshambher.socialmedia.domain.response.PostResponse;
import com.beshambher.socialmedia.entity.post.Post;
import com.beshambher.socialmedia.service.post.PostService;

@RestController
@RequestMapping("/social")
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping("/posts")
	public Page<PostResponse> getPosts(@RequestParam(required = false) String orderby,
			@RequestParam(required = false) String sortby, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		return postService.getPostsWithFriends(orderby, sortby, page, size);
	}

	@GetMapping("/user/posts")
	public Page<PostResponse> getUserPosts(@RequestParam(required = false) String orderby,
			@RequestParam(required = false) String sortby, @RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		return postService.getPosts(orderby, sortby, page, size);
	}

	@PostMapping("/posts")
	public ResponseEntity<Post> makePost(@RequestBody(required = true) Post post) {
		post = postService.create(post);
		return ResponseEntity.noContent().header("location", "/posts/" + post.getId()).build();
	}

	@PutMapping("/posts/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable String id, @RequestBody(required = true) Post post)
			throws Exception {
		post = postService.update(post, id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/posts/{id}")
	public ResponseEntity<Post> getPosts(@PathVariable String id) throws Exception {
		postService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
