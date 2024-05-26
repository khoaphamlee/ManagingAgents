package models;

import java.sql.Date;

public class Sales_Report {
	private int SalesReport_Id;
	private int Id_User;
	private Date SalesReport_Date;
	
	public Sales_Report() {
		super();
	}

	public Sales_Report(int salesReport_Id, int id_User, Date salesReport_Date) {
		super();
		SalesReport_Id = salesReport_Id;
		Id_User = id_User;
		SalesReport_Date = salesReport_Date;
	}

	public int getSalesReport_Id() {
		return SalesReport_Id;
	}

	public void setSalesReport_Id(int salesReport_Id) {
		SalesReport_Id = salesReport_Id;
	}

	public int getId_User() {
		return Id_User;
	}

	public void setId_User(int id_User) {
		Id_User = id_User;
	}

	public Date getSalesReport_Date() {
		return SalesReport_Date;
	}

	public void setSalesReport_Date(Date salesReport_Date) {
		SalesReport_Date = salesReport_Date;
	}

	@Override
	public String toString() {
		return "Sales_Report [SalesReport_Id=" + SalesReport_Id + ", Id_User=" + Id_User + ", SalesReport_Date="
				+ SalesReport_Date + "]";
	}
	
}
