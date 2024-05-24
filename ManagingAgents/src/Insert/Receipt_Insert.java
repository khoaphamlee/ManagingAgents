package Insert;

import java.sql.Date;
import java.time.LocalDate;

import Data_Access_Object.DAO_Receipt;
import models.Receipt;

public class Receipt_Insert {

	public static void main(String[] args) {
		
		// Chuyển dữ liệu Date
		LocalDate slDate = LocalDate.of(2012,04,21);
		Date b = Date.valueOf(slDate);

		Receipt Receipt1 = new Receipt(0, 0, 0, b, 0, true);
		
		DAO_Receipt.getInstance().Add(Receipt1);
	}

}
