package Database;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import com.mysql.cj.jdbc.Driver;

public class JDBCUtil {
	public static Connection getConnection() {
		Connection c = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String url = "jdbc:mySQL://localhost:3306/qldl";
			String username = "root";
			String password = "123456";
			
			c = DriverManager.getConnection(url, username, password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
	
	public static void closeConnection(Connection c) {
		try {
			if (c != null) {
				c.close();
			}
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
