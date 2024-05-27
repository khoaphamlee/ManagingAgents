package Insert;

import Data_Access_Object.DAO_Debt_Agent_Report_Detail;
import models.Debt_Agent_Report_Detail;

public class Debt_Agent_Report_Detail_Insert {
	public static void main(String[] args) {
		Debt_Agent_Report_Detail DebtAgentReportDetail1 = new Debt_Agent_Report_Detail(0, 0, 0, 0, 0, 0);
		
		DAO_Debt_Agent_Report_Detail.getInstance().Add(DebtAgentReportDetail1);
	}
}
