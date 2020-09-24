package com.java.springboot.assignment.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import com.java.springboot.assignment.entity.Branch;
import com.java.springboot.assignment.entity.Business;
import com.java.springboot.assignment.repository.BranchRepository;
import com.java.springboot.assignment.repository.BusinessRepository;
import com.java.springboot.assignment.service.impl.BusinessServiceImpl;

public class BusinessServiceTest {

	@InjectMocks
	BusinessServiceImpl businessService;
	
	@Mock
	BusinessRepository businessRepository;
	
	@Mock
	BranchRepository branchRepository;
	
	Business bs;
	
	Branch bn;
	
	List<Business> businessList;
	
	List<Branch> branchList;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		bs = new Business();
		bs.setName("Stock Exchange");
		bs.setContactNo("345678");
		bs.setPan("4567");
		bs.setCreatedDate(LocalDate.now());
		bs.setUpdatedDate(LocalDate.now());
		
		businessList = new ArrayList<Business>();
		businessList.add(bs);
		
		bn = new Branch();
		bn.setContact("8765434");
		bn.setAddress("London, UK");
		bn.setActiveInd("Y");
		bn.setBusiness(bs);
		bn.setCreatedDate(LocalDate.now());
		bn.setUpdatedDate(LocalDate.now());
		
		branchList = new ArrayList<Branch>();
		branchList.add(bn);
	}
	
	@Test
	public void addBusinessTest() {
		Mockito.when(businessRepository.save(Mockito.any(Business.class))).thenReturn(bs);
		assertNotNull(businessService.addBusiness(bs));
	}
	
	@Test
	public void addBranchTest() {
		Mockito.when(branchRepository.save(Mockito.any(Branch.class))).thenReturn(bn);
		assertNotNull(businessService.addBranch(bn));
	}
	
	@Test
	public void case1updateBusinessTest() {
		Mockito.when(businessRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(bs));
		assertNotNull(businessService.updateBusiness(bs));
	}
	
	@Test
	public void case2updateBusinessTest() {
		// case 2 : exception thrown
		Mockito.when(businessRepository.findById(Mockito.anyInt())).thenThrow(ResponseStatusException.class);
		assertThrows( ResponseStatusException.class, () -> {
			businessService.updateBusiness(bs);
		});
	}
	
	@Test
	public void case1updateBranchTest() {
		Mockito.when(branchRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(bn));
		assertNotNull(businessService.updateBranch(bn));
	}
	
	@Test
	public void case2updateBranchTest() {
		// case 2 : exception thrown
		Mockito.when(branchRepository.findById(Mockito.anyInt())).thenThrow(ResponseStatusException.class);
		assertThrows( ResponseStatusException.class, () -> {
			businessService.updateBranch(bn);
		});
	}
	
	@Test
	public void case1searchTest() {
		// valid Business data found by name
		Mockito.when(businessRepository.findByName(Mockito.anyString())).thenReturn(businessList);
		assertTrue(!businessService.search("Stock Exchange").getBusinessList().isEmpty());
	}
	
	@Test
	public void case2searchTest() {
		// valid Business data found by pan
		Mockito.when(businessRepository.findByName(Mockito.anyString())).thenReturn(new ArrayList<Business>());
		Mockito.when(businessRepository.findByPan(Mockito.anyString())).thenReturn(businessList);
		assertTrue(!businessService.search("4567").getBusinessList().isEmpty());
	}
	
	@Test
	public void case3searchTest() {
		// valid Branch data found by branch address
		Mockito.when(businessRepository.findByName(Mockito.anyString())).thenReturn(new ArrayList<Business>());
		Mockito.when(businessRepository.findByPan(Mockito.anyString())).thenReturn(new ArrayList<Business>());
		Mockito.when(branchRepository.findByAddress(Mockito.anyString())).thenReturn(branchList);
		assertTrue(!businessService.search("London, UK").getBranchList().isEmpty());
	}
	
	@Test
	public void case4searchTest() {
		// valid Branch data found by active ind
		Mockito.when(businessRepository.findByName(Mockito.anyString())).thenReturn(new ArrayList<Business>());
		Mockito.when(businessRepository.findByPan(Mockito.anyString())).thenReturn(new ArrayList<Business>());
		Mockito.when(branchRepository.findByAddress(Mockito.anyString())).thenReturn(new ArrayList<Branch>());
		Mockito.when(branchRepository.findByActiveInd(Mockito.anyString())).thenReturn(branchList);
		assertTrue(!businessService.search("Y").getBranchList().isEmpty());
	}
	
	@Test
	public void case5searchTest() {
		// valid Branch data found by branch created date
		Mockito.when(businessRepository.findByName(Mockito.anyString())).thenReturn(new ArrayList<Business>());
		Mockito.when(businessRepository.findByPan(Mockito.anyString())).thenReturn(new ArrayList<Business>());
		Mockito.when(branchRepository.findByAddress(Mockito.anyString())).thenReturn(new ArrayList<Branch>());
		Mockito.when(branchRepository.findByActiveInd(Mockito.anyString())).thenReturn(new ArrayList<Branch>());
		Mockito.when(branchRepository.findByCreatedDate(Mockito.any(LocalDate.class))).thenReturn(branchList);
		assertTrue(!businessService.search("2020-12-13").getBranchList().isEmpty());
	}
	
	@Test
	public void case6searchTest() {
		// Branch created date parse exception
		Mockito.when(businessRepository.findByName(Mockito.anyString())).thenReturn(new ArrayList<Business>());
		Mockito.when(businessRepository.findByPan(Mockito.anyString())).thenReturn(new ArrayList<Business>());
		Mockito.when(branchRepository.findByAddress(Mockito.anyString())).thenReturn(new ArrayList<Branch>());
		Mockito.when(branchRepository.findByActiveInd(Mockito.anyString())).thenReturn(new ArrayList<Branch>());
		Mockito.when(branchRepository.findByCreatedDate(Mockito.any(LocalDate.class))).thenReturn(branchList);
		assertNotNull(businessService.search("2020/12/13"));
	}
	
	@Test
	public void case7searchTest() {
		// no search data found
		Mockito.when(businessRepository.findByName(Mockito.anyString())).thenReturn(new ArrayList<Business>());
		Mockito.when(businessRepository.findByPan(Mockito.anyString())).thenReturn(new ArrayList<Business>());
		Mockito.when(branchRepository.findByAddress(Mockito.anyString())).thenReturn(new ArrayList<Branch>());
		Mockito.when(branchRepository.findByActiveInd(Mockito.anyString())).thenReturn(new ArrayList<Branch>());
		Mockito.when(branchRepository.findByCreatedDate(Mockito.any(LocalDate.class))).thenReturn(new ArrayList<Branch>());
		assertNull(businessService.search("2016-08-21"));
	}
}
