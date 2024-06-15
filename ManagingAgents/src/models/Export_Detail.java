package models;

public class Export_Detail {
	private int ExportDetail_Id;
	private int IdExport;
	
	private int ItemsId;
	private int ItemsQuantity;
	private double PriceExport;
	private double TotalMoney_Export;
	
	public Export_Detail() {
		super();
	}
	
	public Export_Detail(int exportDetail_Id, int idExport, int items, int itemsQuantity,
			double priceExport, double totalMoney_Export) {
		super();
		ExportDetail_Id = exportDetail_Id;
		IdExport = idExport;
		
		ItemsId = items;
		ItemsQuantity = itemsQuantity;
		PriceExport = priceExport;
		TotalMoney_Export = totalMoney_Export;
	}
	
	
	public int getExportDetail_Id() {
		return ExportDetail_Id;
	}
	public void setExportDetail_Id(int exportDetail_Id) {
		ExportDetail_Id = exportDetail_Id;
	}
	public int getIdExport() {
		return IdExport;
	}
	public void setIdExport(int idExport) {
		IdExport = idExport;
	}
	
	
	public int getItemsQuantity() {
		return ItemsQuantity;
	}
	public void setItemsQuantity(int itemsQuantity) {
		ItemsQuantity = itemsQuantity;
	}
	public double getPriceExport() {
		return PriceExport;
	}
	public void setPriceExport(double priceExport) {
		PriceExport = priceExport;
	}
	public double getTotalMoney_Export() {
		return TotalMoney_Export;
	}
	public void setTotalMoney_Export(double totalMoney_Export) {
		TotalMoney_Export = totalMoney_Export;
	}
	
	public int getItemsId() {
		return ItemsId;
	}
	public void setItemsId(int itemsId) {
		ItemsId = itemsId;
	}
	@Override
	public String toString() {
		return ""	;
	}
	
}
