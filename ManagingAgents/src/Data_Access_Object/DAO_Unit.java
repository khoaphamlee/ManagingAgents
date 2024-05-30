package Data_Access_Object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;

import Database.JDBCUtil;
<<<<<<< HEAD
=======

>>>>>>> 906790c5ce4401371dc295a95bc25ebb2a8f9660
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
            String sql = "INSERT INTO UNIT(Unit_Id, Unit_Name, Unit_Status) " +
                         "VALUES (" + t.getUnit_Id() + " , '" + t.getUnit_Name() + "', " + t.getUnit_Status() + ")";
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
}
