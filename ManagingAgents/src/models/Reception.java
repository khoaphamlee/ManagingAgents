package models;

import java.sql.Date;

public class Reception {
	private int Reception_id;
	private int IdAgent;
	private int IdAgentType;
	private Date ReceptionDate;
	
	public Reception() {
		super();
	}

	public Reception(int reception_id, int idAgent, int idAgentType, Date receptionDate) {
		super();
		Reception_id = reception_id;
		IdAgent = idAgent;
		IdAgentType = idAgentType;
		ReceptionDate = receptionDate;
	}

	public int getReception_id() {
		return Reception_id;
	}

	public void setReception_id(int reception_id) {
		Reception_id = reception_id;
	}

	public int getIdAgent() {
		return IdAgent;
	}

	public void setIdAgent(int idAgent) {
		IdAgent = idAgent;
	}

	public int getIdAgentType() {
		return IdAgentType;
	}

	public void setIdAgentType(int idAgentType) {
		IdAgentType = idAgentType;
	}

	public Date getReceptionDate() {
		return ReceptionDate;
	}

	public void setReceptionDate(Date receptionDate) {
		ReceptionDate = receptionDate;
	}

	@Override
	public String toString() {
		return "Reception [Reception_id=" + Reception_id + ", IdAgent=" + IdAgent + ", IdAgentType=" + IdAgentType
				+ ", ReceptionDate=" + ReceptionDate + "]";
	}
}
