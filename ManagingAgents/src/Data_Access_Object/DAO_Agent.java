package Data_Access_Object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.JDBCUtil;
import models.Agent;

public class DAO_Agent implements Interface<Agent> {
    
    public static DAO_Agent getInstance() {
        return new DAO_Agent();
    }
    
    @Override
    public int Add(Agent agent) {
        try {
            Connection connect = JDBCUtil.getConnection();
            String sql = "INSERT INTO agent ( Agent_Name, Agent_Phone, Agent_Address, district_Id, Agent_Status, Agent_Debt, AgentType_Id, Agent_Email) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connect.prepareStatement(sql);
           
            ps.setString(1, agent.getAgent_Name());
            ps.setString(2, agent.getAgent_Phone());
            ps.setString(3, agent.getAgent_Address());
            ps.setInt(4, agent.getAgent_District());
            ps.setInt(5, agent.getAgent_Status());
            ps.setDouble(6, agent.getAgent_Debt());
            ps.setInt(7, agent.getAgent_Type());
            ps.setString(8, agent.getAgent_Email());

            int result = ps.executeUpdate();
            System.out.println("Bạn đã thực thi");
            JDBCUtil.closeConnection(connect);

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Trả về -1 nếu có lỗi xảy ra
        }
    }

    @Override
    public int Update(Agent agent) {
        try {
            Connection connect = JDBCUtil.getConnection();
            String sql = "UPDATE agent SET Agent_Name = ?, Agent_Phone = ?, Agent_Address = ?, district_Id = ?, Agent_Status = ?, Agent_Debt = ?, AgentType_Id = ?, Agent_Email = ? WHERE Agent_Id = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, agent.getAgent_Name());
            ps.setString(2, agent.getAgent_Phone());
            ps.setString(3, agent.getAgent_Address());
            ps.setInt(4, agent.getAgent_District());
            ps.setInt(5, agent.getAgent_Status());
            ps.setDouble(6, agent.getAgent_Debt());
            ps.setInt(7, agent.getAgent_Type());
            ps.setString(8, agent.getAgent_Email());
            ps.setInt(9, agent.getAgent_Id());

            int result = ps.executeUpdate();
            System.out.println("Bạn đã thực thi");
            JDBCUtil.closeConnection(connect);

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Trả về -1 nếu có lỗi xảy ra
        }
    }

    @Override
    public int Delete(Agent agent) {
        try {
            Connection connect = JDBCUtil.getConnection();
            String sql = "DELETE FROM agent WHERE Agent_Id = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, agent.getAgent_Id());

            int result = ps.executeUpdate();
            System.out.println("Bạn đã thực thi");
            JDBCUtil.closeConnection(connect);

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Trả về -1 nếu có lỗi xảy ra
        }
    }

    @Override
    public ArrayList<Agent> selectAll() {
        ArrayList<Agent> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM agent";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
            	int Agent_Id = rs.getInt("Agent_Id");
                String Agent_Name = rs.getString("Agent_Name");
                String Agent_Phone = rs.getString("Agent_Phone");
                String Agent_Address = rs.getString("Agent_Address");
                int Agent_District = rs.getInt("district_Id");
                int Agent_Status = rs.getInt("Agent_Status");
                double Agent_Debt = rs.getDouble("Agent_Debt");
               int  Agent_Type = rs.getInt("AgentType_Id");
                String Agent_Email = rs.getString("Agent_Email");

                Agent agent = new Agent(Agent_Id, Agent_Name, Agent_Phone, Agent_Address, Agent_District, Agent_Status, Agent_Debt, Agent_Type, Agent_Email);
                result.add(agent);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Agent seclectById(Agent agent) {
        Agent result = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM agent WHERE Agent_Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, agent.getAgent_Id());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	int Agent_Id = rs.getInt("Agent_Id");
                String Agent_Name = rs.getString("Agent_Name");
                String Agent_Phone = rs.getString("Agent_Phone");
                String Agent_Address = rs.getString("Agent_Address");
                int Agent_District = rs.getInt("district_Id");
                int Agent_Status = rs.getInt("Agent_Status");
                double Agent_Debt = rs.getDouble("Agent_Debt");
               int  Agent_Type = rs.getInt("AgentType_Id");
                String Agent_Email = rs.getString("Agent_Email");

                result = new Agent(Agent_Id, Agent_Name, Agent_Phone, Agent_Address, Agent_District, Agent_Status, Agent_Debt, Agent_Type, Agent_Email);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Agent> selectByCondition(String condition) {
        ArrayList<Agent> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM agent WHERE " + condition;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
            	int Agent_Id = rs.getInt("Agent_Id");
                String Agent_Name = rs.getString("Agent_Name");
                String Agent_Phone = rs.getString("Agent_Phone");
                String Agent_Address = rs.getString("Agent_Address");
                int Agent_District = rs.getInt("district_Id");
                int Agent_Status = rs.getInt("Agent_Status");
                double Agent_Debt = rs.getDouble("Agent_Debt");
               int  Agent_Type = rs.getInt("AgentType_Id");
                String Agent_Email = rs.getString("Agent_Email");

                Agent agent = new Agent(Agent_Id, Agent_Name, Agent_Phone, Agent_Address, Agent_District, Agent_Status, Agent_Debt, Agent_Type, Agent_Email);
                result.add(agent);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    public Agent selectByCondition2(String condition) {
        Agent result = new Agent();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM agent WHERE " + condition;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
            	int Agent_Id = rs.getInt("Agent_Id");
                String Agent_Name = rs.getString("Agent_Name");
                String Agent_Phone = rs.getString("Agent_Phone");
                String Agent_Address = rs.getString("Agent_Address");
                int Agent_District = rs.getInt("district_Id");
                int Agent_Status = rs.getInt("Agent_Status");
                double Agent_Debt = rs.getDouble("Agent_Debt");
               int  Agent_Type = rs.getInt("AgentType_Id");
                String Agent_Email = rs.getString("Agent_Email");

                Agent agent = new Agent(Agent_Id, Agent_Name, Agent_Phone, Agent_Address, Agent_District, Agent_Status, Agent_Debt, Agent_Type, Agent_Email);
                result = agent;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getAgentIdByName(String agentName) {
        int agentId = -1;

        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT Agent_Id FROM agent WHERE Agent_Name = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, agentName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                agentId = rs.getInt("Agent_Id");
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agentId;
    }

    public ResultSet getCurrentId() throws SQLException {
        Connection connect = JDBCUtil.getConnection();
        String sql = "SELECT MAX(Agent_Id) FROM agent"; 
        PreparedStatement statement = connect.prepareStatement(sql);
        return statement.executeQuery();
    }
    
    public List<String> getNamesFromDatabase() {
        List<String> names = new ArrayList<>();

        try {
            Connection connect = JDBCUtil.getConnection();
            String sql = "SELECT Agent_Name FROM agent";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("Agent_Name");
                names.add(name);
            }

            JDBCUtil.closeConnection(connect);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return names;
    }
    
    public String getAgentNameById(int agentId) {
        String agentName = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT Agent_Name FROM agent WHERE Agent_Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, agentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                agentName = rs.getString("Agent_Name");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agentName;
    }
    
    public String getAgentPhoneById(int agentId) {
        String phone = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT Agent_Phone FROM agent WHERE Agent_Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, agentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                phone = rs.getString("Agent_Phone");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phone;
    }

    public String getAgentAddressById(int agentId) {
        String address = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT Agent_Address FROM agent WHERE Agent_Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, agentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                address = rs.getString("Agent_Address");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }

    public String getAgentDistrictById(int agentId) {
        String district = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT Agent_District FROM agent WHERE Agent_Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, agentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                district = rs.getString("Agent_District");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return district;
    }

    public double getAgentDebtById(int agentId) {
        double debt = -1;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT Agent_Debt FROM agent WHERE Agent_Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, agentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                debt = rs.getDouble("Agent_Debt");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return debt;
    }

    public int getAgentStatusById(int agentId) {
        int status = -1;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT Agent_Status FROM agent WHERE Agent_Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, agentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                status = rs.getInt("Agent_Status");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public String getAgentEmailById(int agentId) {
        String email = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT Agent_Email FROM agent WHERE Agent_Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, agentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                email = rs.getString("Agent_Email");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return email;
    }

}
