package com.java.springboot.assignment.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Business_Master")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "branches"})
public class Business {

	@Id
	@GeneratedValue
	@Column(name="business_id")
	private int id;
	
	@Column(name="business_name")
	private String name;
	
	@Column(name="contact_no")
	private String contactNo;
	
	@Column(name="pan")
	private String pan;
	
	@Column(name="created_date")
	private LocalDate createdDate;
	
	@Column(name="updated_date")
	private LocalDate updatedDate;
	
	@OneToMany(mappedBy = "business", fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE)
    private List<Branch> branches;

	public Business() {
	}

	public Business(int id, String name, String contactNo, String pan, LocalDate createdDate, LocalDate updatedDate) {
		this.name = name;
		this.contactNo = contactNo;
		this.pan = pan;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}
}
