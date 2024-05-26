package models;

import java.sql.Date;

public class Receipt {
	private int Receipt_Id;
	private int Id_Agent;
	private int Id_User;
	private Date ReceiptDate;
	private double MoneyReceived;
	private boolean IsReceived;
	
	public Receipt() {
		super();
	
	}
	
	public Receipt(int recept_Id, int id_Agent, int id_User, Date receptDate, double moneyReceived, boolean isReceived) {
		super();
		Receipt_Id = recept_Id;
		Id_Agent = id_Agent;
		Id_User = id_User;
		ReceiptDate = receptDate;
		MoneyReceived = moneyReceived;
		IsReceived = isReceived;
	}

	public int getReceipt_Id() {
		return Receipt_Id;
	}

	public void setReceipt_Id(int recept_Id) {
		Receipt_Id = recept_Id;
	}

	public int getId_Agent() {
		return Id_Agent;
	}

	public void setId_Agent(int id_Agent) {
		Id_Agent = id_Agent;
	}

	public int getId_User() {
		return Id_User;
	}

	public void setId_User(int id_User) {
		Id_User = id_User;
	}

	public Date getReceiptDate() {
		return ReceiptDate;
	}

	public void setReceiptDate(Date receptDate) {
		ReceiptDate = receptDate;
	}

	public double getMoneyReceived() {
		return MoneyReceived;
	}

	public void setMoneyReceived(double moneyReceived) {
		MoneyReceived = moneyReceived;
	}
	
	public boolean getIsReceived() {
		return IsReceived;
	}
	
	public void setIsReceived(boolean isReceived) {
		IsReceived = isReceived;
	}

	@Override
	public String toString() {
		return "Receipt [Receipt_Id=" + Receipt_Id + ", Id_Agent=" + Id_Agent
				+ ", ReceiptDate=" + ReceiptDate + ", MoneyReceived=" + MoneyReceived + "]";
	}
	
}
