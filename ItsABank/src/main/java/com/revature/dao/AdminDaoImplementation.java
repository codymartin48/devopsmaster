package com.revature.dao;

import java.sql.SQLException;
import org.apache.logging.log4j.*;

import com.revature.accounts.CustomerAccount;
import com.revature.utilities.ConnectionFactory;

public class AdminDaoImplementation extends EmployeeDaoImplementation implements AdminDao {

	private static final Logger logger = LogManager.getLogger(AdminDaoImplementation.class);
	
	
	//remove an account from the customers table
	public void closeAccount(String username) {
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM customers WHERE username = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, username);
			int n = stmt.executeUpdate();
			
			if(n != 0) {
				logger.info("Closed account: " +  username);
			}
			else {
				logger.info("Could not find account: " + username);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			closeResources();
		}
		
	}

	//withdraw funds from a customer's account
	public void withdraw(double amount, String username) {
		CustomerAccount customer = getCustomer(username);
		
		//withdraw returns true if the withdraw is successful.
		if(customer.withdraw(amount)) {
			try {
				connection = ConnectionFactory.getConnection();
				String sql = "UPDATE customers SET balance = ? WHERE username = ?";
				stmt = connection.prepareStatement(sql);
				
				//balance has already been updated in the if statement
				stmt.setDouble(1, customer.getBalance());
				stmt.setString(2, username);
				
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				closeResources();
			}
		}
	}
	
	//deposit funds into a customer's account
	public void deposit(double amount, String username) {
		CustomerAccount customer = getCustomer(username);
		
		//deposit returns true if the withdraw is successful.
		if(customer.deposit(amount)) {
			try {
				connection = ConnectionFactory.getConnection();
				String sql = "UPDATE customers SET balance = ? WHERE username = ?";
				stmt = connection.prepareStatement(sql);
				
				//balance has already been updated in the if statement
				stmt.setDouble(1, customer.getBalance());
				stmt.setString(2, username);
				
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				closeResources();
			}
		}
		
	}

	//transfer funds between two customers
	public void transfer(double amount, String source, String recipient) {
		
		CustomerAccount customerS = getCustomer(source);
		CustomerAccount customerR = getCustomer(recipient);
		
		//update the database if the transfer is successful
		if(customerS.transfer(amount, customerR.getUsername())) {
			try {
				connection = ConnectionFactory.getConnection();
				String sql = "UPDATE customers SET balance = ? WHERE username = ?";
				stmt = connection.prepareStatement(sql);
				
				//balance has already been updated in the if statement
				stmt.setDouble(1, customerR.getBalance());
				stmt.setString(2, customerR.getUsername());
				
				stmt.executeUpdate();
				
				sql = "UPDATE customers SET balance = ? WHERE username = ?";
				stmt = connection.prepareStatement(sql);
				
				stmt.setDouble(1, customerS.getBalance());
				stmt.setString(2, customerS.getUsername());
				
				stmt.executeUpdate();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				closeResources();
			}
		}
	}

}
