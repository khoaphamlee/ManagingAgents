package Insert;

import Data_Access_Object.DAO_Permission_Item;
import models.Permission_Item;

public class Permission_Item_Insert {
	public static void main(String[] args) {
		Permission_Item Permission_Item1 = new Permission_Item(0, null);
		
		DAO_Permission_Item.getInstance().Add(Permission_Item1);
		
		
	}
}
