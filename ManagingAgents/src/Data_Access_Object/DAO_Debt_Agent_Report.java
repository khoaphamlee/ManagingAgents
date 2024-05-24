package Data_Access_Object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;
import models.Agent;
import models.Debt_Agent_Report;

public class DAO_Debt_Agent_Report implements Interface<Debt_Agent_Report>{

	public static DAO_Debt_Agent_Report getInstance() {
		return new DAO_Debt_Agent_Report();
	}
	
	@Override
	public int Add(Debt_Agent_Report t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "INSERT INTO DEBT_AGENT_REPORT(DebtAgentReport_Id, DebtAgentReport_Date)"
					+ "VALUES (" + t.getDebtAgentReport_Id() + " , '" 
					+ t.getDebtAgentReport_Date() +"')";
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
	public int Update(Debt_Agent_Report t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "UPDATE DEBT_AGENT_REPORT "+
						 " SET "+
						 " DebtAgentReport_Date= '" + t.getDebtAgentReport_Date() + "'" +
						 " WHERE DebtAgentReport_Id= " + t.getDebtAgentReport_Id() + "" ;
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
	public int Delete(Debt_Agent_Report t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "DELETE from DEBT_AGENT_REPORT "+
						 " WHERE DebtAgentReport_Id= " + t.getDebtAgentReport_Id() + "" ;
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
	public ArrayList<Debt_Agent_Report> selectAll() {
		ArrayList<Debt_Agent_Report> kq = new ArrayList<Debt_Agent_Report>();
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM DEBT_AGENT_REPORT";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int DebtAgentReport_Id = rs.getInt("DebtAgentReport_Id");
				Date DebtAgentReport_Date = rs.getDate("DebtAgentReport_Date");
				
				Debt_Agent_Report debt_agent_report = new Debt_Agent_Report(DebtAgentReport_Id, DebtAgentReport_Date);
				kq.add(debt_agent_report);
			}
			
			JDBCUtil.closeConnection(con);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public Debt_Agent_Report seclectById(Debt_Agent_Report t) {
		Debt_Agent_Report kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM DEBT_AGENT_REPORT where DebtAgentReport_Id=" + t.getDebtAgentReport_Id();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int DebtAgentReport_Id = rs.getInt("DebtAgentReport_Id");
				Date DebtAgentReport_Date = rs.getDate("DebtAgentReport_Date");
				
				kq = new Debt_Agent_Report(DebtAgentReport_Id, DebtAgentReport_Date);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public ArrayList<Debt_Agent_Report> selectByCondition(String condition) {
		ArrayList<Debt_Agent_Report> kq = new ArrayList<>();
	    
	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();
	        
	        String sql = "SELECT * FROM DEBT_AGENT_REPORT WHERE " + condition;
	        System.out.println(sql); 
	        
	        ResultSet rs = st.executeQuery(sql);
	        
	        while (rs.next()) {
	        	int DebtAgentReport_Id = rs.getInt("DebtAgentReport_Id");
				Date DebtAgentReport_Date = rs.getDate("DebtAgentReport_Date");
	            
	            Debt_Agent_Report debt_agent_report = new Debt_Agent_Report(DebtAgentReport_Id, DebtAgentReport_Date);
	            kq.add(debt_agent_report);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}

}
