package Data_Access_Object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Agent_Type;

public class DAO_Agent_Type implements Interface<Agent_Type> {
    
    public static DAO_Agent_Type getInstance() {
        return new DAO_Agent_Type();
    }
    
    @Override
    public int Add(Agent_Type t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            String sql = "INSERT INTO AGENT_TYPE ( AgentType_Name, AgentType_MaxDebt, AgentType_Status) VALUES (?, ?, ?)";
            PreparedStatement ps = connect.prepareStatement(sql);
            
            ps.setString(1, t.getAgentType_Name());
            ps.setDouble(2, t.getAgentType_MaxDebt());
            ps.setInt(3, t.getAgentType_Status()); 

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
            String sql = "UPDATE AGENT_TYPE SET AgentType_Name = ?, AgentType_MaxDebt = ?, AgentType_Status = ? WHERE AgentType_Id = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, t.getAgentType_Name());
            ps.setDouble(2, t.getAgentType_MaxDebt());
            ps.setInt(3, t.getAgentType_Status());
            ps.setInt(4, t.getAgentType_Id());

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
    public int Delete(Agent_Type t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            String sql = "DELETE FROM AGENT_TYPE WHERE AgentType_Id = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, t.getAgentType_Id());

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
    public ArrayList<Agent_Type> selectAll() {
        ArrayList<Agent_Type> kq = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM AGENT_TYPE";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int AgentType_Id = rs.getInt("AgentType_Id");
                String AgentType_Name = rs.getString("AgentType_Name");
                double AgentType_MaxDebt = rs.getDouble("AgentType_MaxDebt");
                int AgentType_Status = rs.getInt("AgentType_Status");

                Agent_Type agentType = new Agent_Type(AgentType_Id, AgentType_Name, AgentType_MaxDebt, AgentType_Status);
                kq.add(agentType);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    public ObservableList<Agent_Type> selectAll2() {
    	ObservableList<Agent_Type> kq = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM AGENT_TYPE";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int AgentType_Id = rs.getInt("AgentType_Id");
                String AgentType_Name = rs.getString("AgentType_Name");
                double AgentType_MaxDebt = rs.getDouble("AgentType_MaxDebt");
                int AgentType_Status = rs.getInt("AgentType_Status");

                Agent_Type agentType = new Agent_Type(AgentType_Id, AgentType_Name, AgentType_MaxDebt, AgentType_Status);
                kq.add(agentType);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    
    @Override
    public Agent_Type seclectById(Agent_Type t) {
        Agent_Type kq = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM AGENT_TYPE WHERE AgentType_Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, t.getAgentType_Id());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int AgentType_Id = rs.getInt("AgentType_Id");
                String AgentType_Name = rs.getString("AgentType_Name");
                double AgentType_MaxDebt = rs.getDouble("AgentType_MaxDebt");
                int AgentType_Status = rs.getInt("AgentType_Status");

                kq = new Agent_Type(AgentType_Id, AgentType_Name, AgentType_MaxDebt, AgentType_Status);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    
    public Agent_Type seclectById2(int t) {
        Agent_Type kq = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM AGENT_TYPE WHERE AgentType_Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, t);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int AgentType_Id = rs.getInt("AgentType_Id");
                String AgentType_Name = rs.getString("AgentType_Name");
                double AgentType_MaxDebt = rs.getDouble("AgentType_MaxDebt");
                int AgentType_Status = rs.getInt("AgentType_Status");

                kq = new Agent_Type(AgentType_Id, AgentType_Name, AgentType_MaxDebt, AgentType_Status);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public ArrayList<Agent_Type> selectByCondition(String condition) {
        ArrayList<Agent_Type> kq = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM AGENT_TYPE WHERE " + condition;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int AgentType_Id = rs.getInt("AgentType_Id");
                String AgentType_Name = rs.getString("AgentType_Name");
                double AgentType_MaxDebt = rs.getDouble("AgentType_MaxDebt");
                int AgentType_Status = rs.getInt("AgentType_Status");

                Agent_Type agentType = new Agent_Type(AgentType_Id, AgentType_Name, AgentType_MaxDebt, AgentType_Status);
                kq.add(agentType);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    public int getAgentTypeIdByName(String agentTypeName) {
        int agentTypeId = -1;

        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT AgentType_Id FROM AGENT_TYPE WHERE AgentType_Name = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, agentTypeName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                agentTypeId = rs.getInt("AgentType_Id");
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agentTypeId;
    }

    public ResultSet getCurrentId() throws SQLException {
        Connection connect = JDBCUtil.getConnection();
        String sql = "SELECT MAX(AgentType_Id) FROM AGENT_TYPE"; 
        PreparedStatement statement = connect.prepareStatement(sql);
        return statement.executeQuery();
    }
    
    public List<String> getTypeNamesFromDatabase() {
        List<String> typeNames = new ArrayList<>();

        try {
            Connection connect = JDBCUtil.getConnection();
            String sql = "SELECT AgentType_Name FROM AGENT_TYPE";
            Statement st = connect.createStatement();
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
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, agentTypeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                agentTypeName = rs.getString("AgentType_Name");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agentTypeName;
    }
}
