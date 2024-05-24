package Data_Access_Object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;
import models.Agent;
import models.Sales_Report_Detail;

public class DAO_Sales_Report_Detail implements Interface<Sales_Report_Detail> {

	public static DAO_Sales_Report_Detail getInstance() {
		return new DAO_Sales_Report_Detail();
	}
	
	@Override
	public int Add(Sales_Report_Detail t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "INSERT INTO SALES_REPORT_DETAIL(SalesReportDetail_Id, IdSalesReport, IdAgentType, AmountExport, TotalMoney, Rate)"
					+ "VALUES (" + t.getSalesReportDetail_Id() + " , " 
					+ t.getIdSalesReport() + " , " 
					+ t.getIdAgentType() + " , " 
					+ t.getAmountExport() + " , " 
					+ t.getTotalMoney() + " , " 
					+ t.getRate()+")";
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
	public int Update(Sales_Report_Detail t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "UPDATE SALES_REPORT_DETAIL "+
						 " SET "+
						 " IdSalesReport= " + t.getIdSalesReport() + "" +
						 ", IdAgentType= " + t.getIdAgentType() + "" +
						 ", AmountExport= " + t.getAmountExport() + "" +
						 ", TotalMoney= " + t.getTotalMoney() + "" + 
						 ", Rate= " + t.getRate() + "" +
						 " WHERE SalesReportDetail_Id= " + t.getSalesReportDetail_Id() + "" ;
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
	public int Delete(Sales_Report_Detail t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "DELETE from SALES_REPORT_DETAIL "+
						 " WHERE SalesReportDetail_Id= " + t.getSalesReportDetail_Id() + "" ;
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
	public ArrayList<Sales_Report_Detail> selectAll() {
		ArrayList<Sales_Report_Detail> kq = new ArrayList<Sales_Report_Detail>();
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM SALES_REPORT_DETAIL";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int SalesReportDetail_Id = rs.getInt("SalesReportDetail_Id");
				int IdSalesReport = rs.getInt("IdSalesReport");
				int IdAgentType = rs.getInt("IdAgentType");
				int AmountExport = rs.getInt("AmountExport");
				double TotalMoney = rs.getDouble("TotalMoney");
				float Rate = rs.getFloat("Rate");
				
				Sales_Report_Detail sale_report_detail = new Sales_Report_Detail(SalesReportDetail_Id, IdSalesReport, IdAgentType, AmountExport, TotalMoney, Rate);
				kq.add(sale_report_detail);
			}
			
			JDBCUtil.closeConnection(con);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public Sales_Report_Detail seclectById(Sales_Report_Detail t) {
		Sales_Report_Detail kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM SALES_REPORT_DETAIL where SalesReportDetail_Id=" + t.getSalesReportDetail_Id();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int SalesReportDetail_Id = rs.getInt("SalesReportDetail_Id");
				int IdSalesReport = rs.getInt("IdSalesReport");
				int IdAgentType = rs.getInt("IdAgentType");
				int AmountExport = rs.getInt("AmountExport");
				double TotalMoney = rs.getDouble("TotalMoney");
				float Rate = rs.getFloat("Rate");
				
				kq = new Sales_Report_Detail(SalesReportDetail_Id, IdSalesReport, IdAgentType, AmountExport, TotalMoney, Rate);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public ArrayList<Sales_Report_Detail> selectByCondition(String condition) {
		ArrayList<Sales_Report_Detail> kq = new ArrayList<>();
	    
	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();
	        
	        String sql = "SELECT * FROM SALES_REPORT_DETAIL WHERE " + condition;
	        System.out.println(sql); 
	        
	        ResultSet rs = st.executeQuery(sql);
	        
	        while (rs.next()) {
	        	int SalesReportDetail_Id = rs.getInt("SalesReportDetail_Id");
				int IdSalesReport = rs.getInt("IdSalesReport");
				int IdAgentType = rs.getInt("IdAgentType");
				int AmountExport = rs.getInt("AmountExport");
				double TotalMoney = rs.getDouble("TotalMoney");
				float Rate = rs.getFloat("Rate");
	            
				Sales_Report_Detail sale_report_detail = new Sales_Report_Detail(SalesReportDetail_Id, IdSalesReport, IdAgentType, AmountExport, TotalMoney, Rate);
	            kq.add(sale_report_detail);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}

}
