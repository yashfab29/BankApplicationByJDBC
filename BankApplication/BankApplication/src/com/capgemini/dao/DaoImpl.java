package com.capgemini.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.capgemini.bean.Account;
import com.capgemini.bean.Loan;

public class DaoImpl implements IDao{

	public static final String URL = "jdbc:mysql://localhost:3306/bank";
	public static final String USER = "root";
	public static final String PASS = "root";
	public Connection con=null;
	public Statement stmt = null;
	public ResultSet rs=null;
	public PreparedStatement ps=null;
	
	public Connection getConnection() {
	    try {
	        //DriverManager.registerDriver(new Driver());
	    	System.out.println("*****************DATABASE STARTED********************");
	    	return (Connection) DriverManager.getConnection(URL, USER, PASS);
	        
	    } catch (SQLException ex) {
	        throw new RuntimeException("Error connecting to the database", ex);
	    }
	    
	}
	

	
	public void createAccount(Account account) throws SQLException
	{
		con=getConnection();
		try {
	        ps = con.prepareStatement("INSERT INTO account VALUES(?,?,?,?)");
	        ps.setString(1, account.getaccountId());
	        ps.setString(2, account.getaccountName());
	        ps.setString(3, account.getAddress());
	        ps.setDouble(4, account.getDepositAmount());
	        System.out.println("********added********");
	        int i=ps.executeUpdate();  
	        System.out.println(i+" records inserted");  
	        con.close();
		}
		catch (SQLException ex) {
	        ex.printStackTrace();
	    }

		
	}
	
	@Override
	public Account getDetails(String accountId) {
		con=getConnection();
		Account ac= new Account();
		try {
			String query="SELECT * FROM account where accountId=?";
			ps = con.prepareStatement(query);
	        ps.setString(1, accountId);
	        rs=ps.executeQuery();
	       
			while(rs.next()) 
	       {
	    	  ac.setaccountId(rs.getString(1));
	    	  ac.setaccountName(rs.getString(2));
	    	  ac.setAddress(rs.getString(3));
	    	  ac.setDepositAmount(rs.getDouble(4));
	       }
	       
			con.close();
			return ac;
		}
		catch (SQLException ex) {
	        ex.printStackTrace();
	    }

		return null;
		
	}

	@Override
	public Loan getLoan(String loanId,String loanType,Double loanAmount) {
		Loan loan=new Loan();
		con=getConnection();
		try {
	        ps = con.prepareStatement("INSERT INTO loan VALUES(?,?,?)");
	        ps.setString(1, loanId);
	        ps.setString(2, loanType);
	        ps.setDouble(3, loanAmount);
	        int i=ps.executeUpdate();  
	        System.out.println(i+" records inserted");  
	        con.close();
	        return loan;
		}
		catch (SQLException ex) {
	        ex.printStackTrace();
	    }

		
		return null;
		
	}

	@Override
	public Loan showLoanDetails(Loan loan) {
		System.out.println(loan.getLoanId());
		System.out.println(loan.getLoanAmount());
		System.out.println(loan.getLoanType());
		return loan;
		
	}

	@Override
	public Double depositAmount(String AccountId, Integer amount) {
		con=getConnection();
			Double bal=0.0;
		 try {
		        ps = con.prepareStatement("SELECT amount FROM account where accountId=?");
		        ps.setString(1, AccountId);
		        rs=ps.executeQuery();
		       while(rs.next()) 
		       {
		    	   bal=rs.getDouble("amount");
		       
		       bal=bal+amount;
		       } 
		        ps = con.prepareStatement("UPDATE account SET amount=? WHERE accountId=?");
		        ps.setDouble(1, bal);
		        ps.setString(2, AccountId);
		        ps.executeUpdate();
		        con.close();
		        return bal;
		 }
		 catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		 		return null;
		 
		
	}

	@Override
	public Double withdrawAmount(String AccountId, Integer amount) {
	
		con=getConnection();
		Double bal=0.0;
	 try {
	        ps = con.prepareStatement("SELECT amount FROM account where accountId=?");
	        ps.setString(1, AccountId);
	        rs=ps.executeQuery();
	       while(rs.next()) 
	       {
	    	   bal=rs.getDouble("amount");
	       
	       bal = bal - amount;
	       } 
	        ps = con.prepareStatement("UPDATE account SET amount=? WHERE accountId=?");
	        ps.setDouble(1, bal);
	        ps.setString(2, AccountId);
	        ps.executeUpdate();
	        con.close();
	        return bal;
	 }
	 catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	 		return null;
			
	}

	@Override
	public Loan payLoan(Loan loan,Double loanamount) {
		loan.setLoanAmount(loan.getLoanAmount()-loanamount);
		return loan;
		
	}

}
