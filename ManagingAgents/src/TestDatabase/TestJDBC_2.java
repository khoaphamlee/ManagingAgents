package TestDatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Database.JDBCUtil_2;

public class TestJDBC_2 {
	public static void main(String[] args) {
		try {
			Connection connection = JDBCUtil_2.getConnection();
			
			Statement st = connection.createStatement();
			String sql = "INSERT INTO AGENT(Agent_Id, Agent_Name, Agent_Phone, Agent_Address, Agent_District, Agent_Debt)\r\n"
					+ "VALUES"
					+ "(22520601, \"Vo Duc Kha\", 0834353033,\"Ho Chi Minh\", \"Linh Trung\",2000000)";
			
			int check = st.executeUpdate(sql);
			
			System.out.println("So dong thay doi " + check);
			if (check > 0) {
				System.out.println("Success");
			}else {
				System.out.println("Fail");
			}
			
			JDBCUtil_2.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
