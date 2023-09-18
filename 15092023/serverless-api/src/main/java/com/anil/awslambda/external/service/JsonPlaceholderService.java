package com.anil.awslambda.external.service;

import java.util.List;

import org.springframework.web.service.annotation.GetExchange;

import com.anil.awslambda.model.Post;

public interface JsonPlaceholderService {
	@GetExchange("/posts")
    List<Post> loadPosts();
}
