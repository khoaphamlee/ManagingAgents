package controllers;

import java.io.IOException;
import java.net.URL;
<<<<<<< HEAD
=======
import java.sql.ResultSet;
import java.sql.SQLException;
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Stream;

<<<<<<< HEAD
import io.github.palexdev.materialfx.beans.BiPredicateBean;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterPane;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
=======
import Data_Access_Object.DAO_Agent_Type;
import Data_Access_Object.DAO_District;
import io.github.palexdev.materialfx.beans.BiPredicateBean;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXFilterPane;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;

import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
<<<<<<< HEAD
=======
import io.github.palexdev.materialfx.filter.LongFilter;
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.i18n.I18N;
import io.github.palexdev.materialfx.utils.FXCollectors;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
<<<<<<< HEAD
import javafx.collections.ObservableList;
=======
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
<<<<<<< HEAD
=======
import javafx.scene.control.Alert;
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
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
import models.District;
import models.Items;
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3

public class SettingScreenController implements Initializable  {
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
	private MFXTableView<Agent_Type> table;
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
	Dialog<ButtonType> dialog = new Dialog<>();
	
	@FXML
	private MFXButton homepageBtn,agencyBtn ,goodsBtn ,exportBtn ,reportBtn ,settingBtn;
	
	@FXML
	private MFXButton agencyTypeBtn,districtBtn,unitBtn,staffBtn;
<<<<<<< HEAD
;

=======
	
	@FXML
    private MFXTextField agenttype_id, agenttype_name;
	
    @FXML
    private MFXComboBox<Long> agenttype_maxdebt;
	
	private boolean noAction;
	
	private ObservableList<Agent_Type> agentTypeList;
    
    private DAO_Agent_Type daoAgentType;
;

public SettingScreenController() {
	this.daoAgentType = DAO_Agent_Type.getInstance();
	this.agentTypeList = FXCollections.observableArrayList();
}
public SettingScreenController(boolean action) {
	noAction = action;
}
	
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
	
@SuppressWarnings("unchecked")
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
<<<<<<< HEAD
	 
	setupScreen();
	setupTable();
	setupTabChange ();
    
=======
	noAction = false;
	setupScreen();
	setupTable();
	loadDataFromDatabase();
	setupTabChange ();
    setupComboBoxes();
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
    
        
    }

<<<<<<< HEAD
=======
	private void setupComboBoxes() {
	    ObservableList<Long> maxdebt = FXCollections.observableArrayList(
	            10000000L,
	            5000000L
	    );
	    
	    agenttype_maxdebt.setItems(maxdebt);
	}
	
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
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
		
<<<<<<< HEAD
			
=======
		agenttype_id.setText(String.valueOf(generatedId()));
	    agenttype_id.setEditable(false);

>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3

		
		
		
		
		
		
		
		
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
	    
<<<<<<< HEAD

	}
	public void setupTable() {
		
	}
=======
	}
	public void setupTable() {
		MFXTableColumn<Agent_Type> idColumn = new MFXTableColumn<>("ID");
		MFXTableColumn<Agent_Type> nameColumn = new MFXTableColumn<>("Name");
		MFXTableColumn<Agent_Type> maxdebtColumn = new MFXTableColumn<>("Maximum debt");
		
		idColumn.setRowCellFactory(item -> new MFXTableRowCell<>(Agent_Type::getAgentType_Id));
	    nameColumn.setRowCellFactory(item -> new MFXTableRowCell<>(Agent_Type::getAgentType_Name));
	    maxdebtColumn.setRowCellFactory(item -> new MFXTableRowCell<>(Agent_Type::getAgentType_MaxDebt));
	    
	    table.getTableColumns().addAll(idColumn, nameColumn, maxdebtColumn);
	    
	    idColumn.setPrefWidth(100);
	    nameColumn.setPrefWidth(200);
	    maxdebtColumn.setPrefWidth(200);
	    
	    table.getFilters().addAll(
		        new IntegerFilter<>("ID", Agent_Type::getAgentType_Id),
		        new StringFilter<>("Name", Agent_Type::getAgentType_Name),
		        new LongFilter<>("Maximum debt", Agent_Type::getAgentType_MaxDebt)
		);
	    
	    addBtn.setOnAction(event->handleAddButtonAction(event));
	    
	    setupScreen();
	}
	private void showAlert(String message) {
	    Alert alert = new Alert(Alert.AlertType.ERROR);
	    alert.setTitle("Error");
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();
	}
	
	private void addToDatabase(Agent_Type newAgentType) {
		daoAgentType.Add(newAgentType);
    }
	
	private int generatedId() {
		int Id = 0;
        try {
            ResultSet resultSet = daoAgentType.getCurrentId(); 
            if (resultSet.next()) {
                Id = resultSet.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Id;
	}
	
	private void loadDataFromDatabase() {
	    //table.getItems().clear();

	    List<Agent_Type> agentTypes = daoAgentType.selectAll();

	    table.getItems().addAll(agentTypes);
	}

	@FXML
    private void handleAddButtonAction(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	    alert.setTitle("Confirmation");
	    alert.setHeaderText(null);
	    alert.setContentText("Are you sure you want to add?");
	    
		Optional<ButtonType> result = alert.showAndWait();
		    if (result.isPresent() && result.get() == ButtonType.OK) {
		        try {
	
		            int id = generatedId();
		            String name = agenttype_name.getText();
		            long maxx = agenttype_maxdebt.getValue();
		            Agent_Type newAgentType = new Agent_Type(id, name, maxx);
	
		            table.getItems().add(newAgentType);
	
		            addToDatabase(newAgentType);
		        } catch (NumberFormatException e) {
		            e.printStackTrace();
	
		       }
			    agenttype_id.setText(String.valueOf(generatedId()));
		        agenttype_name.clear();
		        agenttype_maxdebt.getSelectionModel().clearSelection();
		        
    }}
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
	
}

                                      

