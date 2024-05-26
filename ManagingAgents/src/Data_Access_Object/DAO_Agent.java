package Data_Access_Object;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Result;

import Database.JDBCUtil;
import TestDatabase.TestJDCBC;
import javafx.scene.control.Alert;
import models.Agent;

public class DAO_Agent implements Interface<Agent> {
	public static DAO_Agent getInstance() {
		return new DAO_Agent();
	}
	
	@Override
	public int Add(Agent t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "INSERT INTO AGENT(Agent_Id, Agent_Name, Agent_Phone, Agent_Address, Agent_District, Agent_Debt, Agent_Type, Agent_Email, Agent_Status)"
					+ "VALUES (" + t.getAgent_Id() + " , '" 
					+ t.getAgent_Name() + "' , '" 
					+ t.getAgent_Phone() + "' , '" 
					+ t.getAgent_Address() + "' , '" 
					+ t.getAgent_District() + "' , "
							+ t.getAgent_Debt() + "' , "
									+ t.g() + "' , "
											+ t.getAgent_District() + "' , "
					+ t.getAgent_Debt()+")";
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
	public int Update(Agent t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "UPDATE AGENT "+
						 " SET "+
						 " Agent_Name= '" + t.getAgent_Name() + "'" +
						 ", Agent_Phone= '" + t.getAgent_Phone() + "'" +
						 ", Agent_Address= '" + t.getAgent_Address() + "'" +
						 ", Agent_District= '" + t.getAgent_District() + "'" + 
						 ", Agent_Debt= " + t.getAgent_Debt() + "" +
						 " WHERE Agent_Id= " + t.getAgent_Id() + "\'" ;
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
	public int Delete(Agent t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "DELETE from AGENT "+
						 " WHERE Agent_Id= " + t.getAgent_Id() + "" ;
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
	public ArrayList<Agent> selectAll() {
		ArrayList<Agent> kq = new ArrayList<Agent>();
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM AGENT";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int Agent_Id = rs.getInt("Agent_Id");
				String Agent_Name = rs.getString("Agent_Name");
				String Agent_Phone = rs.getString("Agent_Phone");
				String Agent_Address = rs.getString("Agent_Address");
				String Agent_District = rs.getString("Agent_District");
				double Agent_Debt = rs.getDouble("Agent_Debt");
				String Agent_Type = rs.getString("Agent_Type");
				String Agent_Email = rs.getString("Agent_Email");
				boolean Agent_Status = rs.getBoolean("Agent_Status");
				
				Agent agent = new Agent(Agent_Id, Agent_Name, Agent_Phone, Agent_Address, Agent_District, Agent_Debt, Agent_Type, Agent_Email,Agent_Status);
				kq.add(agent);
			}
			
			JDBCUtil.closeConnection(con);	
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText(null);
	        alert.setContentText("An error occurred while fetching data from the database. Please try again later.");
	        alert.showAndWait();
		}
		return kq;
	}

	@Override
	public Agent seclectById(Agent t) {
		Agent kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM AGENT where Agent_Id=" + t.getAgent_Id();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int Agent_Id = rs.getInt("Agent_Id");
				String Agent_Name = rs.getString("Agent_Name");
				String Agent_Phone = rs.getString("Agent_Phone");
				String Agent_Address = rs.getString("Agent_Address");
				String Agent_District = rs.getString("Agent_District");
				double Agent_Debt = rs.getDouble("Agent_Debt");
				String Agent_Type = rs.getString("Agent_Type");
				String Agent_Email = rs.getString("Agent_Email");
				boolean Agent_Status = rs.getBoolean("Agent_Status");
				
				kq = new Agent(Agent_Id, Agent_Name, Agent_Phone, Agent_Address, Agent_District, Agent_Debt, Agent_Type, Agent_Email,Agent_Status);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}
	
	@Override
	public ArrayList<Agent> selectByCondition(String condition) {
		ArrayList<Agent> kq = new ArrayList<>();
	    
	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();
	        
	        String sql = "SELECT * FROM AGENT WHERE " + condition;
	        System.out.println(sql); 
	        
	        ResultSet rs = st.executeQuery(sql);
	        
	        while (rs.next()) {
	            int Agent_Id = rs.getInt("Agent_Id");
	            String Agent_Name = rs.getString("Agent_Name");
	            String Agent_Phone = rs.getString("Agent_Phone");
	            String Agent_Address = rs.getString("Agent_Address");
	            String Agent_District = rs.getString("Agent_District");
	            double Agent_Debt = rs.getDouble("Agent_Debt");
	            String Agent_Type = rs.getString("Agent_Type");
				String Agent_Email = rs.getString("Agent_Email");
				boolean Agent_Status = rs.getBoolean("Agent_Status");
	            
	            Agent agent = new Agent(Agent_Id, Agent_Name, Agent_Phone, Agent_Address, Agent_District, Agent_Debt, Agent_Type, Agent_Email,Agent_Status);
	            kq.add(agent);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}
	
	public ResultSet getCurrentId() throws SQLException {
		
		Connection connect = JDBCUtil.getConnection();
        String sql = "SELECT MAX(Agent_Id) FROM agent"; 
        PreparedStatement statement = connect.prepareStatement(sql);
        return statement.executeQuery();
    }
	
	public String getAgentNameById(int agentId) {
	    String agentName = null;
	    try {
	        Connection con = JDBCUtil.getConnection();
	        String sql = "SELECT Agent_Name FROM AGENT WHERE Agent_Id = ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setInt(1, agentId);
	        ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	            agentName = rs.getString("Agent_Name");
	        }
	        JDBCUtil.closeConnection(con);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return agentName;
	}
	
	public String getAgentPhoneById(int agentId) {
	    String agentPhone = null;
	    try {
	        Connection con = JDBCUtil.getConnection();
	        String sql = "SELECT Agent_Phone FROM AGENT WHERE Agent_Id = ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setInt(1, agentId);
	        ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	            agentPhone = rs.getString("Agent_Phone");
	        }
	        JDBCUtil.closeConnection(con);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return agentPhone;
	}

	public String getAgentAddressById(int agentId) {
	    String agentAddress = null;
	    try {
	        Connection con = JDBCUtil.getConnection();
	        String sql = "SELECT Agent_Address FROM AGENT WHERE Agent_Id = ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setInt(1, agentId);
	        ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	            agentAddress = rs.getString("Agent_Address");
	        }
	        JDBCUtil.closeConnection(con);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return agentAddress;
	}

	public String getAgentDistrictById(int agentId) {
	    String agentDistrict = null;
	    try {
	        Connection con = JDBCUtil.getConnection();
	        String sql = "SELECT Agent_District FROM AGENT WHERE Agent_Id = ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setInt(1, agentId);
	        ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	            agentDistrict = rs.getString("Agent_District");
	        }
	        JDBCUtil.closeConnection(con);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return agentDistrict;
	}

	public double getAgentDebtById(int agentId) {
	    double agentDebt = 0.0;
	    try {
	        Connection con = JDBCUtil.getConnection();
	        String sql = "SELECT Agent_Debt FROM AGENT WHERE Agent_Id = ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setInt(1, agentId);
	        ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	            agentDebt = rs.getDouble("Agent_Debt");
	        }
	        JDBCUtil.closeConnection(con);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return agentDebt;
	}


	
}
