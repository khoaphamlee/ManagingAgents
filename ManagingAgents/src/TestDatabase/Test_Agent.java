package TestDatabase;

import Data_Access_Object.DAO_Agent;
import models.Agent;
import models.Agent_Type;
import models.Debt_Agent_Report;
import models.Debt_Agent_Report_Detail;
import models.District;
import models.Export;
import models.Export_Detail;
import models.Fixed_Values;
import models.Import;
import models.Import_Detail;
import models.Items;
import models.Receipt;
import models.Reception;
import models.Sales_Report;
import models.Sales_Report_Detail;
import models.Unit;
import Data_Access_Object.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Test_Agent {
	public static void main(String[] args) {
		//Agent DaiLy_1 = new Agent(22520603,"Minh Kha ","0868353011", "HoChiMinhCity", "Bladie", 15000000);
		
		Agent_Type x = new Agent_Type(22520602, "Khang", 0);
		
		
		LocalDate slDate = LocalDate.of(2012,04,21);
		Date b = Date.valueOf(slDate);
		
		double N_1 = 47.35;
		double N_2 = 14.00;
		BigDecimal n1 = new BigDecimal(N_1);
		BigDecimal n2 = new BigDecimal(N_2);

		
		//District item = new District(20, null, 0);
		
		/*DAO_Agent.getInstance().Add(DaiLy_1);*/
		
		/*
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
