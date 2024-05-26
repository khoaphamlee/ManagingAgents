package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Stream;

<<<<<<< HEAD
=======
import Data_Access_Object.DAO_Unit;
import datatable.SettingScreenTable;
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.Agency;
<<<<<<< HEAD
=======
import models.Agent_Type;
import models.Items;
import models.Unit;
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3

public class SettingScreenController3 implements Initializable  {
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
<<<<<<< HEAD
	private MFXTableView<Agency> table;
=======
	private MFXTableView<SettingScreenTable> table;
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
	Dialog<ButtonType> dialog = new Dialog<>();
	
	@FXML
	private MFXButton homepageBtn,agencyBtn ,goodsBtn ,exportBtn ,reportBtn ,settingBtn;
	
	@FXML
	private MFXButton agencyTypeBtn,districtBtn,unitBtn,staffBtn;
<<<<<<< HEAD
;
=======
	
	private DAO_Unit dao_Unit;
	
public SettingScreenController3() {
	this.dao_Unit = DAO_Unit.getInstance();
}
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3

	
@SuppressWarnings("unchecked")
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	 
	setupScreen();
	setupTable();
	setupTabChange ();
    
    
        
    }

	private void setupTabChange() {
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
            
            window.setScene(scene);
            
        });
		
}

	public void setupScreen() {
		
			

		
		
		
		
		
		
		
		
		darkPane.setVisible(false);


	    javafx.animation.FadeTransition fadeTransition=new javafx.animation.FadeTransition(Duration.seconds(0.39),darkPane);
	    fadeTransition.setFromValue(1);
	    fadeTransition.setToValue(0);
	    fadeTransition.play();

	    javafx.animation.TranslateTransition translateTransition=new javafx.animation.TranslateTransition(Duration.seconds(0.01),detailMenuPane);
	    translateTransition.setByX(-600);
	    translateTransition.play();

	   
	    
	    canChange = true;
	    menuPane.setOnMouseEntered(event -> {
	    	timeChange += 1;
	    	if(canChange&& timeChange ==1) {
	    		menuImg.setVisible(false);
	    		darkPane.setVisible(true);
	        
	        javafx.animation.FadeTransition fadeTransition1=new javafx.animation.FadeTransition(Duration.seconds(0.39),darkPane);
	        //fadeTransition1.setFromValue(0);
	        //fadeTransition1.setToValue(0.1);
	        fadeTransition1.play();
	        
	        

	        javafx.animation.TranslateTransition translateTransition1=new javafx.animation.TranslateTransition(Duration.seconds(0.39),detailMenuPane);
	        translateTransition1.setByX(+600);
	        translateTransition1.setOnFinished(event1 -> {
	            canChange = false;
	            
	            
	        });
	        translateTransition1.play();
	        
	        
	        
	        
	        
	       
	    	}
	        
	    });
	    detailMenuPane.setOnMouseExited(event -> {
	    	
	    	if(!canChange && event.getX()> 100) {
	    		
	        javafx.animation.FadeTransition fadeTransition1=new javafx.animation.FadeTransition(Duration.seconds(0.39),darkPane);
	        //fadeTransition1.setFromValue(0.15);
	        //fadeTransition1.setToValue(0);
	        fadeTransition1.play();

	        fadeTransition1.setOnFinished(event1 -> {
	        	darkPane.setVisible(false);
	        });


	        javafx.animation.TranslateTransition translateTransition1=new javafx.animation.TranslateTransition(Duration.seconds(0.39),detailMenuPane);
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
	public void setupTable() {
<<<<<<< HEAD
		
=======
		MFXTableColumn<SettingScreenTable> idColumn = new MFXTableColumn<>("ID");
		MFXTableColumn<SettingScreenTable> nameColumn = new MFXTableColumn<>("Name");
		MFXTableColumn<SettingScreenTable> unitColumn = new MFXTableColumn<>("Unit");
		
		idColumn.setRowCellFactory(item -> new MFXTableRowCell<>(SettingScreenTable::getUnit_Id));
	    nameColumn.setRowCellFactory(item -> new MFXTableRowCell<>(SettingScreenTable::getItems_Name));
	    unitColumn.setRowCellFactory(item -> new MFXTableRowCell<>(SettingScreenTable::getUnit_Name));
	    
	    table.getTableColumns().addAll(idColumn, nameColumn, unitColumn);
	    
	    idColumn.setPrefWidth(100);
	    nameColumn.setPrefWidth(200);
	    unitColumn.setPrefWidth(200);
	    
	    table.getFilters().addAll(
		        new IntegerFilter<>("ID", SettingScreenTable::getUnit_Id),
		        new StringFilter<>("Name", SettingScreenTable::getItems_Name),
		        new StringFilter<>("Unit", SettingScreenTable::getUnit_Name)
		);
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
	}
	
}

                                      

