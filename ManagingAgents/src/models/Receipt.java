package models;

import java.sql.Date;
import java.time.LocalDate;

public class Receipt {
    private int Receipt_Id;
    private int Id_Agent;
    private int Id_User;
    private Date ReceiptDate;
    private double MoneyReceived;
    private int IsReceived; // Change to int
    
    public Receipt() {
        super();
    }
    
    public Receipt(int receipt_Id, int id_Agent, int id_User, Date receiptDate, double moneyReceived, int isReceived) { // Change to int
        super();
        Receipt_Id = receipt_Id;
        Id_Agent = id_Agent;
        Id_User = id_User;
        ReceiptDate = receiptDate;
        MoneyReceived = moneyReceived;
        IsReceived = isReceived; // Change to int
    }

    public int getReceipt_Id() {
        return Receipt_Id;
    }

    public void setReceipt_Id(int receipt_Id) {
        Receipt_Id = receipt_Id;
    }

    public int getId_Agent() {
        return Id_Agent;
    }

    public void setId_Agent(int id_Agent) {
        Id_Agent = id_Agent;
    }

    public int getId_User() {
        return Id_User;
    }

    public void setId_User(int id_User) {
        Id_User = id_User;
    }

    public Date getReceiptDate() {
        return ReceiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        ReceiptDate = receiptDate;
    }

    public double getMoneyReceived() {
        return MoneyReceived;
    }

    public void setMoneyReceived(double moneyReceived) {
        MoneyReceived = moneyReceived;
    }
    
    public int getIsReceived() { // Change to int
        return IsReceived;
    }
    
    public void setIsReceived(int isReceived) { // Change to int
        IsReceived = isReceived;
    }

    @Override
    public String toString() {
        return "Receipt [Receipt_Id=" + Receipt_Id + ", Id_Agent=" + Id_Agent
                + ", ReceiptDate=" + ReceiptDate + ", MoneyReceived=" + MoneyReceived + "]";
    }
}
