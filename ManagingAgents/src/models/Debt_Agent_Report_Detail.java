package models;

public class Debt_Agent_Report_Detail {
	private int DebtAgentReportDetail_Id;
	private int IdDebtAgentReport;
	private int IdAgent;
	private double NoDau;
	private double PhatSinh;
	private double NoCuoi;
	
	public Debt_Agent_Report_Detail() {
		super();
	}

	public Debt_Agent_Report_Detail(int debtAgentReportDetail_Id, int idDebtAgentReport, int idAgent, double noDau,
			double phatSinh, double noCuoi) {
		super();
		DebtAgentReportDetail_Id = debtAgentReportDetail_Id;
		IdDebtAgentReport = idDebtAgentReport;
		IdAgent = idAgent;
		NoDau = noDau;
		PhatSinh = phatSinh;
		NoCuoi = noCuoi;
	}

	public int getDebtAgentReportDetail_Id() {
		return DebtAgentReportDetail_Id;
	}

	public void setDebtAgentReportDetail_Id(int debtAgentReportDetail_Id) {
		DebtAgentReportDetail_Id = debtAgentReportDetail_Id;
	}

	public int getIdDebtAgentReport() {
		return IdDebtAgentReport;
	}

	public void setIdDebtAgentReport(int idDebtAgentReport) {
		IdDebtAgentReport = idDebtAgentReport;
	}

	public int getIdAgent() {
		return IdAgent;
	}

	public void setIdAgent(int idAgent) {
		IdAgent = idAgent;
	}

	public double getNoDau() {
		return NoDau;
	}

	public void setNoDau(double noDau) {
		NoDau = noDau;
	}

	public double getPhatSinh() {
		return PhatSinh;
	}

	public void setPhatSinh(double phatSinh) {
		PhatSinh = phatSinh;
	}

	public double getNoCuoi() {
		return NoCuoi;
	}

	public void setNoCuoi(double noCuoi) {
		NoCuoi = noCuoi;
	}

	@Override
	public String toString() {
		return "Debt_Agent_Report_Detail [DebtAgentReportDetail_Id=" + DebtAgentReportDetail_Id + ", IdDebtAgentReport="
				+ IdDebtAgentReport + ", IdAgent=" + IdAgent + ", NoDau=" + NoDau + ", PhatSinh=" + PhatSinh
				+ ", NoCuoi=" + NoCuoi + "]";
	}
	
}
