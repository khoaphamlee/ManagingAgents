package Data_Access_Object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.JDBCUtil;
import models.Agent_Type;

public class DAO_Agent_Type implements Interface<Agent_Type> {
	
	public static DAO_Agent_Type getInstance() {
		return new DAO_Agent_Type();
	}
	
	@Override
	public int Add(Agent_Type t) {
        String sql = "INSERT INTO AGENT_TYPE (AgentType_Id, AgentType_Name, AgentType_MaxDebt, AgentType_Status) VALUES (?, ?, ?, ?)";
        
        try {
            Connection connect = JDBCUtil.getConnection();
            PreparedStatement ps = connect.prepareStatement(sql);

            // Set the values for the prepared statement
            ps.setInt(1, t.getAgentType_Id());
            ps.setString(2, t.getAgentType_Name());
            ps.setLong(3, t.getAgentType_MaxDebt());
            ps.setInt(4, t.getAgentType_Status()); 

            int kq = ps.executeUpdate();

            System.out.println("Bạn đã thực thi");
            JDBCUtil.closeConnection(connect);

            return kq;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Trả về -1 nếu có lỗi xảy ra
        }
    }

	@Override
	public int Update(Agent_Type t) {
	    try {
	        Connection connect = JDBCUtil.getConnection();
	        Statement st = connect.createStatement();

	        String sql = "UPDATE AGENT_TYPE " +
	                     "SET " +
	                     "AgentType_Name = '" + t.getAgentType_Name() + "', " +
	                     "AgentType_MaxDebt = " + t.getAgentType_MaxDebt() + ", " +
	                     "AgentType_Status = " + t.getAgentType_Status() + " " + // Sử dụng giá trị int
	                     "WHERE AgentType_Id = " + t.getAgentType_Id();
	        int kq = st.executeUpdate(sql);

	        System.out.println("Bạn đã thực thi");
	        JDBCUtil.closeConnection(connect);

	        return kq;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return -1; // Trả về -1 nếu có lỗi xảy ra
	    }
	}

	@Override
	public int Delete(Agent_Type t) {
	    try {
	        Connection connect = JDBCUtil.getConnection();
	        Statement st = connect.createStatement();

	        String sql = "DELETE from AGENT_TYPE " +
	                     "WHERE AgentType_Id = " + t.getAgentType_Id();
	        int kq = st.executeUpdate(sql);

	        System.out.println("Bạn đã thực thi");
	        JDBCUtil.closeConnection(connect);

	        return kq;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return -1; // Trả về -1 nếu có lỗi xảy ra
	    }
	}

	@Override
	public ArrayList<Agent_Type> selectAll() {
	    ArrayList<Agent_Type> kq = new ArrayList<Agent_Type>();
	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();

	        String sql = "SELECT * FROM AGENT_TYPE";
	        System.out.println(sql);
	        ResultSet rs = st.executeQuery(sql);

	        while (rs.next()) {
	            int AgentType_Id = rs.getInt("AgentType_Id");
	            String AgentType_Name = rs.getString("AgentType_Name");
	            long AgentType_MaxDebt = rs.getLong("AgentType_MaxDebt");
	            int AgentType_Status = rs.getInt("AgentType_Status"); // Lấy giá trị int trực tiếp

	            Agent_Type agentType = new Agent_Type(AgentType_Id, AgentType_Name, AgentType_MaxDebt, AgentType_Status);
	            kq.add(agentType);
	        }

	        JDBCUtil.closeConnection(con);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return kq;
	}

	@Override
	public Agent_Type seclectById(Agent_Type t) {
		Agent_Type kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM AGENT_TYPE where AgentType_Id=" + t.getAgentType_Id();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int AgentType_Id = rs.getInt("AgentType_Id");
				String AgentType_Name = rs.getString("AgentType_Name");
				long AgentType_MaxDebt = rs.getLong("AgentType_MaxDebt");
				int AgentType_Status = rs.getInt("AgentType_Status"); // Lấy giá trị int trực tiếp
				
				kq = new Agent_Type(AgentType_Id, AgentType_Name, AgentType_MaxDebt, AgentType_Status);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public ArrayList<Agent_Type> selectByCondition(String condition) {
		ArrayList<Agent_Type> kq = new ArrayList<Agent_Type>();
	    
	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();
	        
	        String sql = "SELECT * FROM AGENT_TYPE WHERE " + condition;
	        System.out.println(sql); 
	        
	        ResultSet rs = st.executeQuery(sql);
	        
	        while (rs.next()) {
	        	int AgentType_Id = rs.getInt("AgentType_Id");
				String AgentType_Name = rs.getString("AgentType_Name");
				long AgentType_MaxDebt = rs.getLong("AgentType_MaxDebt");
				int AgentType_Status = rs.getInt("AgentType_Status"); // Lấy giá trị int trực tiếp
				
				Agent_Type agentType = new Agent_Type(AgentType_Id, AgentType_Name, AgentType_MaxDebt, AgentType_Status);
				kq.add(agentType);
	        }
	        
	        JDBCUtil.closeConnection(con); 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}
	
	public int getAgentTypeIdByName(String agentTypeName) {
	    int agentTypeId = -1; //

	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();

	        String sql = "SELECT AgentType_Id FROM AGENT_TYPE WHERE AgentType_Name = '" + agentTypeName + "'";
	        System.out.println(sql);
	        ResultSet rs = st.executeQuery(sql);

	        if (rs.next()) {
	            agentTypeId = rs.getInt("AgentType_Id");
	        }

	        JDBCUtil.closeConnection(con);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return agentTypeId;
	}

	public ResultSet getCurrentId() throws SQLException {
		
		Connection connect = JDBCUtil.getConnection();
        String sql = "SELECT MAX(AgentType_Id) FROM agent_type"; 
        PreparedStatement statement = connect.prepareStatement(sql);
        return statement.executeQuery();
    }
	
	public List<String> getTypeNamesFromDatabase() {
        List<String> typeNames = new ArrayList<>();

        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();

            String sql = "SELECT AgentType_Name FROM AGENT_TYPE";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String typeName = rs.getString("AgentType_Name");
                typeNames.add(typeName);
            }

            JDBCUtil.closeConnection(connect);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return typeNames;
    }
	
	public String getAgentTypeNameById(int agentTypeId) {
	    String agentTypeName = null;
	    try {
	        Connection con = JDBCUtil.getConnection();
	        String sql = "SELECT AgentType_Name FROM AGENT_TYPE WHERE AgentType_Id = ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setInt(1, agentTypeId);
	        ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	            agentTypeName = rs.getString("AgentType_Name");
	        }
	        JDBCUtil.closeConnection(con);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return agentTypeName;
	}
}
