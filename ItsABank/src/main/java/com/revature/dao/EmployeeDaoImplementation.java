package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.accounts.CustomerAccount;
import com.revature.utilities.ConnectionFactory;

public class EmployeeDaoImplementation implements EmployeeDao {

	private static Logger logger = LogManager.getLogger(EmployeeDaoImplementation.class);

	Connection connection = null;
	PreparedStatement stmt = null;

	// get all customers from the database in a list
	public List<CustomerAccount> getAllCustomers() {
		List<CustomerAccount> customers = new ArrayList<CustomerAccount>();

		try {
			connection = ConnectionFactory.getConnection(); // open connection
			String sql = "Select * FROM customers"; // create query
			stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(); // place the results of the executed query into the result set

			// add all results into the customers list
			while (rs.next()) {
				CustomerAccount customer = new CustomerAccount();

				customer.setBalance(rs.getDouble("balance"));
				customer.setFirstName(rs.getString("firstName"));
				customer.setLastName(rs.getString("lastName"));
				customer.setFirstName2(rs.getString("firstName2"));
				customer.setLastName2(rs.getString("lastName2"));
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("password"));

				customers.add(customer);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}

		// return results
		return customers;
	}

	// get a specific customer
	public CustomerAccount getCustomer(String username) {

		CustomerAccount customer = new CustomerAccount();

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "Select * FROM customers WHERE username = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, username);

			ResultSet rs = stmt.executeQuery();

			// if there's no next then the query failed
			if (!rs.next()) {
				return null;
			}

			customer.setBalance(rs.getDouble("balance"));
			customer.setFirstName(rs.getString("firstName"));
			customer.setLastName(rs.getString("lastName"));
			customer.setFirstName2(rs.getString("firstName2"));
			customer.setLastName2(rs.getString("lastName2"));
			customer.setUsername(rs.getString("username"));
			customer.setPassword(rs.getString("password"));

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return customer;
	}

	// add normal customer to database
	public boolean addCustomer(CustomerAccount customer) {

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO customers VALUES (?, ?, ?, ?, ?, ?, ?)";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLastName());
			stmt.setString(3, customer.getFirstName2());
			stmt.setString(4, customer.getLastName2());
			stmt.setString(5, customer.getUsername());
			stmt.setString(6, customer.getPassword());
			stmt.setDouble(7, customer.getBalance());

			if (stmt.executeUpdate() != 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	//close the connection to prevent memory leaks
	protected void closeResources() {

		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}

		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}

	}

	// approve an account
	public boolean approveAccount(String username) {
		CustomerAccount customer = new CustomerAccount();

		try { //retrieve the account from the pendingCustomers table
			connection = ConnectionFactory.getConnection();
			String sql = "Select * from pendingCustomers WHERE username = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				customer.setFirstName(rs.getString("firstName"));
				customer.setLastName(rs.getString("lastName"));
				customer.setFirstName2(rs.getString("firstName2"));
				customer.setLastName2(rs.getString("lastName2"));
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("password"));
			} else {
				System.out.println("Invalid Username");
				return false;
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		//delete the retrieved value from the pending table
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "Delete from pendingCustomers WHERE username = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, username);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//insert the account into the customers table
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO customers VALUES (?, ?, ?, ?, ?, ?, ?)";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, customer.getFirstName());
			stmt.setString(2, customer.getLastName());
			stmt.setString(3, customer.getFirstName2());
			stmt.setString(4, customer.getLastName2());
			stmt.setString(5, customer.getUsername());
			stmt.setString(6, customer.getPassword());
			stmt.setDouble(7, 0);

			if (stmt.executeUpdate() != 0) {
				logger.info("Approved account: " + customer.getUsername());
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	//deny an account application
	public void denyAccount(String username) {

		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM pendingCustomers WHERE username = ?";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, username);
			int n = stmt.executeUpdate();

			if (n == 1) {
				logger.info("Denied account: " + username);
			} else {
				System.out.println("No username " + username + " found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}

	}
	
	//get a list of all pending customers
	public List<CustomerAccount> getAllPendingCustomers() {

		List<CustomerAccount> customers = new ArrayList<CustomerAccount>();

		try {
			connection = ConnectionFactory.getConnection(); // open connection
			String sql = "Select * FROM pendingCustomers"; // create query
			stmt = connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery(); // place the results of the executed query into the result set

			// add all results into the customers list
			while (rs.next()) {
				CustomerAccount customer = new CustomerAccount();

				customer.setFirstName(rs.getString("firstName"));
				customer.setLastName(rs.getString("lastName"));
				customer.setFirstName2(rs.getString("firstName2"));
				customer.setLastName2(rs.getString("lastName2"));
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("password"));

				customers.add(customer);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}

		// return results
		return customers;
	}

}
