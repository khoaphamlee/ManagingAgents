package datatable;

public class GoodsScreenTable {
    private int Items_Id;
    private String Items_Name;
    private String Item_unit;
    private int Item_unitId;
    private double Items_Price;
    private int Items_Quantity;
    private String Items_Status;

    public GoodsScreenTable() {
        super();
    }

    

    public GoodsScreenTable(int items_Id, String items_Name,String itemUnit,int itmeUnitId, double items_Price, int items_Quantity, String items_Status) {
        super();
        Items_Id = items_Id;
        Items_Name = items_Name;
        Items_Price = items_Price;
        Items_Quantity = items_Quantity;
        Items_Status = items_Status;
        Item_unit = itemUnit;
        Item_unitId = itmeUnitId;
    }
    public String getItem_unit() {
		return Item_unit;
	}
    public int getItem_unitId() {
		return Item_unitId;
	}
    public void setItem_unit(String item_unit) {
		Item_unit = item_unit;
	}
    public void setItem_unitId(int item_unitId) {
		Item_unitId = item_unitId;
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

    public void setItems_Quantity(int items_Quantity) {
        Items_Quantity = items_Quantity;
    }

    public String getItems_Status() {
        return Items_Status;
    }

    public void setItems_Status(String items_Status) {
        Items_Status = items_Status;
    }

    @Override
    public String toString() {
        return "Items [Items_Id=" + Items_Id + ", Items_Name=" + Items_Name + ", Items_Price=" + Items_Price
                + ", Items_Quantity=" + Items_Quantity + ", Items_Status=" + Items_Status + "]";
    }
}
