package Data_Access_Object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Role;

public class DAO_Role implements Interface<Role> {
    public static DAO_Role getInstance() {
        return new DAO_Role();
    }

    @Override
    public int Add(Role t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();

            String sql = "INSERT INTO ROLE(Role_1, Role_2, Role_3, Role_4, Role_5, Position, User_Id)" + 
                         " VALUES (false, false, false, false, false, '" + t.getPosition() + "', " + t.getUserId() + ")";
            int kq = st.executeUpdate(sql);

            System.out.println("Thêm thành công");
            JDBCUtil.closeConnection(connect);
            return kq;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int Update(Role t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(
                    "UPDATE ROLE SET Role_1 = ?, Role_2 = ?, Role_3 = ?, Role_4 = ?, Role_5 = ?, " +
                    "Position = ?, User_Id = ? WHERE Role_Id = ?");

            // Thiết lập các tham số cho câu lệnh truy vấn
            preparedStatement.setBoolean(1, t.isRole1());
            preparedStatement.setBoolean(2, t.isRole2());
            preparedStatement.setBoolean(3, t.isRole3());
            preparedStatement.setBoolean(4, t.isRole4());
            preparedStatement.setBoolean(5, t.isRole5());
            
            preparedStatement.setString(6, t.getPosition());
            preparedStatement.setInt(7, t.getUserId());
            preparedStatement.setInt(8, t.getRoleId());

            // Thực thi câu lệnh truy vấn
            int kq = preparedStatement.executeUpdate();
            
            System.out.println("Cập nhật thành công");
            
            // Đóng kết nối
            JDBCUtil.closeConnection(connect);
            
            return kq;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    public int Delete(Role t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();

            String sql = "DELETE from ROLE " +
                         "WHERE Role_Id= " + t.getRoleId();
            int kq = st.executeUpdate(sql);

            System.out.println("Xóa thành công");
            JDBCUtil.closeConnection(connect);
            return kq;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

  
    public ObservableList<Role> selectAll2() {
        ObservableList<Role> kq = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM ROLE";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int Role_Id = rs.getInt("Role_Id");
                boolean Role_1 = rs.getBoolean("Role_1");
                boolean Role_2 = rs.getBoolean("Role_2");
                boolean Role_3 = rs.getBoolean("Role_3");
                boolean Role_4 = rs.getBoolean("Role_4");
                boolean Role_5 = rs.getBoolean("Role_5");
                
                String Position = rs.getString("Position");
                int User_Id = rs.getInt("User_Id");

                Role role = new Role(Role_Id, Role_1, Role_2, Role_3, Role_4, Role_5, Position, User_Id);
                kq.add(role);
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public Role seclectById(Role t) {
        Role kq = null;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM ROLE where Role_Id=" + t.getRoleId();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int Role_Id = rs.getInt("Role_Id");
                boolean Role_1 = rs.getBoolean("Role_1");
                boolean Role_2 = rs.getBoolean("Role_2");
                boolean Role_3 = rs.getBoolean("Role_3");
                boolean Role_4 = rs.getBoolean("Role_4");
                boolean Role_5 = rs.getBoolean("Role_5");
                
                String Position = rs.getString("Position");
                int User_Id = rs.getInt("User_Id");

                kq = new Role(Role_Id, Role_1, Role_2, Role_3, Role_4, Role_5, Position, User_Id);
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public ArrayList<Role> selectByCondition(String condition) {
        ArrayList<Role> kq = new ArrayList<>();

        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM ROLE WHERE " + condition;
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int Role_Id = rs.getInt("Role_Id");
                boolean Role_1 = rs.getBoolean("Role_1");
                boolean Role_2 = rs.getBoolean("Role_2");
                boolean Role_3 = rs.getBoolean("Role_3");
                boolean Role_4 = rs.getBoolean("Role_4");
                boolean Role_5 = rs.getBoolean("Role_5");
                
                String Position = rs.getString("Position");
                int User_Id = rs.getInt("User_Id");

                Role role = new Role(Role_Id, Role_1, Role_2, Role_3, Role_4, Role_5,  Position, User_Id);
                kq.add(role);
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kq;
    }
    
    
    public Role selectByCondition2(String condition) {
        Role kq = new Role();
        
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM ROLE WHERE " + condition;
            ResultSet rs = st.executeQuery(sql);
            System.out.print(sql);
            
            while (rs.next()) {
                int Role_Id = rs.getInt("Role_Id");
                boolean Role_1 = rs.getBoolean("Role_1");
                boolean Role_2 = rs.getBoolean("Role_2");
                boolean Role_3 = rs.getBoolean("Role_3");
                boolean Role_4 = rs.getBoolean("Role_4");
                boolean Role_5 = rs.getBoolean("Role_5");
                
                String Position = rs.getString("Position");
                int User_Id = rs.getInt("User_Id");

                Role role = new Role(Role_Id, Role_1, Role_2, Role_3, Role_4, Role_5,  Position, User_Id);
                kq = role;
            }

            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kq;
    }

	@Override
	public ArrayList<Role> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
