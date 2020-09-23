package com.java.springboot.assignment.dto;

import java.util.List;

import com.java.springboot.assignment.entity.Branch;
import com.java.springboot.assignment.entity.Business;

public class SearchResultDto {

	private List<Business> business;
	
	private List<Branch> branch;

	public List<Business> getBusiness() {
		return business;
	}

	public void setBusiness(List<Business> business) {
		this.business = business;
	}

	public List<Branch> getBranch() {
		return branch;
	}

	public void setBranch(List<Branch> branch) {
		this.branch = branch;
	}
}
