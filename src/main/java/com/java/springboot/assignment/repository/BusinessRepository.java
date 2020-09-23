package com.java.springboot.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.springboot.assignment.entity.Business;

public interface BusinessRepository extends JpaRepository<Business, Integer> {
	
	List<Business> findByName(String name);
	
	List<Business> findByPan(String pan);
}
