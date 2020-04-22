package com.revature.tests;

import java.sql.Connection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.revature.utilities.ConnectionFactory;

class ConnectionTest {

	@Test
	void test() {
		Connection connection = ConnectionFactory.getConnection();
		
		Assertions.assertEquals(connection.equals(null), false);
	}

}
