package Data_Access_Object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.JDBCUtil;

import models.Reception;

public class DAO_Reception implements Interface<Reception>{

	public static DAO_Reception getInstance() {
		return new DAO_Reception();
	}
	
	@Override
	public int Add(Reception t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "INSERT INTO RECEPTION(Reception_Id, IdAgent, IdAgentType, ReceptionDate)"
					+ "VALUES (" + t.getReception_id() + " , " + t.getIdAgent() + " , " + t.getIdAgentType()
					+ " , '" + t.getReceptionDate() + "')";
			int kq = st.executeUpdate(sql);
			
			System.out.println("Bạn đã thực thi");
			JDBCUtil.closeConnection(connect);
			
			return kq;
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int Update(Reception t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "UPDATE RECEPTION "+
						 " SET "+
						 " IdAgent= " + t.getIdAgent() + "" +
						 ", IdAgentType= " + t.getIdAgentType() + "" +
						 ", ReceptionDate= '" + t.getReceptionDate() + "'" +
						 " WHERE Reception_Id= " + t.getReception_id() + "" ;
			int kq = st.executeUpdate(sql);
			
			System.out.println("Bạn đã thực thi");
			JDBCUtil.closeConnection(connect);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Delete(Reception t) {
		try {
			Connection connect = JDBCUtil.getConnection();
			
			Statement st = connect.createStatement();
			
			String sql = "DELETE from RECEPTION "+
						 " WHERE Reception_Id= " + t.getReception_id() + "" ;
			int kq = st.executeUpdate(sql);
			
			System.out.println("Bạn đã thực thi");
			JDBCUtil.closeConnection(connect);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Reception> selectAll() {
		ArrayList<Reception> kq = new ArrayList<Reception>();
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM RECEPTION";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int Reception_Id = rs.getInt("Reception_Id");
				int IdAgent = rs.getInt("IdAgent");
				int IdAgentType = rs.getInt("IdAgentType");
				Date ReceptionDate = rs.getDate("ReceptionDate");
				
				Reception reception = new Reception(Reception_Id, IdAgent, IdAgentType, ReceptionDate);
				kq.add(reception);
			}
			
			JDBCUtil.closeConnection(con);	
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public Reception seclectById(Reception t) {
		Reception kq = null;
		try {
			Connection con = JDBCUtil.getConnection();
			
			Statement st = con.createStatement();
			
			String sql = "SELECT * FROM RECEPTION where Reception_Id=" + t.getReception_id();
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				int Reception_Id = rs.getInt("Reception_Id");
				int IdAgent = rs.getInt("IdAgent");
				int IdAgentType = rs.getInt("IdAgentType");
				Date ReceptionDate = rs.getDate("ReceptionDate");
				
				kq = new Reception(Reception_Id, IdAgent, IdAgentType, ReceptionDate);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return kq;
	}

	@Override
	public ArrayList<Reception> selectByCondition(String condition) {
		ArrayList<Reception> kq = new ArrayList<>();
	    
	    try {
	        Connection con = JDBCUtil.getConnection();
	        Statement st = con.createStatement();
	        
	        String sql = "SELECT * FROM RECEPTION WHERE " + condition;
	        System.out.println(sql); 
	        
	        ResultSet rs = st.executeQuery(sql);
	        
	        while (rs.next()) {
	        	int Reception_Id = rs.getInt("Reception_Id");
				int IdAgent = rs.getInt("IdAgent");
				int IdAgentType = rs.getInt("IdAgentType");
				Date ReceptionDate = rs.getDate("ReceptionDate");
	            
				Reception reception = new Reception(Reception_Id, IdAgent, IdAgentType, ReceptionDate);
	            kq.add(reception);
	        }
	        
	        JDBCUtil.closeConnection(con); 	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return kq;
	}
	
	public ArrayList<Integer> getReceptionIds() {
        ArrayList<Integer> receptionIds = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT Reception_Id FROM RECEPTION";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int receptionId = rs.getInt("Reception_Id");
                receptionIds.add(receptionId);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receptionIds;
    }

    public ArrayList<Integer> getAgentIds() {
        ArrayList<Integer> agentIds = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT IdAgent FROM RECEPTION";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int agentId = rs.getInt("IdAgent");
                agentIds.add(agentId);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agentIds;
    }

    public ArrayList<Integer> getAgentTypeIds() {
        ArrayList<Integer> agentTypeIds = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT IdAgentType FROM RECEPTION";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int agentTypeId = rs.getInt("IdAgentType");
                agentTypeIds.add(agentTypeId);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agentTypeIds;
    }

    public ArrayList<java.sql.Date> getReceptionDates() {
        ArrayList<java.sql.Date> receptionDates = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT ReceptionDate FROM RECEPTION";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                java.sql.Date receptionDate = rs.getDate("ReceptionDate");
                receptionDates.add(receptionDate);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receptionDates;
    }
    
    public int countRecords() {
        int count = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT COUNT(*) AS recordCount FROM RECEPTION";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("recordCount");
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }


}
