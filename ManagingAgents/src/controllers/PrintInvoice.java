package controllers; 
import java.awt.*;
import java.awt.print.*;
import javax.swing.*;

import java.awt.*;
import java.awt.print.*;
import javax.swing.*;

public class PrintInvoice implements Printable {

    public static void main(String[] args) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new PrintInvoice());

        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        if (page > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        g2d.setFont(new Font("Arial", Font.PLAIN, 10));

        int y = 20;
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("HÓA ĐƠN BÁN HÀNG", 200, y);

        y += 30;
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawString("Số hóa đơn: HD123456", 10, y);
        y += 20;
        g2d.drawString("Ngày: 01/06/2024", 10, y);

        y += 30;
        g2d.drawString("Khách hàng: Nguyễn Văn A", 10, y);
        y += 20;
        g2d.drawString("Địa chỉ: 123 Đường ABC, Quận XYZ, TP.HCM", 10, y);

        y += 30;
        g2d.drawString("Danh sách mặt hàng:", 10, y);

        y += 20;
        g2d.drawLine(10, y, 550, y);
        y += 20;
        g2d.drawString("STT", 10, y);
        g2d.drawString("Tên mặt hàng", 50, y);
        g2d.drawString("Số lượng", 200, y);
        g2d.drawString("Đơn giá (VND)", 300, y);
        g2d.drawString("Thành tiền (VND)", 420, y);
        
        y += 20;
        g2d.drawLine(10, y, 550, y);

        String[][] items = {
            {"1", "Mặt hàng 1", "2", "12,000", "24,000"},
            {"2", "Mặt hàng 2", "1", "15,000", "15,000"},
            {"3", "Mặt hàng 3", "5", "10,000", "50,000"},
        };

        for (String[] item : items) {
            y += 20;
            g2d.drawString(item[0], 10, y);
            g2d.drawString(item[1], 50, y);
            g2d.drawString(item[2], 200, y);
            g2d.drawString(item[3], 300, y);
            g2d.drawString(item[4], 420, y);
        }

        y += 30;
        g2d.drawLine(10, y, 550, y);
        y += 20;
        g2d.drawString("Tổng cộng:", 300, y);
        g2d.drawString("89,000", 420, y);

        y += 30;
        g2d.setFont(new Font("Arial", Font.ITALIC, 10));
        g2d.drawString("Cảm ơn quý khách!", 10, y);

        return PAGE_EXISTS;
    }
}
