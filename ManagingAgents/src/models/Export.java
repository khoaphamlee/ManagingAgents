package models;

import java.sql.Date;

public class Export {
	private int Export_Id;
	private int Id_Agent;
	private int Id_User;
	private int Id_Receipt;
	private Date Export_Date;
	private double Export_TotalMoney;
	private double PayAmount;
	private double Remaining;
	public Export() {
		super();
	}
	public Export(int export_Id, int idAgent, int id_User, int id_Receipt, Date export_Date, double export_TotalMoney,
			double payAmount, double remaining) {
		super();
		Export_Id = export_Id;
		Id_Agent = idAgent;
		Id_User = id_User;
		Id_Receipt = id_Receipt;
		Export_Date = export_Date;
		Export_TotalMoney = export_TotalMoney;
		PayAmount = payAmount;
		Remaining = remaining;
	}
	public int getExport_Id() {
		return Export_Id;
	}
	public void setExport_Id(int export_Id) {
		Export_Id = export_Id;
	}
	public int getId_Agent() {
		return Id_Agent;
	}
	public void setId_Agent(int idAgent) {
		Id_Agent = idAgent;
	}
	public int getId_User() {
		return Id_User;
	}
	public void setId_User(int id_User) {
		Id_User = id_User;
	}
	public int getId_Receipt() {
		return Id_Receipt;
	}
	public void setId_Receipt(int id_Receipt) {
		Id_Receipt = id_Receipt;
	}
	public Date getExport_Date() {
		return Export_Date;
	}
	public void setExport_Date(Date export_Date) {
		Export_Date = export_Date;
	}
	public double getExport_TotalMoney() {
		return Export_TotalMoney;
	}
	public void setExport_TotalMoney(double export_TotalMoney) {
		Export_TotalMoney = export_TotalMoney;
	}
	public double getPayAmount() {
		return PayAmount;
	}
	public void setPayAmount(double payAmount) {
		PayAmount = payAmount;
	}
	public double getRemaining() {
		return Remaining;
	}
	public void setRemaining(double remaining) {
		Remaining = remaining;
	}
	@Override
	public String toString() {
		return "Export [Export_Id=" + Export_Id + ", Id_Agent=" + Id_Agent + ", Id_User=" + Id_User + ", Export_Date="
				+ Export_Date + ", Export_TotalMoney=" + Export_TotalMoney + ", PayAmount=" + PayAmount + "]";
	}
	
	
}
