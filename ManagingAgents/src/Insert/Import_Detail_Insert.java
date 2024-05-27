package Insert;

import Data_Access_Object.DAO_Import_Detail;
import models.Import_Detail;

public class Import_Detail_Insert {
	public static void main(String[] args) {
		
		Import_Detail ImportDetail1 = new Import_Detail(0, 0, 0, 0, 0, 0, 0);
		
		DAO_Import_Detail.getInstance().Add(ImportDetail1);
	}
}
