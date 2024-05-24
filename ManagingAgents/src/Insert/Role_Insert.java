package Insert;

import Data_Access_Object.DAO_Role;
import models.Role;

public class Role_Insert {
	public static void main(String[] args) {
		Role Role1 = new Role(0, null);
		
		DAO_Role.getInstance().Add(Role1);
		
	}
}