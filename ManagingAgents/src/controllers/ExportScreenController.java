package controllers;

import java.io.IOException;
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
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Stream;

import Data_Access_Object.DAO_Export;
import Data_Access_Object.DAO_Import;
import Data_Access_Object.DAO_Items;
import Data_Access_Object.DAO_Role;
import Database.JDBCUtil;
import datatable.AgentTypeScreenTable;
import datatable.GoodsScreen2Table;
import io.github.palexdev.materialfx.beans.BiPredicateBean;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterPane;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;

import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.i18n.I18N;
import io.github.palexdev.materialfx.utils.FXCollectors;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.Export;
import models.Import;
import models.Items;

import models.Receipt;
import models.Role;

public class ExportScreenController implements Initializable  {
	Stage window;
	private boolean canChange ;
	private int timeChange;
	
	@FXML
	private AnchorPane darkPane,detailMenuPane,menuPane;
	
	@FXML 
	private ImageView menuImg ; 
	
	@FXML 
	private MFXButton addBtn,detailBtn;
	
	@FXML
	private MFXTableView<Export> table;
	Dialog<ButtonType> dialog = new Dialog<>();
	
	@FXML
	private MFXButton homepageBtn,agencyBtn ,goodsBtn ,exportBtn ,reportBtn ,settingBtn;
	
	@FXML
	private MFXButton receiptBtn,goodsListBtn;
	
	@FXML
	private MFXButton importDialogBtn;
	
	private MFXButton cancelBtn;
	ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	ObservableList<Export> dataList = FXCollections.observableArrayList();
	AlertMessage alertMessage = new AlertMessage();

	
@SuppressWarnings("unchecked")
@Override
public void initialize(URL arg0, ResourceBundle arg1) {

	
	
Role role = DAO_Role.getInstance().selectByCondition2(" User_Id = " + CurrentUser.userId);
	
	
	
	if(!role.isRole1()) agencyBtn.setDisable(true);
	
	if(!role.isRole2()) goodsBtn.setDisable(true);
	
	if(!role.isRole3()) exportBtn.setDisable(true);
	
	if(!role.isRole4()) reportBtn.setDisable(true);
	
	if(!role.isRole5()) settingBtn.setDisable(true);
	
	setupScreen();
	setupTable();
	try {
		setupTabChange();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	setupButton();

}

private void setupTabChange() throws SQLException {
	
	
	
	
	homepageBtn.setOnAction(event -> {
		window = (Stage)menuImg.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainScreen.fxml"));
        Parent root = null;
		try {
			root = loader.load();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Scene scene = new Scene(root);
        window.setTitle("Managing Agents");
        window.getIcons().add(new Image("/images/mylogo.png"));
        window.setScene(scene);
        
    });
	
	
	goodsBtn.setOnAction(event -> {
		window = (Stage)menuImg.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GoodsScreen.fxml"));
        Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Scene scene = new Scene(root);
        window.setTitle("Managing Agents");
        window.getIcons().add(new Image("/images/mylogo.png"));
        window.setScene(scene);
        
    });
	exportBtn.setOnAction(event -> {
		window = (Stage)menuImg.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ExportScreen.fxml"));
        Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Scene scene = new Scene(root);
        window.setTitle("Managing Agents");
        window.getIcons().add(new Image("/images/mylogo.png"));
        window.setScene(scene);
        
    });
	reportBtn.setOnAction(event -> {
		window = (Stage)menuImg.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ReportScreen.fxml"));
        Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Scene scene = new Scene(root);
        window.setTitle("Managing Agents");
        window.getIcons().add(new Image("/images/mylogo.png"));
        window.setScene(scene);
        
    });
	settingBtn.setOnAction(event -> {
		window = (Stage)menuImg.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SettingScreen.fxml"));
        Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Scene scene = new Scene(root);
        window.setTitle("Managing Agents");
        window.getIcons().add(new Image("/images/mylogo.png"));
        window.setScene(scene);
        
    });
	agencyBtn.setOnAction(event -> {
		window = (Stage)menuImg.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AgencyScreen.fxml"));
        Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Scene scene = new Scene(root);
        window.setTitle("Managing Agents");
        window.getIcons().add(new Image("/images/mylogo.png"));
        window.setScene(scene);
        
    });
	

}

public void setupScreen() {

	
	
	darkPane.setVisible(false);

	javafx.animation.FadeTransition fadeTransition = new javafx.animation.FadeTransition(Duration.seconds(0.39),
			darkPane);
	fadeTransition.setFromValue(1);
	fadeTransition.setToValue(0);
	fadeTransition.play();

	javafx.animation.TranslateTransition translateTransition = new javafx.animation.TranslateTransition(
			Duration.seconds(0.01), detailMenuPane);
	translateTransition.setByX(-600);
	translateTransition.play();

	
	
	
	
	
	canChange = true;
	menuPane.setOnMouseEntered(event -> {
		timeChange += 1;
		if (canChange && timeChange == 1) {
			menuImg.setVisible(false);
			darkPane.setVisible(true);

			javafx.animation.FadeTransition fadeTransition1 = new javafx.animation.FadeTransition(
					Duration.seconds(0.39), darkPane);
			// fadeTransition1.setFromValue(0);
			// fadeTransition1.setToValue(0.1);
			fadeTransition1.play();

			javafx.animation.TranslateTransition translateTransition1 = new javafx.animation.TranslateTransition(
					Duration.seconds(0.01), detailMenuPane);
			translateTransition1.setByX(+600);
			translateTransition1.setOnFinished(event1 -> {
				canChange = false;

			});
			translateTransition1.play();

		}

	});
	detailMenuPane.setOnMouseExited(event -> {

		if (!canChange && event.getX() > 100) {

			javafx.animation.FadeTransition fadeTransition1 = new javafx.animation.FadeTransition(
					Duration.seconds(0.39), darkPane);
			// fadeTransition1.setFromValue(0.15);
			// fadeTransition1.setToValue(0);
			fadeTransition1.play();

			fadeTransition1.setOnFinished(event1 -> {
				darkPane.setVisible(false);
			});

			javafx.animation.TranslateTransition translateTransition1 = new javafx.animation.TranslateTransition(
					Duration.seconds(0.39), detailMenuPane);
			translateTransition1.setByX(-600);
			translateTransition1.setOnFinished(event1 -> {
				canChange = true;
				timeChange = 0;
				menuImg.setVisible(true);

			});
			translateTransition1.play();
		}
	});

}


// Load data from the database
public void loadTable() {
	dataList = DAO_Export.getInstance().selectAll2();
	
	table.setItems(dataList);
	table.autosize();
	table.autosizeColumns();
	table.autosizeColumnsOnInitialization();
}



public void reloadTable() {
	dataList.clear();
	loadTable();
}

@SuppressWarnings("unchecked")
public void setupTable() {
	MFXTableColumn<Export> idColumn = new MFXTableColumn<>("ID", true, Comparator.comparing(Export::getExport_Id));
	MFXTableColumn<Export> dateColumn = new MFXTableColumn<>("Date", true, Comparator.comparing(Export::getExport_Date));
	MFXTableColumn<Export> amountColumn = new MFXTableColumn<>("Total money", true, Comparator.comparing(Export::getExport_TotalMoney));
	MFXTableColumn<Export> paymentColumn = new MFXTableColumn<>("Payment", true, Comparator.comparing(Export::getPayAmount));
	MFXTableColumn<Export> remainColumn = new MFXTableColumn<>("Remain", true, Comparator.comparing(Export::getRemaining));
	MFXTableColumn<Export> user = new MFXTableColumn<>("Exporter", true, Comparator.comparing(Export::getId_User));
	
	
	idColumn.setRowCellFactory(item -> new MFXTableRowCell<>(Export::getExport_Id));
    dateColumn.setRowCellFactory(item -> new MFXTableRowCell<>(Export::getExport_Date));
    amountColumn.setRowCellFactory(item -> new MFXTableRowCell<>(Export::getExport_TotalMoney));
    user.setRowCellFactory(item -> new MFXTableRowCell<>(Export::getId_User));
    paymentColumn.setRowCellFactory(item -> new MFXTableRowCell<>(Export::getPayAmount));
    remainColumn.setRowCellFactory(item -> new MFXTableRowCell<>(Export::getRemaining));
    table.getTableColumns().addAll(idColumn, dateColumn, amountColumn,paymentColumn,remainColumn, user);
    
    
    
    table.getFilters().addAll(
	        new IntegerFilter<>("ID", Export::getExport_Id),
	        new DoubleFilter<>("Amount", Export::getExport_TotalMoney),
	        new DoubleFilter<>("Payment", Export::getPayAmount),
	        new DoubleFilter<>("Remain", Export::getRemaining),
	        new DateFilter<>("Date", Export::getExport_Date),
	        new IntegerFilter<>("User", Export::getId_User)
	        
	       
	);
    table.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
		if (newValue != null) {
			newValue.values().stream().findFirst().ifPresent(p -> {
				CurrentExport.Id_User = p.getId_User();
				CurrentExport.Export_TotalMoney = p.getExport_TotalMoney();
				CurrentExport.ExportDate = p.getExport_Date();
				CurrentExport.ExportId = p.getExport_Id();
				CurrentExport.Id_Agent =  p.getId_Agent();
				CurrentExport.Export_Payment = p.getPayAmount();
				CurrentExport.Export_Remain = p.getRemaining();
			});
		}
	});
	loadTable();

	// select row
	
}


public void setupButton() {
	detailBtn.setOnMouseClicked(event -> {
		if (CurrentExport.ExportId == 0 ) {
			alertMessage.errorMessage("Please select the object you want to change information!");
			return;
		}
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ExportReceiptDialog.fxml"));
			Parent root = (Parent) loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false); 
			
			stage.setTitle("Managing Agents");
			stage.getIcons().add(new Image("/images/mylogo.png"));
			stage.showAndWait();
			if (!stage.isShowing()) {
				reloadTable();
				CurrentExport.Id_User = 0;
				CurrentExport.Export_TotalMoney = 0;
				CurrentExport.ExportDate = null;
				CurrentExport.ExportId = 0;
				CurrentExport.Id_Agent =0;
				DetailImportHelper.Import_Detail_Id = 0;
				DetailImportHelper.isAdd = true;
				DetailImportHelper.IdItems = 0;
				DetailImportHelper.IdUnit = 0;
				DetailImportHelper.Items_Quantity = 0;
				DetailImportHelper.Items_Price = 0;
				DetailImportHelper.itemName = "";
				DetailImportHelper.unitName = "";
				DetailImportHelper.importDetail.clear();
				DetailImportHelper.Total_Money =0;
				DetailImportHelper.IdImport= 0;
				DetailImportHelper.exportDetail.clear();
			}
		} catch (Exception e) {
			alertMessage.errorMessage("Can't load the scene!");
			e.printStackTrace();
			return;
		}
	});

	addBtn.setOnMouseClicked(event -> {
		try {
			CurrentExport.Id_User =0;
			CurrentExport.Export_TotalMoney = 0;
			CurrentExport.ExportDate = Date.valueOf(LocalDate.now());
			CurrentExport.ExportId = 0;
			CurrentExport.Id_Agent =0;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ExportReceiptDialog.fxml"));
			Parent root = (Parent) loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false); 
			stage.setTitle("Managing Agents");
			stage.getIcons().add(new Image("/images/mylogo.png"));
			
			stage.showAndWait();
			if (!stage.isShowing()) {
				reloadTable();
				CurrentExport.Id_User =0;
				CurrentExport.Export_TotalMoney = 0;
				CurrentExport.ExportDate = Date.valueOf(LocalDate.now());
				CurrentExport.ExportId = 0;
				CurrentExport.Id_Agent =0;
				
				DetailImportHelper.Import_Detail_Id = 0;
				DetailImportHelper.isAdd = true;
				DetailImportHelper.IdItems = 0;
				DetailImportHelper.IdUnit = 0;
				DetailImportHelper.Items_Quantity = 0;
				DetailImportHelper.Items_Price = 0;
				DetailImportHelper.itemName = "";
				DetailImportHelper.unitName = "";
				DetailImportHelper.importDetail.clear();
				DetailImportHelper.Total_Money =0;
				DetailImportHelper.IdImport= 0;
				DetailImportHelper.exportDetail.clear();
				
			}
		} catch (Exception e) {
			alertMessage.errorMessage("Can't load the scene!");
			e.printStackTrace();
			return;
		}
	});
	
	
	
}
}