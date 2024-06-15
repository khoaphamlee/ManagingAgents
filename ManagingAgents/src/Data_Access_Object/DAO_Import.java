package Data_Access_Object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Database.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Import;
import java.time.LocalDate;

public class DAO_Import implements Interface<Import> {
	
	public static DAO_Import getInstance() {
		return new DAO_Import();
	}
	
	@Override
	public int Add(Import t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "INSERT INTO IMPORT( Id_User, ImportDate, Import_TotalMoney) VALUES (" 
					
					+ t.getId_User() + ", '"
					+ t.getImport_Date() + "', " 
					+ t.getImport_TotalMoney() + ")";
			int kq = st.executeUpdate(sql);
			
			System.out.println("Bạn đã thực thi");
			JDBCUtil.closeConnection(connect);
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int Update(Import t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "UPDATE IMPORT SET Id_User=" + t.getId_User() 
					+ ", ImportDate='" + t.getImport_Date() 
					+ "', Import_TotalMoney=" + t.getImport_TotalMoney() 
					+ " WHERE ImportId=" + t.getImport_Id();
			int kq = st.executeUpdate(sql);
			
			System.out.println("Bạn đã thực thi");
			JDBCUtil.closeConnection(connect);
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int Delete(Import t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "DELETE FROM IMPORT WHERE ImportId=" + t.getImport_Id();
			int kq = st.executeUpdate(sql);
			
			System.out.println("Bạn đã thực thi");
			JDBCUtil.closeConnection(connect);
			return kq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<Import> selectAll() {
		ArrayList<Import> kq = new ArrayList<>();
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM IMPORT";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int ImportId = rs.getInt("ImportId");
				int Id_User = rs.getInt("Id_User");
				Date importDateSQL = rs.getDate("ImportDate");
				
				double Import_TotalMoney = rs.getDouble("Import_TotalMoney");
				
				Import imporT = new Import(ImportId, Id_User,importDateSQL, Import_TotalMoney);
				kq.add(imporT);
			}
			
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}
	
	
	public ObservableList<Import> selectAll2() {
		ObservableList<Import> kq =  FXCollections.observableArrayList();
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM IMPORT";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int ImportId = rs.getInt("ImportId");
				int Id_User = rs.getInt("Id_User");
				Date importDateSQL = rs.getDate("ImportDate");
				
				double Import_TotalMoney = rs.getDouble("Import_TotalMoney");
				
				Import imporT = new Import(ImportId, Id_User, importDateSQL, Import_TotalMoney);
				kq.add(imporT);
			}
			
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public Import seclectById(Import t) {
		Import kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM IMPORT WHERE ImportId=" + t.getImport_Id();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int ImportId = rs.getInt("ImportId");
				int Id_User = rs.getInt("Id_User");
				Date importDateSQL = rs.getDate("ImportDate");
				
				double Import_TotalMoney = rs.getDouble("Import_TotalMoney");
				
				kq = new Import(ImportId, Id_User, importDateSQL, Import_TotalMoney);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
				Date importDateSQL = rs.getDate("ImportDate");
				
				double Import_TotalMoney = rs.getDouble("Import_TotalMoney");
				
				Import imporT = new Import(ImportId, Id_User, importDateSQL, Import_TotalMoney);
				kq.add(imporT);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}

	// New methods to get specific attributes based on ImportId
	public int getUserIdByImportId(int importId) {
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			
			String sql = "SELECT Id_User FROM IMPORT WHERE ImportId=" + importId;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			if (rs.next()) {
				return rs.getInt("Id_User");
			}
			
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Return -1 if no result found
	}

	public Date getImportDateByImportId(int importId) {
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			
			String sql = "SELECT ImportDate FROM IMPORT WHERE ImportId=" + importId;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			if (rs.next()) {
				return rs.getDate("ImportDate");
			}
			
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // Return null if no result found
	}

	public double getTotalMoneyByImportId(int importId) {
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			
			String sql = "SELECT Import_TotalMoney FROM IMPORT WHERE ImportId=" + importId;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			if (rs.next()) {
				return rs.getDouble("Import_TotalMoney");
			}
			
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1.0; // Return -1.0 if no result found
	}
	
	public ResultSet getCurrentId() throws SQLException {
        Connection connect = JDBCUtil.getConnection();
        String sql = "SELECT MAX(ImportId) FROM import"; 
        PreparedStatement statement = connect.prepareStatement(sql);
        return statement.executeQuery();
    }


}
