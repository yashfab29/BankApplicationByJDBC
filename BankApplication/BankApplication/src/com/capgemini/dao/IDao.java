package com.capgemini.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.capgemini.bean.Account;
import com.capgemini.bean.Loan;

public interface IDao {
	
	Connection getConnection();
	void createAccount(Account account) throws SQLException;
	Account getDetails(String accountId);
	Loan getLoan(String loanId,String loanType,Double loanAmount);
	Loan showLoanDetails(Loan loan);
	Double withdrawAmount(String AccountId, Integer amount);
	Loan payLoan(Loan loan,Double loanamount);
	Double depositAmount(String AccountId, Integer amount);

}
