package Data_Access_Object;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Result;

import Database.JDBCUtil;
import TestDatabase.TestJDCBC;
import models.Permission_Item;

public class DAO_Permission_Item implements Interface<Permission_Item> {
	public static DAO_Permission_Item getInstance() {
		return new DAO_Permission_Item();
	}
	
	@Override
	public int Add(Permission_Item t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "INSERT INTO PERMISSION_ITEM(PermissionItem_Id, PermissionItem_Name)"
					+ "VALUES (" + t.getPermissionItem_Id() + " , '" 
					+ t.getPermissionItem_Name() + "')";
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
	public int Update(Permission_Item t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "UPDATE PERMISSION_ITEM "+
						 " SET "+
						 " PermissionItem_Name= '" + t.getPermissionItem_Name() + "'" +
						 " WHERE PermissionItem_Id= " + t.getPermissionItem_Id() + "\'" ;
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
	public int Delete(Permission_Item t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "DELETE from PERMISSION_ITEM "+
						 " WHERE PermissionItem_Id= " + t.getPermissionItem_Id() + "" ;
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
	public ArrayList<Permission_Item> selectAll() {
		ArrayList<Permission_Item> kq = new ArrayList<Permission_Item>();
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM PERMISSION_ITEM";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int PermissionItem_Id = rs.getInt("PermissionItem_Id");
				String PermissionItem_Name = rs.getString("PermissionItem_Name");
				
				Permission_Item permissionItem = new Permission_Item(PermissionItem_Id, PermissionItem_Name);
				kq.add(permissionItem);
			}
			
			JDBCUtil.closeConnection(con);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public Permission_Item seclectById(Permission_Item t) {
		Permission_Item kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM PERMISSION_ITEM where PermissionItem_Id=" + t.getPermissionItem_Id();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int PermissionItem_Id = rs.getInt("PermissionItem_Id");
				String PermissionItem_Name = rs.getString("PermissionItem_Name");
				
				
				kq = new Permission_Item(PermissionItem_Id, PermissionItem_Name);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}
	
	@Override
	public ArrayList<Permission_Item> selectByCondition(String condition) {
		ArrayList<Permission_Item> kq = new ArrayList<>();
	    
	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();
	        
	        String sql = "SELECT * FROM PERMISSION_ITEM WHERE " + condition;
	        System.out.println(sql); 
	        
	        ResultSet rs = st.executeQuery(sql);
	        
	        while (rs.next()) {
	            int PermissionItem_Id = rs.getInt("PermissionItem_Id");
	            String PermissionItem_Name = rs.getString("PermissionItem_Name");
	            
	            
	            Permission_Item permissionItem = new Permission_Item(PermissionItem_Id, PermissionItem_Name);
	            kq.add(permissionItem);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}
	
}
