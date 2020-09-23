package com.java.springboot.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	public String home() {
		return "server running!";
	}

	@Override
	public Business addBusiness(Business business) {
		businessService.addBusiness(business);
		return business;
	}

	@Override
	public Branch addBranch(Branch branch) {
		businessService.addBranch(branch);
		return branch;
	}

	@Override
	public Business updateBusiness(Business business) {
		businessService.updateBusiness(business);
		return business;
	}

	@Override
	public Branch updateBranch(Branch branch) {
		businessService.updateBranch(branch);
		return branch;
	}

	@Override
	public SearchResultDto Search(String searchString) {
		SearchResultDto searchResult = businessService.Search(searchString);
		return searchResult;
	}

}
