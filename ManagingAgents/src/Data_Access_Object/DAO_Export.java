package Data_Access_Object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;
import models.Agent;
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
			
			String sql = "INSERT INTO EXPORT(Export_Id, IdAgent, Id_User, Export_Date, Export_TotalMoney,PayAmount)"
					+ "VALUES (" 
					+ t.getExport_Id() + " , " 
					+ t.getIdAgent() + " , " 
					+ t.getId_User() + " , '" 
					+ t.getExport_Date() + "' , " 
					+ t.getExport_TotalMoney() + " , " 
					+ t.getPayAmount() + ")";
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
						 " IdAgent= " + t.getIdAgent() + "" +
						 " Id_User= " + t.getId_User() + "" +
						 ", Export_Date= '" + t.getExport_Date() + "'" +
						 ", Export_TotalMoney= " + t.getExport_TotalMoney() + "" +
						 ", PayAmount= " + t.getPayAmount() + "" + 
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
				int IdAgent = rs.getInt("IdAgent");
				int Id_User = rs.getInt("Id_User");
				Date Export_Date = rs.getDate("Export_Date");
				double Export_TotalMoney = rs.getDouble("Export_TotalMoney");
				double PayAmount = rs.getDouble("PayAmount");
				
				Export export = new Export(Export_Id, IdAgent, Id_User, Export_Date, Export_TotalMoney, PayAmount);
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
				int IdAgent = rs.getInt("IdAgent");
				int Id_User = rs.getInt("Id_User");
				Date Export_Date = rs.getDate("Export_Date");
				double Export_TotalMoney = rs.getDouble("Export_TotalMoney");
				double PayAmount = rs.getDouble("PayAmount");
				
				kq = new Export(Export_Id, IdAgent, Id_User, Export_Date, Export_TotalMoney, PayAmount);
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
				int IdAgent = rs.getInt("IdAgent");
				int Id_User = rs.getInt("Id_User");
				Date Export_Date = rs.getDate("Export_Date");
				double Export_TotalMoney = rs.getDouble("Export_TotalMoney");
				double PayAmount = rs.getDouble("PayAmount");
				
				Export export = new Export(Export_Id, IdAgent, Id_User, Export_Date, Export_TotalMoney, PayAmount);
	            kq.add(export);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}

}
