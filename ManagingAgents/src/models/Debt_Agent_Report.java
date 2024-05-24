package models;

import java.sql.Date;

public class Debt_Agent_Report {
	private int DebtAgentReport_Id;
	private Date DebtAgentReport_Date;
	
	public Debt_Agent_Report() {
		super();
	}
	
	public Debt_Agent_Report(int debtAgentReport_Id, Date debtAgentReport_Date) {
		super();
		DebtAgentReport_Id = debtAgentReport_Id;
		DebtAgentReport_Date = debtAgentReport_Date;
	}
	
	public int getDebtAgentReport_Id() {
		return DebtAgentReport_Id;
	}
	public void setDebtAgentReport_Id(int debtAgentReport_Id) {
		DebtAgentReport_Id = debtAgentReport_Id;
	}
	public Date getDebtAgentReport_Date() {
		return DebtAgentReport_Date;
	}
	public void setDebtAgentReport_Date(Date debtAgentReport_Date) {
		DebtAgentReport_Date = debtAgentReport_Date;
	}

	@Override
	public String toString() {
		return "Debt_Agent_Report [DebtAgentReport_Id=" + DebtAgentReport_Id + ", DebtAgentReport_Date="
				+ DebtAgentReport_Date + "]";
	}
	
}
