package com.revature.tests;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.revature.utilities.ConnectionFactory;

class ClosedConnectionTest {

	@Test
	void test() {
		Connection connection = ConnectionFactory.getConnection();
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		connection = ConnectionFactory.getConnection();
		
		try {
			Assertions.assertEquals(connection.isClosed(), false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
