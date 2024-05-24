package Insert;

import java.sql.Date;
import java.time.LocalDate;

import Data_Access_Object.DAO_Reception;
import models.Reception;

public class Reception_Insert {

	public static void main(String[] args) {
		
		// Chuyển dữ liệu Date
		LocalDate slDate = LocalDate.of(2012,04,21);
		Date b = Date.valueOf(slDate);
		
		Reception Reception1 = new Reception(0, 0, 0, b);
		
		DAO_Reception.getInstance().Add(Reception1);

	}

}
