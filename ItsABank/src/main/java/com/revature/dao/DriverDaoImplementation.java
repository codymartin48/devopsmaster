package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.accounts.AdminAccount;
import com.revature.accounts.CustomerAccount;
import com.revature.accounts.EmployeeAccount;
import com.revature.utilities.ConnectionFactory;

public class DriverDaoImplementation implements DriverDao {

	Connection connection = null;
	PreparedStatement stmt = null;

	//attempt to login to a customer account
	public CustomerAccount loginCustomer(String username, String password) {

		CustomerAccount customer = new CustomerAccount();

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "Select * FROM customers where username = ? AND password = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, username);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();
			
			//if no matching username and password is found do not return a customer
			if (!rs.next()) {
				return null;
			}

			customer.setFirstName(rs.getString("firstname"));
			customer.setLastName(rs.getString("lastname"));
			customer.setFirstName2(rs.getString("firstname2"));
			customer.setLastName2(rs.getString("lastname2"));
			customer.setUsername(rs.getString("username"));
			customer.setPassword(rs.getString("password"));
			customer.setBalance(rs.getDouble("balance"));

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return customer;
	}

	//attempt to login to an employee account
	public EmployeeAccount loginEmployee(String username, String password) {

		EmployeeAccount employee = new EmployeeAccount();

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "Select * FROM employees where username = ? AND password = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, username);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();
	
			//return null if no matching username and password is found
			if (!rs.next()) {
				return null;
			}

			employee.setfName(rs.getString("firstname"));
			employee.setlName(rs.getString("lastname"));
			employee.setUsername(rs.getString("username"));
			employee.setPassword(rs.getString("password"));

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return employee;
	}

	//attempt to login to an admin account
	public AdminAccount loginAdmin(String username, String password) {

		AdminAccount admin = new AdminAccount();

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "Select * FROM admins where username = ? AND password = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, username);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();

			//return null if no match is found in the database
			if (!rs.next()) {
				return null;
			}

			admin.setfName(rs.getString("firstname"));
			admin.setlName(rs.getString("lastname"));
			admin.setUsername(rs.getString("username"));
			admin.setPassword(rs.getString("password"));

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return admin;
	}

	//apply for a customer account
	public void apply(String firstName, String lastName, String firstName2, String lastName2, String username,
			String password) {

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO pendingCustomers VALUES (?, ?, ?, ?, ?, ?)";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, firstName2);
			stmt.setString(4, lastName2);
			stmt.setString(5, username);
			stmt.setString(6, password);

			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	//checking if an application is valid
	public boolean checkApplication(String username) {
		
		//check if the username is present in any of the account tables
		
		try { //check admins
			connection = ConnectionFactory.getConnection();
			String sql = "Select * FROM admins where username = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		try { //check customers
			connection = ConnectionFactory.getConnection();
			String sql = "Select * FROM customers where username = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		try { //check pendingCustomers
			connection = ConnectionFactory.getConnection();
			String sql = "Select * FROM pendingCustomers where username = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		try { //check employees
			connection = ConnectionFactory.getConnection();
			String sql = "Select * FROM employees where username = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			else { //only return false if it appears in none of the tables
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
