package Data_Access_Object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;
import models.Agent;
import models.Items;

public class DAO_Items implements Interface<Items>{

	public static DAO_Items getInstance() {
		return new DAO_Items();
	}
	
	@Override
	public int Add(Items t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "INSERT INTO ITEMS(Items_Id, Items_Name, Items_Price, Items_Quentity)"
					+ "VALUES (" + t.getItems_Id() + " , '" + t.getItems_Name() + "' , " + t.getItems_Price()
					+ " , " + t.getItems_Quantity() +")";
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
	public int Update(Items t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "UPDATE ITEMS "+
						 " SET "+
						 " Items_Name= '" + t.getItems_Name() + "'" +
						 ", Items_Price= " + t.getItems_Price() + "" +
						 ", Items_Quentity= " + t.getItems_Quantity() + "" +
						 " WHERE Items_Id= " + t.getItems_Id() + "" ;
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
	public int Delete(Items t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "DELETE from ITEMS "+
						 " WHERE Items_Id= " + t.getItems_Id() + "" ;
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
	public ArrayList<Items> selectAll() {
		ArrayList<Items> kq = new ArrayList<Items>();
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM ITEMS";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int Items_Id = rs.getInt("Items_Id");
				String Items_Name = rs.getString("Items_Name");
				double Items_Price = rs.getDouble("Items_Price");
				int Items_Quentity = rs.getInt("Items_Quentity");
				
				Items itemS = new Items(Items_Id, Items_Name, Items_Price, Items_Quentity);
				kq.add(itemS);
			}
			
			JDBCUtil.closeConnection(con);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public Items seclectById(Items t) {
		Items kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM ITEMS where Items_Id=" + t.getItems_Id();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int Items_Id = rs.getInt("Items_Id");
				String Items_Name = rs.getString("Items_Name");
				double Items_Price = rs.getDouble("Items_Price");
				int Items_Quentity = rs.getInt("Items_Quentity");
				
				kq = new Items(Items_Id, Items_Name, Items_Price, Items_Quentity);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public ArrayList<Items> selectByCondition(String condition) {
ArrayList<Items> kq = new ArrayList<>();
	    
	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();
	        
	        String sql = "SELECT * FROM ITEMS WHERE " + condition;
	        System.out.println(sql); 
	        
	        ResultSet rs = st.executeQuery(sql);
	        
	        while (rs.next()) {
	        	int Items_Id = rs.getInt("Items_Id");
				String Items_Name = rs.getString("Items_Name");
				double Items_Price = rs.getDouble("Items_Price");
				int Items_Quentity = rs.getInt("Items_Quentity");
	            
				Items itemS = new Items(Items_Id, Items_Name, Items_Price, Items_Quentity);
	            kq.add(itemS);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}

}
