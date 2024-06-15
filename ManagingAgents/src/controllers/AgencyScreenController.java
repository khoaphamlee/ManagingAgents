package controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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

import Data_Access_Object.DAO_Reception;
import Data_Access_Object.DAO_Role;

import Database.JDBCUtil;
import datatable.AgencyScreenTable;
import io.github.palexdev.materialfx.beans.BiPredicateBean;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterPane;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.BooleanFilter;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.FXCollectors;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import javafx.stage.Modality;
import javafx.stage.Stage;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.Agent;
import models.Agent_Type;
import models.DVT;
import models.District;
import models.Reception;
import models.Role;


public class AgencyScreenController implements Initializable  {
	Stage window;
	private boolean canChange ;
	private int timeChange;
	
	@FXML
	private AnchorPane darkPane,detailMenuPane,menuPane;
	
	@FXML 
	private ImageView menuImg ; 
	
    @FXML
    private MFXTextField idTf, nameTf, addressTf, phoneTf, emailTf;
    
    @FXML
    private MFXDatePicker dateDp;
    
    @FXML
    private MFXComboBox<String>   statusCbb;
	
    
    @FXML
    private MFXComboBox<District> districtCbb;
    
    @FXML
    private MFXComboBox<Agent_Type> typeCbb;
	@FXML 
	private MFXButton addBtn,editBtn, collectBtn, historyBtn, cancelBtn;
	
	@FXML
	private MFXTableView<AgencyScreenTable> table;
	
	
	Dialog<ButtonType> dialog = new Dialog<>();
	
	@FXML
	private MFXButton homepageBtn,agencyBtn ,goodsBtn ,exportBtn ,reportBtn ,settingBtn;
	
	private AlertMessage alertMessage = new AlertMessage();
	
	private DAO_Agent daoAgent;
	
	private ObservableList<AgencyScreenTable> dataList = FXCollections.observableArrayList();
	
	private DAO_Agent_Type daoAgentType = DAO_Agent_Type.getInstance();
	
	private DAO_Reception daoReception = DAO_Reception.getInstance();
	
	private DAO_District daoDistrict = DAO_District.getInstance();
	
	private Stage stage;
	
	public String convertStatus(boolean status) {
        return status ? "Active" : "Inactive";
    }
	
	private String convertIntToStatusString(int status) {
        return status == 1 ? "Active" : "Inactive";
    }

    private int convertStatusStringToInt(String status) {
        return "Active".equalsIgnoreCase(status) ? 1 : 0;
    }
    ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    
    
    ArrayList<Agent> agents;

    @SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

    	setupScreen();
    	
    	
    	Role role = DAO_Role.getInstance().selectByCondition2(" User_Id = " + CurrentUser.userId);
    	
    	
    	
    	if(!role.isRole1()) agencyBtn.setDisable(true);
    	
    	if(!role.isRole2()) goodsBtn.setDisable(true);
    	
    	if(!role.isRole3()) exportBtn.setDisable(true);
    	
    	if(!role.isRole4()) reportBtn.setDisable(true);
    	
    	if(!role.isRole5()) settingBtn.setDisable(true);
		
		
		
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
		
		connection = JDBCUtil.getConnection();
		String query = "SELECT district_Id, district_Name FROM district WHERE district_Status = "+ 1;
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			List<District> dvts = new ArrayList<District>();
			while (resultSet.next()) {
				int district_Id = resultSet.getInt("district_Id");
				String district_Name = resultSet.getString("district_Name");
				
				
				
				
				dvts.add(new District(district_Id , district_Name));
			}
			districtCbb.setItems(FXCollections.observableArrayList(dvts));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.close();
		
		
		connection = JDBCUtil.getConnection();
		String query2 = "SELECT AgentType_Id, AgentType_Name FROM AGENT_TYPE WHERE AgentType_Status = "+ 1;
		try {
			preparedStatement = connection.prepareStatement(query2);
			resultSet = preparedStatement.executeQuery();
			List<Agent_Type> dvts = new ArrayList<Agent_Type>();
			while (resultSet.next()) {
				int AgentType_Id = resultSet.getInt("AgentType_Id");
				String AgentType_Name = resultSet.getString("AgentType_Name");
				dvts.add(new Agent_Type(AgentType_Id , AgentType_Name,0));
			}
			typeCbb.setItems(FXCollections.observableArrayList(dvts));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.close();
		
		statusCbb.setItems(statusList);
		statusCbb.setPromptText("Select Status");
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
		agents =  DAO_Agent.getInstance().selectAll();
		
		for(int i = 0;i<agents.size();i++) {
			Agent agent = agents.get(i);
			Reception recep = DAO_Reception.getInstance().selectByCondition2(" IdAgent = " + agent.getAgent_Id());
			String typeName = DAO_Agent_Type.getInstance().getAgentTypeNameById(agent.getAgent_Type());
			String adress = DAO_District.getInstance().getDistrictName(agent.getAgent_District());
			
			
			AgencyScreenTable tempt = new AgencyScreenTable(agent.getAgent_Id(),agent.getAgent_Name(),typeName,agent.getAgent_Type(),agent.getAgent_Phone(),agent.getAgent_Address(),adress,agent.getAgent_District(),agent.getAgent_Debt(),recep.getReceptionDate(),agent.getAgent_Email(),convertIntToStatusString(agent.getAgent_Status()));
			dataList.add(tempt);
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
		// Initialize columns
		MFXTableColumn<AgencyScreenTable> idColumn = new MFXTableColumn<>("ID", true, Comparator.comparing(AgencyScreenTable::getAgent_Id));
		MFXTableColumn<AgencyScreenTable> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(AgencyScreenTable::getAgent_Name));
		MFXTableColumn<AgencyScreenTable> typeColumn = new MFXTableColumn<>("Type", true, Comparator.comparing(AgencyScreenTable::getAgent_Type));
		MFXTableColumn<AgencyScreenTable> districtColumn = new MFXTableColumn<>("District", true, Comparator.comparing(AgencyScreenTable::getAgent_District));
		MFXTableColumn<AgencyScreenTable> debtColumn = new MFXTableColumn<>("Debt", true, Comparator.comparing(AgencyScreenTable::getAgent_Debt));
		
		MFXTableColumn<AgencyScreenTable> adColumn = new MFXTableColumn<>("Acceptance date", true, Comparator.comparing(AgencyScreenTable::getAgent_Date));
		
		
		MFXTableColumn<AgencyScreenTable> statusColumn = new MFXTableColumn<>("Status", true, Comparator.comparing(AgencyScreenTable::getAgent_Status));
		
		idColumn.setRowCellFactory(agency -> new MFXTableRowCell<>(AgencyScreenTable::getAgent_Id));
		nameColumn.setRowCellFactory(agency-> new MFXTableRowCell<>(AgencyScreenTable::getAgent_Name));
		typeColumn.setRowCellFactory(agency -> new MFXTableRowCell<>(AgencyScreenTable::getAgent_Type));
		districtColumn.setRowCellFactory(agency-> new MFXTableRowCell<>(AgencyScreenTable::getAgent_District));
		debtColumn.setRowCellFactory(agency -> new MFXTableRowCell<>(AgencyScreenTable::getAgent_Debt));
		
		adColumn.setRowCellFactory(agency -> new MFXTableRowCell<>(AgencyScreenTable::getAgent_Date));
		
		statusColumn.setRowCellFactory(agency -> new MFXTableRowCell<>(AgencyScreenTable::getAgent_Status));
		
		table.getTableColumns().addAll(idColumn,nameColumn,typeColumn,adColumn,districtColumn,debtColumn,statusColumn);
	

		table.getFilters().addAll(
				new IntegerFilter<>("ID", AgencyScreenTable::getAgent_Id),
				new StringFilter<>("Name", AgencyScreenTable::getAgent_Name),
				new StringFilter<>("Type", AgencyScreenTable::getAgent_Type),
				
				new DateFilter<>("Acceptance date", AgencyScreenTable::getAgent_Date),
				new StringFilter<>("District", AgencyScreenTable::getAgent_District),
				
				
				new DoubleFilter<>("Debt", AgencyScreenTable::getAgent_Debt),
				new StringFilter<>("Status", AgencyScreenTable::getAgent_Status)
				
				
				
				
		);
		
		
		loadTable();

		// select row
		idTf.setEditable(false);
		table.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				newValue.values().stream().findFirst().ifPresent(p -> {
					
					
					idTf.setText(String.valueOf(p.getAgent_Id()));
					nameTf.setText(p.getAgent_Name());
					
					
					int v = 0;
					
					for(int i = 0;i<typeCbb.getItems().size();i++) {
						if(typeCbb.getItems().get(i).getAgentType_Id() == p.getAgentType_id()) v = i;
					}
					
					typeCbb.setValue(typeCbb.getItems().get(v));
					
					
					
					addressTf.setText(p.getAgent_Address());
					
					int k = 0;
					
					for(int i = 0;i<districtCbb.getItems().size();i++) {
						if(districtCbb.getItems().get(i).getDistrict_Id() == p.getDistrict_id()) k = i;
					}
					districtCbb.setValue(districtCbb.getItems().get(k));
					
					
					phoneTf.setText(p.getAgent_Phone());
					emailTf.setText(p.getAgent_Email());
					LocalDate date = p.getAgent_Date().toLocalDate();
	                dateDp.setValue(date);
	                
	                if(p.getAgent_Status() == "Active")
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
			if (idTf.getText().isEmpty()) {
				alertMessage.errorMessage("Please select the object you want to change information!");
				return;
			}
			if (nameTf.getText().isEmpty() || typeCbb.getText().isEmpty() || statusCbb.getText().isEmpty() || addressTf.getText().isEmpty()
	    			|| phoneTf.getText().isEmpty() || emailTf.getText().isEmpty() || statusCbb.getText().isEmpty() || dateDp.getText().isEmpty()) {
	            alertMessage.errorMessage("Please fill all blank fields!");
	            return;
	        }
			 else {
				 
				 
				 try {
			            int id = Integer.parseInt(idTf.getText());
			            String name = nameTf.getText();
			            String address = addressTf.getText();
			            String phone = phoneTf.getText();
			            String email = emailTf.getText();
			            int type = typeCbb.getValue().getAgentType_Id();
			            int district = districtCbb.getValue().getDistrict_Id();
			            
			            
			            int aid = DAO_Agent.getInstance().selectByCondition("district_Id = " + district + " and Agent_Id <> " + id).size();
						
						int max =0 ;
						
						try {
					        Connection con = JDBCUtil.getConnection();
					        Statement st = con.createStatement();
					        String sql = "SELECT * FROM FIXED_VALUES";
					        System.out.println(sql);
					        ResultSet rs = st.executeQuery(sql);
					        if (rs.next()) {
					            
					        	
					        	max = rs.getInt("Maximum_Agent_District");
					        	
					        }
					    } catch (Exception e) {
					        e.printStackTrace();
					    }
						if(aid == max) {
							 alertMessage.errorMessage("The district is full of agents");
							 return;
						}
			            
			            
			            
			            
			            
			            String stringStatus = statusCbb.getValue();
			            int status = convertStatusStringToInt(stringStatus);
			            
			           
			            double debt = DAO_Agent.getInstance().getAgentDebtById(id);
			            
			            Agent updatedAgent = new Agent(id, name, phone, address, district, status, debt, type, email);
			            DAO_Agent.getInstance().Update(updatedAgent);
			            reloadTable();
			            alertMessage.successMessage("Information changed successfully!");
			            
			            
			        } catch (NumberFormatException e) {
			            e.printStackTrace();
			            alertMessage.errorMessage("Fail!");
			        }
				
			}
		});

		addBtn.setOnMouseClicked(event -> {
			int typeId  = 0;
			int districtId = 0;
			try{
			
			typeId= typeCbb.getItems().getFirst().getAgentType_Id();
			
			
			
			
			ObservableList<District>  district = districtCbb.getItems();
			int flag = 0;
			for(int i = 0;i< district.size();i++) {
				
				int aid = DAO_Agent.getInstance().selectByCondition("district_Id = " + district.get(i).getDistrict_Id()).size();
				int max =0 ;
				
				try {
			        Connection con = JDBCUtil.getConnection();
			        Statement st = con.createStatement();
			        String sql = "SELECT * FROM FIXED_VALUES";
			        System.out.println(sql);
			        ResultSet rs = st.executeQuery(sql);
			        if (rs.next()) {
			            
			        	
			        	max = rs.getInt("Maximum_Agent_District");
			        	
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
				if(aid != max) {
					districtId= district.get(i).getDistrict_Id();
					break;
				}
				
			}
			if(districtId == 0){
				alertMessage.errorMessage("Every district is full of agents!");
				return;
			}
			
			
			
			
			
			}
			catch(Exception e) {
				alertMessage.errorMessage("No agent type or district exists");
				return;
			}
			
			Agent agent = new Agent(0,"","","",districtId,0,typeId,"");
			
			DAO_Agent.getInstance().Add(agent);
			ArrayList<Agent> agents = DAO_Agent.getInstance().selectAll();
			
			
			Reception recep = new Reception(0,agents.getLast().getAgent_Id(),1,Date.valueOf(LocalDate.now()));
					 
			DAO_Reception.getInstance().Add(recep);
			
			
			
			
			
			reloadTable();
			AgencyScreenTable p = dataList.getLast();
			
			idTf.setText(String.valueOf(p.getAgent_Id()));
			nameTf.setText(p.getAgent_Name());
			typeCbb.setValue(typeCbb.getItems().getFirst());
			addressTf.setText(p.getAgent_Address());
			
			for(int i = 0;i< districtCbb.getItems().size();i++) {
				if(districtCbb.getItems().get(i).getDistrict_Id() == p.getDistrict_id()) {
					districtCbb.setValue(districtCbb.getItems().get(i));
				}
			}
			
			phoneTf.setText(p.getAgent_Phone());
			emailTf.setText(p.getAgent_Email());
			LocalDate date = p.getAgent_Date().toLocalDate();
            dateDp.setValue(date);
			
			statusCbb.setValue(statusList.get(0));
				
			
				
			alertMessage.successMessage("New object added successfully!");
		});

		
		collectBtn.setOnMouseClicked(event -> {
			if (idTf.getText().isEmpty()) {
				alertMessage.errorMessage("Please select the agent you want to collect money!");
				return;
			}
			if (nameTf.getText().isEmpty() || typeCbb.getText().isEmpty() || statusCbb.getText().isEmpty() || addressTf.getText().isEmpty()
	    			|| phoneTf.getText().isEmpty() || emailTf.getText().isEmpty() || statusCbb.getText().isEmpty() || dateDp.getText().isEmpty()) {
	            alertMessage.errorMessage("Please fill all blank fields!");
	            return;
	        }
			try {
				CurrentAgent.agentId = Integer.parseInt(idTf.getText());
				CurrentAgent.agentName = nameTf.getText();
				CurrentAgent.agentAdrress = addressTf.getText();
				CurrentAgent.agentPhone =phoneTf.getText();
				CurrentAgent.agentEmail = emailTf.getText();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/CollectMoneyDialog.fxml"));
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
					
					
					
				}
			} catch (Exception e) {
				alertMessage.errorMessage("Can't load the scene!");
				e.printStackTrace();
				return;
			}
		});
		
		historyBtn.setOnMouseClicked(event -> {
			if (idTf.getText().isEmpty()) {
				alertMessage.errorMessage("Please select the agent you want to collect money!");
				return;
			}
			if (nameTf.getText().isEmpty() || typeCbb.getText().isEmpty() || statusCbb.getText().isEmpty() || addressTf.getText().isEmpty()
	    			|| phoneTf.getText().isEmpty() || emailTf.getText().isEmpty() || statusCbb.getText().isEmpty() || dateDp.getText().isEmpty()) {
	            alertMessage.errorMessage("Please fill all blank fields!");
	            return;
	        }
			try {
				CurrentAgent.agentId = Integer.parseInt(idTf.getText());
				CurrentAgent.agentName = nameTf.getText();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/CollectMoneyList.fxml"));
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
					
					
					
				}
			} catch (Exception e) {
				alertMessage.errorMessage("Can't load the scene!");
				e.printStackTrace();
				return;
			}
		});
		
	}
}

	 
	
        





