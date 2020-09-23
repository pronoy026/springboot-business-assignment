package com.java.springboot.assignment.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Branch_Master")
public class Branch {
	
	@Id
	@GeneratedValue
	@Column(name="branch_id")
	private int id;
	
	@Column(name="branch_address")
	private String address;
	
	@Column(name="branch_contact")
	private String contact;
	
	@Column(name="active_ind")
	private String activeInd;
	
	@Column(name="created_date")
	private LocalDate createdDate;
	
	@Column(name="updated_date")
	private LocalDate updatedDate;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade=CascadeType.MERGE)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;
	
	public Branch() {
	}

	public Branch(int id, String address, String contact, String activeInd, LocalDate createdDate, LocalDate updatedDate,
			Business business) {
		this.address = address;
		this.contact = contact;
		this.activeInd = activeInd;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.business = business;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getActiveInd() {
		return activeInd;
	}

	public void setActiveInd(String activeInd) {
		this.activeInd = activeInd;
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

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}
	
}