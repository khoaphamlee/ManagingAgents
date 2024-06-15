package datatable;

public class ImportDetailTable {
	private int detailId;
	private String importName;
	private String importUnit;
	private int importQuantity;
	private double importPrice;
	private double importTotal;
	private int idUnit;
	private int idItem;

	 public ImportDetailTable(ImportDetailTable other) {
	        this.detailId = other.detailId;
	        this.importName = other.importName;
	        this.importUnit = other.importUnit;
	        this.importQuantity = other.importQuantity;
	        this.importPrice = other.importPrice;
	        this.importTotal = other.importTotal;
	        this.idUnit = other.idUnit;
	        this.idItem = other.idItem;
	    }
	public ImportDetailTable(int detailId,String importName,int idItem, String importUnit,int idUnit, int importQuantity, double importPrice, double importTotal) {
        this.importName = importName;
        this.importUnit = importUnit;
        this.importQuantity = importQuantity;
        this.importPrice = importPrice;
        this.importTotal = importTotal;
        this.detailId = detailId;
        this.idUnit = idUnit;
        this.idItem = idItem;
    }
	public void update(ImportDetailTable other) {
        if (other != null) {
            this.detailId = other.detailId;
            this.importName = other.importName;
            this.importUnit = other.importUnit;
            this.importQuantity = other.importQuantity;
            this.importPrice = other.importPrice;
            this.importTotal = other.importTotal;
            this.idUnit = other.idUnit;
            this.idItem = other.idItem;
        }
    }
	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public void setIdUnit(int idUnit) {
		this.idUnit = idUnit;
	}
	public int getIdUnit() {
		return idUnit;
	}
	public int getDetailId() {
		return detailId;
	}
	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}
	public String getImportName() {
		return importName;
	}
	public double getImportPrice() {
		return importPrice;
	}
	public double getImportTotal() {
		return importTotal;
	}
	public int getImportQuantity() {
		return importQuantity;
	}
	 public String getImportUnit() {
		return importUnit;
	}
	 public void setImportName(String importName) {
		this.importName = importName;
	}
	 public void setImportQuantity(int importQuantity) {
		this.importQuantity = importQuantity;
	}
	 public void setImportTotal(double importTotal) {
		this.importTotal = importTotal;
	}
	 public void setImportUnit(String importUnit) {
		this.importUnit = importUnit;
	}
	 public void setImportPrice(double importPrice) {
		this.importPrice = importPrice;
	}
}
