package com.revature.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.accounts.CustomerAccount;
import com.revature.dao.CustomerDaoImplementation;

@RunWith(JUnitPlatform.class)
@TestInstance(Lifecycle.PER_CLASS)
public class MockCustomerTest {

	@Mock
	CustomerDaoImplementation customerDao;

	@InjectMocks
	CustomerAccount customer;

	@BeforeAll
	public void setup() {
		customerDao = new CustomerDaoImplementation();
		customer = new CustomerAccount("Richard", "Martin", "Kristi", "Martin", "whobosseswho", "BothAreTooStubbornToBeBossed");
		MockitoAnnotations.initMocks(this);
	}
	//tests execute in reverse of the order they appear apparently
	//so 200 -> 150 -> 200
	@Test
	public void testDeposit() {
		customer.setBalance(200);
		Mockito.when(customer.deposit(50)).thenReturn(true);
		
		Assertions.assertEquals(250, customer.getBalance());
	}
	
	@Test
	public void testWithdraw() {
		customer.setBalance(200);
		Mockito.when(customer.withdraw(50)).thenReturn(true);
		
		Assertions.assertEquals(150, customer.getBalance());
	}
	
	@Test
	public void testGetBalance() {
		customer.setBalance(200);
		Assertions.assertEquals(200, customer.getBalance());
	}
	
	
}
