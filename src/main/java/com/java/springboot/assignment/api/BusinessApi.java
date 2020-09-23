package com.java.springboot.assignment.api;

import java.text.ParseException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.springboot.assignment.dto.SearchResultDto;
import com.java.springboot.assignment.entity.Branch;
import com.java.springboot.assignment.entity.Business;

@RequestMapping("/app/v1")
@RestController
public interface BusinessApi {
	
	@GetMapping("/home")
	public String home();
	
	@PostMapping("/add/business")
	public Business addBusiness(@RequestBody Business business);
	
	@PostMapping("/add/branch")
	public Branch addBranch(@RequestBody Branch branch);
	
	@PostMapping("/update/business")
	public Business updateBusiness(@RequestBody Business business);
	
	@PostMapping("/update/branch")
	public Branch updateBranch(@RequestBody Branch branch);
	
	@GetMapping("/search/{searchString}")
	public SearchResultDto Search(@PathVariable String searchString);
	
}
