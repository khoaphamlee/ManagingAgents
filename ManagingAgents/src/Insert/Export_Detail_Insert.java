package Insert;

import Data_Access_Object.DAO_Export_Detail;
import models.Export_Detail;

public class Export_Detail_Insert {
	public static void main(String[] args) {
		Export_Detail ExportDetail1 = new Export_Detail(0, 0, 0, 0, 0, 0, 0);
		
		DAO_Export_Detail.getInstance().Add(ExportDetail1);
	}
}
