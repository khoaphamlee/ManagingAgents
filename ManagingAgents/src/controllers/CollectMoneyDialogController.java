package controllers;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Data_Access_Object.DAO_Agent;
import Data_Access_Object.DAO_Items;
import Data_Access_Object.DAO_Receipt;
import Data_Access_Object.DAO_Unit;
import Database.JDBCUtil;
import controllers.ExportReceiptScreenController.PrintExportDetails;
import datatable.ExportDetailTable;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Agent;
import models.Items;
import models.Receipt;
import models.Unit;






public class CollectMoneyDialogController implements Initializable {
	@FXML
	private MFXButton cancelBtn;

	

	

	

	@FXML
	private MFXButton okBtn;

	@FXML
	private AnchorPane scenePane;
	
	@FXML
	private MFXDatePicker dateDp;
	
	@FXML
	private MFXTextField agentTf,moneyTf;
	

	private AlertMessage alertMessage = new AlertMessage();
	private Stage window;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	float Rate = 0;
	public void setupButton() {
		cancelBtn.setOnMouseClicked(event -> {
			CurrentExport.isCancel = true;
			window = (Stage) cancelBtn.getScene().getWindow();
			window.close();
		});

		okBtn.setOnMouseClicked(event -> {
			
				if (moneyTf.getText().isEmpty() ) {
					alertMessage.errorMessage("Please fill all blank fields!");
					return;
				} else {
					double money = 0;
					
					try {
						money = Double.parseDouble(moneyTf.getText());
					}
					catch(Exception e) {
						return;
					}
					Agent agent = DAO_Agent.getInstance().selectByCondition2("Agent_Id = " + CurrentAgent.agentId);
					
					
					
					
					double debt = agent.getAgent_Debt();
					if(money > debt) {
						alertMessage.errorMessage("Received amount exceeds the debt!");
						return ;
					}
					Receipt receipt = new Receipt(0,CurrentAgent.agentId,CurrentUser.userId,Date.valueOf(LocalDate.now()),money,1);
					DAO_Receipt.getInstance().Add(receipt);
					
					agent.setAgent_Debt(agent.getAgent_Debt() - money);
					DAO_Agent.getInstance().Update(agent);
					
							
					
					
					
					
					
					
				}
			
			
			showConfirmationDialog();
		});
		
	}

	public void setupScreen() throws SQLException {
		agentTf.setText(CurrentAgent.agentName);
		dateDp.setDisable(true);
		dateDp.setValue(LocalDate.now());
		agentTf.setDisable(true);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CurrentExport.isCancel = true;
		// TODO Auto-generated method stub
		try {
			setupScreen();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setupButton();
	}
	
	public class PrintExportDetails implements Printable {

	   
	    private int idExporter;
	    private int exportId;
	    private String agentName;
	    private int agentId;
	    
	    private double payment;
	    private LocalDate date;
	    
	    private String agentAdrress;
	    private String agentPhone;
	    private String agentEmail;
	    
	    
	    public PrintExportDetails(int idExporter, int exportId, String agentName, int agentId, double payment, LocalDate date, String agentAddress, String agentPhone, String agentEmail) {
	        this.idExporter = idExporter;
	        this.exportId = exportId;
	        this.agentName = agentName;
	        this.agentId = agentId;
	        this.payment = payment;
	        this.date = date;
	        this.agentAdrress = agentAddress;  // Gán giá trị cho thuộc tính mới
	        this.agentPhone = agentPhone;      // Gán giá trị cho thuộc tính mới
	        this.agentEmail = agentEmail;      // Gán giá trị cho thuộc tính mới
	    }

	    @Override
	    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
	        if (page > 0) {
	            return NO_SUCH_PAGE;
	        }

	        Graphics2D g2d = (Graphics2D) g;
	        g2d.translate(pf.getImageableX(), pf.getImageableY());
	        g2d.setFont(new Font("Arial", Font.BOLD, 20));

	        int y = 20;
	        
	        
	        g2d.drawString("MANAGING AGENTS", 219, y);
	        g2d.setFont(new Font("Arial", Font.PLAIN, 15));
	        y += 30;

	        g2d.drawString("COLLECT MONEY RECEIPT", 225, y);
	        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
	        y += 30;
	        g2d.drawString("Receipt id: "+ exportId , 10, y);
	        y += 20;
	        g2d.drawString("Date: " + date.toString(), 10, y);

	        y += 30;
	        g2d.drawString("Agent: " + agentName, 10, y);
	        y += 20;
	        g2d.drawString("Agent id: " + agentId, 10, y);
	        y += 20;
	        g2d.drawString("Agent address: " + agentAdrress, 10, y);
	        y += 20;
	        g2d.drawString("Agent phone: " + agentPhone, 10, y);
	        y += 20;
	        g2d.drawString("Agent email: " + agentEmail, 10, y);

	        y += 30;
	        
	        
	        
	        g2d.setFont(new Font("Arial", Font.BOLD, 12));
	        
	        g2d.drawString("Money: " + String.valueOf(payment) + " VND", 400, y + 30);

	        y += 20;

	        
	        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
	        g2d.drawString("Id collector: " + idExporter, 10, y);
	        y += 20;
	        g2d.setFont(new Font("Arial", Font.ITALIC, 10));
	        g2d.drawString("Printed on: 01/06/2024", 10, y);

	        return PAGE_EXISTS;
	    }
	}
private void showConfirmationDialog() {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Do you want to print receipt?");
        

        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonData.NO);

        
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeYes) {
            
            System.out.println("OK pressed. Performing action...");
            PrinterJob job = PrinterJob.getPrinterJob();
            
            Receipt re = DAO_Receipt.getInstance().selectAll().getLast();
            
            
	        job.setPrintable(new PrintExportDetails( re.getId_User(),re.getReceipt_Id(),agentTf.getText(),re.getId_Agent(),re.getMoneyReceived(), LocalDate.now() ,CurrentAgent.agentAdrress,CurrentAgent.agentPhone,CurrentAgent.agentEmail));

	        boolean doPrint = job.printDialog();
	        if (doPrint) {
	            try {
	                job.print();
	            } catch (PrinterException e) {
	                e.printStackTrace();
	            }
	        }
	        window = (Stage) cancelBtn.getScene().getWindow();
			window.close();
            
        } else {
        	window = (Stage) cancelBtn.getScene().getWindow();
			window.close();
        }
    }


}
