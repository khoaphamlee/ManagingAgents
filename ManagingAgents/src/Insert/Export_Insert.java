package Insert;

import java.sql.Date;
import java.time.LocalDate;

import Data_Access_Object.DAO_Export;
import models.Export;

public class Export_Insert {
	public static void main(String[] args) {
		// Chuyển dữ liệu Date
		LocalDate slDate = LocalDate.of(2012,04,21);
		Date b = Date.valueOf(slDate);
		
		Export Export1 = new Export(0, 0, 0, 0, b, 0, 0, 0);
		
		DAO_Export.getInstance().Add(Export1);
	}
}
