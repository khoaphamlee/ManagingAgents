package Data_Access_Object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;

import models.Unit;

public class DAO_Unit implements Interface<Unit> {
	public static DAO_Unit getInstance() {
		return new DAO_Unit();
	}

	@Override
	public int Add(Unit t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "INSERT INTO UNIT(Unit_Id, Unit_Name)"
					+ "VALUES (" + t.getUnit_Id() + " , '" + t.getUnit_Name() + "')";
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
	public int Update(Unit t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "UPDATE UNIT "+
						 " SET "+
						 " Unit_Name= '" + t.getUnit_Name() + "'" +
						 " WHERE Unit_Id= " + t.getUnit_Id() + "" ;
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
	public int Delete(Unit t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "DELETE from UNIT "+
						 " WHERE Unit_Id= " + t.getUnit_Id() + "" ;
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
	public ArrayList<Unit> selectAll() {
		ArrayList<Unit> kq = new ArrayList<Unit>();
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM UNIT";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int Unit_Id = rs.getInt("Unit_Id");
				String Unit_Name = rs.getString("Unit_Name");
				
				Unit unit = new Unit(Unit_Id, Unit_Name);
				kq.add(unit);
			}
			
			JDBCUtil.closeConnection(con);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public Unit seclectById(Unit t) {
		Unit kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM UNIT where Unit_Id=" + t.getUnit_Id();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int Unit_Id = rs.getInt("Unit_Id");
				String Unit_Name = rs.getString("Unit_Name");
				
				kq = new Unit(Unit_Id, Unit_Name);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public ArrayList<Unit> selectByCondition(String condition) {
		ArrayList<Unit> kq = new ArrayList<>();
	    
	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();
	        
	        String sql = "SELECT * FROM UNIT WHERE " + condition;
	        System.out.println(sql); 
	        
	        ResultSet rs = st.executeQuery(sql);
	        
	        while (rs.next()) {
	        	int Unit_Id = rs.getInt("Unit_Id");
				String Unit_Name = rs.getString("Unit_Name");
	            
				Unit unit = new Unit(Unit_Id, Unit_Name);
	            kq.add(unit);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}
}
