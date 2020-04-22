package com.revature.accounts;

import com.revature.dao.AdminDaoImplementation;

public class AdminAccount extends EmployeeAccount {

	public AdminAccount(String fName, String lName, String username, String password) {
		super(fName, lName, username, password);
	}
	
	public AdminAccount() {
		super();
	}
	
	//dao object that communicates with the database
	AdminDaoImplementation dao = new AdminDaoImplementation();
	
	//withdraw from the account
	public void withdraw(String username, double amount) {
		CustomerAccount customerAccount = dao.getCustomer(username);
		
		//if no username matches
		if(customerAccount == null) {
			System.out.println("Invalid username");
			return;
		}
		
		if(amount < customerAccount.getBalance() && amount > 0) {
			dao.withdraw(amount, username);
		}
		else {
			System.out.println("Invalid withdraw amount");
		}
	}
	
	//deposit to the account
	public void deposit(String username, double amount) {
		CustomerAccount customerAccount = dao.getCustomer(username);
		
		//if no username matches
		if(customerAccount == null) {
			System.out.println("Ivalid username");
			return;
		}
		
		if(amount > 0) {
			dao.deposit(amount, username);
		}
		else {
			System.out.println("Cannot deposit negative amount.");
		}
	}
	
	//transfer funds between two customers
	public void transfer(String source, String destination, double amount) {
		CustomerAccount customer = dao.getCustomer(source);
		
		//if no username matches
		if(customer == null) {
			System.out.println("Invalid source username.");
			return;
		}
		
		customer.transfer(amount, destination);
	}
	
	//close the given account
	public void closeAccount(String username) {
		dao.closeAccount(username);
	}
}
