package com.plms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "AVAILABLE_POLICIES")
public class AvailablePolicies {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer policyid;
    private String policyname;
    private String policydetails;
	public Integer getPolicyid() {
		return policyid;
	}
	public void setPolicyid(Integer policyid) {
		this.policyid = policyid;
	}
	public String getPolicyname() {
		return policyname;
	}
	public void setPolicyname(String policyname) {
		this.policyname = policyname;
	}
	public String getPolicydetails() {
		return policydetails;
	}
	public void setPolicydetails(String policydetails) {
		this.policydetails = policydetails;
	}
	
    
}
