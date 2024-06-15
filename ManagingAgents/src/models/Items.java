package models;

public class Items {
	private int Items_Id;
	private String Items_Name;
	private double Items_Price;
	private int Items_Quantity;
	private int Items_Status;
	private int Items_Unit;
	public Items() {
		super();
	}
	
	public Items(int items_Id, String items_Name, int item_Unit,double items_Price, int items_Quentity) {
		super();
		Items_Id = items_Id;
		Items_Name = items_Name;
		Items_Price = items_Price;
		Items_Quantity = items_Quentity;
		Items_Unit = item_Unit;
		Items_Status = 1;
	}
	
	public Items(int items_Id, String items_Name, int item_Unit, double items_Price, int items_Quentity, int items_Status) {
		super();
		Items_Id = items_Id;
		Items_Name = items_Name;
		Items_Price = items_Price;
		Items_Quantity = items_Quentity;
		Items_Status = items_Status;
		Items_Unit = item_Unit;
	}
	
	public int getItems_Id() {
		return Items_Id;
	}
	public void setItems_Id(int items_Id) {
		Items_Id = items_Id;
	}
	public String getItems_Name() {
		return Items_Name;
	}
	public void setItems_Name(String items_Name) {
		Items_Name = items_Name;
	}
	public double getItems_Price() {
		return Items_Price;
	}
	public void setItems_Price(double items_Price) {
		Items_Price = items_Price;
	}
	public int getItems_Quantity() {
		return Items_Quantity;
	}
	public void setItems_Quantity(int items_Quentity) {
		Items_Quantity = items_Quentity;
	}
	public String getItems_Status() {
		if(Items_Status == 1)
			return "Active";
		else 
			return "Inactive";
	}
	
	public int getItems_Status2() {
		return Items_Status;
	}
	public void setItems_Status(int items_Status) {
		Items_Status = items_Status;
	}
	
	public int getItems_Unit() {
		return Items_Unit;
	}
	public void setItems_Unit(int items_Unit) {
		Items_Unit = items_Unit;
	}

	@Override
	public String toString() {
		return Items_Name;
	}
	
}
