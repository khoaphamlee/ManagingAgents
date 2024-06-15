package datatable;

import java.sql.Date;
import java.time.LocalDate;

public class GoodsScreen2Table {
    private int Receipt_Id;
    private LocalDate Receipt_Date;
    private double Receipt_Amount;
    private String Receipt_Status;

    public GoodsScreen2Table() {
        super();
    }

    public GoodsScreen2Table(int receipt_Id, LocalDate receipt_Date, double receipt_Amount, String receipt_Status) {
        super();
        Receipt_Id = receipt_Id;
        Receipt_Date = receipt_Date;
        Receipt_Amount = receipt_Amount;
        Receipt_Status = receipt_Status;
    }

    public int getReceipt_Id() {
        return Receipt_Id;
    }

    public void setReceipt_Id(int receipt_Id) {
        Receipt_Id = receipt_Id;
    }

    public LocalDate getReceipt_Date() {
        return Receipt_Date;
    }

    public void setReceipt_Date(LocalDate receipt_Date) {
        Receipt_Date = receipt_Date;
    }

    public double getReceipt_Amount() {
        return Receipt_Amount;
    }

    public void setReceipt_Amount(double receipt_Amount) {
        Receipt_Amount = receipt_Amount;
    }

    public String getReceipt_Status() {
        return Receipt_Status;
    }

    public void setReceipt_Status(String receipt_Status) {
        Receipt_Status = receipt_Status;
    }

    @Override
    public String toString() {
        return "GoodsScreen2Table [Receipt_Id=" + Receipt_Id + ", Receipt_Date=" + Receipt_Date
                + ", Receipt_Amount=" + Receipt_Amount + ", Receipt_Status=" + Receipt_Status + "]";
    }
}
