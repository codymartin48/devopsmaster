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

import com.revature.dao.DriverDaoImplementation;
import com.revature.driver.Driver;

@RunWith(JUnitPlatform.class)
@TestInstance(Lifecycle.PER_CLASS)
public class MockDriverTest {
	@Mock
	DriverDaoImplementation driverDao;
	
	@InjectMocks
	Driver driver;
	
	@BeforeAll
	public void setup() {
		driverDao = new DriverDaoImplementation();
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void checkApplicationTest() {
		
		Mockito.when(driverDao.checkApplication("tiddlywinks")).thenReturn(false);
		
		Assertions.assertEquals(false, driverDao.checkApplication("tiddlywinks"));
	}
}
