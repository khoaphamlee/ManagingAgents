package datatable;

public class DistrictScreenTable {
    private int district_Id;
    private String district_Name;
    private String district_Status;

    public DistrictScreenTable() {
        super();
    }

    public DistrictScreenTable(int district_Id, String district_Name) {
        super();
        this.district_Id = district_Id;
        this.district_Name = district_Name;
        this.district_Status = "Active";
    }

    public DistrictScreenTable(int district_Id, String district_Name, String district_Status) {
        super();
        this.district_Id = district_Id;
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

    public String getDistrict_Status() {
        return district_Status;
    }

    public void setDistrict_Status(String district_Status) {
        this.district_Status = district_Status;
    }
    
    @Override
    public String toString() {
        return "DistrictScreenTable [district_Id=" + district_Id + ", district_Name=" + district_Name + ", district_Status=" + district_Status + "]";
    }

    public static void main(String[] args) {
        // Test the class functionality
        DistrictScreenTable district1 = new DistrictScreenTable(1, "District 1");
        System.out.println(district1);

        DistrictScreenTable district2 = new DistrictScreenTable(2, "District 2", "Inactive");
        System.out.println(district2);
    }
}
