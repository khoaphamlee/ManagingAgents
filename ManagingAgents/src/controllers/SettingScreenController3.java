package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Stream;

import Data_Access_Object.DAO_District;
import Data_Access_Object.DAO_Role;
import Data_Access_Object.DAO_Unit;
import Database.JDBCUtil;
import datatable.DistrictScreenTable;
import datatable.SettingScreenTable;
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
import models.Agent_Type;
import models.District;
import models.Items;
import models.Role;
import models.Unit;

public class SettingScreenController3 implements Initializable  {
	Stage window;
	private boolean canChange ;
	private int timeChange;
	
	@FXML
	private AnchorPane darkPane,detailMenuPane,menuPane;
	
	@FXML 
	private ImageView menuImg ; 
	
	@FXML 
	private MFXButton addBtn,editBtn,saveBtn;
	
	@FXML
	private MFXTableView<Unit> table;
	Dialog<ButtonType> dialog = new Dialog<>();
	
	@FXML
	private MFXButton homepageBtn,agencyBtn ,goodsBtn ,exportBtn ,reportBtn ,settingBtn;
	
	@FXML
	private MFXButton agencyTypeBtn,districtBtn,unitBtn,staffBtn;
	@FXML
    private MFXComboBox<String> statusCbb;

    @FXML
    private MFXTextField unitTf, rateTf, idTf;
	
	private DAO_Unit daoUnit;
	
    private AlertMessage alertMessage = new AlertMessage();
	
    private String convertIntToStatusString(int status) {
        return status == 1 ? "Active" : "Inactive";
    }

    private int convertStatusStringToInt(String status) {
        return "Active".equalsIgnoreCase(status) ? 1 : 0;
    }
	
public SettingScreenController3() {
	this.daoUnit = DAO_Unit.getInstance();
}

ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");
ObservableList<Unit> dataList ;



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
	agencyTypeBtn.setOnAction(event -> {
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
	districtBtn.setOnAction(event -> {
		window = (Stage)menuImg.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SettingScreen2.fxml"));
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
	unitBtn.setOnAction(event -> {
		window = (Stage)menuImg.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SettingScreen3.fxml"));
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
	staffBtn.setOnAction(event -> {
		window = (Stage)menuImg.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SettingScreen4.fxml"));
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
	dataList = DAO_Unit.getInstance().selectAll2();
	
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
	MFXTableColumn<Unit> idColumn = new MFXTableColumn<>("ID", true, Comparator.comparing(Unit::getUnit_Id));
	MFXTableColumn<Unit> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(Unit::getUnit_Name));
	MFXTableColumn<Unit> statusColumn = new MFXTableColumn<>("Status", true, Comparator.comparing(Unit::getUnit_Status2));
	
	idColumn.setRowCellFactory(item -> new MFXTableRowCell<>(Unit::getUnit_Id));
    nameColumn.setRowCellFactory(item -> new MFXTableRowCell<>(Unit::getUnit_Name));
    statusColumn.setRowCellFactory(item -> new MFXTableRowCell<>(Unit::getUnit_Status2));
    
    table.getTableColumns().addAll(idColumn, nameColumn, statusColumn);
    
    
    
    table.getFilters().addAll(
	        new IntegerFilter<>("ID", Unit::getUnit_Id),
	        new StringFilter<>("Name", Unit::getUnit_Name),
	        new StringFilter<>("Status", Unit::getUnit_Status2));
    
    
	loadTable();
	statusCbb.setItems(statusList);
	// select row
	idTf.setEditable(false);
	table.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
		if (newValue != null) {
			newValue.values().stream().findFirst().ifPresent(p -> {
				idTf.setText(String.valueOf(p.getUnit_Id()));
				unitTf.setText(p.getUnit_Name());	
				statusCbb.setText(p.getUnit_Status2());
				
				
				
			});
		}
	});
	
	float Rate = 0;
	
	try {
        Connection con = JDBCUtil.getConnection();
        Statement st = con.createStatement();
        String sql = "SELECT * FROM FIXED_VALUES";
        System.out.println(sql);
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            
        	
        	Rate = rs.getFloat("Rate");
        	
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
	rateTf.setText(Rate * 100  + "");
}


public void setupButton() {
	editBtn.setOnMouseClicked(event -> {
		if (idTf.getText().isEmpty()) {
			alertMessage.errorMessage("Please select the object you want to change information!");
			return;
		}
		if (unitTf.getText().isEmpty() || statusCbb.getText() == null ) {
            alertMessage.errorMessage("Please fill all blank fields!");
            return;
        }

		 else {
			 
			 
			 try {
				 int id = Integer.parseInt(idTf.getText());
		            String name = unitTf.getText();
		            String stringStatus = statusCbb.getText();
		            int status = convertStatusStringToInt(stringStatus);

		            Unit updatedUnit = new Unit(id, name, status);
		            DAO_Unit.getInstance().Update(updatedUnit);

		            alertMessage.successMessage("Information changed successfully!");
		            reloadTable();
		            
		            
		        } catch (NumberFormatException e) {
		            e.printStackTrace();
		            alertMessage.errorMessage("Fail!");
		        }
			
		}
	});

	addBtn.setOnMouseClicked(event -> {
		Unit updatedUnit = new Unit(0, "", 1);
		DAO_Unit.getInstance().Add(updatedUnit);
		reloadTable();
		Unit p =  DAO_Unit.getInstance().selectAll().getLast();
		
		
		idTf.setText(String.valueOf(p.getUnit_Id()));
		unitTf.setText(p.getUnit_Name());	
		statusCbb.setValue(statusCbb.getItems().get(0));
		

		
		
		
		
		
		
		
		
		
		
		
		
		
			
		alertMessage.successMessage("New object added successfully!");
	});

	
	saveBtn.setOnMouseClicked(event -> {
		if (rateTf.getText().isEmpty() 
				) {
			alertMessage.errorMessage("Please fill all blank fields!");
			return;
		} else {
			try {
				Connection con = JDBCUtil.getConnection();
	            Statement st = con.createStatement();
	            
	            
	            
	            
	            
	            String sql = "UPDATE FIXED_VALUES SET Rate = " + (Float.parseFloat(rateTf.getText())/100) ;
	                         
	            int result = st.executeUpdate(sql);
	            System.out.println("Bạn đã thực thi: " + sql);
	            JDBCUtil.closeConnection(con);
	            alertMessage.successMessage("Saved!");
	        } catch (Exception e) {
	            e.printStackTrace();
	            alertMessage.errorMessage("Incorrect format entry!");
	        }
	       
		}
		
			
		
	});
	
}
}

                                      

