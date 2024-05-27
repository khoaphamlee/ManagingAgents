package Data_Access_Object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;
import models.Receipt;

public class DAO_Receipt implements Interface<Receipt>{

	public static DAO_Receipt getInstance() {
		return new DAO_Receipt(); 
	}
	
	@Override
	public int Add(Receipt t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "INSERT INTO RECEIPT(Receipt_Id, Id_Agent, Id_User, ReceiptDate, MoneyReceived, IsReceived)"
					+ "VALUES (" + t.getReceipt_Id() + " , " + t.getId_Agent() + " , " + t.getId_User()
					+ " , '" + t.getReceiptDate() + "' , " + t.getMoneyReceived() + " , '" + t.getIsReceived()+"')";
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
	public int Update(Receipt t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "UPDATE RECEIPT "+
						 " SET "+
						 " Id_Agent= " + t.getId_Agent() + "" +
						 ", Id_User= " + t.getId_User() + "" +
						 ", ReceiptDate= '" + t.getReceiptDate() + "'" +
						 ", MoneyReceived= " + t.getMoneyReceived() + "" + 
						 ", IsReceived= '" + t.getIsReceived() + "'" +
						 " WHERE Receipt_Id= " + t.getReceipt_Id() + "" ;
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
	public int Delete(Receipt t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "DELETE from RECEIPT "+
						 " WHERE Receipt_Id= " + t.getReceipt_Id() + "" ;
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
	public ArrayList<Receipt> selectAll() {
		ArrayList<Receipt> kq = new ArrayList<Receipt>();
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM RECEIPT";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int Receipt_Id = rs.getInt("Receipt_Id");
				int Id_Agent = rs.getInt("Id_Agent");
				int Id_User = rs.getInt("Id_User");
				Date ReceiptDate = rs.getDate("ReceiptDate");
				Double MoneyReceived = rs.getDouble("MoneyReceived");
				Boolean IsReceived = rs.getBoolean("IsReceived");
				
				Receipt recept = new Receipt(Receipt_Id, Id_Agent, Id_User, ReceiptDate, MoneyReceived, IsReceived);
				kq.add(recept);
			}
			
			JDBCUtil.closeConnection(con);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public Receipt seclectById(Receipt t) {
		Receipt kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM RECEIPT where Receipt_Id=" + t.getReceipt_Id();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int Receipt_Id = rs.getInt("Receipt_Id");
				int Id_Agent = rs.getInt("Id_Agent");
				int Id_User = rs.getInt("Id_User");
				Date ReceiptDate = rs.getDate("ReceiptDate");
				Double MoneyReceived = rs.getDouble("MoneyReceived");
				Boolean IsReceived = rs.getBoolean("IsReceived");
				
				kq = new Receipt(Receipt_Id, Id_Agent, Id_User, ReceiptDate, MoneyReceived, IsReceived);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public ArrayList<Receipt> selectByCondition(String condition) {
		ArrayList<Receipt> kq = new ArrayList<>();
	    
	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();
	        
	        String sql = "SELECT * FROM RECEIPT WHERE " + condition;
	        System.out.println(sql); 
	        
	        ResultSet rs = st.executeQuery(sql);
	        
	        while (rs.next()) {
	        	int Receipt_Id = rs.getInt("Receipt_Id");
				int Id_Agent = rs.getInt("Id_Agent");
				int Id_User = rs.getInt("Id_User");
				Date ReceiptDate = rs.getDate("ReceiptDate");
				Double MoneyReceived = rs.getDouble("MoneyReceived");
				Boolean IsReceived = rs.getBoolean("IsReceived");
	            
				Receipt recept = new Receipt(Receipt_Id, Id_Agent, Id_User, ReceiptDate, MoneyReceived, IsReceived);
	            kq.add(recept);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}

}