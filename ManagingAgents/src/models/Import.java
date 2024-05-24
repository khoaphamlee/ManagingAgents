package models;

import java.sql.Date;

public class Import {
	private int ImportId;
	private int Id_User;
	private Date ImportDate;
	private double Import_TotalMoney;
	
	public Import() {
		super();
	}

	
	public Import(int importId, int id_User, Date importDate, double import_TotalMoney) {
		super();
		ImportId = importId;
		Id_User = id_User;
		ImportDate = importDate;
		Import_TotalMoney = import_TotalMoney;
	}


	public int getImportId() {
		return ImportId;
	}

	public void setImportId(int importId) {
		ImportId = importId;
	}

	public Date getImportDate() {
		return ImportDate;
	}

	public void setImportDate(Date importDate) {
		ImportDate = importDate;
	}

	public double getImport_TotalMoney() {
		return Import_TotalMoney;
	}

	public void setImport_TotalMoney(double import_TotalMoney) {
		Import_TotalMoney = import_TotalMoney;
	}

	
	public int getId_User() {
		return Id_User;
	}


	public void setId_User(int id_User) {
		Id_User = id_User;
	}


	@Override
	public String toString() {
		return "Import [ImportId=" + ImportId + ", Id_User=" + Id_User + ", ImportDate=" + ImportDate
				+ ", Import_TotalMoney=" + Import_TotalMoney + "]";
	}
	
}
