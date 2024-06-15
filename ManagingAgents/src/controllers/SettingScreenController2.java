package controllers;

import java.io.IOException;
import java.math.BigDecimal;
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

import Data_Access_Object.DAO_Agent;
import Data_Access_Object.DAO_Agent_Type;
import Data_Access_Object.DAO_District;
import Data_Access_Object.DAO_Fixed_Values;
<<<<<<< HEAD
import Data_Access_Object.DAO_Role;
import Database.JDBCUtil;
=======
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
import datatable.DistrictScreenTable;
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
import models.Agent;
import models.Agent_Type;
import models.District;
import models.Role;

public class SettingScreenController2 implements Initializable  {
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
	private MFXTableView<DistrictScreenTable> table;
	Dialog<ButtonType> dialog = new Dialog<>();
	
	@FXML
	private MFXButton homepageBtn,agencyBtn ,goodsBtn ,exportBtn ,reportBtn ,settingBtn;
	
	@FXML
	private MFXButton agencyTypeBtn,districtBtn,unitBtn,staffBtn;
	
    @FXML
    private MFXTextField maxAgency, district_Id, district_Name;
    
    @FXML
    private MFXComboBox<String> statusCbb;
    
<<<<<<< HEAD
    private ObservableList<District> dataList;
=======
    private ObservableList<District> districtList;
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
    
    private DAO_District daoDistrict;
    
    private DAO_Fixed_Values daoFixedValue;
<<<<<<< HEAD
=======
    
    private String convertIntToStatusString(int status) {
        return status == 1 ? "Active" : "Inactive";
    }

    private int convertStatusStringToInt(String status) {
        return "Active".equalsIgnoreCase(status) ? 1 : 0;
    }

	
@SuppressWarnings("unchecked")
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	 
	setupScreen();
	setupTable();
	loadDataFromDatabase();
	setupTabChange ();
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
    
    private AlertMessage alertMessage = new AlertMessage();
    
    private String convertIntToStatusString(int status) {
        return status == 1 ? "Active" : "Inactive";
    }

    private int convertStatusStringToInt(String status) {
        return "Active".equalsIgnoreCase(status) ? 1 : 0;
    }

	
<<<<<<< HEAD
    ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");
=======
	private void loadDataFromDatabase() {
	    table.getItems().clear();
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780


<<<<<<< HEAD
	
	
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
    	dataList = DAO_District.getInstance().selectAll2();
    	
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
    	MFXTableColumn<District> idColumn = new MFXTableColumn<>("ID", true, Comparator.comparing(District::getDistrict_Id));
    	MFXTableColumn<District> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(District::getDistrict_Name));
    	MFXTableColumn<District> statusColumn = new MFXTableColumn<>("Status", true, Comparator.comparing(District::getDistrict_Status2));
		
		idColumn.setRowCellFactory(item -> new MFXTableRowCell<>(District::getDistrict_Id));
	    nameColumn.setRowCellFactory(item -> new MFXTableRowCell<>(District::getDistrict_Name));
	    statusColumn.setRowCellFactory(item -> new MFXTableRowCell<>(District::getDistrict_Status2));
=======
	    for (District district : districts) {
	        String stringStatus = convertIntToStatusString(district.getDistrict_Status());

	        DistrictScreenTable districtTable = new DistrictScreenTable(
	                district.getDistrict_Id(),
	                district.getDistrict_Name(),
	                stringStatus
	        );

	        table.getItems().add(districtTable);
	    }
	}


	public void setupTable() {
		MFXTableColumn<DistrictScreenTable> idColumn = new MFXTableColumn<>("ID");
		MFXTableColumn<DistrictScreenTable> nameColumn = new MFXTableColumn<>("Name");
		MFXTableColumn<DistrictScreenTable> statusColumn = new MFXTableColumn<>("Status");
		
		idColumn.setRowCellFactory(item -> new MFXTableRowCell<>(DistrictScreenTable::getDistrict_Id));
	    nameColumn.setRowCellFactory(item -> new MFXTableRowCell<>(DistrictScreenTable::getDistrict_Name));
	    statusColumn.setRowCellFactory(item -> new MFXTableRowCell<>(DistrictScreenTable::getDistrict_Status));
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
	    
	    table.getTableColumns().addAll(idColumn, nameColumn, statusColumn);
	    
	    idColumn.setPrefWidth(100);
	    nameColumn.setPrefWidth(200);
	    statusColumn.setPrefWidth(200);
	    
	    table.getFilters().addAll(
<<<<<<< HEAD
		        new IntegerFilter<>("ID", District::getDistrict_Id),
		        new StringFilter<>("Name", District::getDistrict_Name),
		        new StringFilter<>("Status", District::getDistrict_Status2)
		);
	    
    	loadTable();
    	statusCbb.setItems(statusList);
    	// select row
    	district_Id.setEditable(false);
    	table.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
    		if (newValue != null) {
    			newValue.values().stream().findFirst().ifPresent(p -> {
    				district_Id.setText(String.valueOf(p.getDistrict_Id()));
					district_Name.setText(p.getDistrict_Name());	
					statusCbb.setText(p.getDistrict_Status2());
    				
    				
    				
    			});
    		}
    	});
    	int Maximum_Agent_District = 0;
		
		try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM FIXED_VALUES";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                
            	
            	Maximum_Agent_District = rs.getInt("Maximum_Agent_District");
            	
=======
		        new IntegerFilter<>("ID", DistrictScreenTable::getDistrict_Id),
		        new StringFilter<>("Name", DistrictScreenTable::getDistrict_Name),
		        new StringFilter<>("Status", DistrictScreenTable::getDistrict_Status)
		);
	    
	    statusCbb.getItems().addAll("Active", "Inactive");
        statusCbb.setPromptText("Select Status");
        
	    addBtn.setOnAction(event->handleAddButtonAction(event));
	    
	    setupScreen();
	}
	
	
	
	private int generatedId() {
		int Id = 0;
        try {
            ResultSet resultSet = daoDistrict.getCurrentId(); 
            if (resultSet.next()) {
                Id = resultSet.getInt(1) + 1;
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
<<<<<<< HEAD
		maxAgency.setText(Maximum_Agent_District + "");
    	
    }
=======
        return Id;
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
	            int id = Integer.parseInt(district_Id.getText());
	            String name = district_Name.getText();
	            String stringStatus = statusCbb.getValue();
	            int maxx = Integer.parseInt(maxAgency.getText());
	            int status = convertStatusStringToInt(stringStatus);

	            District newDistrict = new District(id, name, status);
	            
	            DistrictScreenTable newDistrictScreenTable = new DistrictScreenTable(id, name, stringStatus);
	            
	            table.getItems().add(newDistrictScreenTable);

	            addToDatabase(newDistrict);
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	        }
	        district_Id.setText(String.valueOf(generatedId()));
	        district_Name.clear();
	        statusCbb.getSelectionModel().clearSelection();
	    }
=======
		Optional<ButtonType> result = alert.showAndWait();
		    if (result.isPresent() && result.get() == ButtonType.OK) {
		        try {
	
		            int id = Integer.parseInt(district_Id.getText());
		            String name = district_Name.getText();
		            int maxx = Integer.parseInt(maxAgency.getText());
	
		            District newDistrict = new District(id, name);
		            
		            District newDistrict2 = new District(id, name);
		            table.getItems().add(newDistrict2);
	
		            addToDatabase(newDistrict);
		        } catch (NumberFormatException e) {
		            e.printStackTrace();
	
		       }
		        
		        district_Id.setText(String.valueOf(generatedId()));
		        district_Name.clear();
		        maxAgency.clear();
		        
    }
>>>>>>> 906790c5ce4401371dc295a95bc25ebb2a8f9660
	}
}
>>>>>>> bdab178a8e0469e9b6ac50a1e13c9cd5dd462780


    public void setupButton() {
    	editBtn.setOnMouseClicked(event -> {
    		if (district_Id.getText().isEmpty()) {
    			alertMessage.errorMessage("Please select the object you want to change information!");
    			return;
    		}
    		if (district_Name.getText().isEmpty() || statusCbb.getText() == null ) {
	            alertMessage.errorMessage("Please fill all blank fields!");
	            return;
	        }
    		 else {
    			 
    			 
    			 try {
    				 int id = Integer.parseInt(district_Id.getText());
    		            String name = district_Name.getText();
    		            String stringStatus = statusCbb.getText();
    		            int status = convertStatusStringToInt(stringStatus);

    		            District updatedDistrict = new District(id, name, status);
    		            DAO_District.getInstance().Update(updatedDistrict);

    		            alertMessage.successMessage("Information changed successfully!");
    		            reloadTable();
    		            
    		            
    		        } catch (NumberFormatException e) {
    		            e.printStackTrace();
    		            alertMessage.errorMessage("Fail!");
    		        }
    			
    		}
    	});

    	addBtn.setOnMouseClicked(event -> {
    		District updatedDistrict = new District(0, "", 1);
    		 DAO_District.getInstance().Add(updatedDistrict);
    		 reloadTable();
    		 District p = DAO_District.getInstance().selectAll().getLast();
    		 district_Id.setText(String.valueOf(p.getDistrict_Id()));
				district_Name.setText(p.getDistrict_Name());	
				statusCbb.setValue(statusCbb.getItems().get(0));

    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    			
    		alertMessage.successMessage("New object added successfully!");
    	});

    	saveBtn.setOnMouseClicked(event -> {
    		if (maxAgency.getText().isEmpty() 
					) {
				alertMessage.errorMessage("Please fill all blank fields!");
				return;
			} else {
				try {
					Connection con = JDBCUtil.getConnection();
		            Statement st = con.createStatement();
		            
		            
		            
		            
		            
		            String sql = "UPDATE FIXED_VALUES SET Maximum_Agent_District = " + Integer.parseInt(maxAgency.getText()) ;
		                         
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
