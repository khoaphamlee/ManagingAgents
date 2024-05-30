package datatable;

import java.sql.Date;
import java.time.LocalDate;

public class AgencyScreenTable {
	private int Agent_Id;
	private String Agent_Name;
	private String Agent_Type;
	private String Agent_Phone;
	private String Agent_Address;
	private String Agent_District;
	private double Agent_Debt;
	private Date Agent_Date;
	private String Agent_Email;
	private String Agent_Status;
	
	public AgencyScreenTable() {
		super();
	};
		
	public AgencyScreenTable(int agent_Id, String agent_Name,String Agent_type, String agent_Phone, String agent_Address, String agent_District,
			double agent_Debt, Date agent_Date, String agent_Email, String agent_Status) {
		super();
		Agent_Id = agent_Id;
		Agent_Name = agent_Name;
		Agent_Type = Agent_type;
		Agent_Phone = agent_Phone;
		Agent_Address = agent_Address;
		Agent_District = agent_District;
		Agent_Debt = agent_Debt;
		Agent_Date = agent_Date;
		Agent_Email = agent_Email;
		Agent_Status = agent_Status;
	}

	public void setAgent_Id(int id) {
		this.Agent_Id = id;
		
	}
	
	public int getAgent_Id() {
		return Agent_Id;
	}

	public String getAgent_Name() {
		return Agent_Name;
	}

	public void setAgent_Type(String agent_type) {
		Agent_Type = agent_type;
	}
	
	public String getAgent_Type() {
		return Agent_Type;
	}

	public void setAgent_Name(String agent_Name) {
		Agent_Name = agent_Name;
	}

	public String getAgent_Phone() {
		return Agent_Phone;
	}

	public void setAgent_Phone(String agent_Phone) {
		Agent_Phone = agent_Phone;
	}

	public String getAgent_Address() {
		return Agent_Address;
	}

	public void setAgent_Address(String agent_Address) {
		Agent_Address = agent_Address;
	}

	public String getAgent_District() {
		return Agent_District;
	}

	public void setAgent_District(String agent_District) {
		Agent_District = agent_District;
	}

	public double getAgent_Debt() {
		return Agent_Debt;
	}

	public void setAgent_Debt(double agent_Debt) {
		Agent_Debt = agent_Debt;
	}
	
	public String getAgent_Email() {
		return Agent_Email;
	}

	public void setAgent_Email(String agent_Email) {
		Agent_Email = agent_Email;
	}
	
	public String getAgent_Status() {
		return Agent_Status;
	}

	public void setAgent_Status(String agent_Status) {
		Agent_Status = agent_Status;
	}

	public void setAgentType_Date(Date agent_Date) {
		Agent_Date = agent_Date;
	}
	
	public Date getAgent_Date() {
		return Agent_Date;
	}
	
	@Override
	public String toString() {
		return "Agent [Agent_Id=" + Agent_Id + ", Agent_Name=" + Agent_Name + ", Agent_Phone=" + Agent_Phone
				+ ", Agent_Address=" + Agent_Address + ", Agent_District=" + Agent_District + ", Agent_Debt="
				+ Agent_Debt + ", Agent_Date=" + Agent_Date + "]";
	}
	
	
	
}
