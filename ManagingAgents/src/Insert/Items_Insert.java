package Insert;

import Data_Access_Object.DAO_Items;
import models.Items;

public class Items_Insert {
	public static void main(String[] args) {
		Items Items1 = new Items(0, null, 0, 0);
		
		DAO_Items.getInstance().Add(Items1);
	}
}
