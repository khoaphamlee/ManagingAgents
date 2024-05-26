package Insert;

import java.sql.Date;
import java.time.LocalDate;

import Data_Access_Object.DAO_Debt_Agent_Report;
import models.Debt_Agent_Report;

public class Debt_Agent_Report_Insert {
	public static void main(String[] args) {
		
		LocalDate slDate = LocalDate.of(2012,04,21);
		Date b = Date.valueOf(slDate);
		
		Debt_Agent_Report DebtAgentReport1 = new Debt_Agent_Report(0, b);
		DAO_Debt_Agent_Report.getInstance().Add(DebtAgentReport1);
	}
}
