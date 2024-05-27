package Data_Access_Object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;
import models.Sales_Report;

public class DAO_Sales_Report implements Interface<Sales_Report>{

	public static DAO_Sales_Report getInstance() {
		return new DAO_Sales_Report(); 
	}
	
	@Override
	public int Add(Sales_Report t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "INSERT INTO SALES_REPORT(SalesReport_Id, Id_User, SalesReport_Date)"
					+ "VALUES (" 
					+ t.getSalesReport_Id() + " , " 
					+ t.getId_User() + " , '"
					+ t.getSalesReport_Date()+"')";
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
	public int Update(Sales_Report t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "UPDATE SALES_REPORT "+
						 " SET "+
						 " SalesReport_Date= '" + t.getSalesReport_Date() + "'" +
						 " Id_User= " + t.getId_User() + "" +
						 " WHERE SalesReport_Id= " + t.getSalesReport_Id() + "" ;
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
	public int Delete(Sales_Report t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "DELETE from SALES_REPORT "+
						 " WHERE SalesReport_Id= " + t.getSalesReport_Id() + "" ;
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
	public ArrayList<Sales_Report> selectAll() {
		ArrayList<Sales_Report> kq = new ArrayList<Sales_Report>();
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM SALES_REPORT";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int SalesReport_Id = rs.getInt("SalesReport_Id");
				int Id_User = rs.getInt("Id_User");
				Date SalesReport_Date = rs.getDate("SalesReport_Date");
				
				
				Sales_Report sale_report = new Sales_Report(SalesReport_Id, Id_User, SalesReport_Date);
				kq.add(sale_report);
			}
			
			JDBCUtil.closeConnection(con);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public Sales_Report seclectById(Sales_Report t) {
		Sales_Report kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM SALES_REPORT where SalesReport_Id=" + t.getSalesReport_Id();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int SalesReport_Id = rs.getInt("SalesReport_Id");
				int Id_User = rs.getInt("Id_User");
				Date SalesReport_Date = rs.getDate("SalesReport_Date");
				
				kq = new Sales_Report(SalesReport_Id, Id_User, SalesReport_Date);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public ArrayList<Sales_Report> selectByCondition(String condition) {
		ArrayList<Sales_Report> kq = new ArrayList<>();
	    
	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();
	        
	        String sql = "SELECT * FROM SALES_REPORT WHERE " + condition;
	        System.out.println(sql); 
	        
	        ResultSet rs = st.executeQuery(sql);
	        
	        while (rs.next()) {
	        	int SalesReport_Id = rs.getInt("SalesReport_Id");
				int Id_User = rs.getInt("Id_User");
				Date SalesReport_Date = rs.getDate("SalesReport_Date");
				
				
				Sales_Report sale_report = new Sales_Report(SalesReport_Id, Id_User, SalesReport_Date);
	            kq.add(sale_report);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}

}
