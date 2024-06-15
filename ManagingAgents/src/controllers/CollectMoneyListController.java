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

public class CollectMoneyListController implements Initializable {

    Stage window;
    private boolean canChange;
    private int timeChange;

    @FXML
    private MFXButton addBtn;

    @FXML
    private MFXTextField agentTf;

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
    private MFXComboBox<Agent> agencyCCB;

    @FXML
    private MFXTableView<Receipt> table;

    Dialog<ButtonType> dialog = new Dialog<>();
    
    private DAO_Import daoImport;
    
    private DAO_Receipt daoReceipt;
    
    

    
    ObservableList<Receipt> receipt = FXCollections.observableArrayList();
    

    private AlertMessage alertMessage = new AlertMessage();

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
		
		
		receipt= DAO_Receipt.getInstance().selectByCondition2(" Id_Agent = " + CurrentAgent.agentId);
		
		
		
		
		table.setItems(receipt);
		
		
		
		table.autosize();
		table.autosizeColumns();
		table.autosizeColumnsOnInitialization();
	}

	// Reload screen
	public void reloadTable() throws SQLException {
		receipt.clear();
		
		loadTable();
	}

	

	@SuppressWarnings("unchecked")
	public void setupScreen() throws SQLException {
		
		agentTf.setText(CurrentAgent.agentName);
		agentTf.setDisable(true);
		// Initialize columns
		MFXTableColumn<Receipt> idColumn = new MFXTableColumn<>("Id", true, Comparator.comparing(Receipt::getReceipt_Id));
		MFXTableColumn<Receipt> idUserColumn = new MFXTableColumn<>("Id collector", true, Comparator.comparing(Receipt::getId_User));
		MFXTableColumn<Receipt> dateColumn = new MFXTableColumn<>("Date", true, Comparator.comparing(Receipt::getReceiptDate));
		MFXTableColumn<Receipt> moneyColumn = new MFXTableColumn<>("Money", true, Comparator.comparing(Receipt::getMoneyReceived));
		
		

		// Set row cell factories
		idColumn.setRowCellFactory(col -> new MFXTableRowCell<>(Receipt::getReceipt_Id));
		idUserColumn.setRowCellFactory(col -> new MFXTableRowCell<>(Receipt::getId_User));
		dateColumn.setRowCellFactory(col -> new MFXTableRowCell<>(Receipt::getReceiptDate));
		moneyColumn.setRowCellFactory(col -> new MFXTableRowCell<>(Receipt::getMoneyReceived));
		
		// Add columns to the table
		table.getTableColumns().addAll(idColumn,idUserColumn,dateColumn,moneyColumn);
		
		

		// Add filters to the table
		table.getFilters().add(new IntegerFilter<>("Id", Receipt::getReceipt_Id));
		table.getFilters().add(new IntegerFilter<>("Id collector", Receipt::getId_User));
		table.getFilters().add(new DateFilter<>("Date", Receipt::getReceiptDate));
		table.getFilters().add(new DoubleFilter<>("Money", Receipt::getMoneyReceived));
		
		
		
		

		loadTable();
		
		
		
		
	}
	
	public void setupButton() {
		
		
		
		cancelBtn.setOnMouseClicked(event -> {
			
			
			window = (Stage) cancelBtn.getScene().getWindow();
			window.close();
		});
		
		
	
	}
	
}
