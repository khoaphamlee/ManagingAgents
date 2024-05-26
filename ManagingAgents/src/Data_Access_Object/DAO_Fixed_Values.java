package Data_Access_Object;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;
import models.Agent;
import models.Fixed_Values;

public class DAO_Fixed_Values implements Interface<Fixed_Values>{

	public static DAO_Fixed_Values getInstance() {
		return new DAO_Fixed_Values();
	}
	
	@Override
	public int Add(Fixed_Values t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "INSERT INTO FIXED_VALUES(Maximum_Agent, Maximum_InDebt, Maximum_IsOverPay)"
					+ "VALUES (" + t.getMaximum_Agent() + " , " 
					+ t.getMaximum_InDebt() + " , " 
					+ t.getMaximum_IsOverPay() +")";
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
	public int Update(Fixed_Values t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "UPDATE FIXED_VALUES "+
						 " SET "+
						 " Maximum_Agent= " + t.getMaximum_Agent() + "" +
						 ", Maximum_InDebt= " + t.getMaximum_InDebt() + "" +
						 ", Maximum_IsOverPay= " + t.getMaximum_IsOverPay() + "";
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
	public int Delete(Fixed_Values t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "DELETE from FIXED_VALUES ";
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
	public ArrayList<Fixed_Values> selectAll() {
		ArrayList<Fixed_Values> kq = new ArrayList<Fixed_Values>();
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM FIXED_VALUES";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int Maximum_Agent = rs.getInt("Maximum_Agent");
				BigDecimal Maximum_InDebt = rs.getBigDecimal("Maximum_InDebt");
				BigDecimal Maximum_IsOverPay = rs.getBigDecimal("Maximum_IsOverPay");
				
				Fixed_Values fixed_values = new Fixed_Values(Maximum_Agent, Maximum_InDebt, Maximum_IsOverPay);
				kq.add(fixed_values);
			}
			
			JDBCUtil.closeConnection(con);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public Fixed_Values seclectById(Fixed_Values t) {
		Fixed_Values kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM FIXED_VALUES where Maximum_Agent=" + t.getMaximum_Agent();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int Maximum_Agent = rs.getInt("Maximum_Agent");
				BigDecimal Maximum_InDebt = rs.getBigDecimal("Maximum_InDebt");
				BigDecimal Maximum_IsOverPay = rs.getBigDecimal("Maximum_IsOverPay");
				
				kq = new Fixed_Values(Maximum_Agent, Maximum_InDebt, Maximum_IsOverPay);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public ArrayList<Fixed_Values> selectByCondition(String condition) {
		ArrayList<Fixed_Values> kq = new ArrayList<>();
	    
	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();
	        
	        String sql = "SELECT * FROM FIXED_VALUES WHERE " + condition;
	        System.out.println(sql); 
	        
	        ResultSet rs = st.executeQuery(sql);
	        
	        while (rs.next()) {
				int Maximum_Agent = rs.getInt("Maximum_Agent");
				BigDecimal Maximum_InDebt = rs.getBigDecimal("Maximum_InDebt");
				BigDecimal Maximum_IsOverPay = rs.getBigDecimal("Maximum_IsOverPay");
	            
				Fixed_Values fixed_values = new Fixed_Values(Maximum_Agent, Maximum_InDebt, Maximum_IsOverPay);
	            kq.add(fixed_values);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}

}
