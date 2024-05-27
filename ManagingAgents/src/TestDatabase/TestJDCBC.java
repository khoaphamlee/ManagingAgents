package TestDatabase;

import java.sql.Connection;

import Database.JDBCUtil;

public class TestJDCBC {
	public static void main(String[] args) {
		Connection connection = JDBCUtil.getConnection();
		System.out.println(connection);
	}
}
