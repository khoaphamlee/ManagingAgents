package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.mail.FetchProfile.Item;

import Data_Access_Object.DAO_Agent;
import Data_Access_Object.DAO_Items;
import Data_Access_Object.DAO_Reception;
import Data_Access_Object.DAO_Role;
import Data_Access_Object.DAO_Unit;
import Data_Access_Object.DAO_User;
import Database.JDBCUtil;
import datatable.AgencyScreenTable;
import datatable.AgentTypeScreenTable;
import io.github.palexdev.materialfx.beans.BiPredicateBean;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXFilterPane;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.Agent;
import models.Agent_Type;
import models.Device;
import models.District;
import models.Items;
import models.Reception;
import models.Role;
import models.Unit;
import datatable.GoodsScreenTable;

public class GoodsScreenController implements Initializable  {
	Stage window;
	private boolean canChange ;
	private int timeChange;
	
	@FXML
	private AnchorPane darkPane,detailMenuPane,menuPane;
	
	@FXML 
	private ImageView menuImg ; 
	
	@FXML 
	private MFXButton addBtn,editBtn;
	
	@FXML
	private MFXTableView<GoodsScreenTable> table;
	
	Dialog<ButtonType> dialog = new Dialog<>();
	
	@FXML
	private MFXButton homepageBtn,agencyBtn ,goodsBtn ,exportBtn ,reportBtn ,settingBtn;
	
	@FXML
	private MFXButton receiptBtn,goodsListBtn;
	
	@FXML
	private MFXTextField items_id, items_name, items_quantity, items_price;
	
	@FXML
	private MFXComboBox<String> statusCbb;
	
	@FXML
	private MFXComboBox<Unit> unitCbb;
	
	private boolean noAction;
	
	private DAO_Items daoItems;
	
	private AlertMessage alertMessage = new AlertMessage();

	private String convertIntToStatusString(int status) {
        return status == 1 ? "Active" : "Inactive";
    }

    private int convertStatusStringToInt(String status) {
        return "Active".equalsIgnoreCase(status) ? 1 : 0;
    }
	public String convertStatus(boolean status) {
        return status ? "Active" : "Inactive";
    }
;
public GoodsScreenController() {
	this.daoItems = DAO_Items.getInstance();
}
public GoodsScreenController(boolean action) {
	noAction = action;
}
ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");
Connection connection;
PreparedStatement preparedStatement;
ResultSet resultSet;
ObservableList<GoodsScreenTable> dataList = FXCollections.observableArrayList();



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
	
	items_quantity.setDisable(true);
	
	statusCbb.setItems(statusList);
	statusCbb.setPromptText("Select Status");
	
	
	
	connection = JDBCUtil.getConnection();
	String query = "SELECT Unit_Id, Unit_Name  FROM UNIT WHERE Unit_Status = "+ 1;
	try {
		preparedStatement = connection.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		List<Unit> dvts = new ArrayList<Unit>();
		while (resultSet.next()) {
			int Unit_Id = resultSet.getInt("Unit_Id");
			String Unit_Name = resultSet.getString("Unit_Name");
			dvts.add(new Unit(Unit_Id,Unit_Name));
		}
		unitCbb.setItems(FXCollections.observableArrayList(dvts));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	connection.close();
	
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
	ArrayList<Items> items = DAO_Items.getInstance().selectAll();
	
	for(int i =0;i<items.size();i++) {
		Items tempt = items.get(i);
		Unit unit = DAO_Unit.getInstance().selectByCondition2(" Unit_Id = "+  tempt.getItems_Unit() );
		System.out.print(tempt.getItems_Id());
		
		dataList.add(new GoodsScreenTable(tempt.getItems_Id(),tempt.getItems_Name(),unit.getUnit_Name(),unit.getUnit_Id(),tempt.getItems_Price(),tempt.getItems_Quantity(),tempt.getItems_Status()));
	}
	
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
	MFXTableColumn<GoodsScreenTable> idColumn = new MFXTableColumn<>("ID",true,Comparator.comparing(GoodsScreenTable::getItems_Id));
    MFXTableColumn<GoodsScreenTable> nameColumn = new MFXTableColumn<>("Name",true,Comparator.comparing(GoodsScreenTable::getItems_Name));
    MFXTableColumn<GoodsScreenTable> unitColumn = new MFXTableColumn<>("Unit",true,Comparator.comparing(GoodsScreenTable::getItem_unit));
    MFXTableColumn<GoodsScreenTable> quantityColumn = new MFXTableColumn<>("Quantity",true,Comparator.comparing(GoodsScreenTable::getItems_Quantity));
    MFXTableColumn<GoodsScreenTable> priceColumn = new MFXTableColumn<>("Price import",true,Comparator.comparing(GoodsScreenTable::getItems_Price));
    MFXTableColumn<GoodsScreenTable> statusColumn = new MFXTableColumn<>("Status",true,Comparator.comparing(GoodsScreenTable::getItems_Status));


    idColumn.setRowCellFactory(item -> new MFXTableRowCell<>(GoodsScreenTable::getItems_Id));
    nameColumn.setRowCellFactory(item -> new MFXTableRowCell<>(GoodsScreenTable::getItems_Name)); 
    unitColumn.setRowCellFactory(item -> new MFXTableRowCell<>(GoodsScreenTable::getItem_unit )); 
    quantityColumn.setRowCellFactory(item -> new MFXTableRowCell<>(GoodsScreenTable::getItems_Quantity));
    priceColumn.setRowCellFactory(item -> new MFXTableRowCell<>(GoodsScreenTable::getItems_Price));
    statusColumn.setRowCellFactory(item -> new MFXTableRowCell<>(GoodsScreenTable::getItems_Status));

    table.getTableColumns().addAll(idColumn, nameColumn,unitColumn, quantityColumn, priceColumn, statusColumn);

    

    table.getFilters().addAll(
        new IntegerFilter<>("ID", GoodsScreenTable::getItems_Id),
        new StringFilter<>("Name", GoodsScreenTable::getItems_Name),
        new StringFilter<>("Unit", GoodsScreenTable::getItem_unit),
        new IntegerFilter<>("Quantity", GoodsScreenTable::getItems_Quantity),
        new DoubleFilter<>("Price import", GoodsScreenTable::getItems_Price),
        new StringFilter<>("Status", GoodsScreenTable::getItems_Status)
    );
	
	
	loadTable();

	// select row
	items_id.setEditable(false);
	table.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
		if (newValue != null) {
			newValue.values().stream().findFirst().ifPresent(p -> {
				items_id.setText(String.valueOf(p.getItems_Id()));
				items_name.setText(p.getItems_Name());
				items_price.setText(String.valueOf(p.getItems_Price()));
				items_quantity.setText(String.valueOf(p.getItems_Quantity()));
				if(p.getItems_Status() == "Active")
					statusCbb.setValue(statusCbb.getItems().get(0));
	                else {
	                	statusCbb.setValue(statusCbb.getItems().get(1));
	                }
				
				
				int v = 0;
				
				for(int i = 0;i<unitCbb.getItems().size();i++) {
					if(unitCbb.getItems().get(i).getUnit_Id() == p.getItem_unitId()) v = i;
				}
				
				unitCbb.setValue(unitCbb.getItems().get(v));
			});
		}
	});
}


public void setupButton() {
	editBtn.setOnMouseClicked(event -> {
		if (items_id.getText().isEmpty()) {
			alertMessage.errorMessage("Please select the object you want to change information!");
			return;
		}
		if (items_name.getText().isEmpty() || items_price.getText().isEmpty() || items_quantity.getText().isEmpty() || statusCbb.getValue() == null || statusCbb.getText().isEmpty()) {
            alertMessage.errorMessage("Please fill all blank fields!");
            return;
        }
        
		 else {
			 
			 
			 try {
				 int id = Integer.parseInt(items_id.getText());
		            String name = items_name.getText();
		            double price = Double.parseDouble(items_price.getText());
		            int quantity = Integer.parseInt(items_quantity.getText());
		            String stringStatus = statusCbb.getValue();
		            int status = convertStatusStringToInt(stringStatus);
		            int unitId = unitCbb.getValue().getUnit_Id();
		            Items updatedItems = new Items(id, name,unitId, price, quantity, status);
		            daoItems.Update(updatedItems);

		            alertMessage.successMessage("Information changed successfully!");
		            reloadTable();
		            
		            
		            
		        } catch (NumberFormatException e) {
		            e.printStackTrace();
		            alertMessage.errorMessage("Fail!");
		        }
			
		}
	});

	addBtn.setOnMouseClicked(event -> {
		int unitId = 0;
		try{
			unitId= unitCbb.getItems().getFirst().getUnit_Id();
		
		}
		catch(Exception e) {
			alertMessage.errorMessage("No unit type exists");
			return;
		}
		Items items = new Items(0,"",unitId,0,0	);
		DAO_Items.getInstance().Add(items);
		reloadTable();
		ArrayList<Items> itemList = DAO_Items.getInstance().selectAll();
		Items p = itemList.getLast();
		items_id.setText(String.valueOf(p.getItems_Id()));
		items_name.setText(p.getItems_Name());
		items_price.setText(String.valueOf(p.getItems_Price()));
		items_quantity.setText(String.valueOf(p.getItems_Quantity()));
		statusCbb.setValue(statusCbb.getItems().get(0));
		
		
		unitCbb.setValue(unitCbb.getItems().getFirst());
		
		
		
		
		
		
		
		
		
		
			
		alertMessage.successMessage("New object added successfully!");
	});

	
	
	
}
}
