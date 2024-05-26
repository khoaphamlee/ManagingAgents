package Insert;

import java.math.BigDecimal;

import Data_Access_Object.DAO_Fixed_Values;
import models.Fixed_Values;

public class Fixed_Values_Insert {
	public static void main(String[] args) {
		
		double N_1 = 47.35;
		double N_2 = 14.00;
		BigDecimal n1 = new BigDecimal(N_1);
		BigDecimal n2 = new BigDecimal(N_2);
		
		Fixed_Values FixedValues1 = new Fixed_Values(0, n1, n2);
		
		DAO_Fixed_Values.getInstance().Add(FixedValues1);
	}
}
