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
import models.District;

public class DAO_District implements Interface<District>{
	
	public static DAO_District getInstance() {
		return new DAO_District();
	}
	
	@Override
	public ArrayList<District> selectAll() {
	    ArrayList<District> districts = new ArrayList<>();
	    try (Connection con = JDBCUtil.getConnection();
	         Statement st = con.createStatement();
	         ResultSet rs = st.executeQuery("SELECT * FROM DISTRICT")) {
	        while (rs.next()) {
	            int district_Id = rs.getInt("district_Id");
	            String district_Name = rs.getString("district_Name");
	            //int maximum_Agent = rs.getInt("maximum_Agent");
	            District district = new District(district_Id, district_Name); 
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
	         PreparedStatement ps = connect.prepareStatement("INSERT INTO DISTRICT(district_id, district_Name, maximum_Agent) VALUES (?, ?, ?)")) {
	        ps.setInt(1, t.getDistrict_Id());
	        ps.setString(2, t.getDistrict_Name());
	    	ps.setInt(3, t.getMaximum_Agent());

	        int rowsAffected = ps.executeUpdate();
	        System.out.println("Thực thi thành công");
	        return rowsAffected;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}


	@Override
	public int Update(District t) {
	    try (Connection connect = JDBCUtil.getConnection();
	         PreparedStatement ps = connect.prepareStatement("UPDATE DISTRICT SET maximum_Agent = ? WHERE district_Id = ?")) {
	        ps.setInt(1, t.getMaximum_Agent());
	        ps.setInt(2, t.getDistrict_Id());
	        int rowsAffected = ps.executeUpdate();
	        System.out.println("Thực thi thành công");
	        return rowsAffected;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}

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

	@Override
	public District seclectById(District t) {
	    District district = null;
	    try (Connection con = JDBCUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement("SELECT * FROM DISTRICT WHERE district_Id = ?")) {
	        ps.setInt(1, t.getDistrict_Id());
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                int maximum_Agent = rs.getInt("maximum_Agent");
	                district = new District(t.getDistrict_Id(), "", maximum_Agent); // Tạm thời set district_Name là rỗng
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return district;
	}


	@Override
	public ArrayList<District> selectByCondition(String condition) {
	    ArrayList<District> districts = new ArrayList<>();
	    try (Connection con = JDBCUtil.getConnection();
	         Statement st = con.createStatement();
	         ResultSet rs = st.executeQuery("SELECT * FROM DISTRICT WHERE " + condition)) {
	        while (rs.next()) {
	            int district_Id = rs.getInt("district_Id");
	            int maximum_Agent = rs.getInt("maximum_Agent");
	            District district = new District(district_Id, "", maximum_Agent); // Tạm thời set district_Name là rỗng
	            districts.add(district);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return districts;
	}


	public ResultSet getCurrentId() throws SQLException {
		
		Connection connect = JDBCUtil.getConnection();
        String sql = "SELECT MAX(District_Id) FROM district"; 
        PreparedStatement statement = connect.prepareStatement(sql);
        return statement.executeQuery();
    }
	
	public List<String> getDistrictNamesFromDatabase() {
        List<String> districtNames = new ArrayList<>();

        try {
            Connection connect = JDBCUtil.getConnection();
            Statement st = connect.createStatement();

            String sql = "SELECT District_Name FROM district";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String typeName = rs.getString("district_Name");
                districtNames.add(typeName);
            }

            JDBCUtil.closeConnection(connect);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return districtNames;
    }
}
