package Data_Access_Object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;
import models.Debt_Agent_Report_Detail;

public class DAO_Debt_Agent_Report_Detail implements Interface<Debt_Agent_Report_Detail> {
	public static DAO_Debt_Agent_Report_Detail getInstance() {
		return new DAO_Debt_Agent_Report_Detail();
	}
	
	@Override
	public int Add(Debt_Agent_Report_Detail t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "INSERT INTO DEBT_AGENT_REPORT_DETAIL(DebtAgentReportDetail_Id, IdDebtAgentReport, IdAgent, NoDau, PhatSinh, NoCuoi)"
					+ "VALUES (" + t.getDebtAgentReportDetail_Id() + " , " 
					+ t.getIdDebtAgentReport() + " , " 
					+ t.getIdAgent() + " , " 
					+ t.getNoDau() + " , " 
					+ t.getPhatSinh() + " , " 
					+ t.getNoCuoi()+")";
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
	public int Update(Debt_Agent_Report_Detail t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "UPDATE DEBT_AGENT_REPORT_DETAIL "+
						 " SET "+
						 " IdDebtAgentReport= " + t.getIdDebtAgentReport() + "" +
						 ", IdAgent= " + t.getIdAgent() + "" +
						 ", NoDau= " + t.getNoDau() + "" +
						 ", PhatSinh= " + t.getPhatSinh() + "" + 
						 ", NoCuoi= " + t.getNoCuoi() + "" +
						 " WHERE DebtAgentReportDetail_Id= " + t.getDebtAgentReportDetail_Id() + "" ;
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
	public int Delete(Debt_Agent_Report_Detail t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "DELETE from DEBT_AGENT_REPORT_DETAIL "+
						 " WHERE DebtAgentReportDetail_Id= " + t.getDebtAgentReportDetail_Id() + "" ;
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
	public ArrayList<Debt_Agent_Report_Detail> selectAll() {
		ArrayList<Debt_Agent_Report_Detail> kq = new ArrayList<Debt_Agent_Report_Detail>();
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM DEBT_AGENT_REPORT_DETAIL";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int DebtAgentReportDetail_Id = rs.getInt("DebtAgentReportDetail_Id");
				int IdDebtAgentReport = rs.getInt("IdDebtAgentReport");
				int IdAgent = rs.getInt("IdAgent");
				double NoDau = rs.getDouble("NoDau");
				double PhatSinh = rs.getDouble("PhatSinh");
				double NoCuoi = rs.getDouble("NoCuoi");
				
				Debt_Agent_Report_Detail debt_agent_report_detail = new Debt_Agent_Report_Detail(DebtAgentReportDetail_Id, IdDebtAgentReport, IdAgent, NoDau, PhatSinh, NoCuoi);
				kq.add(debt_agent_report_detail);
			}
			
			JDBCUtil.closeConnection(con);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public Debt_Agent_Report_Detail seclectById(Debt_Agent_Report_Detail t) {
		Debt_Agent_Report_Detail kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM DEBT_AGENT_REPORT_DETAIL where DebtAgentReportDetail_Id=" + t.getDebtAgentReportDetail_Id();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int DebtAgentReportDetail_Id = rs.getInt("DebtAgentReportDetail_Id");
				int IdDebtAgentReport = rs.getInt("IdDebtAgentReport");
				int IdAgent = rs.getInt("IdAgent");
				double NoDau = rs.getDouble("NoDau");
				double PhatSinh = rs.getDouble("PhatSinh");
				double NoCuoi = rs.getDouble("NoCuoi");
				
				kq = new Debt_Agent_Report_Detail(DebtAgentReportDetail_Id, IdDebtAgentReport, IdAgent, NoDau, PhatSinh, NoCuoi);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public ArrayList<Debt_Agent_Report_Detail> selectByCondition(String condition) {
		ArrayList<Debt_Agent_Report_Detail> kq = new ArrayList<>();
	    
	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();
	        
	        String sql = "SELECT * FROM DEBT_AGENT_REPORT_DETAIL WHERE " + condition;
	        System.out.println(sql); 
	        
	        ResultSet rs = st.executeQuery(sql);
	        
	        while (rs.next()) {
	        	int DebtAgentReportDetail_Id = rs.getInt("DebtAgentReportDetail_Id");
				int IdDebtAgentReport = rs.getInt("IdDebtAgentReport");
				int IdAgent = rs.getInt("IdAgent");
				double NoDau = rs.getDouble("NoDau");
				double PhatSinh = rs.getDouble("PhatSinh");
				double NoCuoi = rs.getDouble("NoCuoi");
	            
	            Debt_Agent_Report_Detail debt_agent_report_detail = new Debt_Agent_Report_Detail(DebtAgentReportDetail_Id, IdDebtAgentReport, IdAgent, NoDau, PhatSinh, NoCuoi);
	            kq.add(debt_agent_report_detail);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}
	

}
