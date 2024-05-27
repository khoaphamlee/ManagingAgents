package Data_Access_Object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;

import models.Import;

public class DAO_Import implements Interface<Import>{
	
	public static DAO_Import getInstance() {
		return new DAO_Import(); 
	}
	
	@Override
	public int Add(Import t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "INSERT INTO IMPORT(ImportId, Id_User, ImportDate, Import_TotalMoney)"
					+ "VALUES (" 
					+ t.getImportId() + " , " 
					+ t.getId_User() + " , '"
					+ t.getImportDate() + "' , " 
					+ t.getImport_TotalMoney() +")";
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
	public int Update(Import t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "UPDATE IMPORT "+
						 " SET "+
						 " Id_User= " + t.getId_User() + "" +
						 " ImportDate= '" + t.getImportDate() + "'" +
						 ", Import_TotalMoney= " + t.getImport_TotalMoney() + "" +
						 " WHERE ImportId= " + t.getImportId() + "" ;
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
	public int Delete(Import t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "DELETE from IMPORT "+
						 " WHERE ImportId= " + t.getImportId() + "" ;
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
	public ArrayList<Import> selectAll() {
		ArrayList<Import> kq = new ArrayList<Import>();
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM IMPORT";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int ImportId = rs.getInt("ImportId");
				int Id_User = rs.getInt("Id_User");
				Date ImportDate = rs.getDate("ImportDate");
				double Import_TotalMoney = rs.getDouble("Import_TotalMoney");
				
				Import imporT = new Import(ImportId, Id_User, ImportDate, Import_TotalMoney);
				kq.add(imporT);
			}
			
			JDBCUtil.closeConnection(con);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public Import seclectById(Import t) {
		Import kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM IMPORT where ImportId=" + t.getImportId();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int ImportId = rs.getInt("ImportId");
				int Id_User = rs.getInt("Id_User");
				Date ImportDate = rs.getDate("ImportDate");
				double Import_TotalMoney = rs.getDouble("Import_TotalMoney");
				
				 kq = new Import(ImportId, Id_User, ImportDate, Import_TotalMoney);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public ArrayList<Import> selectByCondition(String condition) {
		ArrayList<Import> kq = new ArrayList<>();
	    
	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();
	        
	        String sql = "SELECT * FROM IMPORT WHERE " + condition;
	        System.out.println(sql); 
	        
	        ResultSet rs = st.executeQuery(sql);
	        
	        while (rs.next()) {
	        	int ImportId = rs.getInt("ImportId");
				int Id_User = rs.getInt("Id_User");
				Date ImportDate = rs.getDate("ImportDate");
				double Import_TotalMoney = rs.getDouble("Import_TotalMoney");
				
				Import imporT = new Import(ImportId, Id_User, ImportDate, Import_TotalMoney);
				kq.add(imporT);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}

}
