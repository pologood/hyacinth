/*
 * Copyright (c) 2016 www.jd.com All rights reserved.
 * 本软件源代码版权归京东成都云平台所有,未经许可不得任意复制与传播.
 */
package com.jd.hyacinth.common;
/**
 * 账号类
 * @author J-ONE
 * @since 2016-10-20
 */
public class Account {
	private String personId;

	private String account;

	private String personName;

	private String orgId;

	private String orgName;

	private int isPassModi;

	private int passOverdue;

	private String cvnPassModiTime;

	private String cvnCreateTime;

	private String cvnModiTime;

	private String cvnEffectiveStartTime;

	private String cvnEffectiveEndTime;

	private int isDefault;

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public int getIsPassModi() {
		return isPassModi;
	}

	public void setIsPassModi(int isPassModi) {
		this.isPassModi = isPassModi;
	}

	public int getPassOverdue() {
		return passOverdue;
	}

	public void setPassOverdue(int passOverdue) {
		this.passOverdue = passOverdue;
	}

	public String getCvnPassModiTime() {
		return cvnPassModiTime;
	}

	public void setCvnPassModiTime(String cvnPassModiTime) {
		this.cvnPassModiTime = cvnPassModiTime;
	}

	public String getCvnCreateTime() {
		return cvnCreateTime;
	}

	public void setCvnCreateTime(String cvnCreateTime) {
		this.cvnCreateTime = cvnCreateTime;
	}

	public String getCvnModiTime() {
		return cvnModiTime;
	}

	public void setCvnModiTime(String cvnModiTime) {
		this.cvnModiTime = cvnModiTime;
	}

	public String getCvnEffectiveStartTime() {
		return cvnEffectiveStartTime;
	}

	public void setCvnEffectiveStartTime(String cvnEffectiveStartTime) {
		this.cvnEffectiveStartTime = cvnEffectiveStartTime;
	}

	public String getCvnEffectiveEndTime() {
		return cvnEffectiveEndTime;
	}

	public void setCvnEffectiveEndTime(String cvnEffectiveEndTime) {
		this.cvnEffectiveEndTime = cvnEffectiveEndTime;
	}

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "Account [personId=" + personId + ", account=" + account + ", personName=" + personName + ", orgId=" + orgId
				+ ", orgName=" + orgName + ", isPassModi=" + isPassModi + ", passOverdue=" + passOverdue + ", cvnPassModiTime="
				+ cvnPassModiTime + ", cvnCreateTime=" + cvnCreateTime + ", cvnModiTime=" + cvnModiTime
				+ ", cvnEffectiveStartTime=" + cvnEffectiveStartTime + ", cvnEffectiveEndTime=" + cvnEffectiveEndTime
				+ ", isDefault=" + isDefault + "]";
	}
}
