package com.anil.awslambda.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anil.awslambda.Exception.PostNotFoundException;
import com.anil.awslambda.external.service.JsonPlaceholderService;
import com.anil.awslambda.model.Post;

import jakarta.annotation.PostConstruct;

@RestController()
@RequestMapping("/api/posts")
public class PostController {
	private final JsonPlaceholderService jsonPlaceholderService;
	private static final Logger log = LoggerFactory.getLogger(PostController.class);
	private List<Post> posts = new ArrayList<>();

	public PostController(JsonPlaceholderService jsonPlaceholderService) {
		this.jsonPlaceholderService = jsonPlaceholderService;
	}

	@GetMapping
	List<Post> findAll() {
		return posts;
	}

	@GetMapping("/{id}")
	Optional<Post> findById(@PathVariable Integer id) {
		return Optional.ofNullable(posts.stream().filter(post -> post.id().equals(id)).findFirst()
				.orElseThrow(() -> new PostNotFoundException("Post with id: " + id + " not found.")));
	}

	@PostMapping
	void create(@RequestBody Post post) {
		posts.add(post);
	}

	@PutMapping("/{id}")
	void update(@RequestBody Post post, @PathVariable Integer id) {
		posts.stream().filter(p -> p.id().equals(id)).findFirst()
				.ifPresent(value -> posts.set(posts.indexOf(value), post));
	}

	@DeleteMapping("/{id}")
	void delete(@PathVariable Integer id) {
		posts.removeIf(post -> post.id().equals(id));
	}

	@PostConstruct
	private void init() {
		if (posts.isEmpty()) {
			log.info("Loading Posts using JsonPlaceHolderService");
			posts = jsonPlaceholderService.loadPosts();
		}
	}
}
