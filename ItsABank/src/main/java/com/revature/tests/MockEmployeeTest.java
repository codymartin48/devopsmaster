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

import com.revature.accounts.CustomerAccount;
import com.revature.accounts.EmployeeAccount;
import com.revature.dao.EmployeeDaoImplementation;


@RunWith(JUnitPlatform.class)
@TestInstance(Lifecycle.PER_CLASS)
public class MockEmployeeTest {
	
	@Mock
	EmployeeDaoImplementation employeeDao;
	
	@InjectMocks
	EmployeeAccount employeeAccount;
	
	@BeforeAll
	public void setup() {
		employeeDao = new EmployeeDaoImplementation();
		employeeAccount = new EmployeeAccount("Baldanders", "Latro", "thesoonanother", "soldierofarete");
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllInfo() {
		List<CustomerAccount> mockCustomers = Arrays.asList(
				new CustomerAccount("Cody", "Martin", null, null, "literallyme", "123456789"),
				new CustomerAccount("Richard", "Martin", "Kristi", "Martin", "whobosseswho", "BothAreTooStubbornToBeBossed"));
		
		Mockito.when(employeeAccount.getAllInfo()).thenReturn(mockCustomers);
		
		Assertions.assertEquals("Richard", mockCustomers.get(1).getFirstName());
	}
	
	@Test
	public void testGetUserInfo() {
		CustomerAccount mockCustomer = new CustomerAccount("Cody", "Martin", null, null, "literallyme", "123456789");
		
		Mockito.when(employeeAccount.getUserInfo("literallyme")).thenReturn(mockCustomer);
		
		Assertions.assertEquals("Cody", mockCustomer.getFirstName());
	}
	
	@Test
	public void testGetPendingUsers() {
		List<CustomerAccount> mockPending = Arrays.asList(
				new CustomerAccount("Cody", "Martin", null, null, "literallyme", "123456789"),
				new CustomerAccount("Richard", "Martin", "Kristi", "Martin", "whobosseswho", "BothAreTooStubbornToBeBossed"));
		
		Mockito.when(employeeAccount.getAllPendingCustomers()).thenReturn(mockPending);
		Assertions.assertEquals("whobosseswho", mockPending.get(1).getUsername());
	}
	
	@Test
	public void testGetFName() {
		Assertions.assertEquals("Baldanders", employeeAccount.getfName());
	}
	
	@Test
	public void testGetLName() {
		Assertions.assertEquals("Latro", employeeAccount.getlName());
	}
	
	@Test
	public void testGetUsername() {
		Assertions.assertEquals("thesoonanother", employeeAccount.getUsername());
	}
	
	@Test
	public void testGetPassword() {
		Assertions.assertEquals("soldierofarete", employeeAccount.getPassword());
	}
	
}
