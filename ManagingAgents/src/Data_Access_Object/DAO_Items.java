package Data_Access_Object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.JDBCUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Items;

public class DAO_Items implements Interface<Items> {

    public static DAO_Items getInstance() {
        return new DAO_Items();
    }

    @Override
    public int Add(Items t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            String sql = "INSERT INTO ITEMS( Items_Name, Items_Price, Items_Quantity,IdUnit, Items_Status) VALUES ( ?, ?, ?, ?,?)";
            PreparedStatement ps = connect.prepareStatement(sql);
            
            ps.setString(1, t.getItems_Name());
            ps.setDouble(2, t.getItems_Price());
            ps.setInt(3, t.getItems_Quantity());
            ps.setInt(4, t.getItems_Unit());
            ps.setInt(5, t.getItems_Status2());

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
    public int Update(Items t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            String sql = "UPDATE ITEMS SET Items_Name = ?, Items_Price = ?, Items_Quantity = ?, IdUnit = ? , Items_Status = ? WHERE Items_Id = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, t.getItems_Name());
            ps.setDouble(2, t.getItems_Price());
            ps.setInt(3, t.getItems_Quantity());
            ps.setInt(4, t.getItems_Unit());
            ps.setInt(5, t.getItems_Status2());
            ps.setInt(6, t.getItems_Id());

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
    public int Delete(Items t) {
        try {
            Connection connect = JDBCUtil.getConnection();
            String sql = "DELETE FROM ITEMS WHERE Items_Id = ?";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, t.getItems_Id());

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
    public ArrayList<Items> selectAll() {
        ArrayList<Items> kq = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ITEMS";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int Items_Id = rs.getInt("Items_Id");
                String Items_Name = rs.getString("Items_Name");
                double Items_Price = rs.getDouble("Items_Price");
                int Items_Quantity = rs.getInt("Items_Quantity");
                int Items_Status = rs.getInt("Items_Status");
                int IdUnit = rs.getInt("IdUnit");

                Items itemS = new Items(Items_Id, Items_Name,IdUnit, Items_Price, Items_Quantity, Items_Status);
                kq.add(itemS);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    

    public ObservableList<Items> selectAll2() {
    	ObservableList<Items> kq = FXCollections.observableArrayList();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ITEMS";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int Items_Id = rs.getInt("Items_Id");
                String Items_Name = rs.getString("Items_Name");
                double Items_Price = rs.getDouble("Items_Price");
                int Items_Quantity = rs.getInt("Items_Quantity");
                int Items_Status = rs.getInt("Items_Status");
                int IdUnit = rs.getInt("IdUnit");

                Items itemS = new Items(Items_Id, Items_Name,IdUnit, Items_Price, Items_Quantity, Items_Status);
                kq.add(itemS);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public Items seclectById(Items t) {
        Items kq = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ITEMS WHERE Items_Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, t.getItems_Id());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int Items_Id = rs.getInt("Items_Id");
                String Items_Name = rs.getString("Items_Name");
                double Items_Price = rs.getDouble("Items_Price");
                int Items_Quantity = rs.getInt("Items_Quantity");
                int Items_Status = rs.getInt("Items_Status");
                int IdUnit = rs.getInt("IdUnit");

                 
                kq = new Items(Items_Id, Items_Name,IdUnit, Items_Price, Items_Quantity, Items_Status);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public ArrayList<Items> selectByCondition(String condition) {
        ArrayList<Items> kq = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ITEMS WHERE " + condition;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int Items_Id = rs.getInt("Items_Id");
                String Items_Name = rs.getString("Items_Name");
                double Items_Price = rs.getDouble("Items_Price");
                int Items_Quantity = rs.getInt("Items_Quantity");
                int Items_Status = rs.getInt("Items_Status");
                int IdUnit = rs.getInt("IdUnit");

                Items itemS = new Items(Items_Id, Items_Name,IdUnit, Items_Price, Items_Quantity, Items_Status);
                kq.add(itemS);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    public Items selectByCondition2(String condition) {
        Items kq = new Items();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ITEMS WHERE " + condition;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int Items_Id = rs.getInt("Items_Id");
                String Items_Name = rs.getString("Items_Name");
                double Items_Price = rs.getDouble("Items_Price");
                int Items_Quantity = rs.getInt("Items_Quantity");
                int Items_Status = rs.getInt("Items_Status");
                int IdUnit = rs.getInt("IdUnit");

                Items itemS = new Items(Items_Id, Items_Name,IdUnit, Items_Price, Items_Quantity, Items_Status);
                kq = itemS;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    public ResultSet getCurrentId() throws SQLException {
        Connection connect = JDBCUtil.getConnection();
        String sql = "SELECT MAX(items_Id) FROM items"; 
        PreparedStatement statement = connect.prepareStatement(sql);
        return statement.executeQuery();
    }

    // Thêm các phương thức để lấy giá trị thuộc tính dựa vào Items_Id
    public String getItemsNameById(int itemsId) {
        String name = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT Items_Name FROM ITEMS WHERE Items_Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, itemsId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                name = rs.getString("Items_Name");
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public double getItemsPriceById(int itemsId) {
        double price = -1;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT Items_Price FROM ITEMS WHERE Items_Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, itemsId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                price = rs.getDouble("Items_Price");
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }

    public int getItemsQuantityById(int itemsId) {
        int quantity = -1;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT Items_Quantity FROM ITEMS WHERE Items_Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, itemsId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                quantity = rs.getInt("Items_Quantity");
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quantity;
    }

    public int getItemsStatusById(int itemsId) {
        int status = -1;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT Items_Status FROM ITEMS WHERE Items_Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, itemsId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                status = rs.getInt("Items_Status");
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
    
    public int getIdByItemsName(String itemsName) {
        int itemsId = -1;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT Items_Id FROM ITEMS WHERE Items_Name = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, itemsName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                itemsId = rs.getInt("Items_Id");
           /* } else {
                // Nếu không tồn tại itemsName, tạo mới Items_Id
                sql = "INSERT INTO ITEMS (Items_Name) VALUES (?)";
                ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, itemsName);
                ps.executeUpdate();

                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    itemsId = rs.getInt(1);
                }*/
                JDBCUtil.closeConnection(con);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemsId;
    }
}
