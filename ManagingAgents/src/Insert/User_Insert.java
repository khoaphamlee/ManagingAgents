package Insert;

import Data_Access_Object.DAO_User;
import models.User;

public class User_Insert {
	public static void main(String[] args) {
		User User1 = new User(0, 0, null, null);
		
		DAO_User.getInstance().Add(User1);	
		
		
		
	}
}
