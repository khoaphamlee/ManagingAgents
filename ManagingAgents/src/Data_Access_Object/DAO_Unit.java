package Data_Access_Object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;

import Database.JDBCUtil;
<<<<<<< HEAD
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
=======
<<<<<<< HEAD
=======

>>>>>>> 906790c5ce4401371dc295a95bc25ebb2a8f9660
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
import models.Unit;

public class DAO_Unit implements Interface<Unit> {
    public static DAO_Unit getInstance() {
        return new DAO_Unit();
    }

    @Override
    public int Add(Unit t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
<<<<<<< HEAD
            String sql = "INSERT INTO UNIT( Unit_Name, Unit_Status) " +
                         "VALUES (' "  + t.getUnit_Name() + "', " + t.getUnit_Status() + ")";
=======
            String sql = "INSERT INTO UNIT(Unit_Id, Unit_Name, Unit_Status) " +
                         "VALUES (" + t.getUnit_Id() + " , '" + t.getUnit_Name() + "', " + t.getUnit_Status() + ")";
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi");
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int Update(Unit t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "UPDATE UNIT " +
                         " SET Unit_Name= '" + t.getUnit_Name() + "', " +
                         " Unit_Status= " + t.getUnit_Status() + 
                         " WHERE Unit_Id= " + t.getUnit_Id();
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi");
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int Delete(Unit t) {
        int result = 0;
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "DELETE from UNIT WHERE Unit_Id= " + t.getUnit_Id();
            result = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi");
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Unit> selectAll() {
        ArrayList<Unit> kq = new ArrayList<Unit>();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM UNIT";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int Unit_Id = rs.getInt("Unit_Id");
                String Unit_Name = rs.getString("Unit_Name");
                int Unit_Status = rs.getInt("Unit_Status");
                Unit unit = new Unit(Unit_Id, Unit_Name, Unit_Status);
                kq.add(unit);
            }
            JDBCUtil.closeConnection(con);    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
<<<<<<< HEAD
    
    
    public ObservableList<Unit> selectAll2() {
    	ObservableList<Unit> kq = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM UNIT";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int Unit_Id = rs.getInt("Unit_Id");
                String Unit_Name = rs.getString("Unit_Name");
                int Unit_Status = rs.getInt("Unit_Status");
                Unit unit = new Unit(Unit_Id, Unit_Name, Unit_Status);
                kq.add(unit);
            }
            JDBCUtil.closeConnection(con);    
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public Unit seclectById(Unit t) {
        Unit kq = null;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM UNIT where Unit_Id=" + t.getUnit_Id();
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int Unit_Id = rs.getInt("Unit_Id");
                String Unit_Name = rs.getString("Unit_Name");
                int Unit_Status = rs.getInt("Unit_Status");
                kq = new Unit(Unit_Id, Unit_Name, Unit_Status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public ArrayList<Unit> selectByCondition(String condition) {
        ArrayList<Unit> kq = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM UNIT WHERE " + condition;
            System.out.println(sql); 
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int Unit_Id = rs.getInt("Unit_Id");
                String Unit_Name = rs.getString("Unit_Name");
                int Unit_Status = rs.getInt("Unit_Status");
                Unit unit = new Unit(Unit_Id, Unit_Name, Unit_Status);
                kq.add(unit);
            }
            JDBCUtil.closeConnection(con);     
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    public Unit selectByCondition2(String condition) {
        Unit kq = new Unit();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM UNIT WHERE " + condition;
            System.out.println(sql); 
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int Unit_Id = rs.getInt("Unit_Id");
                String Unit_Name = rs.getString("Unit_Name");
                int Unit_Status = rs.getInt("Unit_Status");
                Unit unit = new Unit(Unit_Id, Unit_Name, Unit_Status);
                kq= unit;
            }
            JDBCUtil.closeConnection(con);     
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public ResultSet getCurrentId() throws SQLException {
        Connection con = JDBCUtil.getConnection();
        Statement st = con.createStatement();
        String sql = "SELECT MAX(Unit_Id) FROM UNIT";
        return st.executeQuery(sql);
    }
    
    public ArrayList<String> getAllUnitNames() {
        ArrayList<String> unitNames = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT Unit_Name FROM UNIT";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String Unit_Name = rs.getString("Unit_Name");
                unitNames.add(Unit_Name);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unitNames;
    }
    
    public int getIdByUnitName(String unitName) {
        int unitId = -1;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT Unit_Id FROM UNIT WHERE Unit_Name = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, unitName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                unitId = rs.getInt("Unit_Id");
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unitId;
    }

=======

    @Override
    public Unit seclectById(Unit t) {
        Unit kq = null;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM UNIT where Unit_Id=" + t.getUnit_Id();
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int Unit_Id = rs.getInt("Unit_Id");
                String Unit_Name = rs.getString("Unit_Name");
                int Unit_Status = rs.getInt("Unit_Status");
                kq = new Unit(Unit_Id, Unit_Name, Unit_Status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public ArrayList<Unit> selectByCondition(String condition) {
        ArrayList<Unit> kq = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM UNIT WHERE " + condition;
            System.out.println(sql); 
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int Unit_Id = rs.getInt("Unit_Id");
                String Unit_Name = rs.getString("Unit_Name");
                int Unit_Status = rs.getInt("Unit_Status");
                Unit unit = new Unit(Unit_Id, Unit_Name, Unit_Status);
                kq.add(unit);
            }
            JDBCUtil.closeConnection(con);     
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public ResultSet getCurrentId() throws SQLException {
        Connection con = JDBCUtil.getConnection();
        Statement st = con.createStatement();
        String sql = "SELECT MAX(Unit_Id) FROM UNIT";
        return st.executeQuery(sql);
    }
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
}
