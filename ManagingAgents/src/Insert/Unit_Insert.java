package Insert;

import Data_Access_Object.DAO_Unit;
import models.Unit;

public class Unit_Insert {
	public static void main(String[] args) {
		Unit Unit1 = new Unit(0, "Unit1", true);
		
		DAO_Unit.getInstance().Add(Unit1);
	}
}
