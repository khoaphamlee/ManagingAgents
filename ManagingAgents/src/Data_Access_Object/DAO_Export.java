package Data_Access_Object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;

import models.Export;

public class DAO_Export implements Interface<Export>{
	
	public static DAO_Export getInstance() {
		return new DAO_Export(); 
	}
	
	@Override
	public int Add(Export t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "INSERT INTO EXPORT(Export_Id, Id_Agent, Id_User, Id_Receipt, Export_Date, Export_TotalMoney,PayAmount, Remaining)"
					+ "VALUES (" 
					+ t.getExport_Id() + " , " 
					+ t.getId_Agent() + " , " 
					+ t.getId_User() + " , " 
					+ t.getId_Receipt() + " , '" 
					+ t.getExport_Date() + "' , " 
					+ t.getExport_TotalMoney() + " , " 
					+ t.getPayAmount() + " , " 
					+ t.getRemaining() +")";
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
	public int Update(Export t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "UPDATE EXPORT "+
						 " SET "+
						 " Id_Agent= " + t.getId_Agent() + "" +
						 " Id_User= " + t.getId_User() + "" +
						 " Id_Receipt= " + t.getId_Receipt() + "" +
						 ", Export_Date= '" + t.getExport_Date() + "'" +
						 ", Export_TotalMoney= " + t.getExport_TotalMoney() + "" +
						 ", PayAmount= " + t.getPayAmount() + "" + 
						 ", Remaining= " + t.getRemaining() + "" + 
						 " WHERE Export_Id= " + t.getExport_Id() + "" ;
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
	public int Delete(Export t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "DELETE from EXPORT "+
						 " WHERE Export_Id= " + t.getExport_Id() + "" ;
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
	public ArrayList<Export> selectAll() {
		ArrayList<Export> kq = new ArrayList<Export>();
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM EXPORT";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int Export_Id = rs.getInt("Export_Id");
				int Id_Agent = rs.getInt("Id_Agent");
				int Id_User = rs.getInt("Id_User");
				int Id_Receipt = rs.getInt("Id_Receipt");
				Date Export_Date = rs.getDate("Export_Date");
				double Export_TotalMoney = rs.getDouble("Export_TotalMoney");
				double PayAmount = rs.getDouble("PayAmount");
				double Remaining = rs.getDouble("Remaining");
				
				Export export = new Export(Export_Id, Id_Agent, Id_User, Id_Receipt, Export_Date, Export_TotalMoney, PayAmount, Remaining);
				kq.add(export);
			}
			
			JDBCUtil.closeConnection(con);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public Export seclectById(Export t) {
		Export kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM EXPORT where Export_Id=" + t.getExport_Id();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int Export_Id = rs.getInt("Export_Id");
				int Id_Agent = rs.getInt("Id_Agent");
				int Id_User = rs.getInt("Id_User");
				int Id_Receipt = rs.getInt("Id_Receipt");
				Date Export_Date = rs.getDate("Export_Date");
				double Export_TotalMoney = rs.getDouble("Export_TotalMoney");
				double PayAmount = rs.getDouble("PayAmount");
				double Remaining = rs.getDouble("Remaining");
				
				kq = new Export(Export_Id, Id_Agent, Id_User, Id_Receipt, Export_Date, Export_TotalMoney, PayAmount, Remaining);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public ArrayList<Export> selectByCondition(String condition) {
		ArrayList<Export> kq = new ArrayList<>();
	    
	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();
	        
	        String sql = "SELECT * FROM EXPORT WHERE " + condition;
	        System.out.println(sql); 
	        
	        ResultSet rs = st.executeQuery(sql);
	        
	        while (rs.next()) {
	        	int Export_Id = rs.getInt("Export_Id");
				int Id_Agent = rs.getInt("Id_Agent");
				int Id_User = rs.getInt("Id_User");
				int Id_Receipt = rs.getInt("Id_Receipt");
				Date Export_Date = rs.getDate("Export_Date");
				double Export_TotalMoney = rs.getDouble("Export_TotalMoney");
				double PayAmount = rs.getDouble("PayAmount");
				double Remaining = rs.getDouble("Remaining");
				
				Export export = new Export(Export_Id, Id_Agent, Id_User, Id_Receipt, Export_Date, Export_TotalMoney, PayAmount, Remaining);
	            kq.add(export);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}

}
