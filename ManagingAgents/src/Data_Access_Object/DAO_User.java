package Data_Access_Object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;

import Database.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.User;

public class DAO_User implements Interface<User> {
    public static DAO_User getInstance() {
        return new DAO_User();
    }

    @Override
    public int Add(User t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            
            String sql = "INSERT INTO USER(UserName, Password, Email, Name, Address, BirthDate, SDT, Gender, Status) " +
                         "VALUES ('" + t.getUserName() + "', '" + t.getPassword() + "', '" + 
                         t.getEmail() + "', '" + t.getName() + "', '" + t.getAddress() + "', '" + t.getBirthDate() + 
                         "', '" + t.getSDT() + "', '" + t.getGender() + "', " + t.getStatus() + ")";
            int kq = st.executeUpdate(sql);
            
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int Update(User t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            
            String sql = "UPDATE USER SET UserName= '" + t.getUserName() + 
                         "', Password= '" + t.getPassword() + 
                         "', Email= '" + t.getEmail() + 
                         "', Name= '" + t.getName() + 
                         "', Address= '" + t.getAddress() + 
                         "', BirthDate= '" + t.getBirthDate() + 
                         "', Gender= '" + t.getGender() + 
                         "', SDT= '" + t.getSDT() + 
                         "', Status= " + t.getStatus() + 
                         " WHERE User_Id= " + t.getUser_Id();
            int kq = st.executeUpdate(sql);
            
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int UpdateStatus(User t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            
            String sql = "UPDATE USER SET Status= " + t.getStatus() + 
                         " WHERE User_Id= " + t.getUser_Id();
            int kq = st.executeUpdate(sql);
            
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int Delete(User t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            
            String sql = "DELETE FROM USER WHERE User_Id= " + t.getUser_Id();
            int kq = st.executeUpdate(sql);
            
            System.out.println("Bạn đã thực thi: " + sql);
            JDBCUtil.closeConnection(connect);
            return kq;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

   
    public ObservableList<User> selectAll2() {
        ObservableList<User> kq = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            
            String sql = "SELECT * FROM USER";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                int User_Id = rs.getInt("User_Id");
                String UserName = rs.getString("UserName");
                String Password = rs.getString("Password");
                String Email = rs.getString("Email");
                String Name = rs.getString("Name");
                String Address = rs.getString("Address");
                Date BirthDate = rs.getDate("BirthDate");
                String SDT = rs.getString("SDT");
                String Gender = rs.getString("Gender");
                boolean Status = rs.getBoolean("Status");
                
                User user = new User(User_Id, UserName, Password, Email, Name, Address, BirthDate, SDT, Gender, Status);
                kq.add(user);
            }
            
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public User seclectById(User t) {
        User kq = null;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            
            String sql = "SELECT * FROM USER WHERE User_Id=" + t.getUser_Id();
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                int User_Id = rs.getInt("User_Id");
                String UserName = rs.getString("UserName");
                String Password = rs.getString("Password");
                String Email = rs.getString("Email");
                String Name = rs.getString("Name");
                String Address = rs.getString("Address");
                Date BirthDate = rs.getDate("BirthDate");
                String SDT = rs.getString("SDT");
                String Gender = rs.getString("Gender");
                boolean Status = rs.getBoolean("Status");
                
                kq = new User(User_Id, UserName, Password, Email, Name, Address, BirthDate, SDT, Gender, Status);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public ArrayList<User> selectByCondition(String condition) {
        ArrayList<User> kq = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            
            String sql = "SELECT * FROM USER WHERE " + condition;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                int User_Id = rs.getInt("User_Id");
                String UserName = rs.getString("UserName");
                String Password = rs.getString("Password");
                String Email = rs.getString("Email");
                String Name = rs.getString("Name");
                String Address = rs.getString("Address");
                Date BirthDate = rs.getDate("BirthDate");
                String SDT = rs.getString("SDT");
                String Gender = rs.getString("Gender");
                boolean Status = rs.getBoolean("Status");
                
                User user = new User(User_Id, UserName, Password, Email, Name, Address, BirthDate, SDT, Gender, Status);
                kq.add(user);
            }
            
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    
    public User selectByCondition2(String condition) {
        User kq = new User();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            
            String sql = "SELECT * FROM USER WHERE " + condition;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                int User_Id = rs.getInt("User_Id");
                String UserName = rs.getString("UserName");
                String Password = rs.getString("Password");
                String Email = rs.getString("Email");
                String Name = rs.getString("Name");
                String Address = rs.getString("Address");
                Date BirthDate = rs.getDate("BirthDate");
                String SDT = rs.getString("SDT");
                String Gender = rs.getString("Gender");
                boolean Status = rs.getBoolean("Status");
                
                User user = new User(User_Id, UserName, Password, Email, Name, Address, BirthDate, SDT, Gender, Status);
                kq = user;
            }
            
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

	@Override
	public ArrayList<User> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
