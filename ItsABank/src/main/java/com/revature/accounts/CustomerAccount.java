package com.revature.accounts;

import com.revature.dao.CustomerDaoImplementation;

public class CustomerAccount {
	private String firstName = "";
	private String lastName = "";
	private String firstName2;
	private String lastName2;
	private String username = "";
	private String password = "";
	private double balance = 0.0;

	public CustomerAccount(String fName, String lName, String uName, String pWord) {
		firstName = fName;
		lastName = lName;
		username = uName;
		password = pWord;
		balance = 0.0;
		firstName2 = null;
		lastName2 = null;
	}

	public CustomerAccount(String fName, String lName, String fName2, String lName2, String uName, String pWord) {
		firstName = fName;
		lastName = lName;
		firstName2 = fName2;
		lastName2 = lName2;
		username = uName;
		password = pWord;
		balance = 0.0;
	}

	public CustomerAccount() {

	}

	//dao object for database communication
	private CustomerDaoImplementation dao = new CustomerDaoImplementation();

	// add money to the account
	public boolean deposit(double deposit) {
		if (deposit > 0) {
			balance += deposit;
			dao.deposit(deposit, username);
			return true;
		} else {
			System.out.println("Cannot deposit negative amounts");
			return false;
		}
	}

	// remove money from account
	public boolean withdraw(double withdraw) {
		if (withdraw > balance) {
			System.out.println("Withdrawl amount exceeds balance");
			return false;
		} else {
			balance -= withdraw;
			dao.withdraw(withdraw, username);
			return true;
		}
	}

	// transfer money from one account to another
	public boolean transfer(double transfer, String recipient) {
		if (transfer > balance || transfer < 0) {
			System.out.println("Insufficient funds");
			return false;
		} else {
			// balance -= transfer is only updating the current customer object
			// no need to update the recipient here since they don't have an active object
			if (dao.getCustomer(recipient) != null) { //only execute if the recipient exists
				balance -= transfer;
				dao.withdraw(transfer, username);
				dao.deposit(transfer, recipient);
				return true;
			}
			else {
				System.out.println("Invalid username");
				return false;
			}
		}
	}

	/********************************************************/
	
	//getters and setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getFirstName2() {
		return firstName2;
	}

	public void setFirstName2(String firstName2) {
		this.firstName2 = firstName2;
	}

	public String getLastName2() {
		return lastName2;
	}

	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
	}
}
