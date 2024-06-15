package controllers;

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


import Data_Access_Object.DAO_Import;
import Data_Access_Object.DAO_Import_Detail;
import Data_Access_Object.DAO_Items;

import Data_Access_Object.DAO_Receipt;

import Data_Access_Object.DAO_Unit;
import Database.JDBCUtil;
import datatable.ImportScreenTable;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
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
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;


import models.Import;
import models.Import_Detail;
import models.Items;

import models.Receipt;


import models.Unit;
import datatable.GoodsScreen2Table;
import datatable.ImportDetailTable;

public class GoodsReceiptScreenController implements Initializable {

    Stage window;
    private boolean canChange;
    private int timeChange;

    @FXML
    private MFXButton addBtn;

    @FXML
    private MFXTextField amountTf;

    @FXML
    private MFXButton cancelBtn;

    @FXML
    private MFXDatePicker dateDP;

    @FXML
    private MFXButton deleteBtn;

    @FXML
    private MFXButton editBtn;

    @FXML
    private MFXButton okBtn;

    @FXML
    private MFXTableView<ImportDetailTable> table;

    Dialog<ButtonType> dialog = new Dialog<>();
    
    private DAO_Import daoImport;
    
    private DAO_Receipt daoReceipt;
    
    

    
    ObservableList<ImportDetailTable> importDetail = FXCollections.observableArrayList();
    ObservableList<ImportDetailTable> importDetail2 = FXCollections.observableArrayList();

    private AlertMessage alertMessage = new AlertMessage();

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	
		setupScreen();
		setupButton();
	}

	// Load data from the database
	public void loadTable() {
		if(CurrentImport.ImportId != 0) {
		ArrayList<Import_Detail> imDetails = DAO_Import_Detail.getInstance().selectByCondition(" IdImport = " + CurrentImport.ImportId);
		
		 int importQuantity;
		 double importPrice;
		 double importTotal;
		 System.out.print(imDetails.size());
		 
		 
		  
		 
		 
		 
		for(int i = 0;i<imDetails.size();i++) {
			Import_Detail imDetail = imDetails.get(i);
			Items items = DAO_Items.getInstance().selectByCondition2(" Items_Id = " + imDetail.getIdItems());
			
			String importName = items.getItems_Name();
			
			Unit unit = DAO_Unit.getInstance().selectByCondition2(" Unit_Id = "+  items.getItems_Unit() );
			
			importDetail.add( new ImportDetailTable(i+1,importName,items.getItems_Id(),unit.getUnit_Name(),unit.getUnit_Id(),imDetail.getItems_Quantity(),imDetail.getItems_Price(),imDetail.getTotal_Money()));
		}
		table.setItems(importDetail);
		}
		
		else {
			for(int i = 0;i<DetailImportHelper.importDetail.size();i++) {
				importDetail2.add(new ImportDetailTable(DetailImportHelper.importDetail.get(i)));
			}
			table.setItems(DetailImportHelper.importDetail);
		}
		
		
		table.autosize();
		table.autosizeColumns();
		table.autosizeColumnsOnInitialization();
	}

	// Reload screen
	public void reloadTable() {
		importDetail.clear();
		
		loadTable();
	}

	

	@SuppressWarnings("unchecked")
	public void setupScreen() {
		dateDP.setDisable(true);
		
		if(CurrentImport.ImportId == 0) {
			dateDP.setValue(LocalDate.now());
		}
		else {
			dateDP.setValue(CurrentImport.ImportDate.toLocalDate());
			okBtn.setDisable(true);
			amountTf.setDisable(true);
			addBtn.setDisable(true);
			editBtn.setDisable(true);
			deleteBtn.setDisable(true);
			amountTf.setText(CurrentImport.Import_TotalMoney + "");
			
		}
		
		
		// Initialize columns
		MFXTableColumn<ImportDetailTable> idColumn = new MFXTableColumn<>("Id", true, Comparator.comparing(ImportDetailTable::getDetailId));
		MFXTableColumn<ImportDetailTable> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(ImportDetailTable::getImportName));
		MFXTableColumn<ImportDetailTable> unitColumn = new MFXTableColumn<>("Unit", true, Comparator.comparing(ImportDetailTable::getImportUnit));
		MFXTableColumn<ImportDetailTable> quantityColumn = new MFXTableColumn<>("Quantity", true, Comparator.comparing(ImportDetailTable::getImportQuantity));
		MFXTableColumn<ImportDetailTable> priceColumn = new MFXTableColumn<>("Price", true, Comparator.comparing(ImportDetailTable::getImportPrice));
		MFXTableColumn<ImportDetailTable> totalColumn = new MFXTableColumn<>("Total", true, Comparator.comparing(ImportDetailTable::getImportTotal));
		
		

		// Set row cell factories
		idColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ImportDetailTable::getDetailId));
		nameColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ImportDetailTable::getImportName));
		unitColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ImportDetailTable::getImportUnit));
		quantityColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ImportDetailTable::getImportQuantity));
		priceColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ImportDetailTable::getImportPrice));
		totalColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ImportDetailTable::getImportTotal));
		
			
		
		// Add columns to the table
		table.getTableColumns().addAll(idColumn,nameColumn,unitColumn,quantityColumn,priceColumn,totalColumn);
		
		

		// Add filters to the table
		table.getFilters().add(new IntegerFilter<>("Id", ImportDetailTable::getDetailId));
		table.getFilters().add(new StringFilter<>("Name", ImportDetailTable::getImportName));
		table.getFilters().add(new StringFilter<>("Unit", ImportDetailTable::getImportUnit));
		table.getFilters().add(new IntegerFilter<>("Quantity", ImportDetailTable::getImportQuantity));
		table.getFilters().add(new DoubleFilter<>("Price", ImportDetailTable::getImportPrice));
		table.getFilters().add(new DoubleFilter<>("Total", ImportDetailTable::getImportTotal));
		
		
		

		loadTable();
		table.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				newValue.values().stream().findFirst().ifPresent(p -> {
					DetailImportHelper.Import_Detail_Id = p.getDetailId();
					DetailImportHelper.isAdd = false;
					DetailImportHelper.IdItems = p.getIdItem();
					DetailImportHelper.IdUnit = p.getIdUnit();
					DetailImportHelper.Items_Quantity = p.getImportQuantity();
					DetailImportHelper.Items_Price = p.getImportPrice();
				});
			}
		});
		
		
		
	}
	
	public void setupButton() {
		

		okBtn.setOnMouseClicked(event -> {
			if(CurrentImport.ImportId == 0) {
				
				Import tempt = new Import(0,CurrentUser.userId, Date.valueOf(LocalDate.now()),Float.parseFloat(amountTf.getText()));
				DAO_Import.getInstance().Add(tempt);
				
				ArrayList<Import> a = DAO_Import.getInstance().selectAll();
				for(int i = 0;i<DetailImportHelper.importDetail.size();i++) {
					ImportDetailTable detailTable = DetailImportHelper.importDetail.get(i);
					
					Items tempt2 = DAO_Items.getInstance().selectByCondition2("Items_Id = " + detailTable.getIdItem() );
					tempt2.setItems_Quantity(tempt2.getItems_Quantity() + detailTable.getImportQuantity());
					tempt2.setItems_Price(detailTable.getImportPrice());
					
					DAO_Items.getInstance().Update(tempt2);
					Import_Detail detail = new Import_Detail(detailTable.getDetailId(),a.getLast().getImport_Id() , detailTable.getIdItem(), detailTable.getImportPrice(), detailTable.getImportTotal() ,detailTable.getImportQuantity());
					DAO_Import_Detail.getInstance().Add(detail);
					window = (Stage) cancelBtn.getScene().getWindow();
					window.close();
				}
			}
			
		});
		
		cancelBtn.setOnMouseClicked(event -> {
			
			
			window = (Stage) cancelBtn.getScene().getWindow();
			window.close();
		});
		
		addBtn.setOnMouseClicked(event -> {
			
			if(DetailImportHelper.importDetail.size() > 0) {
				DetailImportHelper.isAdd= true;
			DetailImportHelper.Import_Detail_Id = DetailImportHelper.importDetail.getLast().getDetailId();
			
			}
			else {
				
				DetailImportHelper.isAdd= true;
				DetailImportHelper.Import_Detail_Id = 0;
			}
			try {
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
				
					
					
					DetailImportHelper.importDetail.add(new ImportDetailTable(DetailImportHelper.Import_Detail_Id+1, DetailImportHelper.itemName, DetailImportHelper.IdItems,DetailImportHelper.unitName, DetailImportHelper.IdUnit, DetailImportHelper.Items_Quantity, DetailImportHelper.Items_Price,DetailImportHelper.Items_Quantity* DetailImportHelper.Items_Price ));
					
					importDetail2.clear();
					for(int i = 0;i<DetailImportHelper.importDetail.size();i++) {
						importDetail2.add(new ImportDetailTable(DetailImportHelper.importDetail.get(i)));
					}
					table.setItems(importDetail2);
					double total = 0;
					for(ImportDetailTable im : importDetail2) {
						total += im.getImportTotal();
					}
					amountTf.setText(total+"");
					table.autosize();
					table.autosizeColumns();
					table.autosizeColumnsOnInitialization();
					DetailImportHelper.Import_Detail_Id = 0;
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
			
			try {
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
					
					ImportDetailTable a = new ImportDetailTable(DetailImportHelper.Import_Detail_Id, DetailImportHelper.itemName, DetailImportHelper.IdItems,DetailImportHelper.unitName, DetailImportHelper.IdUnit, DetailImportHelper.Items_Quantity, DetailImportHelper.Items_Price,DetailImportHelper.Items_Quantity* DetailImportHelper.Items_Price );
					
					for(int i =0;i<DetailImportHelper.importDetail.size();i++) {
						if(DetailImportHelper.importDetail.get(i).getDetailId() == a.getDetailId()) DetailImportHelper.importDetail.get(i).update(a);
					}
					
					importDetail2.clear();
					for(int i = 0;i<DetailImportHelper.importDetail.size();i++) {
						importDetail2.add(new ImportDetailTable(DetailImportHelper.importDetail.get(i)));
					}
					table.setItems(importDetail2);
					double total = 0;
					for(ImportDetailTable im : importDetail2) {
						total += im.getImportTotal();
					}
					amountTf.setText(total+"");
					table.autosize();
					table.autosizeColumns();
					table.autosizeColumnsOnInitialization();
					DetailImportHelper.Import_Detail_Id = 0;
					DetailImportHelper.isAdd = true;
				}
				else {
					CurrentExport.isCancel = false;
				}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		});
		
		
		deleteBtn.setOnMouseClicked(event -> {
			
			
			if(DetailImportHelper.isAdd == true) {
				alertMessage.errorMessage("Please select the object you want to change information!");
				return;
			}
					
					
					
					for(int i =0;i<DetailImportHelper.importDetail.size();i++) {
						if(DetailImportHelper.importDetail.get(i).getDetailId() == DetailImportHelper.Import_Detail_Id) DetailImportHelper.importDetail.remove(i);
					}
					
					importDetail2.clear();
					for(int i = 0;i<DetailImportHelper.importDetail.size();i++) {
						importDetail2.add(new ImportDetailTable(DetailImportHelper.importDetail.get(i)));
					}
					table.setItems(importDetail2);
					double total = 0;
					for(ImportDetailTable im : importDetail2) {
						total += im.getImportTotal();
					}
					amountTf.setText(total+"");
					table.autosize();
					table.autosizeColumns();
					table.autosizeColumnsOnInitialization();
					DetailImportHelper.Import_Detail_Id = 0;
					DetailImportHelper.isAdd = true;
				
				
		
		
	});
	
	}
}
