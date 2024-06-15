package datatable;

public class ExportDetailTable {
    private int detailId;
    private String exportName;
    private String exportUnit;
    private int exportQuantity;
    private double exportPrice;
    private double exportTotal;
    private int idUnit;
    private int idItem;

    public ExportDetailTable(ExportDetailTable other) {
        this.detailId = other.detailId;
        this.exportName = other.exportName;
        this.exportUnit = other.exportUnit;
        this.exportQuantity = other.exportQuantity;
        this.exportPrice = other.exportPrice;
        this.exportTotal = other.exportTotal;
        this.idUnit = other.idUnit;
        this.idItem = other.idItem;
    }

    public ExportDetailTable(int detailId, String exportName, int idItem, String exportUnit, int idUnit, int exportQuantity, double exportPrice, double exportTotal) {
        this.exportName = exportName;
        this.exportUnit = exportUnit;
        this.exportQuantity = exportQuantity;
        this.exportPrice = exportPrice;
        this.exportTotal = exportTotal;
        this.detailId = detailId;
        this.idUnit = idUnit;
        this.idItem = idItem;
    }

    public void update(ExportDetailTable other) {
        if (other != null) {
            this.detailId = other.detailId;
            this.exportName = other.exportName;
            this.exportUnit = other.exportUnit;
            this.exportQuantity = other.exportQuantity;
            this.exportPrice = other.exportPrice;
            this.exportTotal = other.exportTotal;
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

    public String getExportName() {
        return exportName;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public double getExportTotal() {
        return exportTotal;
    }

    public int getExportQuantity() {
        return exportQuantity;
    }

    public String getExportUnit() {
        return exportUnit;
    }

    public void setExportName(String exportName) {
        this.exportName = exportName;
    }

    public void setExportQuantity(int exportQuantity) {
        this.exportQuantity = exportQuantity;
    }

    public void setExportTotal(double exportTotal) {
        this.exportTotal = exportTotal;
    }

    public void setExportUnit(String exportUnit) {
        this.exportUnit = exportUnit;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }
}
