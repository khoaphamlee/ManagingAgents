package Insert;

import java.sql.Date;
import java.time.LocalDate;

import Data_Access_Object.DAO_Sales_Report;
import models.Sales_Report;

public class Sales_Report_Insert {
	public static void main(String[] args) {
		
		LocalDate slDate = LocalDate.of(2012,04,21);
		Date b = Date.valueOf(slDate);
		
		Sales_Report SalesReport1 = new Sales_Report(0, 0, b);
		
		DAO_Sales_Report.getInstance().Add(SalesReport1);
	}
}
