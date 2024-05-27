package Data_Access_Object;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;



import Database.JDBCUtil;

import models.User_Info;

public class DAO_User_Info implements Interface<User_Info> {
	public static DAO_User_Info getInstance() {
		return new DAO_User_Info();
	}
	
	@Override
	public int Add(User_Info t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "INSERT INTO USER_INFO(UserInfo_Id, IdUser, UserInfo_Name, UserInfo_Address, UserInfo_BirthDate, UserInfo_Email, UserInfo_Phone)"
					+ "VALUES (" + t.getUserInfo_Id() + " , " 
					+ t.getIdUser() + " , '" 
					+ t.getUserInfo_Name() + "' , '" 
					+ t.getUserInfo_Address() + "' , '" 
					+ t.getUserInfo_BirthDate() + "' , '"
					+ t.getUserInfo_Email()+ "' , '" 
					+ t.getUserInfo_Phone() +"')";
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
	public int Update(User_Info t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "UPDATE USER_INFO "+
						 " SET "+
						 " IdUser= " + t.getIdUser() + "" +
						 ", UserInfo_Name= '" + t.getUserInfo_Name() + "'" +
						 ", UserInfo_Address= '" + t.getUserInfo_Address() + "'" +
						 ", UserInfo_BirthDate= '" + t.getUserInfo_BirthDate() + "'" +
						 ", UserInfo_Email= '" + t.getUserInfo_Email() + "'" +
						 ", UserInfo_Phone= '" + t.getUserInfo_Phone() + "'" +
						 " WHERE UserInfo_Id= " + t.getUserInfo_Id() + "\'" ;
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
	public int Delete(User_Info t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "DELETE from USER_INFO "+
						 " WHERE UserInfo_Id= " + t.getUserInfo_Id() + "" ;
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
	public ArrayList<User_Info> selectAll() {
		ArrayList<User_Info> kq = new ArrayList<User_Info>();
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM USER_INFO";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int UserInfo_Id = rs.getInt("UserInfo_Id");
				int IdUser = rs.getInt("IdUser");
				String UserInfo_Name = rs.getString("UserInfo_Name");
				String UserInfo_Address = rs.getString("UserInfo_Address");
				Date UserInfo_BirthDate = rs.getDate("UserInfo_BirthDate");
				String UserInfo_Email = rs.getString("UserInfo_Email");
				String UserInfo_Phone = rs.getString("UserInfo_Phone");
				
				User_Info user_info = new User_Info(UserInfo_Id, IdUser, UserInfo_Name, UserInfo_Address, UserInfo_BirthDate, UserInfo_Email, UserInfo_Phone);
				kq.add(user_info);
			}
			
			JDBCUtil.closeConnection(con);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public User_Info seclectById(User_Info t) {
		User_Info kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM USER_INFO where UserInfo_Id=" + t.getUserInfo_Id();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int UserInfo_Id = rs.getInt("UserInfo_Id");
				int IdUser = rs.getInt("IdUser");
				String UserInfo_Name = rs.getString("UserInfo_Name");
				String UserInfo_Address = rs.getString("UserInfo_Address");
				Date UserInfo_BirthDate = rs.getDate("UserInfo_BirthDate");
				String UserInfo_Email = rs.getString("UserInfo_Email");
				String UserInfo_Phone = rs.getString("UserInfo_Phone");
				
				kq = new User_Info(UserInfo_Id, IdUser, UserInfo_Name, UserInfo_Address, UserInfo_BirthDate, UserInfo_Email, UserInfo_Phone);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}
	
	@Override
	public ArrayList<User_Info> selectByCondition(String condition) {
		ArrayList<User_Info> kq = new ArrayList<>();
	    
	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();
	        
	        String sql = "SELECT * FROM USER_INFO WHERE " + condition;
	        System.out.println(sql); 
	        
	        ResultSet rs = st.executeQuery(sql);
	        
	        while (rs.next()) {
	        	int UserInfo_Id = rs.getInt("UserInfo_Id");
				int IdUser = rs.getInt("IdUser");
				String UserInfo_Name = rs.getString("UserInfo_Name");
				String UserInfo_Address = rs.getString("UserInfo_Address");
				Date UserInfo_BirthDate = rs.getDate("UserInfo_BirthDate");
				String UserInfo_Email = rs.getString("UserInfo_Email");
				String UserInfo_Phone = rs.getString("UserInfo_Phone");
	            
	            User_Info user_info = new User_Info(UserInfo_Id, IdUser, UserInfo_Name, UserInfo_Address, UserInfo_BirthDate, UserInfo_Email, UserInfo_Phone);
	            kq.add(user_info);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}
	
}
