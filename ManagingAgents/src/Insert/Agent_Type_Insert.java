package Insert;

import Data_Access_Object.DAO_Agent_Type;
import models.Agent_Type;

public class Agent_Type_Insert {
	public static void main(String[] args) {
		Agent_Type AgentType1 = new Agent_Type(0, null, 0);
		
		DAO_Agent_Type.getInstance().Add(AgentType1);
		// Select xem từ file Agent_Insert
	}
}
