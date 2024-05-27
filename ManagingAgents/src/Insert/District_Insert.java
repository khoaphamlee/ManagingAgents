package Insert;

import Data_Access_Object.DAO_District;
import models.District;

public class District_Insert {
	public static void main(String[] args) {
		District District1 = new District(1, "district 1");
		
		DAO_District.getInstance().Add(District1);
	}
}
