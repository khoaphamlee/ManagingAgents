package models;

public class Agent_Type {
    private int AgentType_Id;
    private String AgentType_Name;
    private double AgentType_MaxDebt;
    private int AgentType_Status;

    public Agent_Type() {
        super();
    }

    public Agent_Type(int agentType_Id, String agentType_Name, double agentType_MaxDebt) {
        super();
        AgentType_Id = agentType_Id;
        AgentType_Name = agentType_Name;
        AgentType_MaxDebt = agentType_MaxDebt;
        AgentType_Status = 1; 
    }

    public Agent_Type(int agentType_Id, String agentType_Name, double agentType_MaxDebt, int agentType_Status) {
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

    public double getAgentType_MaxDebt() {
    	
        return AgentType_MaxDebt;
    }
    
    public String getAgentType_MaxDebt2() {
    	String formatted = String.format("%.0f", AgentType_MaxDebt);
    	return formatted;
    }
    
    public void setAgentType_MaxDebt(double agentType_MaxDebt) {
        AgentType_MaxDebt = agentType_MaxDebt;
    }

    public int getAgentType_Status() {
        return AgentType_Status;
    }
    public String getAgentType_Status2() {
    	if(AgentType_Status ==1)
        return "Active";
    	else return "Inactive";
    }

    public void setAgentType_Status(int agentType_Status) {
        AgentType_Status = agentType_Status;
    }
    
    

    @Override
    public String toString() {
        return  AgentType_Name;
    }
}
