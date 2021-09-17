package com.greatlearning.example.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.greatlearning.example.postmodel.PostResponse;

@FeignClient(url = "https://jsonplaceholder.typicode.com", name = "POST-CLIENT")
public interface PostClient {

	@GetMapping("/posts")
	public List<PostResponse> getAllPost();
	
	@PostMapping("/posts")
	public Object insertPost(PostResponse postResponse);
}
