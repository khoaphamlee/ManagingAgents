package datatable;

import java.time.LocalDate;

public class SettingScreenTable {
	private int Unit_Id;
	private String Unit_Name;
	private String Unit_status;
	
	public SettingScreenTable() {
		super();
	};
		
	public SettingScreenTable(int unit_Id,String unit_Name, String status ) {
		super();
		Unit_Id = unit_Id;
		Unit_Name = unit_Name;
		Unit_status = status;
	}

	public void setUnit_Id(int id) {
		this.Unit_Id = id;
		
	}
	
	public int getUnit_Id() {
		return Unit_Id;
	}

	public String getUnit_Status() {
		return Unit_status;
	}

	public void setUnit_status(String Unit_status) {
		this.Unit_status = Unit_status;
	}
	
	public String getUnit_Name() {
		return Unit_Name;
	}
	
	public void setUnit_Name(String name) {
		this.Unit_Name = name;
	}
	
	/*public String toString() {
		return "Agent [Agent_Id=" + Agent_Id + ", Agent_Name=" + Agent_Name + ", Agent_Phone=" + Agent_Phone
				+ ", Agent_Address=" + Agent_Address + ", Agent_District=" + Agent_District + ", Agent_Debt="
				+ Agent_Debt + ", Agent_Date=" + Agent_Date + "]";
	}*/
	
	
	
}
