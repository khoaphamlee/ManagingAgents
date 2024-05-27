package Insert;

import java.sql.Date;
import java.time.LocalDate;

import Data_Access_Object.DAO_User_Info;
import models.User_Info;

public class User_Info_Insert {

	public static void main(String[] args) {
		
		// Chuyển dữ liệu Date
		LocalDate slDate = LocalDate.of(2012,04,21);
		Date b = Date.valueOf(slDate);
		
		User_Info User_Info1 = new User_Info(0, 0, null, null, b, null, null);
		
		DAO_User_Info.getInstance().Add(User_Info1);

	}

}
