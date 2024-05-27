package Data_Access_Object;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import Database.JDBCUtil;

import models.RoleDetail;

public class DAO_RoleDetail implements Interface<RoleDetail> {
	public static DAO_RoleDetail getInstance() {
		return new DAO_RoleDetail();
	}
	
	@Override
	public int Add(RoleDetail t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "INSERT INTO ROLEDETAIL(RoleDetail_Id, Id_Role, Id_PermissionItem, Permission)"
					+ "VALUES (" + t.getRoleDetail_Id() + " , " 
					+ t.getId_Role() + " , " 
					+ t.getId_PermissionItem() + " , '" 
					+ t.getPermission() +")";
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
	public int Update(RoleDetail t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "UPDATE ROLEDETAIL "+
						 " SET "+
						 " Id_Role= '" + t.getId_Role() + "'" +
						 ", Id_PermissionItem= '" + t.getId_PermissionItem() + "'" +
						 ", Permission= '" + t.getPermission() + "'" +
						 " WHERE RoleDetail_Id= " + t.getRoleDetail_Id() + "\'" ;
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
	public int Delete(RoleDetail t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "DELETE from ROLEDETAIL "+
						 " WHERE RoleDetail_Id= " + t.getRoleDetail_Id() + "" ;
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
	public ArrayList<RoleDetail> selectAll() {
		ArrayList<RoleDetail> kq = new ArrayList<RoleDetail>();
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM ROLEDETAIL";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int RoleDetail_Id = rs.getInt("RoleDetail_Id");
				int Id_Role = rs.getInt("Id_Role");
				int Id_PermissionItem = rs.getInt("Id_PermissionItem");
				String Permission = rs.getString("Permission");
				
				
				RoleDetail roleDetail = new RoleDetail(RoleDetail_Id, Id_Role, Id_PermissionItem, Permission);
				kq.add(roleDetail);
			}
			
			JDBCUtil.closeConnection(con);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public RoleDetail seclectById(RoleDetail t) {
		RoleDetail kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM ROLEDETAIL where RoleDetail_Id=" + t.getRoleDetail_Id();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int RoleDetail_Id = rs.getInt("RoleDetail_Id");
				int Id_Role = rs.getInt("Id_Role");
				int Id_PermissionItem = rs.getInt("Id_PermissionItem");
				String Permission = rs.getString("Permission");
				
				
				
				kq = new RoleDetail(RoleDetail_Id, Id_Role, Id_PermissionItem, Permission);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}
	
	@Override
	public ArrayList<RoleDetail> selectByCondition(String condition) {
		ArrayList<RoleDetail> kq = new ArrayList<>();
	    
	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();
	        
	        String sql = "SELECT * FROM ROLEDETAIL WHERE " + condition;
	        System.out.println(sql); 
	        
	        ResultSet rs = st.executeQuery(sql);
	        
	        while (rs.next()) {
	            int RoleDetail_Id = rs.getInt("RoleDetail_Id");
	            int Id_Role = rs.getInt("Id_Role");
	            int Id_PermissionItem = rs.getInt("Id_PermissionItem");
	            String Permission = rs.getString("Permission");
	           
	            
	            RoleDetail roleDetail = new RoleDetail(RoleDetail_Id, Id_Role, Id_PermissionItem, Permission);
	            kq.add(roleDetail);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}
	
}
