package com.capgemini.bean;

import com.capgemini.dao.DaoImpl;
import com.capgemini.dao.IDao;

public class Transaction extends Loan {
	
	
	IDao dao = new DaoImpl();
	Double amount;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction [amount=" + amount + "]";
	}

	public Double depositAmount(String AccountId, Integer amount) {
		return (dao.depositAmount(AccountId, amount));
	}

	public Double withdrawAmount(String accountId, Integer amount) {
		return (dao.withdrawAmount(accountId,amount));
	}

	public Loan payLoan(Loan loan,Double loanamount) {
		return(dao.payLoan(loan,loanamount));
	}

	public void showAccountDetails() {

	}

}
