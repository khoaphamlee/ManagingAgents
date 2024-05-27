package Insert;

import java.sql.Date;
import java.time.LocalDate;

import Data_Access_Object.DAO_Import;
import models.Import;

public class Import_Insert {
	public static void main(String[] args) {
		
		// Chuyển dữ liệu Date
		LocalDate slDate = LocalDate.of(2012,04,21);
		Date b = Date.valueOf(slDate);
		
		Import Import1 = new Import(0, 0, b, 0);
		
		DAO_Import.getInstance().Add(Import1);
	}
}
