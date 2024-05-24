package Data_Access_Object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;
import models.Agent;
import models.Import_Detail;

public class DAO_Import_Detail implements Interface<Import_Detail> {

	public static DAO_Import_Detail getInstance() {
		return new DAO_Import_Detail();
	}
	
	@Override
	public int Add(Import_Detail t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "INSERT INTO IMPORT_DETAIL(Import_Detail_Id, IdImport, IdUnit, IdItems, Items_Price, Total_Money, Items_Quentity)"
					+ "VALUES (" + t.getImport_Detail_Id() + " , " 
					+ t.getIdImport() + " , " 
					+ t.getIdUnit() + " , " 
					+ t.getIdItems() + " , " 
					+ t.getItems_Price() + " , " 
					+ t.getTotal_Money() + " , "
					+ t.getItems_Quentity() + ")";
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
	public int Update(Import_Detail t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "UPDATE IMPORT_DETAIL "+
						 " SET "+
						 " IdImport= " + t.getIdImport() + "" +
						 ", IdUnit= " + t.getIdUnit() + "" +
						 ", IdItems= " + t.getIdItems() + "" +
						 ", Items_Price= " + t.getItems_Price() + "" + 
						 ", Total_Money= " + t.getTotal_Money() + "" +
						 ", Items_Quentity= " + t.getItems_Quentity() + "" +
						 " WHERE Import_Detail_Id= " + t.getImport_Detail_Id() + "" ;
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
	public int Delete(Import_Detail t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "DELETE from IMPORT_DETAIL "+
						 " WHERE Import_Detail_Id= " + t.getImport_Detail_Id() + "" ;
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
	public ArrayList<Import_Detail> selectAll() {
		ArrayList<Import_Detail> kq = new ArrayList<Import_Detail>();
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM IMPORT_DETAIL";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int Import_Detail_Id = rs.getInt("Import_Detail_Id");
				int IdImport = rs.getInt("IdImport");
				int IdUnit = rs.getInt("IdUnit");
				int IdItems = rs.getInt("IdItems");
				double Items_Price = rs.getDouble("Items_Price");
				double Total_Money = rs.getDouble("Total_Money");
				int Items_Quentity = rs.getInt("Items_Quentity");
				
				Import_Detail Import_detail = new Import_Detail(Import_Detail_Id, IdImport, IdUnit, IdItems, Items_Price, Total_Money, Items_Quentity);
				kq.add(Import_detail);
			}
			
			JDBCUtil.closeConnection(con);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public Import_Detail seclectById(Import_Detail t) {
		Import_Detail kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM IMPORT_DETAIL where Import_Detail_Id=" + t.getImport_Detail_Id();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int Import_Detail_Id = rs.getInt("Import_Detail_Id");
				int IdImport = rs.getInt("IdImport");
				int IdUnit = rs.getInt("IdUnit");
				int IdItems = rs.getInt("IdItems");
				double Items_Price = rs.getDouble("Items_Price");
				double Total_Money = rs.getDouble("Total_Money");
				int Items_Quentity = rs.getInt("Items_Quentity");
				
				kq = new Import_Detail(Import_Detail_Id, IdImport, IdUnit, IdItems, Items_Price, Total_Money, Items_Quentity);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public ArrayList<Import_Detail> selectByCondition(String condition) {
		ArrayList<Import_Detail> kq = new ArrayList<>();
	    
	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();
	        
	        String sql = "SELECT * FROM IMPORT_DETAIL WHERE " + condition;
	        System.out.println(sql); 
	        
	        ResultSet rs = st.executeQuery(sql);
	        
	        while (rs.next()) {
	        	int Import_Detail_Id = rs.getInt("Import_Detail_Id");
				int IdImport = rs.getInt("IdImport");
				int IdUnit = rs.getInt("IdUnit");
				int IdItems = rs.getInt("IdItems");
				double Items_Price = rs.getDouble("Items_Price");
				double Total_Money = rs.getDouble("Total_Money");
				int Items_Quentity = rs.getInt("Items_Quentity");
	            
				Import_Detail Import_detail = new Import_Detail(Import_Detail_Id, IdImport, IdUnit, IdItems, Items_Price, Total_Money, Items_Quentity);
	            kq.add(Import_detail);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}

}
