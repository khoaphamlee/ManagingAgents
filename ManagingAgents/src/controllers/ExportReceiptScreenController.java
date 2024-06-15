package controllers;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
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
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import javax.mail.FetchProfile.Item;

import Data_Access_Object.DAO_Agent;
import Data_Access_Object.DAO_Agent_Type;
import Data_Access_Object.DAO_Export;
import Data_Access_Object.DAO_Export_Detail;
import Data_Access_Object.DAO_Import;
import Data_Access_Object.DAO_Import_Detail;
import Data_Access_Object.DAO_Items;

import Data_Access_Object.DAO_Receipt;

import Data_Access_Object.DAO_Unit;
import Database.JDBCUtil;
import datatable.ImportScreenTable;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Agent;
import models.Agent_Type;
import models.District;
import models.Export;
import models.Export_Detail;
import models.Import;
import models.Import_Detail;
import models.Items;

import models.Receipt;


import models.Unit;
import datatable.ExportDetailTable;
import datatable.GoodsScreen2Table;
import datatable.ExportDetailTable;

public class ExportReceiptScreenController implements Initializable {

    Stage window;
    private boolean canChange;
    private int timeChange;

    @FXML
    private MFXButton addBtn;

    @FXML
    private MFXTextField amountTf, paymentTf,remainTf;

    @FXML
    private MFXButton cancelBtn;

    @FXML
    private MFXDatePicker dateDP;

    @FXML
    private MFXButton deleteBtn,allBtn;

    @FXML
    private MFXButton editBtn;

    @FXML
    private MFXButton okBtn;
    
    @FXML
    private MFXComboBox<Agent> agencyCCB;

    @FXML
    private MFXTableView<ExportDetailTable> table;

    Dialog<ButtonType> dialog = new Dialog<>();
    
    private DAO_Import daoImport;
    
    private DAO_Receipt daoReceipt;
    
    

    
    ObservableList<ExportDetailTable> exportDetail = FXCollections.observableArrayList();
    ObservableList<ExportDetailTable> exportDetail2 = FXCollections.observableArrayList();

    private AlertMessage alertMessage = new AlertMessage();

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		// TODO Auto-generated method stub
		connection = JDBCUtil.getConnection();
		String query = "SELECT Agent_Id, Agent_Name,AgentType_Id FROM AGENT WHERE Agent_Status = "+ 1;
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			List<Agent> dvts = new ArrayList<Agent>();
			while (resultSet.next()) {
				int Agent_Id = resultSet.getInt("Agent_Id");
				String Agent_Name = resultSet.getString("Agent_Name");
				int AgentType_Id = resultSet.getInt("AgentType_Id");
				dvts.add(new Agent(Agent_Id , Agent_Name,"","",0,1,0,AgentType_Id,""));
			}
			
			agencyCCB.setItems(FXCollections.observableArrayList(dvts));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			setupScreen();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setupButton();
		
		
	}

	// Load data from the database
	public void loadTable() throws SQLException {
		
		
		
		
		
		
		if(CurrentExport.ExportId != 0) {
		ArrayList<Export_Detail> exDetails = DAO_Export_Detail.getInstance().selectByCondition(" IdExport = " + CurrentExport.ExportId);
		
		 int importQuantity;
		 double importPrice;
		 double importTotal;
		 System.out.print(exDetails.size());
		 
		 
		  
		 
		 
		 
		for(int i = 0;i<exDetails.size();i++) {
			Export_Detail exDetail = exDetails.get(i);
			Items items = DAO_Items.getInstance().selectByCondition2(" Items_Id = " + exDetail.getItemsId());
			
			String exportName = items.getItems_Name();
			
			Unit unit = DAO_Unit.getInstance().selectByCondition2(" Unit_Id = "+  items.getItems_Unit() );
			
			exportDetail.add( new ExportDetailTable(i+1,exportName,items.getItems_Id(),unit.getUnit_Name(),unit.getUnit_Id(),exDetail.getItemsQuantity(),exDetail.getPriceExport(),exDetail.getTotalMoney_Export()));
		}
		table.setItems(exportDetail);
		}
		
		else {
			for(int i = 0;i<DetailImportHelper.importDetail.size();i++) {
				exportDetail2.add(new ExportDetailTable(DetailImportHelper.exportDetail.get(i)));
			}
			table.setItems(DetailImportHelper.exportDetail);
		}
		
		
		table.autosize();
		table.autosizeColumns();
		table.autosizeColumnsOnInitialization();
	}

	// Reload screen
	public void reloadTable() throws SQLException {
		exportDetail.clear();
		
		loadTable();
	}

	

	@SuppressWarnings("unchecked")
	public void setupScreen() throws SQLException {
		dateDP.setDisable(true);
		
		if(CurrentExport.ExportId == 0) {
			dateDP.setValue(LocalDate.now());
			amountTf.setText(  "0");
		}
		else {
			dateDP.setValue(CurrentExport.ExportDate.toLocalDate());
			okBtn.setDisable(true);
			amountTf.setDisable(true);
			addBtn.setDisable(true);
			editBtn.setDisable(true);
			deleteBtn.setDisable(true);
			amountTf.setText(CurrentExport.Export_TotalMoney + "");
			paymentTf.setText(CurrentExport.Export_Payment + "");
			remainTf.setText(CurrentExport.Export_Remain + "");
			paymentTf.setDisable(true);
			
			
			for(int i= 0;i<agencyCCB.getItems().size();i++) {
				if(agencyCCB.getItems().get(i).getAgent_Id() == CurrentExport.Id_Agent ) {
					agencyCCB.setValue(agencyCCB.getItems().get(i));
					agencyCCB.setText(agencyCCB.getValue().getAgent_Name());
				}
				
			}
			
			agencyCCB.setDisable(true);
		}
		paymentTf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                	
                	double total = Double.parseDouble(amountTf.getText());
    				double payment = Double.parseDouble(newValue);
    				
    				System.out.print(payment);
    				if(payment <= total) {
    					remainTf.setText((total-payment) + "");
    				}
    				else
    				remainTf.setText( "0");
                }
                catch (Exception e){
                	
                }
                
            }
        });
		amountTf.setDisable(true);
		remainTf.setDisable(true);
		
		// Initialize columns
		MFXTableColumn<ExportDetailTable> idColumn = new MFXTableColumn<>("Id", true, Comparator.comparing(ExportDetailTable::getDetailId));
		MFXTableColumn<ExportDetailTable> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(ExportDetailTable::getExportName));
		MFXTableColumn<ExportDetailTable> unitColumn = new MFXTableColumn<>("Unit", true, Comparator.comparing(ExportDetailTable::getExportUnit));
		MFXTableColumn<ExportDetailTable> quantityColumn = new MFXTableColumn<>("Quantity", true, Comparator.comparing(ExportDetailTable::getExportQuantity));
		MFXTableColumn<ExportDetailTable> priceColumn = new MFXTableColumn<>("Price", true, Comparator.comparing(ExportDetailTable::getExportPrice));
		MFXTableColumn<ExportDetailTable> totalColumn = new MFXTableColumn<>("Total", true, Comparator.comparing(ExportDetailTable::getExportTotal));
		
		

		// Set row cell factories
		idColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ExportDetailTable::getDetailId));
		nameColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ExportDetailTable::getExportName));
		unitColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ExportDetailTable::getExportUnit));
		quantityColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ExportDetailTable::getExportQuantity));
		priceColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ExportDetailTable::getExportPrice));
		totalColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ExportDetailTable::getExportTotal));
		
			
		
		// Add columns to the table
		table.getTableColumns().addAll(idColumn,nameColumn,unitColumn,quantityColumn,priceColumn,totalColumn);
		
		

		// Add filters to the table
		table.getFilters().add(new IntegerFilter<>("Id", ExportDetailTable::getDetailId));
		table.getFilters().add(new StringFilter<>("Name", ExportDetailTable::getExportName));
		table.getFilters().add(new StringFilter<>("Unit", ExportDetailTable::getExportUnit));
		table.getFilters().add(new IntegerFilter<>("Quantity", ExportDetailTable::getExportQuantity));
		table.getFilters().add(new DoubleFilter<>("Price", ExportDetailTable::getExportPrice));
		table.getFilters().add(new DoubleFilter<>("Total", ExportDetailTable::getExportTotal));
		
		
		

		loadTable();
		table.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				newValue.values().stream().findFirst().ifPresent(p -> {
					DetailImportHelper.Import_Detail_Id = p.getDetailId();
					DetailImportHelper.isAdd = false;
					DetailImportHelper.IdItems = p.getIdItem();
					DetailImportHelper.IdUnit = p.getIdUnit();
					DetailImportHelper.Items_Quantity = p.getExportQuantity();
					DetailImportHelper.Items_Price = p.getExportPrice();
				});
			}
		});
		
		
		
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
	        job.setPrintable(new PrintExportDetails(table.getItems(), CurrentExport.Id_User,CurrentExport.ExportId,agencyCCB.getValue().getAgent_Name(),agencyCCB.getValue().getAgent_Id(),CurrentExport.Export_TotalMoney,CurrentExport.Export_Payment, LocalDate.of( CurrentExport.ExportDate.getYear()+1900 ,CurrentExport.ExportDate.getMonth()+1,CurrentExport.ExportDate.getDate())) );

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

	public void setupButton() {
		
		
		okBtn.setOnMouseClicked(event -> {
			
			
			if(CurrentExport.ExportId == 0) {
				if (amountTf.getText().isEmpty() || remainTf.getText().isEmpty()
						|| paymentTf.getText().isEmpty() || agencyCCB.getText().isEmpty() ) {
					alertMessage.errorMessage("Please add items!");
					return;
				
				}
				double total = Double.parseDouble(amountTf.getText());
				double payment = Double.parseDouble(paymentTf.getText());
				double remain = Double.parseDouble(remainTf.getText());
				
				
				if(payment > total) {
					alertMessage.errorMessage("Payment must be less than or equal to the total!");
					return;
				}
				
				
				
				double debt = DAO_Agent.getInstance().getAgentDebtById(agencyCCB.getValue().getAgent_Id());
				 Agent_Type atype = DAO_Agent_Type.getInstance().seclectById2(agencyCCB.getValue().getAgent_Type());
				 double maxdebt = atype.getAgentType_MaxDebt();
				 
				 
				 if(remain + debt > maxdebt) {
					 alertMessage.errorMessage("The agent's debt has reached its maximum limit!");
					 return;
				 }
				 
				 Agent agent = DAO_Agent.getInstance().selectByCondition2(" Agent_Id = " + agencyCCB.getValue().getAgent_Id());
				 agent.setAgent_Debt(agent.getAgent_Debt() + remain);
				 
				 DAO_Agent.getInstance().Update(agent);
				 
				 
				 
				 Receipt receipt = new Receipt(0,agencyCCB.getValue().getAgent_Id(),CurrentUser.userId,Date.valueOf(LocalDate.now()),payment,1	);
				DAO_Receipt.getInstance().Add(receipt);
				Receipt receipt2 = DAO_Receipt.getInstance().selectAll().getLast();
						 
				
				
						
				Export tempt = new Export(0,agencyCCB.getValue().getAgent_Id(),CurrentUser.userId,receipt2.getReceipt_Id(), Date.valueOf(LocalDate.now()),total,payment,remain);
				DAO_Export.getInstance().Add(tempt);
				
				ArrayList<Export> a = DAO_Export.getInstance().selectAll();
				for(int i = 0;i<DetailImportHelper.exportDetail.size();i++) {
					
					
					ExportDetailTable detailTable = DetailImportHelper.exportDetail.get(i);
					
					Items tempt2 = DAO_Items.getInstance().selectByCondition2("Items_Id = " + detailTable.getIdItem() );
					tempt2.setItems_Quantity(tempt2.getItems_Quantity() - detailTable.getExportQuantity());
					
					
					DAO_Items.getInstance().Update(tempt2);
					Export_Detail detail = new Export_Detail(detailTable.getDetailId(),a.getLast().getExport_Id() , detailTable.getIdItem(),detailTable.getExportQuantity(), detailTable.getExportPrice(), detailTable.getExportTotal() );
					DAO_Export_Detail.getInstance().Add(detail);
					
				}
				Export tempt2 = DAO_Export.getInstance().selectAll().getLast();
				CurrentExport.Id_User =tempt2.getId_User();
				CurrentExport.Export_TotalMoney = tempt2.getExport_TotalMoney();
				CurrentExport.Export_Payment = tempt2.getPayAmount();
				CurrentExport.ExportDate = tempt2.getExport_Date();
				CurrentExport.ExportId = tempt2.getExport_Id();
				CurrentExport.Id_Agent =tempt2.getId_Agent();
				showConfirmationDialog();
			}
			
			
			
		});
		
		cancelBtn.setOnMouseClicked(event -> {
			
			
			window = (Stage) cancelBtn.getScene().getWindow();
			window.close();
		});
		
		addBtn.setOnMouseClicked(event -> {
			if(agencyCCB.getText().isEmpty()) {
				alertMessage.errorMessage("Please sellect a agent!");
				 return;
			}
			try {
				DetailImportHelper.isExport = true;
				if(DetailImportHelper.exportDetail.size() > 0) {
					DetailImportHelper.isAdd= true;
				DetailImportHelper.Import_Detail_Id = DetailImportHelper.exportDetail.getLast().getDetailId();
				System.out.print("DIT2");
				}
				else {
					
					DetailImportHelper.isAdd= true;
					DetailImportHelper.Import_Detail_Id = 0;
				}
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ImportItem.fxml"));
				Parent root = (Parent) loader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setResizable(false); 
				stage.setTitle("Managing Agents");
				stage.getIcons().add(new Image("/images/mylogo.png"));
				
				
				
				
				
				
				
				stage.showAndWait();
				if (!stage.isShowing() && CurrentExport.isCancel == false) {
				
					
					
					DetailImportHelper.exportDetail.add(new ExportDetailTable(DetailImportHelper.Import_Detail_Id+1, DetailImportHelper.itemName, DetailImportHelper.IdItems,DetailImportHelper.unitName, DetailImportHelper.IdUnit, DetailImportHelper.Items_Quantity, DetailImportHelper.Items_Price,DetailImportHelper.Items_Quantity* DetailImportHelper.Items_Price ));
					
					exportDetail2.clear();
					for(int i = 0;i<DetailImportHelper.exportDetail.size();i++) {
						exportDetail2.add(new ExportDetailTable(DetailImportHelper.exportDetail.get(i)));
					}
					table.setItems(exportDetail2);
					double total = 0;
					for(ExportDetailTable im : exportDetail2) {
						total += im.getExportTotal();
					}
					amountTf.setText(total+"");
					table.autosize();
					table.autosizeColumns();
					table.autosizeColumnsOnInitialization();
					DetailImportHelper.Import_Detail_Id = 0;
					DetailImportHelper.isExport = false;
				}
				else {
					CurrentExport.isCancel = false;
				}
			} catch (Exception e) {
				alertMessage.errorMessage("Can't load the scene!");
				e.printStackTrace();
				return;
			}
		});
		
		editBtn.setOnMouseClicked(event -> {
			if(DetailImportHelper.isAdd == true) {
				alertMessage.errorMessage("Please select the object you want to change information!");
				return;
			}
			DetailImportHelper.isExport = true;
			try {
				System.out.print("DSDLFDLSFSD");
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ImportItem.fxml"));
				Parent root = (Parent) loader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setResizable(false); 
				
				stage.setTitle("Managing Agents");
				stage.getIcons().add(new Image("/images/mylogo.png"));
				
				stage.showAndWait();
				if (!stage.isShowing() && CurrentExport.isCancel == false) {
					
					ExportDetailTable a = new ExportDetailTable(DetailImportHelper.Import_Detail_Id, DetailImportHelper.itemName, DetailImportHelper.IdItems,DetailImportHelper.unitName, DetailImportHelper.IdUnit, DetailImportHelper.Items_Quantity, DetailImportHelper.Items_Price,DetailImportHelper.Items_Quantity* DetailImportHelper.Items_Price );
					
					for(int i =0;i<DetailImportHelper.exportDetail.size();i++) {
						if(DetailImportHelper.exportDetail.get(i).getDetailId() == a.getDetailId()) DetailImportHelper.exportDetail.get(i).update(a);
					}
					
					exportDetail2.clear();
					for(int i = 0;i<DetailImportHelper.exportDetail.size();i++) {
						exportDetail2.add(new ExportDetailTable(DetailImportHelper.exportDetail.get(i)));
					}
					table.setItems(exportDetail2);
					double total = 0;
					for(ExportDetailTable im : exportDetail2) {
						total += im.getExportTotal();
					}
					amountTf.setText(total+"");
					table.autosize();
					table.autosizeColumns();
					table.autosizeColumnsOnInitialization();
					DetailImportHelper.Import_Detail_Id = 0;
					DetailImportHelper.isAdd = true;
					DetailImportHelper.isExport = false;
				}
				else {
					CurrentExport.isCancel = false;
				}
			}
			
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		});
		
		
		deleteBtn.setOnMouseClicked(event -> {
			
			
			if(DetailImportHelper.isAdd == true) {
				alertMessage.errorMessage("Please select the object you want to change information!");
				return;
			}
					
					System.out.print(DetailImportHelper.exportDetail.size());
			
					for(int i =0;i<DetailImportHelper.exportDetail.size();i++) {
						
						if(DetailImportHelper.exportDetail.get(i).getDetailId() == DetailImportHelper.Import_Detail_Id) DetailImportHelper.exportDetail.remove(i);
					}
					System.out.print(DetailImportHelper.exportDetail.size());
					exportDetail2.clear();
					for(int i = 0;i<DetailImportHelper.exportDetail.size();i++) {
						exportDetail2.add(new ExportDetailTable(DetailImportHelper.exportDetail.get(i)));
					}
					table.setItems(exportDetail2);
					double total = 0;
					for(ExportDetailTable im : exportDetail2) {
						total += im.getExportTotal();
					}
					amountTf.setText(total+"");
					table.autosize();
					table.autosizeColumns();
					table.autosizeColumnsOnInitialization();
					DetailImportHelper.Import_Detail_Id = 0;
					DetailImportHelper.isAdd = true;
				
				
		
		
	});
		allBtn.setOnMouseClicked(event -> {
			
			
			String tempt = amountTf.getText();
			if(tempt.isEmpty() == false) {
				paymentTf.setText(tempt);
			}
				
				
		
		
	});
		
		
		
	
	}
	
	
	

	public class PrintExportDetails implements Printable {

	    private ObservableList<ExportDetailTable> exportDetails;
	    private int idExporter;
	    private int exportId;
	    private String agentName;
	    private int agentId;
	    private double total;
	    private double payment;
	    private LocalDate date;
	    
	    
	    public PrintExportDetails(ObservableList<ExportDetailTable> exportDetails,int idExporter, int exportId,String agentName, int agentId, double total, double payment, LocalDate date) {
	        this.exportDetails = exportDetails;
	        this.agentName = agentName;
	        this.agentId = agentId;
	        this.total = total;
	        this.payment = payment;
	        this.date = date;
	        this.exportId =exportId;
	        this.idExporter = idExporter;
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

	        g2d.drawString("EXPORT RECEIPT", 250, y);
	        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
	        y += 30;
	        g2d.drawString("Export id: "+ exportId , 10, y);
	        y += 20;
	        g2d.drawString("Date: " + date.toString(), 10, y);

	        y += 30;
	        g2d.drawString("Agent: " + agentName, 10, y);
	        y += 20;
	        g2d.drawString("Agent id: " + agentId, 10, y);

	        y += 30;
	        g2d.drawString("Export items:", 10, y);

	        y += 20;
	        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
	        g2d.drawLine(10, y, 550, y);
	        y += 20;
	        
	        g2d.drawString("Detail ID", 30, y);
	        g2d.drawString("Export Name", 100, y);
	        g2d.drawString("Export Unit", 200, y);
	        g2d.drawString("Quantity", 300, y);
	        g2d.drawString("Price (VND)", 400, y);
	        g2d.drawString("Total (VND)", 500, y);

	        y += 20;
	        g2d.drawLine(10, y, 550, y);

	        int index = 1;
	        for (ExportDetailTable detail : exportDetails) {
	            y += 20;
	            
	            g2d.drawString(String.valueOf(detail.getDetailId()), 35, y);
	            g2d.drawString(detail.getExportName(), 105, y);
	            g2d.drawString(detail.getExportUnit(), 205, y);
	            g2d.drawString(String.valueOf(detail.getExportQuantity()), 305, y);
	            g2d.drawString(String.valueOf(detail.getExportPrice()), 405, y);
	            g2d.drawString(String.valueOf(detail.getExportTotal()), 505, y);
	            index++;
	        }

	        y += 30;
	        g2d.drawLine(10, y, 550, y);
	        
	        g2d.setFont(new Font("Arial", Font.BOLD, 12));
	        g2d.drawString("Total: " + String.valueOf(total) + " VND", 400, y + 30);
	        g2d.drawString("Payment: " + String.valueOf(payment) + " VND", 400, y + 50);

	        y += 20;

	        // Thêm dòng mã số thu ngân
	        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
	        g2d.drawString("Id exporter: " + idExporter, 10, y);
	        y += 20;
	        g2d.setFont(new Font("Arial", Font.ITALIC, 10));
	        g2d.drawString("Printed on: 01/06/2024", 10, y);

	        return PAGE_EXISTS;
	    }
	}

	
	
	
}
