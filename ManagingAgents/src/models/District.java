package models;

public class District {
	private int district_Id;
	private String district_Name;
	private boolean district_Status;

	public District() {
		super();
	}

	public District(int district_id, String district_Name) {
		super();
		this.district_Id = district_id;
		this.district_Name = district_Name;
		district_Status = true;
	}
	
	public District(int district_id, String district_Name, boolean district_Status) {
		super();
		this.district_Id = district_id;
		this.district_Name = district_Name;
		this.district_Status = district_Status;
	}

	public int getDistrict_Id() {
		return district_Id;
	}
	
	public void setDistrict_Id(int district_Id) {
		this.district_Id = district_Id;
	}
	
	public String getDistrict_Name() {
		return district_Name;
	}
	
	public void setDistrict_Name(String district_Name) {
		this.district_Name = district_Name;
	}

	public boolean getDistrict_Status() {
		return district_Status;
	}

	public void setDistrict_Status(boolean district_Status) {
		this.district_Status = district_Status;
	}
	
}
