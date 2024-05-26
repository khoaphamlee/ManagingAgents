package Insert;

import Data_Access_Object.DAO_DVT;
import models.DVT;

public class DVT_Insert {
	public static void main(String[] args) {
		DVT Dvt1 = new DVT(0);
		
		DAO_DVT.getInstance().Add(Dvt1);
	}
}
