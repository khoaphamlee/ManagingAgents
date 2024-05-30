package models;

public class Agent_Type {
    private int AgentType_Id;
    private String AgentType_Name;
    private long AgentType_MaxDebt;
    private int AgentType_Status;

    public Agent_Type() {
        super();
    }

    public Agent_Type(int agentType_Id, String agentType_Name, long agentType_MaxDebt) {
        super();
        AgentType_Id = agentType_Id;
        AgentType_Name = agentType_Name;
        AgentType_MaxDebt = agentType_MaxDebt;
        AgentType_Status = 1; 
    }

    public Agent_Type(int agentType_Id, String agentType_Name, long agentType_MaxDebt, int agentType_Status) {
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

    public int getAgentType_Status() {
        return AgentType_Status;
    }

    public void setAgentType_Status(int agentType_Status) {
        AgentType_Status = agentType_Status;
    }

    @Override
    public String toString() {
        return "Agent_Type [AgentType_Id=" + AgentType_Id + ", AgentType_Name=" + AgentType_Name + "]";
    }
}
