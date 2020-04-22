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
class CustomerGetTest {
	
	CustomerAccount customer;
	
	@BeforeAll
	public void setup() {
		customer = new CustomerAccount("Richard", "Martin", "Kristi", "Martin", "whobosseswho", "BothAreTooStubbornToBeBossed");
	}

	@Test
	public void testGetFirstName() {
		Assertions.assertEquals("Richard", customer.getFirstName());
	}
	
	@Test
	public void testGetLastName() {
		Assertions.assertEquals("Martin", customer.getLastName());
	}
	
	@Test
	public void testGetFirstName2() {
		Assertions.assertEquals("Kristi", customer.getFirstName2());
	}
	
	@Test
	public void testGetLastName2() {
		Assertions.assertEquals("Martin", customer.getLastName2());
	}
	
	@Test
	public void testGetUsername() {
		Assertions.assertEquals("whobosseswho", customer.getUsername());
	}
	
	@Test
	public void testGetPassword() {
		Assertions.assertEquals("BothAreTooStubbornToBeBossed", customer.getPassword());
	}
	
}
