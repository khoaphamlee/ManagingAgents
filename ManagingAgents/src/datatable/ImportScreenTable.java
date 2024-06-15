package datatable;

public class ImportScreenTable {
    private int import_Id;
    private double import_Money;

    public ImportScreenTable() {
        super();
    }

    public ImportScreenTable(int import_Id, double import_Money) {
        super();
        this.import_Id = import_Id;
        this.import_Money = import_Money;
    }

    public int getImport_Id() {
        return import_Id;
    }

    public void setImport_Id(int import_Id) {
    	this.import_Id = import_Id;
    }

    public double getImport_Money() {
        return import_Money;
    }

    public void setImport_Money(double import_Money) {
        this.import_Money = import_Money;
    }
    
}
