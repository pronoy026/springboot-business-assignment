package com.java.springboot.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.java.springboot.assignment.api.BusinessApi;
import com.java.springboot.assignment.dto.SearchResultDto;
import com.java.springboot.assignment.entity.Branch;
import com.java.springboot.assignment.entity.Business;
import com.java.springboot.assignment.service.BusinessService;

@Component
public class BusinessController implements BusinessApi{

	@Autowired
	BusinessService businessService;

	@Override
	public ResponseEntity<Business> addBusiness(Business business) {
		businessService.addBusiness(business);
		return ResponseEntity.status(HttpStatus.CREATED).body(business);
	}

	@Override
	public ResponseEntity<Branch> addBranch(Branch branch) {
		businessService.addBranch(branch);
		return ResponseEntity.status(HttpStatus.CREATED).body(branch);
	}

	@Override
	public ResponseEntity<Business> updateBusiness(Business business) {
		businessService.updateBusiness(business);
		return ResponseEntity.ok().body(business);
	}

	@Override
	public ResponseEntity<Branch> updateBranch(Branch branch) {
		businessService.updateBranch(branch);
		return ResponseEntity.ok().body(branch);
	}

	@Override
	public ResponseEntity<SearchResultDto> Search(String searchString) {
		SearchResultDto searchResult = businessService.Search(searchString);
		if( (searchResult.getBusinessList() == null || searchResult.getBusinessList().isEmpty())
				&& (searchResult.getBranchList() == null || searchResult.getBranchList().isEmpty())) {
			throw new ResponseStatusException(
      			  HttpStatus.NOT_FOUND, "Relevant Business or Branch not Found."
      			);
		}
		return ResponseEntity.ok().body(searchResult);
	}

}
