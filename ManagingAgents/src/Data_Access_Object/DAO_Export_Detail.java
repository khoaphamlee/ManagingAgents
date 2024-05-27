package Data_Access_Object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;

import models.Export_Detail;

public class DAO_Export_Detail implements Interface<Export_Detail>{
	
	public static DAO_Export_Detail getInstance() {
		return new DAO_Export_Detail();
	}
	
	@Override
	public int Add(Export_Detail t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "INSERT INTO EXPORT_DETAIL(ExportDetail_Id, IdExport, ItemsUnit, Items, ItemsQuantity, PriceExport, TotalMoney_Export)"
					+ "VALUES (" + t.getExportDetail_Id() + " , " 
					+ t.getIdExport() + " , " 
					+ t.getItemsUnit() + " , " 
					+ t.getItems() + " , " 
					+ t.getItemsQuantity() + " , " 
					+ t.getPriceExport() + " , " 
					+ t.getTotalMoney_Export() +")";
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
	public int Update(Export_Detail t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "UPDATE EXPORT_DETAIL "+
						 " SET "+
						 " IdExport= " + t.getIdExport() + "" +
						 ", ItemsUnit= " + t.getItemsUnit() + "" +
						 ", Items= " + t.getItems() + "" +
						 ", ItemsQuantity= " + t.getItemsQuantity() + "" + 
						 ", PriceExport= " + t.getPriceExport() + "" +
						 ", TotalMoney_Export= " + t.getTotalMoney_Export() + "" +
						 " WHERE ExportDetail_Id= " + t.getExportDetail_Id() + "" ;
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
	public int Delete(Export_Detail t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "DELETE from EXPORT_DETAIL "+
						 " WHERE ExportDetail_Id= " + t.getExportDetail_Id() + "" ;
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
	public ArrayList<Export_Detail> selectAll() {
		ArrayList<Export_Detail> kq = new ArrayList<Export_Detail>();
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM EXPORT_DETAIL";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int ExportDetail_Id = rs.getInt("ExportDetail_Id");
				int IdExport = rs.getInt("IdExport");
				int ItemsUnit = rs.getInt("ItemsUnit");
				int Items = rs.getInt("Items");
				int ItemsQuantity = rs.getInt("ItemsQuantity");
				double PriceExport = rs.getDouble("PriceExport");
				double TotalMoney_Export = rs.getDouble("TotalMoney_Export");
				
				Export_Detail export_detail = new Export_Detail(ExportDetail_Id, IdExport, ItemsUnit, Items, ItemsQuantity, PriceExport, TotalMoney_Export );
				kq.add(export_detail);
			}
			
			JDBCUtil.closeConnection(con);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public Export_Detail seclectById(Export_Detail t) {
		Export_Detail kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM EXPORT_DETAIL where ExportDetail_Id=" + t.getExportDetail_Id();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int ExportDetail_Id = rs.getInt("ExportDetail_Id");
				int IdExport = rs.getInt("IdExport");
				int ItemsUnit = rs.getInt("ItemsUnit");
				int Items = rs.getInt("Items");
				int ItemsQuantity = rs.getInt("ItemsQuantity");
				double PriceExport = rs.getDouble("PriceExport");
				double TotalMoney_Export = rs.getDouble("TotalMoney_Export");
				
				kq = new Export_Detail(ExportDetail_Id, IdExport, ItemsUnit, Items, ItemsQuantity, PriceExport, TotalMoney_Export );
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public ArrayList<Export_Detail> selectByCondition(String condition) {
		ArrayList<Export_Detail> kq = new ArrayList<>();
	    
	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();
	        
	        String sql = "SELECT * FROM EXPORT_DETAIL WHERE " + condition;
	        System.out.println(sql); 
	        
	        ResultSet rs = st.executeQuery(sql);
	        
	        while (rs.next()) {
	        	int ExportDetail_Id = rs.getInt("ExportDetail_Id");
				int IdExport = rs.getInt("IdExport");
				int ItemsUnit = rs.getInt("ItemsUnit");
				int Items = rs.getInt("Items");
				int ItemsQuantity = rs.getInt("ItemsQuantity");
				double PriceExport = rs.getDouble("PriceExport");
				double TotalMoney_Export = rs.getDouble("TotalMoney_Export");
				
				Export_Detail export_detail = new Export_Detail(ExportDetail_Id, IdExport, ItemsUnit, Items, ItemsQuantity, PriceExport, TotalMoney_Export );
	            kq.add(export_detail);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}

}
