package com.revature.driver;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.accounts.AdminAccount;
import com.revature.accounts.CustomerAccount;
import com.revature.accounts.EmployeeAccount;
import com.revature.dao.DriverDaoImplementation;

public class Driver {

	// menu for logged in customers
	public static void customerMenu(Scanner s, CustomerAccount customer) {
		boolean custMenu = true;
		int custInput = 0;

		System.out.println("Welcome " + customer.getFirstName() + ".");

		// loop through the whole method until logout input is recieved
		while (custMenu) {
			// repeat the customer menu prompt until valid input is recieved
			while (true) {
				System.out.println("Customer Menu");
				System.out.println("-------------------------------");
				System.out.println("1. Deposit");
				System.out.println("2. Withdraw");
				System.out.println("3. Transfer");
				System.out.println("4. View Balance");
				System.out.println("5. Logout");

				try {
					// only accept integers as input
					custInput = Integer.parseInt(s.nextLine());
					if (custInput > 0 && custInput < 6) { // only accept 1-5 as input
						break;
					} else {
						System.out.println("Input an integer between 1 and 5");
					}

				} catch (NumberFormatException e) {
					System.out.println("Input an integer");
				}
			}

			// perform related method for the given input
			switch (custInput) {
			case 1: // deposit
				System.out.println("Enter deposit amount");
				try {
					double deposit = Double.parseDouble(s.nextLine());

					customer.deposit(deposit);
				} catch (NumberFormatException e) {
					System.out.println("Numbers only");
				}
				break;

			case 2: // withdraw
				System.out.println("Enter withdrawl amount");
				try {
					double withdrawl = Double.parseDouble(s.nextLine());

					customer.withdraw(withdrawl);
				} catch (NumberFormatException e) {
					System.out.println("Numbers only");
				}
				break;

			case 3: // transfer
				System.out.println("Enter transfer recipient's username");
				String recipient = s.nextLine();
				System.out.println("Enter transfer amount");

				try {
					double transfer = Double.parseDouble(s.nextLine());

					customer.transfer(transfer, recipient);
				} catch (NumberFormatException e) {
					System.out.println("Numbers only");
				}
				break;

			case 4: // show balance
				System.out.println("Balance: $" + customer.getBalance());
				break;

			case 5: // logout
				custMenu = false;
				break;
			}
		}
	}

	/******************************************************************************************/

	// employee menu
	public static void employeeMenu(Scanner s, EmployeeAccount employee) {
		boolean employeeMenu = true;
		int employeeInput = 0;

		System.out.println("Welcome " + employee.getfName() + ".");
		while (employeeMenu) {

			while (true) {
				System.out.println("Employee Menu");
				System.out.println("-------------------------------");
				System.out.println("1. View all Customers");
				System.out.println("2. View a single Customer");
				System.out.println("3. View all pending customers");
				System.out.println("4. Approve Account");
				System.out.println("5. Deny Account");
				System.out.println("6. Logout");

				try {
					// only accept ints
					employeeInput = Integer.parseInt(s.nextLine());
					if (employeeInput > 0 && employeeInput < 7) { // only accept 1-6 as input
						break;
					} else {
						System.out.println("Input an integer between 1 and 6");
					}

				} catch (NumberFormatException e) {
					System.out.println("Input an integer");
				}
			}

			switch (employeeInput) {

			case 1: // print out all user info

				ArrayList<CustomerAccount> customerList = (ArrayList<CustomerAccount>) employee.getAllInfo();

				for (CustomerAccount c : customerList) {
					System.out.println("Username: " + c.getUsername());
					System.out.println("First Name: " + c.getFirstName());
					System.out.println("Last Name: " + c.getLastName());
					if (c.getFirstName2() != null) { // don't print out secondary account holder's name if it is a
														// single account
						System.out.println("Secondary First Name: " + c.getFirstName2());
						System.out.println("Secondary Last Name: " + c.getLastName2());
					}
					System.out.println("Balance: $" + c.getBalance());
					System.out.println("-------------------------------");
				}

				break;

			case 2: // print out information for a single customer
				System.out.println("Enter the account's username.");
				String username = s.nextLine();
				CustomerAccount c = employee.getUserInfo(username); // get a specific customer based on username
				
				//exit if an invalid username is given
				if (c == null) {
					System.out.println("Invalid username.");
					break;
				}

				System.out.println("Username: " + c.getUsername());
				System.out.println("First Name: " + c.getFirstName());
				System.out.println("Last Name: " + c.getLastName());
				if (c.getFirstName2() != null) {
					System.out.println("Secondary First Name: " + c.getFirstName2());
					System.out.println("Secondary Last Name: " + c.getLastName2());
				}
				System.out.println("Balance: " + c.getBalance());
				System.out.println("-------------------------------");
				break;

			case 3: // get all applications for an account

				ArrayList<CustomerAccount> pendingCustomerList = (ArrayList<CustomerAccount>) employee
						.getAllPendingCustomers();

				for (CustomerAccount p : pendingCustomerList) {
					System.out.println("Username: " + p.getUsername());
					System.out.println("First Name: " + p.getFirstName());
					System.out.println("Last Name: " + p.getLastName());
					if (p.getFirstName2() != null) {
						System.out.println("Secondary First Name: " + p.getFirstName2());
						System.out.println("Secondary Last Name: " + p.getLastName2());
					}
					System.out.println("Balance: $" + p.getBalance());
					System.out.println("-------------------------------");
				}
				break;

			case 4: // approve a application

				System.out.println("Provide the username to be approved");
				String approveUsername = s.nextLine();
				employee.approveAccount(approveUsername);
				break;

			case 5: // deny an application
				System.out.println("Provide the username to be denied");
				String denyUsername = s.nextLine();
				employee.denyAccount(denyUsername);
				break;

			case 6: // logout

				employeeMenu = false;
				break;
			}
		}
	}

	/*******************************************************************************/

	// admin menu
	public static void adminMenu(Scanner s, AdminAccount admin) {

		boolean adminMenu = true;
		int adminInput = 0;
		// welcom message
		System.out.println("Welcome " + admin.getfName() + ".");

		// keep asking for further input until the admin logs out
		while (adminMenu) {
			// ask what action the admin wants to take
			while (true) {
				System.out.println("Employee Menu");
				System.out.println("-------------------------------");
				System.out.println("1.  View all Customers");
				System.out.println("2.  View a single Customer");
				System.out.println("3.  View all pending customers");
				System.out.println("4.  Approve Account");
				System.out.println("5.  Deny Account");
				System.out.println("6.  Withdraw Customer");
				System.out.println("7.  Deposit Customer");
				System.out.println("8.  Transfer Customer");
				System.out.println("9.  Close Account");
				System.out.println("10. Logout");

				try {
					// only accept integers
					adminInput = Integer.parseInt(s.nextLine());
					if (adminInput > 0 && adminInput < 11) { // only accept 1-10 as input
						break;
					} else {
						System.out.println("Input an integer between 1 and 9");
					}

				} catch (NumberFormatException e) {
					System.out.println("Input an integer");
				}
			}

			switch (adminInput) {

			case 1: // print all customers

				ArrayList<CustomerAccount> customerList = (ArrayList<CustomerAccount>) admin.getAllInfo();

				for (CustomerAccount c : customerList) {
					System.out.println("Username: " + c.getUsername());
					System.out.println("First Name: " + c.getFirstName());
					System.out.println("Last Name: " + c.getLastName());
					if (c.getFirstName2() != null) {
						System.out.println("Secondary First Name: " + c.getFirstName2());
						System.out.println("Secondary Last Name: " + c.getLastName2());
					}
					System.out.println("Balance: $" + c.getBalance());
					System.out.println("-------------------------------");
				}
				break;

			case 2: // print a specific customer based on username

				System.out.println("Enter the account's username.");
				String username = s.nextLine();
				CustomerAccount c = admin.getUserInfo(username);
				
				//exit if invalid username is given
				if (c == null) {
					System.out.println("Invalid username");
					break;
				}
				
				System.out.println("Username: " + c.getUsername());
				System.out.println("First Name: " + c.getFirstName());
				System.out.println("Last Name: " + c.getLastName());
				if (c.getFirstName2() != null) {
					System.out.println("Secondary First Name: " + c.getFirstName2());
					System.out.println("Secondary Last Name: " + c.getLastName2());
				}
				System.out.println("Balance: " + c.getBalance());
				System.out.println("-------------------------------");
				break;

			case 3: // print all pending applications

				ArrayList<CustomerAccount> pendingCustomerList = (ArrayList<CustomerAccount>) admin
						.getAllPendingCustomers();

				for (CustomerAccount p : pendingCustomerList) {
					System.out.println("Username: " + p.getUsername());
					System.out.println("First Name: " + p.getFirstName());
					System.out.println("Last Name: " + p.getLastName());
					if (p.getFirstName2() != null) {
						System.out.println("Secondary First Name: " + p.getFirstName2());
						System.out.println("Secondary Last Name: " + p.getLastName2());
					}
					System.out.println("Balance: $" + p.getBalance());
					System.out.println("-------------------------------");
				}
				break;

			case 4: // approve an application

				System.out.println("Provide the username to be approved");
				String approveUsername = s.nextLine();
				admin.approveAccount(approveUsername);
				break;

			case 5: // deny an application

				System.out.println("Provide the username to be denied");
				String denyUsername = s.nextLine();
				admin.denyAccount(denyUsername);
				break;

			case 6: // withdraw from a customer account

				System.out.println("Provide the username of the account to be withdrawn from.");
				String withdrawUsername = s.nextLine();
				System.out.println("Provide the amount to be withdrawn");

				Double withdrawAmount = 0.0;

				try {
					withdrawAmount = Double.parseDouble(s.nextLine());

					admin.withdraw(withdrawUsername, withdrawAmount);
				} catch (NumberFormatException e) {
					System.out.println("must enter a number");
				}

				break;

			case 7: // deposit money to an account

				System.out.println("Provide the username of the account to be deposited to");
				String depositUsername = s.nextLine();
				System.out.println("Provide the amount to be doposited");

				try {
					Double depositAmount = Double.parseDouble(s.nextLine());

					admin.deposit(depositUsername, depositAmount);
				} catch (NumberFormatException e) {
					System.out.println("must enter a number");
				}
				break;

			case 8: // transfer from one account to another

				System.out.println("Provide the username of the account the funds will be taken from");
				String source = s.nextLine();
				System.out.println("Provide the username of the account the funds will be sent to");
				String destination = s.nextLine();
				System.out.println("Provide the amount to be transfered");

				try {
					Double transferAmount = Double.parseDouble(s.nextLine());

					admin.transfer(source, destination, transferAmount);
				} catch (NumberFormatException e) {
					System.out.println("Must enter a number");
				}

				break;

			case 9: // close an account
				System.out.println("Provide the username of the account that needs to be closed");
				String closeUsername = s.nextLine();

				admin.closeAccount(closeUsername);

				break;

			case 10: // logout
				adminMenu = false;
				break;

			}
		}
	}

	/*******************************************************************************/

	// main
	public static void main(String[] args) {

		Logger logger = LogManager.getLogger(Driver.class);
		DriverDaoImplementation driverDao = new DriverDaoImplementation();
		Scanner s = new Scanner(System.in);

		String loginUsername = "";
		String loginPassword = "";
		int loginInput = 0;
		String appFirstName = "";
		String appLastName = "";
		String appFirstName2 = "";
		String appLastName2 = "";
		String appUsername = "";
		String appPassword = "";

		boolean run = true;
		// loop until exit option is input
		while (run) {
			// initial login menu
			while (true) {

				System.out.println("Login");
				System.out.println("----------------------------------");
				System.out.println("1. Customer Login");
				System.out.println("2. Employee Login");
				System.out.println("3. Admin Login");
				System.out.println("4. Customer Account Application");
				System.out.println("5. Customer Joint Account Application");
				System.out.println("6. Exit");

				try {
					// only accept integers between 1 and 6
					loginInput = Integer.parseInt(s.nextLine());
					if (loginInput > 0 && loginInput < 7) {
						break;
					} else {
						System.out.println("Input an integer between 1 and 6");
					}

				} catch (NumberFormatException e) {
					System.out.println("Input an integer");
				}
			}

			if (loginInput == 6) {
				run = false;
				break;
			}

			// choose action based on input
			switch (loginInput) {
			case 1: // login to a customer account

				CustomerAccount customer = null;

				do { // ask for a username and password until a valid combination is given or user
						// quits
					System.out.println("Enter quit to return to the main menu.");
					System.out.print("Username: ");
					loginUsername = s.nextLine();
					System.out.println();

					if (loginUsername.equalsIgnoreCase("quit")) {
						break;
					}

					System.out.print("Password: ");
					loginPassword = s.nextLine();
					System.out.println();

					if (loginPassword.equalsIgnoreCase("quit")) {
						break;
					}

					customer = driverDao.loginCustomer(loginUsername, loginPassword);
					if (customer == null) {
						System.out.println("Invalid username or password");
					}
				} while (customer == null);

				// return to login menu if quit was entered
				if (loginUsername.equalsIgnoreCase("quit") || loginPassword.equalsIgnoreCase("quit")) {
					break;
				}
				// proceed to the customer menu, logging when the customer logs in and logs out
				logger.info("Logged in customer: " + loginUsername);
				customerMenu(s, customer);
				logger.info("Logged out customer: " + loginUsername);
				break;

			case 2:
				// Employee Login
				EmployeeAccount employee = null;

				do {
					System.out.println("Enter quit to return to the main menu.");
					System.out.print("Username: ");
					loginUsername = s.nextLine();
					System.out.println();

					if (loginUsername.equalsIgnoreCase("quit")) {
						break;
					}

					System.out.print("Password: ");
					loginPassword = s.nextLine();
					System.out.println();

					if (loginPassword.equalsIgnoreCase("quit")) {
						break;
					}

					employee = driverDao.loginEmployee(loginUsername, loginPassword);
					if (employee == null) {
						System.out.println("Invalid username or password");
					}
				} while (employee == null);

				// return to login menu if the user entered quit
				if (loginUsername.equalsIgnoreCase("quit") || loginPassword.equalsIgnoreCase("quit")) {
					break;
				}
				// proceed to the employee menu, logging when the user logs in and logs out
				logger.info("Logged in employee: " + loginUsername);
				employeeMenu(s, employee);
				logger.info("Logged out employee: " + loginUsername);
				break;

			case 3:
				// login admin account
				AdminAccount admin = null;

				do {
					System.out.println("Enter quit to return to the main menu.");
					System.out.print("Username: ");
					loginUsername = s.nextLine();
					System.out.println();

					if (loginUsername.equalsIgnoreCase("quit")) {
						break;
					}

					System.out.print("Password: ");
					loginPassword = s.nextLine();
					System.out.println();

					if (loginPassword.equalsIgnoreCase("quit")) {
						break;
					}

					admin = driverDao.loginAdmin(loginUsername, loginPassword);
					if (admin == null) {
						System.out.println("Invalid username or password");
					}
				} while (admin == null);

				// return to main menu if quit was entered
				if (loginUsername.equalsIgnoreCase("quit") || loginPassword.equalsIgnoreCase("quit")) {
					break;
				}

				// proceed to admind menu, logging when the user logs in and logs out
				logger.info("Logged in admin: " + loginUsername);
				adminMenu(s, admin);
				logger.info("Logged out admin: " + loginUsername);

				break;

			case 4:
				// Apply for single account
				do {// first name
					System.out.print("Enter First Name: ");
					appFirstName = s.nextLine();
					System.out.println();

					// quit
					if (appFirstName.equalsIgnoreCase("quit")) {
						break;
					}

					if (appFirstName.length() < 1) {
						System.out.println("Name must have at least 1 character");
					}
				} while (appFirstName.length() < 1);

				// quit
				if (appFirstName.equalsIgnoreCase("quit")) {
					break;
				}

				do { // last name
					System.out.print("Enter Last Name: ");
					appLastName = s.nextLine();
					System.out.println();

					// quit
					if (appLastName.equalsIgnoreCase("quit")) {
						break;
					}

					if (appLastName.length() < 1) {
						System.out.println("Last name must be at least 1 character");
					}
				} while (appLastName.length() < 1);

				// quit
				if (appLastName.equalsIgnoreCase("quit")) {
					break;
				}

				do { // username
					System.out.print("Enter Desired Username: ");
					appUsername = s.nextLine();
					System.out.println();

					// quit
					if (appUsername.equalsIgnoreCase("quit")) {
						break;
					}

					if (appUsername.length() < 1) {
						System.out.println("Username must be at least 1 character long");
					}

				} while (appUsername.length() < 1);

				// quit
				if (appUsername.equalsIgnoreCase("quit")) {
					break;
				}

				do { // password
					System.out.println("Passwords must have at least 8 characters");
					System.out.println("Enter Desired Password: ");
					appPassword = s.nextLine();

					// quit
					if (appPassword.equalsIgnoreCase("quit")) {
						break;
					}

					System.out.println();
				} while (appPassword.length() < 8);

				// quit
				if (appPassword.equalsIgnoreCase("quit")) {
					break;
				}
				
				//check if the username is already in use (checks admins and employees too)
				if(driverDao.checkApplication(appUsername)) {
					System.out.println("Username already in use.");
					break;
				}
				
				// log and store the application
				logger.info(appFirstName + " " + appLastName + " applied for an account");
				driverDao.apply(appFirstName, appLastName, null, null, appUsername, appPassword);
				break;

			case 5: // apply for a joint account

				do { // primary first name
					System.out.print("Enter Primary First Name: ");
					appFirstName = s.nextLine();
					System.out.println();

					// quit
					if (appFirstName.equalsIgnoreCase("quit")) {
						break;
					}

					if (appFirstName.length() < 1) {
						System.out.println("Name must have at least 1 character");
					}
				} while (appFirstName.length() < 1);

				// quit
				if (appFirstName.equalsIgnoreCase("quit")) {
					break;
				}

				do { // primary last name
					System.out.print("Enter Primary Last Name: ");
					appLastName = s.nextLine();
					System.out.println();

					// quit
					if (appLastName.equalsIgnoreCase("quit")) {
						break;
					}

					if (appLastName.length() < 1) {
						System.out.println("Last name must be at least 1 character");
					}
				} while (appLastName.length() < 1);

				// quit
				if (appLastName.equalsIgnoreCase("quit")) {
					break;
				}

				do { // secondary first name
					System.out.print("Enter Secondary First Name: ");
					appFirstName2 = s.nextLine();
					System.out.println();

					// quit
					if (appFirstName2.equalsIgnoreCase("quit")) {
						break;
					}

					if (appFirstName2.length() < 1) {
						System.out.println("Name must have at least 1 character");
					}
				} while (appFirstName2.length() < 1);

				// quit
				if (appFirstName2.equalsIgnoreCase("quit")) {
					break;
				}

				do { // secondary last name
					System.out.print("Enter Secondary Last Name: ");
					appLastName2 = s.nextLine();
					System.out.println();

					// quit
					if (appLastName2.equalsIgnoreCase("quit")) {
						break;
					}

					if (appLastName2.length() < 1) {
						System.out.println("Last name must be at least 1 character");
					}
				} while (appLastName2.length() < 1);

				// quit
				if (appLastName2.equalsIgnoreCase("quit")) {
					break;
				}

				do { // username
					System.out.print("Enter Desired Username: ");
					appUsername = s.nextLine();
					System.out.println();

					// quit
					if (appUsername.equalsIgnoreCase("quit")) {
						break;
					}

					if (appUsername.length() < 1) {
						System.out.println("Username must be at least 1 character long");
					}
				} while (appUsername.length() < 1);

				// quit
				if (appUsername.equalsIgnoreCase("quit")) {
					break;
				}

				do { // password
					System.out.println("Passwords must have at least 8 characters");
					System.out.println("Enter Desired Password: ");
					appPassword = s.nextLine();
					System.out.println();

					// quit
					if (appPassword.equalsIgnoreCase("quit")) {
						break;
					}

				} while (appPassword.length() < 8);

				// quit
				if (appPassword.equalsIgnoreCase("quit")) {
					break;
				}

				// log and store the application
				logger.info(appFirstName + " " + appLastName + " and " + appFirstName2 + " " + appLastName2
						+ " applied for a joint account");
				driverDao.apply(appFirstName, appLastName, appFirstName2, appLastName2, appUsername, appPassword);
				break;

			default: // in case they somehow get past the main menu's controls
				System.out.println("Invalid.");
				break;
			}
		}
	}

}
