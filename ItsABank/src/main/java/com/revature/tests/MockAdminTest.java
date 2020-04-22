package com.revature.tests;

import java.util.Arrays;
import java.util.List;

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

import com.revature.accounts.AdminAccount;
import com.revature.accounts.CustomerAccount;
import com.revature.dao.AdminDaoImplementation;

@RunWith(JUnitPlatform.class)
@TestInstance(Lifecycle.PER_CLASS)
public class MockAdminTest {
	@Mock
	AdminDaoImplementation adminDao;
	
	@InjectMocks
	AdminAccount adminAccount;
	
	@BeforeAll
	public void setup() {
		adminDao = new AdminDaoImplementation();
		adminAccount = new AdminAccount("Kid", "Cash", "cashmoney", "singasongofsixpence");
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllInfo() {
		List<CustomerAccount> mockCustomers = Arrays.asList(
				new CustomerAccount("Cody", "Martin", null, null, "literallyme", "123456789"),
				new CustomerAccount("Richard", "Martin", "Kristi", "Martin", "whobosseswho", "BothAreTooStubbornToBeBossed"));
		
		Mockito.when(adminAccount.getAllInfo()).thenReturn(mockCustomers);
		
		Assertions.assertEquals("Richard", mockCustomers.get(1).getFirstName());
	}
	
	@Test
	public void testGetUserInfo() {
		CustomerAccount mockCustomer = new CustomerAccount("Cody", "Martin", null, null, "literallyme", "123456789");
		
		Mockito.when(adminAccount.getUserInfo("literallyme")).thenReturn(mockCustomer);
		
		Assertions.assertEquals("Cody", mockCustomer.getFirstName());
	}
	
	@Test
	public void testGetPendingUsers() {
		List<CustomerAccount> mockPending = Arrays.asList(
				new CustomerAccount("Cody", "Martin", null, null, "literallyme", "123456789"),
				new CustomerAccount("Richard", "Martin", "Kristi", "Martin", "whobosseswho", "BothAreTooStubbornToBeBossed"));
		
		Mockito.when(adminAccount.getAllPendingCustomers()).thenReturn(mockPending);
		
		Assertions.assertEquals("whobosseswho", mockPending.get(1).getUsername());
	}
	
	@Test
	public void testGetFName() {
		Assertions.assertEquals("Kid", adminAccount.getfName());
	}
	
	@Test
	public void testGetLName() {
		Assertions.assertEquals("Cash", adminAccount.getlName());
	}
	
	@Test
	public void testGetUsername() {
		Assertions.assertEquals("cashmoney", adminAccount.getUsername());
	}
	
	@Test
	public void testGetPassword() {
		Assertions.assertEquals("singasongofsixpence", adminAccount.getPassword());
	}
}
