package models;

public class Agent_Type {
    private int AgentType_Id;
    private String AgentType_Name;
<<<<<<< HEAD
    private double AgentType_MaxDebt;
=======
    private long AgentType_MaxDebt;
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
    private int AgentType_Status;

    public Agent_Type() {
        super();
    }

<<<<<<< HEAD
    public Agent_Type(int agentType_Id, String agentType_Name, double agentType_MaxDebt) {
=======
    public Agent_Type(int agentType_Id, String agentType_Name, long agentType_MaxDebt) {
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
        super();
        AgentType_Id = agentType_Id;
        AgentType_Name = agentType_Name;
        AgentType_MaxDebt = agentType_MaxDebt;
        AgentType_Status = 1; 
    }

<<<<<<< HEAD
    public Agent_Type(int agentType_Id, String agentType_Name, double agentType_MaxDebt, int agentType_Status) {
=======
    public Agent_Type(int agentType_Id, String agentType_Name, long agentType_MaxDebt, int agentType_Status) {
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
        super();
        AgentType_Id = agentType_Id;
        AgentType_Name = agentType_Name;
        AgentType_MaxDebt = agentType_MaxDebt;
        AgentType_Status = agentType_Status;
    }
<<<<<<< HEAD
    
    
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

    public double getAgentType_MaxDebt() {
    	
        return AgentType_MaxDebt;
    }
    
    public String getAgentType_MaxDebt2() {
    	String formatted = String.format("%.0f", AgentType_MaxDebt);
    	return formatted;
    }
    
    public void setAgentType_MaxDebt(double agentType_MaxDebt) {
=======

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
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
        AgentType_MaxDebt = agentType_MaxDebt;
    }

    public int getAgentType_Status() {
        return AgentType_Status;
    }
<<<<<<< HEAD
    public String getAgentType_Status2() {
    	if(AgentType_Status ==1)
        return "Active";
    	else return "Inactive";
    }
=======
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780

    public void setAgentType_Status(int agentType_Status) {
        AgentType_Status = agentType_Status;
    }
<<<<<<< HEAD
    
    

    @Override
    public String toString() {
        return  AgentType_Name;
=======

    @Override
    public String toString() {
        return "Agent_Type [AgentType_Id=" + AgentType_Id + ", AgentType_Name=" + AgentType_Name + "]";
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
    }
}
