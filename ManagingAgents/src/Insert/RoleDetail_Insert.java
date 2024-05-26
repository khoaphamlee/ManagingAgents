package Insert;

import Data_Access_Object.DAO_RoleDetail;
import models.RoleDetail;

public class RoleDetail_Insert {
	public static void main(String[] args) {
		RoleDetail RoleDetail1 = new RoleDetail(0, 0, 0, null);
		
		DAO_RoleDetail.getInstance().Add(RoleDetail1);
		
		
	
	}
}
