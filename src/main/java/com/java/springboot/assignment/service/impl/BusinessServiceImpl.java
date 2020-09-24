package com.java.springboot.assignment.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.java.springboot.assignment.dto.SearchResultDto;
import com.java.springboot.assignment.entity.Branch;
import com.java.springboot.assignment.entity.Business;
import com.java.springboot.assignment.repository.BranchRepository;
import com.java.springboot.assignment.repository.BusinessRepository;
import com.java.springboot.assignment.service.BusinessService;

@Component
public class BusinessServiceImpl implements BusinessService{

	@Autowired
	BusinessRepository businessRepository;
	
	@Autowired
	BranchRepository branchRepository;
	
	@Override
	public Business addBusiness(Business business) {
		businessRepository.save(business);
		return business;
	}

	@Override
	public Branch addBranch(Branch branch) {
		branchRepository.save(branch);
		return branch;
	}

	@Override
	public Business updateBusiness(Business business) {
		Business bs;
		try {
			bs = businessRepository.findById(business.getId()).get();
		} catch (Exception e) {
			throw new ResponseStatusException(
	      			  HttpStatus.NOT_FOUND, "Business data not found to update."
	      			);
		}
		bs.setName(business.getName());
		bs.setContactNo(business.getContactNo());
		bs.setPan(business.getPan());
		bs.setCreatedDate(business.getCreatedDate());
		bs.setUpdatedDate(LocalDate.now());
		businessRepository.save(bs);
		return bs;
	}

	@Override
	public Branch updateBranch(Branch branch) {
		Branch bn;
		try {
			bn = branchRepository.findById(branch.getId()).get();
		} catch (Exception e) {
			throw new ResponseStatusException(
	      			  HttpStatus.NOT_FOUND, "Branch data not found to update."
	      			);
		}
		bn.setBusiness(branch.getBusiness());
		bn.setAddress(branch.getAddress());
		bn.setContact(branch.getContact());
		bn.setActiveInd(branch.getActiveInd());
		bn.setCreatedDate(branch.getCreatedDate());
		bn.setUpdatedDate(LocalDate.now());
		branchRepository.save(bn);
		return bn;
	}

	@Override
	public SearchResultDto search(String searchString) {
		
		SearchResultDto searchResult = new SearchResultDto();
		List<Business> businessList;
		List<Branch> branchList;
		
		// search by business name
		businessList = businessRepository.findByName(searchString);
		if(!businessList.isEmpty()) {
			searchResult.setBusinessList(businessList);
			return searchResult;
		}
		
		// search by pan
		businessList = businessRepository.findByPan(searchString);
		if(!businessList.isEmpty()) {
			searchResult.setBusinessList(businessList);
			return searchResult;
		}
		
		// search by branch address
		branchList = branchRepository.findByAddress(searchString);
		if(!branchList.isEmpty()) {
			searchResult.setBranchList(branchList);
			return searchResult;
		}
		
		// search by active ind
		branchList = branchRepository.findByActiveInd(searchString);
		if(!branchList.isEmpty()) {
			searchResult.setBranchList(branchList);
			return searchResult;
		}
				
		// search by created date
		LocalDate createdDate;
		DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			createdDate = LocalDate.parse(searchString, dateformatter);
		} catch (Exception e) {
			return searchResult;
		}
		branchList = branchRepository.findByCreatedDate(createdDate);
		if(!branchList.isEmpty()) {
			searchResult.setBranchList(branchList);
			return searchResult;
		}
		
		return null;
	}

}
