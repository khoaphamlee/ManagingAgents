package models;


public class Agent {
	private int Agent_Id;
	private String Agent_Name;
	private String Agent_Phone;
	private String Agent_Address;
	private int Agent_District;
	private int Agent_Status;
	private double Agent_Debt;
	private int Agent_Type;
	private String Agent_Email;
	public Agent() {
		super();
	};
		
	public Agent(int agent_Id, String agent_Name, String agent_Phone, String agent_Address,  int agent_District,
			double agent_Debt, int agent_Type, String agent_Email) {
		super();
		Agent_Id = agent_Id;
		Agent_Name = agent_Name;
		Agent_Phone = agent_Phone;
		Agent_Address = agent_Address;
		Agent_District = agent_District;
		Agent_Debt = agent_Debt;
		Agent_Type = agent_Type;
		Agent_Email = agent_Email;
		Agent_Status = 1;
	}
	
	public Agent(int agent_Id, String agent_Name, String agent_Phone, String agent_Address,  int agent_District, int agent_Status,
			double agent_Debt, int agent_Type, String agent_Email) {
		super();
		Agent_Id = agent_Id;
		Agent_Name = agent_Name;
		Agent_Phone = agent_Phone;
		Agent_Address = agent_Address;
		Agent_District = agent_District;
		Agent_Debt = agent_Debt;
		Agent_Type = agent_Type;
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

	public int getAgent_District() {
		return Agent_District;
	}

	public void setAgent_District(int agent_District) {
		Agent_District = agent_District;
	}

	public double getAgent_Debt() {
		return Agent_Debt;
	}

	public void setAgent_Debt(double agent_Debt) {
		Agent_Debt = agent_Debt;
	}
	
	public int getAgent_Type() {
		return Agent_Type;
	}

	public void setAgent_Type(int agent_Type) {
		Agent_Type = agent_Type;
	}
	
	public String getAgent_Email() {
		return Agent_Email;
	}

	public void setAgent_Email(String agent_Email) {
		Agent_Email = agent_Email;
	}
	
	public int getAgent_Status() {
		return Agent_Status;
	}

	public void setAgent_Status(int agent_Status) {
		Agent_Status = agent_Status;
	}
	
	@Override
	public String toString() {
		return Agent_Name;
	}
	
	
	
}
