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

import Data_Access_Object.DAO_Import;
import Data_Access_Object.DAO_Items;
import Data_Access_Object.DAO_Role;
import Database.JDBCUtil;
import datatable.AgentTypeScreenTable;
import datatable.GoodsScreen2Table;
import datatable.GoodsScreenTable;
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
import models.Import;
import models.Items;

import models.Receipt;
import models.Role;

public class GoodsScreenController2 implements Initializable  {
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
	private MFXTableView<Import> table;
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
	ObservableList<Import> dataList = FXCollections.observableArrayList();
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
	goodsListBtn.setOnAction(event -> {
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
	receiptBtn.setOnAction(event -> {
		window = (Stage)menuImg.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GoodsScreen2.fxml"));
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
	dataList = DAO_Import.getInstance().selectAll2();
	
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
	MFXTableColumn<Import> idColumn = new MFXTableColumn<>("ID",true,Comparator.comparing(Import::getImport_Id));
	MFXTableColumn<Import> dateColumn = new MFXTableColumn<>("Date",true,Comparator.comparing(Import::getImport_Date));
	MFXTableColumn<Import> amountColumn = new MFXTableColumn<>("Total money",true,Comparator.comparing(Import::getImport_TotalMoney));
	MFXTableColumn<Import> user = new MFXTableColumn<>("Importer",true,Comparator.comparing(Import::getId_User));
	
	
	idColumn.setRowCellFactory(item -> new MFXTableRowCell<>(Import::getImport_Id));
    dateColumn.setRowCellFactory(item -> new MFXTableRowCell<>(Import::getImport_Date));
    amountColumn.setRowCellFactory(item -> new MFXTableRowCell<>(Import::getImport_TotalMoney));
    user.setRowCellFactory(item -> new MFXTableRowCell<>(Import::getId_User));
    
    table.getTableColumns().addAll(idColumn, dateColumn, amountColumn, user);
    
    
    
    table.getFilters().addAll(
	        new IntegerFilter<>("ID", Import::getImport_Id),
	        new DoubleFilter<>("Amount", Import::getImport_TotalMoney),
	        new DateFilter<>("Date", Import::getImport_Date),
	        new IntegerFilter<>("User", Import::getId_User)
	        
	       
	);
    table.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
		if (newValue != null) {
			newValue.values().stream().findFirst().ifPresent(p -> {
				CurrentImport.Id_User = p.getId_User();
				CurrentImport.Import_TotalMoney = p.getImport_TotalMoney();
				CurrentImport.ImportDate = p.getImport_Date();
				CurrentImport.ImportId = p.getImport_Id();
			});
		}
	});
	loadTable();

	// select row
	
}


public void setupButton() {
	detailBtn.setOnMouseClicked(event -> {
		if (CurrentImport.ImportId == 0 ) {
			alertMessage.errorMessage("Please select the object you want to change information!");
			return;
		}
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GoodsReceiptDialog.fxml"));
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
				
				CurrentImport.Import_TotalMoney = 0;
				CurrentImport.ImportDate = null;
				CurrentImport.ImportId = 0;
				
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
				DetailImportHelper.importDetail.clear();
			}
		} catch (Exception e) {
			alertMessage.errorMessage("Can't load the scene!");
			e.printStackTrace();
			return;
		}
	});

	addBtn.setOnMouseClicked(event -> {
		CurrentImport.Import_TotalMoney = 0;
		CurrentImport.ImportDate = null;
		CurrentImport.ImportId = 0;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/GoodsReceiptDialog.fxml"));
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
				
				CurrentImport.Import_TotalMoney = 0;
				CurrentImport.ImportDate = null;
				CurrentImport.ImportId = 0;
				
				
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
				
				DetailImportHelper.importDetail.clear();
				
			}
		} catch (Exception e) {
			alertMessage.errorMessage("Can't load the scene!");
			e.printStackTrace();
			return;
		}
	});
	
	
	
}
}