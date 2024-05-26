package models;

public class Items {
	private int Items_Id;
	private String Items_Name;
	private double Items_Price;
	private int Items_Quantity;
	private boolean Items_Status;
	public Items() {
		super();
	}
	
	public Items(int items_Id, String items_Name, double items_Price, int items_Quentity) {
		super();
		Items_Id = items_Id;
		Items_Name = items_Name;
		Items_Price = items_Price;
		Items_Quantity = items_Quentity;
		Items_Status = true;
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
	public boolean getItems_Status() {
		return Items_Status;
	}
	public void setItems_Status(boolean items_Status) {
		Items_Status = items_Status;
	}

	@Override
	public String toString() {
		return "Items [Items_Id=" + Items_Id + ", Items_Name=" + Items_Name + ", Items_Price=" + Items_Price
				+ ", Items_Quentity=" + Items_Quantity + "]";
	}
	
}
