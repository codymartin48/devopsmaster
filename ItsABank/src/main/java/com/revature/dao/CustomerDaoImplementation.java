package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.*;

import com.revature.accounts.CustomerAccount;
import com.revature.utilities.ConnectionFactory;

public class CustomerDaoImplementation implements CustomerDao {

	private static final Logger logger = LogManager.getLogger(CustomerDaoImplementation.class);

	PreparedStatement stmt = null;
	Connection connection = null;

	// deposit money into the account
	public boolean deposit(double balance, String username) {

		CustomerAccount customer = getCustomer(username);

			try {
				connection = ConnectionFactory.getConnection();
				String sql = "UPDATE customers SET balance = ? WHERE username = ?";
				stmt = connection.prepareStatement(sql);

				// create the update
				stmt.setDouble(1, customer.getBalance() + balance); // this will update the balance in the database
				stmt.setString(2, username);

				// log whether the update succeeded or failed
				if (stmt.executeUpdate() != 0) {
					logger.info(customer.getUsername() + " balance updated: $" + customer.getBalance() + "-> $"
							+ (customer.getBalance() + balance));
					return true;
				} else {
					logger.info("Update failed");
					return false;
				}
				// log the exception
			} catch (SQLException e) {
				logger.error("Deposit failed. SQLException thrown.");
				e.printStackTrace();
				return false;
			} finally {
				closeResources();
			}
	}

	public boolean withdraw(double balance, String username) {

		CustomerAccount customer = getCustomer(username);
		// withdraw returns true if the withdraw is successful.
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "UPDATE customers SET balance = ? WHERE username = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setDouble(1, customer.getBalance() - balance);
			stmt.setString(2, username);

			// log the balance change
			if (stmt.executeUpdate() != 0) {
				logger.info(customer.getUsername() + " balance updated: $" + customer.getBalance() + "-> $"
						+ (customer.getBalance() - balance));
				return true;
			} else {
				logger.info("Withdraw Failed");
				return false;
			}
			// log the exception
		} catch (SQLException e) {
			logger.error("Withdraw failed. SQLException thrown.");
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	// close stmt and connection
	protected void closeResources() {

		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			logger.error("Failed to close stmt");
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}

		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			logger.error("Failed to close connection");
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}

	}
	
	//get a customer by username for transfers
	public CustomerAccount getCustomer(String username) {

		CustomerAccount customer = new CustomerAccount();

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "Select * FROM customers WHERE username = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, username);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				customer.setBalance(rs.getDouble("balance"));
				customer.setFirstName(rs.getString("firstName"));
				customer.setLastName(rs.getString("lastName"));
				customer.setFirstName2(rs.getString("firstName2"));
				customer.setLastName2(rs.getString("lastName2"));
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("password"));
				rs.close();
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return customer;
	}

}
