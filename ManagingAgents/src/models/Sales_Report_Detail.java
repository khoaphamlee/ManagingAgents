package models;

import Data_Access_Object.DAO_Agent;

public class Sales_Report_Detail {
	private int SalesReportDetail_Id;
	private int IdSalesReport;
	private int IdAgent;
	private int AmountExport;
	private double TotalMoney;
	private float Rate;
	
	public Sales_Report_Detail() {
		super();
	}

	public Sales_Report_Detail(int salesReportDetail_Id, int idSalesReport, int idAgent, int amountExport,
			double totalMoney, float rate) {
		super();
		SalesReportDetail_Id = salesReportDetail_Id;
		IdSalesReport = idSalesReport;
		IdAgent = idAgent;
		AmountExport = amountExport;
		TotalMoney = totalMoney;
		Rate = rate;
	}

	public String getAgentName() {
		return DAO_Agent.getInstance().getAgentNameById(IdAgent);
	}
	
	public int getSalesReportDetail_Id() {
		return SalesReportDetail_Id;
	}

	public void setSalesReportDetail_Id(int salesReportDetail_Id) {
		SalesReportDetail_Id = salesReportDetail_Id;
	}

	public int getIdSalesReport() {
		return IdSalesReport;
	}

	public void setIdSalesReport(int idSalesReport) {
		IdSalesReport = idSalesReport;
	}

	public int getIdAgent() {
		return IdAgent;
	}

	public void setIdAgent(int idAgent) {
		IdAgent = idAgent;
	}

	public int getAmountExport() {
		return AmountExport;
	}

	public void setAmountExport(int amountExport) {
		AmountExport = amountExport;
	}

	public double getTotalMoney() {
		return TotalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		TotalMoney = totalMoney;
	}

	public float getRate() {
		return Rate;
	}

	public void setRate(float rate) {
		Rate = rate;
	}

	@Override
	public String toString() {
		return "Sales_Report_Detail [SalesReportDetail_Id=" + SalesReportDetail_Id + ", IdSalesReport=" + IdSalesReport
				+ ", IdAgentType=" + IdAgent + ", AmountExport=" + AmountExport + ", TotalMoney=" + TotalMoney
				+ ", Rate=" + Rate + "]";
	}
	
}
