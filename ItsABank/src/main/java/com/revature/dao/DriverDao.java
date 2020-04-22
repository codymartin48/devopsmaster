package com.revature.dao;

import com.revature.accounts.AdminAccount;
import com.revature.accounts.CustomerAccount;
import com.revature.accounts.EmployeeAccount;

public interface DriverDao {
	public CustomerAccount loginCustomer(String username, String password);	
	public EmployeeAccount loginEmployee(String username, String password);
	public AdminAccount loginAdmin(String username, String password);
	public void apply(String firstName, String lastName, String firstName2, String lastName2, String username, String password);
	public boolean checkApplication (String username);
}
