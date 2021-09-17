package com.greatlearning.example.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.greatlearning.example.todomodel.TodoResponse;

@FeignClient(url="https://jsonplaceholder.typicode.com",name="TODO-CLIENT")
public interface TodoClient {

	@GetMapping("/todos")
	public List<TodoResponse> getTodos();
	
}
