package com.java.springboot.assignment.service;

import org.springframework.stereotype.Service;

import com.java.springboot.assignment.dto.SearchResultDto;
import com.java.springboot.assignment.entity.Branch;
import com.java.springboot.assignment.entity.Business;

@Service
public interface BusinessService {

	public Business addBusiness(Business business);
	
	public Branch addBranch(Branch branch);
	
	public Business updateBusiness(Business business);
	
	public Branch updateBranch(Branch branch);
	
	public SearchResultDto search(String searchString);
}
