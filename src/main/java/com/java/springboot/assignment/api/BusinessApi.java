package com.java.springboot.assignment.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.springboot.assignment.dto.SearchResultDto;
import com.java.springboot.assignment.entity.Branch;
import com.java.springboot.assignment.entity.Business;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/v1")
@RestController
public interface BusinessApi {
	
	@PostMapping("/add/business")
	@ApiOperation(value = "Adds new business record to business_master", notes = "Provide valid business details.", response = Business.class)
	public ResponseEntity<Business> addBusiness(@RequestBody Business business);
	
	@PostMapping("/add/branch")
	@ApiOperation(value = "Adds new branch record to branch_master", notes = "Provide valid branch details.", response = Branch.class)
	public ResponseEntity<Branch> addBranch(@RequestBody Branch branch);
	
	@PostMapping("/update/business")
	@ApiOperation(value = "Updates existing business becord", notes = "Provide valid business details with valid business id.", response = Business.class)
	public ResponseEntity<Business> updateBusiness(@RequestBody Business business);
	
	@PostMapping("/update/branch")
	@ApiOperation(value = "Updates existing branch becord", notes = "Provide valid branch details with valid branch id.", response = Branch.class)
	public ResponseEntity<Branch> updateBranch(@RequestBody Branch branch);
	
	@GetMapping("/search/{searchString}")
	@ApiOperation(value = "Searches existing business or branch record", 
		notes = "Provide valid business name, pan, branch address, active ind info or branch created date to search business or branch details.", 
		response = SearchResultDto.class)
	public ResponseEntity<SearchResultDto> search(@PathVariable String searchString);
	
}
