package com.revature.dao;

import com.revature.accounts.CustomerAccount;

public interface AdminDao {
	public void closeAccount(String username);
	public void withdraw(double amount, String username);
	public void deposit(double amount, String username);
	public void transfer(double amount, String source, String recipient);
}
