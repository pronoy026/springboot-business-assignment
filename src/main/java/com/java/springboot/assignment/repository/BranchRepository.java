package com.java.springboot.assignment.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.springboot.assignment.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer>{

	List<Branch> findByAddress(String address);
	
	List<Branch> findByActiveInd(String activeInd);
	
	List<Branch> findByCreatedDate(LocalDate createdDate);
}
