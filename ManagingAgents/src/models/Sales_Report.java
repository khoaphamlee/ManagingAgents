package models;

import java.sql.Date;

public class Sales_Report {
	private int SalesReport_Id;
	private int Month;
	private int Year;
	private Date SalesReport_Date;
	
	public Sales_Report() {
		
		super();
		SalesReport_Id = -1;
	}

	public Sales_Report(int salesReport_Id, Date salesReport_Date) {
		super();
		SalesReport_Id = salesReport_Id;
		
		SalesReport_Date = salesReport_Date;
		Month = salesReport_Date.getMonth() +1 ;
		Year = salesReport_Date.getYear() + 1900;
	}

	public int getSalesReport_Id() {
		return SalesReport_Id;
	}

	public void setSalesReport_Id(int salesReport_Id) {
		SalesReport_Id = salesReport_Id;
	}

	

	public Date getSalesReport_Date() {
		return SalesReport_Date;
	}

	public void setSalesReport_Date(Date salesReport_Date) {
		SalesReport_Date = salesReport_Date;
	}

	
	public int getMonth() {
		return Month;
	}
	public int getYear() {
		return Year;
	}
	
	@Override
	public String toString() {
		return "";
	}
	
}
