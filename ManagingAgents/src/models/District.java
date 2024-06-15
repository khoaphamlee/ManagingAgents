package models;

public class District {
    private int district_Id;
    private String district_Name;
    private int district_Status;

    public District() {
        super();
    }

    public District(int district_id, String district_Name) {
        super();
        this.district_Id = district_id;
        this.district_Name = district_Name;
        this.district_Status = 1;
    }

    public District(int district_id, String district_Name, int district_Status) {
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

    public int getDistrict_Status() {
        return district_Status;
    }

    public void setDistrict_Status(int district_Status) {
        this.district_Status = district_Status;
    }
    public String getDistrict_Status2() {
    	if(district_Status ==1)
        return "Active";
    	else return "Inactive";
    }
    @Override
    public String toString() {
    	
    	return district_Name;
    }
}
