package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Stream;

import Data_Access_Object.DAO_Agent_Type;
import Data_Access_Object.DAO_District;
import Data_Access_Object.DAO_Items;
import Data_Access_Object.DAO_Role;
import Data_Access_Object.DAO_Unit;
import Database.JDBCUtil;
import datatable.AgentTypeScreenTable;
import datatable.GoodsScreenTable;
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
import io.github.palexdev.materialfx.filter.LongFilter;
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
	private MFXTableView<Agent_Type> table;
	Dialog<ButtonType> dialog = new Dialog<>();
	
	@FXML
	private MFXButton homepageBtn,agencyBtn ,goodsBtn ,exportBtn ,reportBtn ,settingBtn;
	
	@FXML
	private MFXButton agencyTypeBtn,districtBtn,unitBtn,staffBtn;
	
	@FXML
    private MFXTextField agenttype_id, agenttype_name;
	
    @FXML
    private MFXTextField agenttype_maxdebt;
<<<<<<< HEAD
    
    @FXML
    private MFXComboBox<String> statusCbb;
=======
<<<<<<< HEAD
    
    @FXML
    private MFXComboBox<String> statusCbb;
=======
>>>>>>> 906790c5ce4401371dc295a95bc25ebb2a8f9660
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
	
	private boolean noAction;
	
	private ObservableList<Agent_Type> dataList;
    
    private DAO_Agent_Type daoAgentType;
    
<<<<<<< HEAD
    private AlertMessage alertMessage = new AlertMessage();
    
=======
<<<<<<< HEAD
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
    private String convertIntToStatusString(int status) {
        return status == 1 ? "Active" : "Inactive";
    }

    private int convertStatusStringToInt(String status) {
        return "Active".equalsIgnoreCase(status) ? 1 : 0;
<<<<<<< HEAD
    }
=======
=======
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
	public String convertStatus(boolean status) {
        return status ? "Active" : "Inactive";
>>>>>>> 906790c5ce4401371dc295a95bc25ebb2a8f9660
    }
	ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");


	
	
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

<<<<<<< HEAD
private void setupTabChange() throws SQLException {
=======
	public void setupScreen() {
		
		agenttype_id.setText(String.valueOf(generatedId()));
	    agenttype_id.setEditable(false);
		
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
		MFXTableColumn<AgentTypeScreenTable> idColumn = new MFXTableColumn<>("ID");
		MFXTableColumn<AgentTypeScreenTable> nameColumn = new MFXTableColumn<>("Name");
		MFXTableColumn<AgentTypeScreenTable> statusColumn = new MFXTableColumn<>("Status");
		
		idColumn.setRowCellFactory(item -> new MFXTableRowCell<>(AgentTypeScreenTable::getAgentType_Id));
	    nameColumn.setRowCellFactory(item -> new MFXTableRowCell<>(AgentTypeScreenTable::getAgentType_Name));
	    statusColumn.setRowCellFactory(item -> new MFXTableRowCell<>(AgentTypeScreenTable::getAgentType_Status));
	    
	    table.getTableColumns().addAll(idColumn, nameColumn, statusColumn);
	    
	    idColumn.setPrefWidth(100);
	    nameColumn.setPrefWidth(200);
	    statusColumn.setPrefWidth(100);
	    
	    table.getFilters().addAll(
		        new IntegerFilter<>("ID", AgentTypeScreenTable::getAgentType_Id),
		        new StringFilter<>("Name", AgentTypeScreenTable::getAgentType_Name),
		        new StringFilter<>("Status", AgentTypeScreenTable::getAgentType_Status)
		);
	    
<<<<<<< HEAD
        statusCbb.getItems().addAll("Active", "Inactive");
        statusCbb.setPromptText("Select Status");
        
	    addBtn.setOnAction(event->handleAddButtonAction(event));
=======
	    //addBtn.setOnAction(event->handleAddButtonAction(event));
>>>>>>> 906790c5ce4401371dc295a95bc25ebb2a8f9660
	    

	    
	    table.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				newValue.values().stream().findFirst().ifPresent(p -> {
					agenttype_id.setText(String.valueOf(p.getAgentType_Id()));
					agenttype_name.setText(p.getAgentType_Name());
					agenttype_maxdebt.setText(String.valueOf(p.getAgentType_MaxDebt()));
					statusCbb.setText(p.getAgentType_Status());
				});
			}
		});
	    
	    setupScreen();
	}
	private void showAlert(String message) {
	    Alert alert = new Alert(Alert.AlertType.ERROR);
	    alert.setTitle("Error");
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();
	}
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
	
	
	
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
	dataList = DAO_Agent_Type.getInstance().selectAll2();
	
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
	MFXTableColumn<Agent_Type> idColumn = new MFXTableColumn<>("ID", true, Comparator.comparing(Agent_Type::getAgentType_Id));
	MFXTableColumn<Agent_Type> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(Agent_Type::getAgentType_Name));
	MFXTableColumn<Agent_Type> maxDebtColumn = new MFXTableColumn<>("Maximum debt", true, Comparator.comparing(Agent_Type::getAgentType_MaxDebt2));
	MFXTableColumn<Agent_Type> statusColumn = new MFXTableColumn<>("Status", true, Comparator.comparing(Agent_Type::getAgentType_Status2));
	
	idColumn.setRowCellFactory(item -> new MFXTableRowCell<>(Agent_Type::getAgentType_Id));
    nameColumn.setRowCellFactory(item -> new MFXTableRowCell<>(Agent_Type::getAgentType_Name));
    statusColumn.setRowCellFactory(item -> new MFXTableRowCell<>(Agent_Type::getAgentType_Status2));
    maxDebtColumn.setRowCellFactory(item -> new MFXTableRowCell<>(Agent_Type::getAgentType_MaxDebt2));
    
    table.getTableColumns().addAll(idColumn, nameColumn,maxDebtColumn, statusColumn);
    
    
    table.getFilters().addAll(
	        new IntegerFilter<>("ID", Agent_Type::getAgentType_Id),
	        new StringFilter<>("Name", Agent_Type::getAgentType_Name),
	        new StringFilter<>("Status", Agent_Type::getAgentType_Status2),
	        new DoubleFilter<>("Maximum debt", Agent_Type::getAgentType_MaxDebt)
	);
	
	loadTable();
	statusCbb.setItems(statusList);
	// select row
	agenttype_id.setEditable(false);
	table.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
		if (newValue != null) {
			newValue.values().stream().findFirst().ifPresent(p -> {
				agenttype_id.setText(String.valueOf(p.getAgentType_Id()));
				
				
				agenttype_name.setText(p.getAgentType_Name());
				
				
				
				
				
				
				agenttype_maxdebt.setText(String.valueOf(p.getAgentType_MaxDebt()));
				if(p.getAgentType_Status2() == "Active")
					statusCbb.setValue(statusCbb.getItems().get(0));
	                else {
	                	statusCbb.setValue(statusCbb.getItems().get(1));
	                }
				
				
				
			});
		}
	});
}


public void setupButton() {
	editBtn.setOnMouseClicked(event -> {
		if (agenttype_id.getText().isEmpty()) {
			alertMessage.errorMessage("Please select the object you want to change information!");
			return;
		}
		if (agenttype_name.getText().isEmpty() || agenttype_maxdebt.getText().isEmpty() || statusCbb.getText().isEmpty()) {
            alertMessage.errorMessage("Please fill all blank fields!");
            return;
        }
<<<<<<< HEAD
        
		 else {
			 
			 
			 try {
				 int id = Integer.parseInt(agenttype_id.getText());
=======
        return Id;
	}
	
<<<<<<< HEAD
	private void loadDataFromDatabase() {
	    table.getItems().clear();

	    List<Agent_Type> agentTypes = daoAgentType.selectAll();

	    for (Agent_Type agentType : agentTypes) {
	        String stringStatus = convertIntToStatusString(agentType.getAgentType_Status());
=======
	/*private void loadDataFromDatabase() {
	    //table.getItems().clear();

	    List<Agent_Type> agentTypes = daoAgentType.selectAll();

	    table.getItems().addAll(agentTypes);
	}*/
>>>>>>> 906790c5ce4401371dc295a95bc25ebb2a8f9660

	        AgentTypeScreenTable agentTypeTable = new AgentTypeScreenTable(
	                agentType.getAgentType_Id(),
	                agentType.getAgentType_Name(),
	                agentType.getAgentType_MaxDebt(),
	                stringStatus
	        );

	        table.getItems().add(agentTypeTable);
	    }
	}
	@FXML
	private void handleAddButtonAction(ActionEvent event) {
	    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	    alert.setTitle("Confirmation");
	    alert.setHeaderText(null);
	    alert.setContentText("Are you sure you want to add?");
	    
<<<<<<< HEAD
	    Optional<ButtonType> result = alert.showAndWait();
	    if (result.isPresent() && result.get() == ButtonType.OK) {
	        try {
	            int id = generatedId();
	            String name = agenttype_name.getText();
	            long maxx = Long.parseLong(agenttype_maxdebt.getText());
	            String stringStatus = statusCbb.getValue();
	            int status = convertStatusStringToInt(stringStatus);
	            
	            Agent_Type newAgentType = new Agent_Type(id, name, maxx, status);
	            AgentTypeScreenTable newAgentTypeTable = new AgentTypeScreenTable(id, name, maxx, stringStatus);
	            table.getItems().add(newAgentTypeTable);

	            addToDatabase(newAgentType);
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	        }

	        agenttype_id.setText(String.valueOf(generatedId()));
	        agenttype_name.clear();
	        agenttype_maxdebt.clear();
	        statusCbb.getSelectionModel().clearSelection();
	    }
	}

=======
		Optional<ButtonType> result = alert.showAndWait();
		    if (result.isPresent() && result.get() == ButtonType.OK) {
		        try {
	
		            int id = generatedId();
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
		            String name = agenttype_name.getText();
		            double maxDebt = Double.parseDouble(agenttype_maxdebt.getText());
		            String stringStatus = statusCbb.getText();
		            int status = convertStatusStringToInt(stringStatus);

		            Agent_Type updatedAgentType = new Agent_Type(id, name, maxDebt, status);
		            DAO_Agent_Type.getInstance().Update(updatedAgentType);

		            alertMessage.successMessage("Information changed successfully!");
		            
		            reloadTable();
		            
		            
		        } catch (NumberFormatException e) {
		            e.printStackTrace();
		            alertMessage.errorMessage("Fail!");
		        }
			
		}
	});

	addBtn.setOnMouseClicked(event -> {
		
		Agent_Type agentType = new Agent_Type(0, "", 0, 1);
	DAO_Agent_Type.getInstance().Add(agentType);
	reloadTable();
		Agent_Type p =  DAO_Agent_Type.getInstance().selectAll().getLast();
	agenttype_id.setText(String.valueOf(p.getAgentType_Id()));
	agenttype_name.setText(p.getAgentType_Name());
	
<<<<<<< HEAD
	
	agenttype_maxdebt.setText(String.valueOf(p.getAgentType_MaxDebt()));
	statusCbb.setValue(statusCbb.getItems().get(0));
		
		
		
		
		
		
		
		
		
		
		
		
			
		alertMessage.successMessage("New object added successfully!");
	});

	
	
	
}
=======
		       }
			    agenttype_id.setText(String.valueOf(generatedId()));
		        agenttype_name.clear();
		        //agenttype_maxdebt.getSelectionModel().clearSelection();
		        
    }}
>>>>>>> 906790c5ce4401371dc295a95bc25ebb2a8f9660
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
	
}

                                      

