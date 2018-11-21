package com.plms.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "USER_PURCHASED_POLICIES")
public class UserPolicy {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer policynumber;
    private Float amount;
    private Date policyenddate;
    private Integer policyid;
    private Integer userid;
	public Integer getPolicynumber() {
		return policynumber;
	}
	public void setPolicynumber(Integer policynumber) {
		this.policynumber = policynumber;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Date getPolicyenddate() {
		return policyenddate;
	}
	public void setPolicyenddate(Date policyenddate) {
		this.policyenddate = policyenddate;
	}
	public Integer getPolicyid() {
		return policyid;
	}
	public void setPolicyid(Integer policyid) {
		this.policyid = policyid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}

    
    
}
