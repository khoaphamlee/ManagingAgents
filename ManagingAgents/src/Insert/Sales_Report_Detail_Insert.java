package Insert;

import Data_Access_Object.DAO_Sales_Report_Detail;
import models.Sales_Report_Detail;

public class Sales_Report_Detail_Insert {
	public static void main(String[] args) {
		Sales_Report_Detail SalesReportDetail1 = new Sales_Report_Detail(0, 0, 0, 0, 0, 0);
		
		DAO_Sales_Report_Detail.getInstance().Add(SalesReportDetail1);
	}
}
