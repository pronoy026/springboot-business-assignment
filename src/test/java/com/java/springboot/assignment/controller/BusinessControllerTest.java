package com.java.springboot.assignment.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import com.java.springboot.assignment.dto.SearchResultDto;
import com.java.springboot.assignment.entity.Branch;
import com.java.springboot.assignment.entity.Business;
import com.java.springboot.assignment.service.BusinessService;

@RunWith(SpringRunner.class)
public class BusinessControllerTest {

	@InjectMocks
	BusinessController businessController;
	
	@Mock
	BusinessService businessService;
	
	Business bs;
	
	Branch bn;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		bs = new Business();
		bs.setName("Stock Exchange");
		bs.setContactNo("345678");
		bs.setPan("4567");
		bs.setCreatedDate(LocalDate.now());
		bs.setUpdatedDate(LocalDate.now());
		
		bn = new Branch();
		bn.setContact("8765434");
		bn.setAddress("London, UK");
		bn.setActiveInd("Y");
		bn.setBusiness(bs);
		bn.setCreatedDate(LocalDate.now());
		bn.setUpdatedDate(LocalDate.now());
	}
	
	@Test
	public void addBusinessTest() {
		Mockito.when(businessService.addBusiness(Mockito.any(Business.class))).thenReturn(bs);
		assertNotNull(businessController.addBusiness(bs));
	}
	
	@Test
	public void addBranchTest() {
		Mockito.when(businessService.addBranch(Mockito.any(Branch.class))).thenReturn(bn);
		assertNotNull(businessController.addBranch(bn));
	}
	
	@Test
	public void updateBusinessTest() {
		bs.setId(1);
		Mockito.when(businessService.updateBusiness(Mockito.any(Business.class))).thenReturn(bs);
		assertNotNull(businessController.updateBusiness(bs));
	}
	
	@Test
	public void updateBranchTest() {
		bn.setId(1);
		Mockito.when(businessService.updateBranch(Mockito.any(Branch.class))).thenReturn(bn);
		assertNotNull(businessController.updateBranch(bn));
	}
	
	@Test
	public void case1searchTest() {
		// case 1: both business list and branch list is null 
		SearchResultDto searchResult = new SearchResultDto();
		searchResult.setBusinessList(null);
		searchResult.setBranchList(null);
		Mockito.when(businessService.search(Mockito.anyString())).thenReturn(searchResult);
		assertThrows(ResponseStatusException.class, () -> {
			businessController.search("Digital Marketing");
		});
	}
	
	@Test
	public void case2searchTest() {
		// case 2: both business list and branch list is empty
		SearchResultDto searchResult = new SearchResultDto();
		searchResult.setBusinessList(new ArrayList<Business>());
		searchResult.setBranchList(new ArrayList<Branch>());
		Mockito.when(businessService.search(Mockito.anyString())).thenReturn(searchResult);
		assertThrows(ResponseStatusException.class, () -> {
			businessController.search("Digital Marketing");
		});
	}
	
	@Test
	public void case3searchTest() {
		// case 3: business list not nul or empty
		SearchResultDto searchResult = new SearchResultDto();
		List<Business> bsList = new ArrayList<Business>();
		bsList.add(bs);
		searchResult.setBusinessList(bsList);
		searchResult.setBranchList(new ArrayList<Branch>());
		Mockito.when(businessService.search(Mockito.anyString())).thenReturn(searchResult);
		assertTrue(!businessController.search("Stock Exchange").getBody().getBusinessList().isEmpty());
	}
	
	@Test
	public void case4searchTest() {
		// case 4: branch list not nul or empty
		SearchResultDto searchResult = new SearchResultDto();
		List<Branch> bnList = new ArrayList<Branch>();
		bnList.add(bn);
		searchResult.setBusinessList(new ArrayList<Business>());
		searchResult.setBranchList(bnList);
		Mockito.when(businessService.search(Mockito.anyString())).thenReturn(searchResult);
		assertTrue(!businessController.search("Stock Exchange").getBody().getBranchList().isEmpty());
	}
	
	
}
