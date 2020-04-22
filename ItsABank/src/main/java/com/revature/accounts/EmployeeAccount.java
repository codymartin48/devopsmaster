package com.revature.accounts;

import java.util.List;

import com.revature.dao.EmployeeDaoImplementation;

public class EmployeeAccount {
	String fName;
	String lName;
	String username;
	String password;
	
	
	public EmployeeAccount(String fName, String lName, String username, String password) {
		this.fName = fName;
		this.lName = lName;
		this.username = username;
		this.password = password;
	}
	
	public EmployeeAccount() {
		fName = "";
		lName = "";
		username = "";
		password = "";
	}

	//dao object for database communication
	EmployeeDaoImplementation dao = new EmployeeDaoImplementation();
	
	//get a specific user from the database
	public CustomerAccount getUserInfo(String username) {
		CustomerAccount customer = dao.getCustomer(username);
		return customer;
	}
	
	//get a list of all the customers in the database
	public List<CustomerAccount> getAllInfo() {
		List<CustomerAccount> customerList = dao.getAllCustomers();
		return customerList;
	}
	
	//get a list of all pending customers in the database
	public List<CustomerAccount> getAllPendingCustomers(){
		List<CustomerAccount> customerList = dao.getAllPendingCustomers();
		return customerList;
	}
	
	//approve a pending account
	public void approveAccount(String username) {
		dao.approveAccount(username);
	}
	
	//deny a pending account
	public void denyAccount(String username) {
		dao.denyAccount(username);
	}

	/*************************************************************/
	
	//getters and setters
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}


	public String getlName() {
		return lName;
	}


	public void setlName(String lName) {
		this.lName = lName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
