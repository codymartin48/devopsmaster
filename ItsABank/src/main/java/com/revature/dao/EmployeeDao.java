package com.revature.dao;

import java.util.List;

import com.revature.accounts.CustomerAccount;

public interface EmployeeDao {
	public List<CustomerAccount> getAllCustomers();
	public List<CustomerAccount> getAllPendingCustomers();
	public CustomerAccount getCustomer(String customer);
	public boolean addCustomer(CustomerAccount customer);
	public boolean approveAccount(String username);
	public void denyAccount(String username);
}
