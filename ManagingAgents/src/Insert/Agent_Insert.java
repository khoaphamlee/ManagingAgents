package Insert;

import Data_Access_Object.DAO_Agent;
import models.Agent;

public class Agent_Insert {
	public static void main(String[] args) {
		Agent Agent1 = new Agent();
		
		DAO_Agent.getInstance().Add(Agent1);
		
		
		/* Phần select
		Agent find = new Agent(22520603, "Kha", "0868853033", "HoChiMinh", "LinhTrung", 5000000);
		Agent rs = DAO_Agent.getInstance().seclectById(find);
		System.out.println(rs);
		*/
		
		/*
		ArrayList<Agent> list = DAO_Agent.getInstance().selectAll();
		for (Agent agent : list) {
			System.out.println(agent.toString());
		}
		*/
		
		/*
		ArrayList<Agent> list = DAO_Agent.getInstance().selectByCondition("Agent_Id != 22520601");
		for (Agent agent : list) {
			System.out.println(agent.toString());
		}
		*/
	}
}
