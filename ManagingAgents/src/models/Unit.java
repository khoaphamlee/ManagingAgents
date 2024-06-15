package models;

public class Unit {
	private int Unit_Id;
	private String Unit_Name;
	private int Unit_Status;
	
	public Unit() {
		super();
	}
	
	public Unit(int unit_Id, String unit_Name, int unit_Status) {
		super();
		Unit_Id = unit_Id;
		Unit_Name = unit_Name;
		Unit_Status = unit_Status;
	}
	
	public Unit(int unit_Id, String unit_Name) {
		super();
		Unit_Id = unit_Id;
		Unit_Name = unit_Name;
		Unit_Status = 1;
	}
	
	public int getUnit_Id() {
		return Unit_Id;
	}
	
	public String getUnit_Status2() {
    	if(Unit_Status ==1)
        return "Active";
    	else return "Inactive";
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
	public int getUnit_Status() {
		return Unit_Status;
	}
	public void setUnit_Status(int unit_Status) {
		Unit_Status = unit_Status;
	}

	@Override
	public String toString() {
		return Unit_Name;
	}
	
}
