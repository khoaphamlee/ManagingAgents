package datatable;

import java.time.LocalDate;

public class SettingScreenTable {
	private int Unit_Id;
	private String Items_Name;
	private String Unit_Name;
	
	public SettingScreenTable() {
		super();
	};
		
	public SettingScreenTable(int unit_Id, String items_Name, String unit_Name) {
		super();
		Unit_Id = unit_Id;
		Items_Name = items_Name;
		Unit_Name = unit_Name;
	}

	public void setUnit_Id(int id) {
		this.Unit_Id = id;
		
	}
	
	public int getUnit_Id() {
		return Unit_Id;
	}

	public String getItems_Name() {
		return Items_Name;
	}

	public void setItems_Name(String name) {
		this.Items_Name = name;
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
