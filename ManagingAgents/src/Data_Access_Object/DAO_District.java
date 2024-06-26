package Data_Access_Object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Database.JDBCUtil;
<<<<<<< HEAD
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.District;

public class DAO_District implements Interface<District> {
    
=======
import models.District;

public class DAO_District implements Interface<District> {
	
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
	public static DAO_District getInstance() {
        return new DAO_District();
    }
	
<<<<<<< HEAD
    @Override
    public ArrayList<District> selectAll() {
        ArrayList<District> districts = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM DISTRICT")) {
            while (rs.next()) {
                int district_Id = rs.getInt("district_Id");
                String district_Name = rs.getString("district_Name");
                int district_Status = rs.getInt("district_Status");
                District district = new District(district_Id, district_Name, district_Status); 
                districts.add(district);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return districts;
    }
    
    
    public ObservableList<District> selectAll2() {
    	ObservableList<District> districts = FXCollections.observableArrayList();
=======
	@Override
	public ArrayList<District> selectAll() {
	    ArrayList<District> districts = new ArrayList<>();
	    try (Connection con = JDBCUtil.getConnection();
	         Statement st = con.createStatement();
	         ResultSet rs = st.executeQuery("SELECT * FROM DISTRICT")) {
	        while (rs.next()) {
	            int district_Id = rs.getInt("district_Id");
	            String district_Name = rs.getString("district_Name");
<<<<<<< HEAD
	            int district_Status = rs.getInt("district_Status");
=======
	            boolean district_Status = rs.getBoolean("district_Status");
>>>>>>> 906790c5ce4401371dc295a95bc25ebb2a8f9660
	            District district = new District(district_Id, district_Name, district_Status); 
	            districts.add(district);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return districts;
	}
	
	@Override
	public int Add(District t) {
	    try (Connection connect = JDBCUtil.getConnection();
	         PreparedStatement ps = connect.prepareStatement("INSERT INTO DISTRICT(district_id, district_Name, district_Status) VALUES (?, ?, ?)")) {
	        ps.setInt(1, t.getDistrict_Id());
	        ps.setString(2, t.getDistrict_Name());
<<<<<<< HEAD
	        ps.setInt(3, t.getDistrict_Status());
=======
	    	ps.setBoolean(3, t.getDistrict_Status());
>>>>>>> 906790c5ce4401371dc295a95bc25ebb2a8f9660
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780

        try (Connection con = JDBCUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM DISTRICT")) {
            while (rs.next()) {
                int district_Id = rs.getInt("district_Id");
                String district_Name = rs.getString("district_Name");
                int district_Status = rs.getInt("district_Status");
                District district = new District(district_Id, district_Name, district_Status); 
                districts.add(district);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return districts;
    }
    @Override
    public int Add(District t) {
        try (Connection connect = JDBCUtil.getConnection();
             PreparedStatement ps = connect.prepareStatement("INSERT INTO DISTRICT( district_Name, district_Status) VALUES ( ?, ?)")) {
            
            ps.setString(1, t.getDistrict_Name());
            ps.setInt(2, t.getDistrict_Status());

            int rowsAffected = ps.executeUpdate();
            System.out.println("Thực thi thành công");
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

<<<<<<< HEAD
    @Override
    public int Update(District t) {
        try (Connection connect = JDBCUtil.getConnection();
             PreparedStatement ps = connect.prepareStatement("UPDATE DISTRICT SET district_Name = ?, district_Status = ? WHERE district_Id = ?")) {
            ps.setString(1, t.getDistrict_Name());
            ps.setInt(2, t.getDistrict_Status());
            ps.setInt(3, t.getDistrict_Id());
=======
	@Override
	public int Update(District t) {
	    try (Connection connect = JDBCUtil.getConnection();
<<<<<<< HEAD
	         PreparedStatement ps = connect.prepareStatement("UPDATE DISTRICT SET district_Name = ?, district_Status = ? WHERE district_Id = ?")) {
	        ps.setString(1, t.getDistrict_Name());
	        ps.setInt(2, t.getDistrict_Status());
	        ps.setInt(3, t.getDistrict_Id());

=======
	         PreparedStatement ps = connect.prepareStatement("UPDATE DISTRICT SET district_Name = ? district_Status = ? WHERE district_Id = ?")) {
	        ps.setString(1, t.getDistrict_Name());
	        ps.setBoolean(2, t.getDistrict_Status());
>>>>>>> 906790c5ce4401371dc295a95bc25ebb2a8f9660
	        int rowsAffected = ps.executeUpdate();
	        System.out.println("Thực thi thành công");
	        return rowsAffected;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780

            int rowsAffected = ps.executeUpdate();
            System.out.println("Thực thi thành công");
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

<<<<<<< HEAD
    @Override
    public int Delete(District t) {
        try (Connection connect = JDBCUtil.getConnection();
             PreparedStatement ps = connect.prepareStatement("DELETE FROM DISTRICT WHERE district_Id = ?")) {
            ps.setInt(1, t.getDistrict_Id());
            int rowsAffected = ps.executeUpdate();
            System.out.println("Thực thi thành công");
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
=======
	@Override
	public District seclectById(District t) {
	    District district = null;
	    try (Connection con = JDBCUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement("SELECT * FROM DISTRICT WHERE district_Id = ?")) {
	        ps.setInt(1, t.getDistrict_Id());
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
<<<<<<< HEAD
	                String district_Name = rs.getString("district_Name");
	                int district_Status = rs.getInt("district_Status");
	                district = new District(t.getDistrict_Id(), district_Name, district_Status);
=======
	                boolean district_Status = rs.getBoolean("district_Status");
	                district = new District(t.getDistrict_Id(), "", district_Status); // Tạm thời set district_Name là rỗng
>>>>>>> 906790c5ce4401371dc295a95bc25ebb2a8f9660
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return district;
	}
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780

    @Override
    public District seclectById(District t) {
        District district = null;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM DISTRICT WHERE district_Id = ?")) {
            ps.setInt(1, t.getDistrict_Id());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String district_Name = rs.getString("district_Name");
                    int district_Status = rs.getInt("district_Status");
                    district = new District(t.getDistrict_Id(), district_Name, district_Status);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return district;
    }

<<<<<<< HEAD
    @Override
    public ArrayList<District> selectByCondition(String condition) {
        ArrayList<District> districts = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM DISTRICT WHERE " + condition)) {
            while (rs.next()) {
                int district_Id = rs.getInt("district_Id");
                String district_Name = rs.getString("district_Name");
                int district_Status = rs.getInt("district_Status");
                District district = new District(district_Id, district_Name, district_Status);
                districts.add(district);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return districts;
    }

    public ResultSet getCurrentId() throws SQLException {
        Connection connect = JDBCUtil.getConnection();
=======
	@Override
	public ArrayList<District> selectByCondition(String condition) {
	    ArrayList<District> districts = new ArrayList<>();
	    try (Connection con = JDBCUtil.getConnection();
	         Statement st = con.createStatement();
	         ResultSet rs = st.executeQuery("SELECT * FROM DISTRICT WHERE " + condition)) {
	        while (rs.next()) {
	            int district_Id = rs.getInt("district_Id");
<<<<<<< HEAD
	            String district_Name = rs.getString("district_Name");
	            int district_Status = rs.getInt("district_Status");
	            District district = new District(district_Id, district_Name, district_Status);
=======
	            boolean district_Status = rs.getBoolean("district_Status");
	            District district = new District(district_Id, "", district_Status); // Tạm thời set district_Name là rỗng
>>>>>>> 906790c5ce4401371dc295a95bc25ebb2a8f9660
	            districts.add(district);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return districts;
	}


	public ResultSet getCurrentId() throws SQLException {
		
		Connection connect = JDBCUtil.getConnection();
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
        String sql = "SELECT MAX(district_Id) FROM district"; 
        PreparedStatement statement = connect.prepareStatement(sql);
        return statement.executeQuery();
    }
    
    public List<String> getDistrictNamesFromDatabase() {
        List<String> districtNames = new ArrayList<>();

<<<<<<< HEAD
        try (Connection connect = JDBCUtil.getConnection();
             Statement st = connect.createStatement();
             ResultSet rs = st.executeQuery("SELECT district_Name FROM district")) {
=======
        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();

            String sql = "SELECT district_Name FROM district";
            ResultSet rs = st.executeQuery(sql);
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780

            while (rs.next()) {
                String districtName = rs.getString("district_Name");
                districtNames.add(districtName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return districtNames;
    }
    
    public String getDistrictName(int id) {
    	String dName = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT district_Name FROM district WHERE district_Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	dName = rs.getString("district_Name");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dName;
    }
}
