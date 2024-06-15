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

import Data_Access_Object.DAO_Debt_Agent_Report;
import Data_Access_Object.DAO_Role;
import Data_Access_Object.DAO_Sales_Report;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.Agent;
import models.Debt_Agent_Report;
import models.Role;
import models.Sales_Report;

public class ReportScreenController2 implements Initializable  {
	Stage window;
	private boolean canChange ;
	private int timeChange;
	
	@FXML
	private AnchorPane darkPane,detailMenuPane,menuPane;
	
	@FXML 
	private ImageView menuImg ; 
	
	@FXML 
	private MFXButton addBtn,editBtn,detailBtn;
	
	
	Dialog<ButtonType> dialog = new Dialog<>();
	
	@FXML
	private MFXButton homepageBtn,agencyBtn ,goodsBtn ,exportBtn ,reportBtn ,settingBtn;
	
	@FXML
	private MFXButton salesBtn,debtBtn;
	
private ObservableList<Debt_Agent_Report> debtList = null;
	
	@FXML
	private MFXTableView<Debt_Agent_Report> table;
;

	
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
		salesBtn.setOnAction(event -> {
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
		debtBtn.setOnAction(event -> {
			window = (Stage)menuImg.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ReportScreen2.fxml"));
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
	public void loadTable() {
		debtList = DAO_Debt_Agent_Report.getInstance().selectAll2();
		table.setItems(debtList);
		table.autosize();
		table.autosizeColumns();
		table.autosizeColumnsOnInitialization();
	}

	// Reload screen
	public void reloadTable() {
		debtList.clear();
		loadTable();
	}

		 public void setupTable() {
				MFXTableColumn<Debt_Agent_Report> idColumn = new MFXTableColumn<>("Id", true, Comparator.comparing(Debt_Agent_Report::getDebtAgentReport_Id));
				MFXTableColumn<Debt_Agent_Report> dateColumn = new MFXTableColumn<>("Date", true, Comparator.comparing(Debt_Agent_Report::getDebtAgentReport_Date));
				
				

				// Set row cell factories
				idColumn.setRowCellFactory(col -> new MFXTableRowCell<>(Debt_Agent_Report::getDebtAgentReport_Id));
				dateColumn.setRowCellFactory(col -> new MFXTableRowCell<>(Debt_Agent_Report::getDebtAgentReport_Date));
				
				

				// Add columns to the table
				table.getTableColumns().addAll(idColumn, dateColumn);

				// Add filters to the table
				table.getFilters().add(new IntegerFilter<>("Id", Debt_Agent_Report::getDebtAgentReport_Id));
				table.getFilters().add(new IntegerFilter<>("Month", Debt_Agent_Report::getMonth));
				table.getFilters().add(new IntegerFilter<>("Year", Debt_Agent_Report::getYear));
				
				
				

				loadTable();
				
				table.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
					if (newValue != null) {
						newValue.values().stream().findFirst().ifPresent(p -> {
							CurrentDebtReport.id = p.getDebtAgentReport_Id();
							CurrentDebtReport.date = p.getDebtAgentReport_Date();
						});
					}
				});
				
				AlertMessage alertMessage = new AlertMessage();
				
				
				detailBtn.setOnAction(event -> {
					if (event.getSource() == detailBtn) {
						if (CurrentDebtReport.id == 0) {
							
							alertMessage.errorMessage("Please select the object you want to change information!");
							return;
						}
						try {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AgentDebtReportDialog.fxml"));
							Parent root = (Parent) loader.load();
							Stage stage = new Stage();
							stage.setScene(new Scene(root));
							stage.initModality(Modality.APPLICATION_MODAL);
							stage.setTitle("Managing Agents");
							stage.getIcons().add(new Image("/images/mylogo.png"));
							stage.setResizable(false);
							stage.showAndWait();
							if (!stage.isShowing()) {
								reloadTable();
								CurrentDebtReport.id = 0;
							}
						} catch (Exception e) {
							alertMessage.errorMessage("Can't load the scene!");
							e.printStackTrace();
							return;
						}
					}
				});
				

			}
	
	
}

                                      

