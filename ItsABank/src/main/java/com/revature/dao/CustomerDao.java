package com.revature.dao;

import com.revature.accounts.CustomerAccount;

public interface CustomerDao {
	public boolean deposit(double balance, String username);
	public boolean withdraw(double balance, String username);
	//public boolean transfer(double amount, String username, String transferUsername);
	public CustomerAccount getCustomer(String username);
}
