package Data_Access_Object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import Database.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Receipt;

public class DAO_Receipt implements Interface<Receipt> {

    public static DAO_Receipt getInstance() {
        return new DAO_Receipt();
    }

    @Override
    public int Add(Receipt t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "INSERT INTO RECEIPT( Id_Agent, Id_User, ReceiptDate, MoneyReceived, IsReceived)"
                    + "VALUES ("  + t.getId_Agent() + " , " + t.getId_User()
                    + " , '" + t.getReceiptDate() + "' , " + t.getMoneyReceived() + " , '" + t.getIsReceived() + "')";
            int kq = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi");
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int Update(Receipt t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "UPDATE RECEIPT " +
                    " SET " +
                    " Id_Agent= " + t.getId_Agent() +
                    ", Id_User= " + t.getId_User() +
                    ", ReceiptDate= '" + t.getReceiptDate() + "'" +
                    ", MoneyReceived= " + t.getMoneyReceived() +
                    ", IsReceived= '" + t.getIsReceived() + "'" +
                    " WHERE Receipt_Id= " + t.getReceipt_Id();
            int kq = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi");
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int Delete(Receipt t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();
            String sql = "DELETE from RECEIPT " +
                    " WHERE Receipt_Id= " + t.getReceipt_Id();
            int kq = st.executeUpdate(sql);
            System.out.println("Bạn đã thực thi");
            JDBCUtil.closeConnection(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Receipt> selectAll() {
        ArrayList<Receipt> kq = new ArrayList<Receipt>();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM RECEIPT";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int Receipt_Id = rs.getInt("Receipt_Id");
                int Id_Agent = rs.getInt("Id_Agent");
                int Id_User = rs.getInt("Id_User");
                Date receiptDateSQL = rs.getDate("ReceiptDate");
				
                Double MoneyReceived = rs.getDouble("MoneyReceived");
                int IsReceived = rs.getInt("IsReceived"); // Đọc kiểu int từ cơ sở dữ liệu
                Receipt recept = new Receipt(Receipt_Id, Id_Agent, Id_User, receiptDateSQL, MoneyReceived, IsReceived);
                kq.add(recept);
            }
            
            
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    
    public ObservableList<Receipt> selectAll2() {
    	ObservableList<Receipt> kq = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM RECEIPT";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int Receipt_Id = rs.getInt("Receipt_Id");
                int Id_Agent = rs.getInt("Id_Agent");
                int Id_User = rs.getInt("Id_User");
                Date receiptDateSQL = rs.getDate("ReceiptDate");
				
                Double MoneyReceived = rs.getDouble("MoneyReceived");
                int IsReceived = rs.getInt("IsReceived"); // Đọc kiểu int từ cơ sở dữ liệu
                Receipt recept = new Receipt(Receipt_Id, Id_Agent, Id_User, receiptDateSQL, MoneyReceived, IsReceived);
                kq.add(recept);
            }
            
            
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public Receipt seclectById(Receipt t) {
        Receipt kq = null;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM RECEIPT where Receipt_Id=" + t.getReceipt_Id();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int Receipt_Id = rs.getInt("Receipt_Id");
                int Id_Agent = rs.getInt("Id_Agent");
                int Id_User = rs.getInt("Id_User");
                Date receiptDateSQL = rs.getDate("ReceiptDate");
				
                Double MoneyReceived = rs.getDouble("MoneyReceived");
                int IsReceived = rs.getInt("IsReceived"); // Đọc kiểu int từ cơ sở dữ liệu
                kq = new Receipt(Receipt_Id, Id_Agent, Id_User, receiptDateSQL, MoneyReceived, IsReceived);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public ArrayList<Receipt> selectByCondition(String condition) {
        ArrayList<Receipt> kq = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM RECEIPT WHERE " + condition;
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int Receipt_Id = rs.getInt("Receipt_Id");
                int Id_Agent = rs.getInt("Id_Agent");
                int Id_User = rs.getInt("Id_User");
                Date receiptDateSQL = rs.getDate("ReceiptDate");
				
                Double MoneyReceived = rs.getDouble("MoneyReceived");
                int IsReceived = rs.getInt("IsReceived"); // Đọc kiểu int từ cơ sở dữ liệu
                Receipt recept = new Receipt(Receipt_Id, Id_Agent, Id_User, receiptDateSQL, MoneyReceived, IsReceived);
                kq.add(recept);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    
    
    
    public ObservableList<Receipt> selectByCondition2(String condition) {
    	ObservableList<Receipt> kq = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM RECEIPT WHERE " + condition;
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int Receipt_Id = rs.getInt("Receipt_Id");
                int Id_Agent = rs.getInt("Id_Agent");
                int Id_User = rs.getInt("Id_User");
                Date receiptDateSQL = rs.getDate("ReceiptDate");
				
                Double MoneyReceived = rs.getDouble("MoneyReceived");
                int IsReceived = rs.getInt("IsReceived"); // Đọc kiểu int từ cơ sở dữ liệu
                Receipt recept = new Receipt(Receipt_Id, Id_Agent, Id_User, receiptDateSQL, MoneyReceived, IsReceived);
                kq .add(recept);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    // New methods to get specific attributes by receipt_id
    public int getIdAgentByReceiptId(int receiptId) {
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT Id_Agent FROM RECEIPT WHERE Receipt_Id=" + receiptId;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("Id_Agent");
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Return -1 or an appropriate default value if not found
    }

    public int getIdUserByReceiptId(int receiptId) {
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT Id_User FROM RECEIPT WHERE Receipt_Id=" + receiptId;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("Id_User");
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Return -1 or an appropriate default value if not found
    }

    public Date getReceiptDateByReceiptId(int receiptId) {
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT ReceiptDate FROM RECEIPT WHERE Receipt_Id=" + receiptId;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getDate("ReceiptDate");
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null or an appropriate default value if not found
    }

    public double getMoneyReceivedByReceiptId(int receiptId) {
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT MoneyReceived FROM RECEIPT WHERE Receipt_Id=" + receiptId;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getDouble("MoneyReceived");
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Return -1 or an appropriate default value if not found
    }

    public int getIsReceivedByReceiptId(int receiptId) {
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT IsReceived FROM RECEIPT WHERE Receipt_Id=" + receiptId;
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("IsReceived"); // Trả về giá trị kiểu int
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Trả về giá trị mặc định khi không tìm thấy
    }
    
    public ResultSet getCurrentId() throws SQLException {
        Connection connect = JDBCUtil.getConnection();
        String sql = "SELECT MAX(receipt_Id) FROM receipt"; 
        PreparedStatement statement = connect.prepareStatement(sql);
        return statement.executeQuery();
    }
}
