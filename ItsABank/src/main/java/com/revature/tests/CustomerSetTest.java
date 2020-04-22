package com.revature.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.revature.accounts.CustomerAccount;

@RunWith(JUnitPlatform.class)
@TestInstance(Lifecycle.PER_CLASS)
class CustomerSetTest {

	CustomerAccount customer;
	
	@BeforeAll
	public void setup() {
		customer = new CustomerAccount();
	}
	
	@Test
	public void testGetFirstName() {
		customer.setFirstName("Richard");
		
		Assertions.assertEquals("Richard", customer.getFirstName());
	}
	
	@Test
	public void testGetLastName() {
		customer.setLastName("Martin");
		
		Assertions.assertEquals("Martin", customer.getLastName());
	}
	
	@Test
	public void testGetFirstName2() {
		customer.setFirstName2("Kristi");
		
		Assertions.assertEquals("Kristi", customer.getFirstName2());
	}
	
	@Test
	public void testGetLastName2() {
		customer.setLastName2("Martin");
		
		Assertions.assertEquals("Martin", customer.getLastName2());
	}
	
	@Test
	public void testGetUsername() {
		customer.setUsername("whobosseswho");
		
		Assertions.assertEquals("whobosseswho", customer.getUsername());
	}
	
	@Test
	public void testGetPassword() {
		customer.setPassword("BothAreTooStubbornToBeBossed");
		
		Assertions.assertEquals("BothAreTooStubbornToBeBossed", customer.getPassword());
	}
	
	@Test
	public void testBalance() {
		customer.setBalance(200);
		
		Assertions.assertEquals(200, customer.getBalance());
	}
}
