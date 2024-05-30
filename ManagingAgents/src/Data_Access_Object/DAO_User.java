package Data_Access_Object;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import Database.JDBCUtil;

import models.User;

public class DAO_User implements Interface<User> {
	public static DAO_User getInstance() {
		return new DAO_User();
	}
	
	@Override
	public int Add(User t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "INSERT INTO USER(User_Id, IdRole, UserName, Password, User_Status, User_Status)"
					+ "VALUES (" + t.getUser_Id() + " , " 
					+ t.getIdRole() + " , '" 
					+ t.getUserName() + "' , '" 
					+ t.getPassword() + "' , '" 
					+ t.getUser_Status() + "')";
			int kq = st.executeUpdate(sql);
			
			System.out.println("Bạn đã thực thi");
			JDBCUtil.closeConnection(connect);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Update(User t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "UPDATE USER "+
						 " SET "+
						 " IdRole= " + t.getIdRole() + "" +
						 ", UserName= '" + t.getUserName() + "'" +
						 ", Password= '" + t.getPassword() + "'" +
						 ", User_Status= '" + t.getUser_Status() + "'" +
						 " WHERE User_Id= " + t.getUser_Id() + "\'" ;
			int kq = st.executeUpdate(sql);
			
			System.out.println("Bạn đã thực thi");
			JDBCUtil.closeConnection(connect);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Delete(User t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "DELETE from USER "+
						 " WHERE User_Id= " + t.getUser_Id() + "" ;
			int kq = st.executeUpdate(sql);
			
			System.out.println("Bạn đã thực thi");
			JDBCUtil.closeConnection(connect);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<User> selectAll() {
		ArrayList<User> kq = new ArrayList<User>();
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM USER";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int User_Id = rs.getInt("User_Id");
				int IdRole = rs.getInt("IdRole");
				String UserName = rs.getString("UserName");
				String Password = rs.getString("Password");
				boolean User_Status = rs.getBoolean("User_Status");
				
				User user = new User(User_Id, IdRole, UserName, Password, User_Status);
				kq.add(user);
			}
			
			JDBCUtil.closeConnection(con);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public User seclectById(User t) {
		User kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM USER where User_Id=" + t.getUser_Id();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int User_Id = rs.getInt("User_Id");
				int IdRole = rs.getInt("IdRole");
				String UserName = rs.getString("UserName");
				String Password = rs.getString("Password");
				boolean User_Status = rs.getBoolean("User_Status");
				
				kq = new User(User_Id, IdRole, UserName, Password, User_Status);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}
	
	@Override
	public ArrayList<User> selectByCondition(String condition) {
		ArrayList<User> kq = new ArrayList<>();
	    
	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();
	        
	        String sql = "SELECT * FROM USER WHERE " + condition;
	        System.out.println(sql); 
	        
	        ResultSet rs = st.executeQuery(sql);
	        
	        while (rs.next()) {
	        	int User_Id = rs.getInt("User_Id");
				int IdRole = rs.getInt("IdRole");
				String UserName = rs.getString("UserName");
				String Password = rs.getString("Password");
				boolean User_Status = rs.getBoolean("User_Status");
	            
	            User user = new User(User_Id, IdRole, UserName, Password, User_Status);
	            kq.add(user);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}
	
}
