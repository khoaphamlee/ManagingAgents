package models;

public class Unit {
	private int Unit_Id;
	private String Unit_Name;
	private boolean Unit_Status;
	
	public Unit() {
		super();
	}
	
	public Unit(int unit_Id, String unit_Name, boolean unit_Status) {
		super();
		Unit_Id = unit_Id;
		Unit_Name = unit_Name;
		Unit_Status = unit_Status;
	}
	
	public Unit(int unit_Id, String unit_Name) {
		super();
		Unit_Id = unit_Id;
		Unit_Name = unit_Name;
		Unit_Status = true;
	}
	
	public int getUnit_Id() {
		return Unit_Id;
	}
	public void setUnit_Id(int unit_Id) {
		Unit_Id = unit_Id;
	}
	public String getUnit_Name() {
		return Unit_Name;
	}
	public void setUnit_Name(String unit_Name) {
		Unit_Name = unit_Name;
	}
	public boolean getUnit_Status() {
		return Unit_Status;
	}
	public void setUnit_Status(boolean unit_Status) {
		Unit_Status = unit_Status;
	}

	@Override
	public String toString() {
		return "Unit [Unit_Id=" + Unit_Id + ", Unit_Name=" + Unit_Name + "]";
	}
	
}
