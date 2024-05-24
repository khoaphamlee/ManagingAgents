package models;

public class Import_Detail {
	private int Import_Detail_Id;
	private int IdImport;
	private int IdUnit;
	private int IdItems;
	private double Items_Price;
	private double Total_Money;
	private int Items_Quantity;
	
	public Import_Detail() {
		super();
	}

	public Import_Detail(int import_Detail_Id, int idImport, int idUnit, int idItems, double items_Price,
			double total_Money, int items_Quantity) {
		super();
		Import_Detail_Id = import_Detail_Id;
		IdImport = idImport;
		IdUnit = idUnit;
		IdItems = idItems;
		Items_Price = items_Price;
		Total_Money = total_Money;
		Items_Quantity = items_Quantity;
	}

	public int getImport_Detail_Id() {
		return Import_Detail_Id;
	}

	public void setImport_Detail_Id(int import_Detail_Id) {
		Import_Detail_Id = import_Detail_Id;
	}

	public int getIdImport() {
		return IdImport;
	}

	public void setIdImport(int idImport) {
		IdImport = idImport;
	}

	public int getIdUnit() {
		return IdUnit;
	}

	public void setIdUnit(int idUnit) {
		IdUnit = idUnit;
	}

	public int getIdItems() {
		return IdItems;
	}

	public void setIdItems(int idItems) {
		IdItems = idItems;
	}

	public double getItems_Price() {
		return Items_Price;
	}

	public void setItems_Price(double items_Price) {
		Items_Price = items_Price;
	}

	public double getTotal_Money() {
		return Total_Money;
	}

	public void setTotal_Money(double total_Money) {
		Total_Money = total_Money;
	}

	public int getItems_Quantity() {
		return Items_Quantity;
	}

	public void setItems_Quentity(int items_Quentity) {
		Items_Quantity = items_Quentity;
	}

	@Override
	public String toString() {
		return "Import_Detail [Import_Detail_Id=" + Import_Detail_Id + ", IdImport=" + IdImport + ", IdUnit=" + IdUnit
				+ ", IdItems=" + IdItems + ", Items_Price=" + Items_Price + ", Total_Money=" + Total_Money
				+ ", Items_Quentity=" + Items_Quantity + "]";
	}
	
}
