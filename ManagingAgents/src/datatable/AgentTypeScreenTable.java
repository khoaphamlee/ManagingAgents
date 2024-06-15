package datatable;

import java.sql.Date;
import java.time.LocalDate;
<<<<<<< HEAD
import java.util.Optional;

import io.github.palexdev.materialfx.controls.MFXTableView;
=======
<<<<<<< HEAD
import java.util.Optional;

import io.github.palexdev.materialfx.controls.MFXTableView;
=======
>>>>>>> 906790c5ce4401371dc295a95bc25ebb2a8f9660
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780

public class AgentTypeScreenTable {
	private int AgentType_Id;
	private String AgentType_Name;
	private long AgentType_MaxDebt;
	private String AgentType_Status;
	
	public AgentTypeScreenTable() {
		super();
	};
		
	public AgentTypeScreenTable(int agentType_Id, String agentType_Name, long agentType_MaxDebt, String agentType_Status) {
		super();
		AgentType_Id = agentType_Id;
		AgentType_Name = agentType_Name;
		AgentType_MaxDebt = agentType_MaxDebt;
		AgentType_Status = agentType_Status;
	}
	

	public int getAgentType_Id() {
		return AgentType_Id;
	}

	public void setAgentType_Id(int agentType_Id) {
		AgentType_Id = agentType_Id;
	}

	public String getAgentType_Name() {
		return AgentType_Name;
	}

	public void setAgentType_Name(String agentType_Name) {
		AgentType_Name = agentType_Name;
	}
	
	public long getAgentType_MaxDebt() {
		return AgentType_MaxDebt;
	}

	public void setAgentType_MaxDebt(long agentType_MaxDebt) {
		AgentType_MaxDebt = agentType_MaxDebt;
	}
	
	public String getAgentType_Status() {
		return AgentType_Status;
	}
	
	public void setAgentType_Status(String agentType_Status) {
		AgentType_Status = agentType_Status;
	}

	@Override
	public String toString() {
		return "Agent_Type [AgentType_Id=" + AgentType_Id + ", AgentType_Name=" + AgentType_Name + "]";
	}
	
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
	
>>>>>>> 906790c5ce4401371dc295a95bc25ebb2a8f9660
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
}
