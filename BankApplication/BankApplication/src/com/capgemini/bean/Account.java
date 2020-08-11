package com.capgemini.bean;

import com.capgemini.dao.DaoImpl;
import com.capgemini.dao.IDao;

public class Account {

	IDao dao=new DaoImpl();
	private String accountId;
	private String accountName;
	private String address;
	private Double depositAmount;

	public String getaccountId() {
		return accountId;
	}

	public void setaccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getaccountName() {
		return accountName;
	}

	public void setaccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public Account getDetails(String accountId) {
		return(dao.getDetails(accountId));
	}

	public void showDetails(String accountId) {
		Account demo=new Account();
		demo.getDetails(accountId);

	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountName=" + accountName + ", address=" + address
				+ ", depositAmount=" + depositAmount + "]";
	}
}
