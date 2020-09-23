package com.java.springboot.assignment.dto;

import java.util.List;

import com.java.springboot.assignment.entity.Branch;
import com.java.springboot.assignment.entity.Business;

public class SearchResultDto {

	private List<Business> businessList;
	
	private List<Branch> branchList;

	public List<Business> getBusinessList() {
		return businessList;
	}

	public void setBusinessList(List<Business> business) {
		this.businessList = business;
	}

	public List<Branch> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<Branch> branch) {
		this.branchList = branch;
	}
}
