package controllers;

import java.io.IOException;
import java.net.URL;
<<<<<<< HEAD
=======
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
import Data_Access_Object.DAO_Agent;
import Data_Access_Object.DAO_Agent_Type;
import Data_Access_Object.DAO_District;
import Data_Access_Object.DAO_Reception;
import datatable.AgencyScreenTable;
import io.github.palexdev.materialfx.beans.BiPredicateBean;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXFilterPane;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;

import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.FXCollectors;
<<<<<<< HEAD
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
=======
import java.sql.Date;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
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
<<<<<<< HEAD
=======
import javafx.scene.layout.VBox;
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.Agency;
<<<<<<< HEAD
=======
import models.Agent;
import models.Agent_Type;
import models.Reception;
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3

public class AgencyScreenController implements Initializable  {
	Stage window;
	private boolean canChange ;
	private int timeChange;
	
	@FXML
	private AnchorPane darkPane,detailMenuPane,menuPane;
	
	@FXML 
	private ImageView menuImg ; 
	
<<<<<<< HEAD
=======
    @FXML
    private MFXTextField idTf, nameTf, addressTf, phoneTf;
    
    @FXML
    private MFXDatePicker dateDp;
    
    @FXML
    private MFXComboBox<String> districtCbb, typeCbb;
	
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
	@FXML 
	private MFXButton addBtn,editBtn;
	
	@FXML
<<<<<<< HEAD
	private MFXTableView<Agency> table;
=======
	private MFXTableView<AgencyScreenTable> table;
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
	Dialog<ButtonType> dialog = new Dialog<>();
	
	@FXML
	private MFXButton homepageBtn,agencyBtn ,goodsBtn ,exportBtn ,reportBtn ,settingBtn;
<<<<<<< HEAD
;
=======
	
	private DAO_Agent daoAgent;
	
	private ObservableList<AgencyScreenTable> dataList = FXCollections.observableArrayList();
	
	private DAO_Agent_Type daoAgentType = DAO_Agent_Type.getInstance();
	
	private DAO_Reception daoReception = DAO_Reception.getInstance();
	
	private DAO_District daoDistrict = DAO_District.getInstance();
	
public AgencyScreenController() {
	this.daoAgent = DAO_Agent.getInstance();
}
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3

	
@SuppressWarnings("unchecked")
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	 
	setupScreen();
	setupTable();
<<<<<<< HEAD
	setupTabChange ();
    
    
        
    }
=======
	setupTabChange();
    setupComboBoxes();
    loadFromDatabase();
        
}
private void setupComboBoxes() {
	
	List<String> typeNames = daoAgentType.getTypeNamesFromDatabase();
	ObservableList<String> typeNameOptions = FXCollections.observableArrayList(typeNames);
    
    typeCbb.setItems(typeNameOptions);
    
    List<String> districtNames = daoDistrict.getDistrictNamesFromDatabase();
	ObservableList<String> districtNameOptions = FXCollections.observableArrayList(districtNames);
    
    districtCbb.setItems(districtNameOptions);
    
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
		
		
}

<<<<<<< HEAD
	public void
	
	
	setupScreen() {
		
			

		
		
		
		
		
		
		
		
		darkPane.setVisible(false);
=======
	public void setupScreen() {
		
	    idTf.setText(String.valueOf(generatedId()));
	    idTf.setEditable(false);
	    
	    darkPane.setVisible(false);
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3


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
		MFXTableColumn<Agency> idColumn = new MFXTableColumn<>("ID", true, Comparator.comparing(Agency::getId));
		MFXTableColumn<Agency> agencyColumn = new MFXTableColumn<>("Agency", true, Comparator.comparing(Agency::getAgency));
		MFXTableColumn<Agency> typeColumn = new MFXTableColumn<>("Type", true, Comparator.comparing(Agency::getType));
		MFXTableColumn<Agency> districtColumn = new MFXTableColumn<>("District", true, Comparator.comparing(Agency::getDistrict));
		MFXTableColumn<Agency> debtColumn = new MFXTableColumn<>("Debt", true, Comparator.comparing(Agency::getDebt));
		MFXTableColumn<Agency> phoneColumn = new MFXTableColumn<>("Phone number", true, Comparator.comparing(Agency::getPhoneNumber));
		MFXTableColumn<Agency> adColumn = new MFXTableColumn<>("Acceptance date", true, Comparator.comparing(Agency::getAcceptanceDate));
		MFXTableColumn<Agency> addressColumn = new MFXTableColumn<>("Address", true, Comparator.comparing(Agency::getAddress));

		idColumn.setRowCellFactory(agency -> new MFXTableRowCell<>(Agency::getId));
		agencyColumn.setRowCellFactory(agency-> new MFXTableRowCell<>(Agency::getAgency));
		typeColumn.setRowCellFactory(agency -> new MFXTableRowCell<>(Agency::getType));
		districtColumn.setRowCellFactory(agency-> new MFXTableRowCell<>(Agency::getDistrict));
		debtColumn.setRowCellFactory(agency -> new MFXTableRowCell<>(Agency::getDebt));
		phoneColumn.setRowCellFactory(agency -> new MFXTableRowCell<>(Agency::getPhoneNumber));
		adColumn.setRowCellFactory(agency -> new MFXTableRowCell<>(Agency::getAcceptanceDate));
		addressColumn.setRowCellFactory(agency -> new MFXTableRowCell<>(Agency::getAddress));
		

		table.getTableColumns().addAll(idColumn,agencyColumn,typeColumn,adColumn,addressColumn,districtColumn,phoneColumn,debtColumn);
		table.getTableColumns().get(0).setPrefWidth(100);
		table.getTableColumns().get(1).setPrefWidth(200);
		table.getTableColumns().get(2).setPrefWidth(200);
=======
	    //Output();
	    //table.setItems(dataList);
	}
	
	private void Output() {
		for (AgencyScreenTable data : dataList) {
		    System.out.println("ID: " + data.getAgent_Id());
		    System.out.println("Name: " + data.getAgent_Name());
		    System.out.println("Type: " + data.getAgent_Type());
		    System.out.println("Phone: " + data.getAgent_Phone());
		    System.out.println("Address: " + data.getAgent_Address());
		    System.out.println("District: " + data.getAgent_District());
		    System.out.println("Debt: " + data.getAgent_Debt());
		    System.out.println("Date: " + data.getAgent_Date());
		    System.out.println("-----------------------------");
		}

	}
	
	private int generatedId() {
		int Id = 0;
        try {
            ResultSet resultSet = daoAgent.getCurrentId(); 
            if (resultSet.next()) {
                Id = resultSet.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Id;
	}
	
	private void addToDatabase(Agent newAgent) {
		daoAgent.Add(newAgent);
    }
	
	private void addToDatabase(Reception newReception) {
		daoReception.Add(newReception);
    }
	
	private void loadFromDatabase() {
		List<AgencyScreenTable> tableList = new ArrayList<>();
		
		ArrayList<Integer> idList = daoReception.getReceptionIds();
		ArrayList<Integer> idAgentList = daoReception.getAgentIds();
		ArrayList<Integer> idAgentTypeList = daoReception.getAgentTypeIds();
		ArrayList<Date> dateList = daoReception.getReceptionDates();
		
		int count = daoReception.countRecords();
		for (int i = 0; i < count; i++) {
			
			int idAgent = idAgentList.get(i);
			int idAgentType = idAgentTypeList.get(i);
			Date dateReception = dateList.get(i);
			
			DAO_Agent daoAgent = new DAO_Agent();
			DAO_Agent_Type daoAgentType = new DAO_Agent_Type();
			
			String nameAgent = daoAgent.getAgentNameById(idAgent);
			String agentType = daoAgentType.getAgentTypeNameById(idAgentType);
			String phoneAgent = daoAgent.getAgentPhoneById(idAgentType);
			String addressAgent = daoAgent.getAgentAddressById(idAgentType);
			String districtAgent = daoAgent.getAgentDistrictById(idAgentType);
			double debtAgent = daoAgent.getAgentDebtById(idAgentType);
			
			AgencyScreenTable agencyScreenTable = new AgencyScreenTable(idAgent, nameAgent, agentType, phoneAgent, addressAgent, districtAgent, debtAgent, dateReception.toString());
			
			table.getItems().add(agencyScreenTable);
		}
	}
	
	public void setupTable() {
		MFXTableColumn<AgencyScreenTable> idColumn = new MFXTableColumn<>("ID", true, Comparator.comparing(AgencyScreenTable::getAgent_Id));
		MFXTableColumn<AgencyScreenTable> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(AgencyScreenTable::getAgent_Name));
		MFXTableColumn<AgencyScreenTable> typeColumn = new MFXTableColumn<>("Type", true, Comparator.comparing(AgencyScreenTable::getAgent_Type));
		MFXTableColumn<AgencyScreenTable> districtColumn = new MFXTableColumn<>("District", true, Comparator.comparing(AgencyScreenTable::getAgent_District));
		MFXTableColumn<AgencyScreenTable> debtColumn = new MFXTableColumn<>("Debt", true, Comparator.comparing(AgencyScreenTable::getAgent_Debt));
		MFXTableColumn<AgencyScreenTable> phoneColumn = new MFXTableColumn<>("Phone number", true, Comparator.comparing(AgencyScreenTable::getAgent_Phone));
		MFXTableColumn<AgencyScreenTable> adColumn = new MFXTableColumn<>("Acceptance date", true, Comparator.comparing(AgencyScreenTable::getAgent_Date));
		MFXTableColumn<AgencyScreenTable> addressColumn = new MFXTableColumn<>("Address", true, Comparator.comparing(AgencyScreenTable::getAgent_Address));

		idColumn.setRowCellFactory(agency -> new MFXTableRowCell<>(AgencyScreenTable::getAgent_Id));
		nameColumn.setRowCellFactory(agency-> new MFXTableRowCell<>(AgencyScreenTable::getAgent_Name));
		typeColumn.setRowCellFactory(agency -> new MFXTableRowCell<>(AgencyScreenTable::getAgent_Type));
		districtColumn.setRowCellFactory(agency-> new MFXTableRowCell<>(AgencyScreenTable::getAgent_District));
		debtColumn.setRowCellFactory(agency -> new MFXTableRowCell<>(AgencyScreenTable::getAgent_Debt));
		phoneColumn.setRowCellFactory(agency -> new MFXTableRowCell<>(AgencyScreenTable::getAgent_Phone));
		adColumn.setRowCellFactory(agency -> new MFXTableRowCell<>(AgencyScreenTable::getAgent_Date));
		addressColumn.setRowCellFactory(agency -> new MFXTableRowCell<>(AgencyScreenTable::getAgent_Address));
		

		table.getTableColumns().addAll(idColumn,nameColumn,typeColumn,adColumn,addressColumn,districtColumn,phoneColumn,debtColumn);
		table.getTableColumns().get(0).setPrefWidth(50);
		table.getTableColumns().get(1).setPrefWidth(200);
		table.getTableColumns().get(2).setPrefWidth(100);
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
		table.getTableColumns().get(3).setPrefWidth(200);
		table.getTableColumns().get(4).setPrefWidth(200);
		table.getTableColumns().get(5).setPrefWidth(200);
		table.getTableColumns().get(6).setPrefWidth(200);
		table.getTableColumns().get(7).setPrefWidth(200);
		table.getFilters().addAll(
<<<<<<< HEAD
				new IntegerFilter<>("ID", Agency::getId),
				new StringFilter<>("Agency", Agency::getAgency),
				new StringFilter<>("Type", Agency::getType),
				new StringFilter<>("District", Agency::getDistrict),
				new DoubleFilter<>("Debt", Agency::getDebt)
				
		);
		
		addBtn.setOnAction(event->{
	    	Optional<ButtonType> result = dialog.showAndWait();
	        if (result.isPresent() && result.get() == ButtonType.OK) {
	            
	        } else {
	            
	        }
	    });
=======
				new IntegerFilter<>("ID", AgencyScreenTable::getAgent_Id),
				new StringFilter<>("Name", AgencyScreenTable::getAgent_Name),
				new StringFilter<>("Type", AgencyScreenTable::getAgent_Type),
				new StringFilter<>("Acceptance date", AgencyScreenTable::getAgent_Date),
				new StringFilter<>("Address", AgencyScreenTable::getAgent_Address),
				new StringFilter<>("District", AgencyScreenTable::getAgent_District),
				new StringFilter<>("Phone number", AgencyScreenTable::getAgent_Phone),
				new DoubleFilter<>("Debt", AgencyScreenTable::getAgent_Debt)
				
		);
		
		
		addBtn.setOnAction(event->handleAddButtonAction(event));
		setupScreen();
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
	    
	    editBtn.setOnAction(event->{
	    	Optional<ButtonType> result = dialog.showAndWait();
	        if (result.isPresent() && result.get() == ButtonType.OK) {
	            
	        } else {
	            
	        }
	    });
	}
	
<<<<<<< HEAD
=======
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
		            String name = nameTf.getText();
		            String type = typeCbb.getValue();
		            String address = addressTf.getText();
		            String phone = phoneTf.getText();
		            String district = districtCbb.getValue();
		            String date = dateDp.getText();
		            double debt = 0.0;
		            
		            int typeId = daoAgentType.getAgentTypeIdByName(type);
		            String currentDateFormat = "MMMM d, yyyy";
		            String newDateFormat = "yyyy-MM-dd";
		            String formattedDate = null;

		            try {
		                SimpleDateFormat currentFormat = new SimpleDateFormat(currentDateFormat);

		                java.util.Date utilDate = currentFormat.parse(date);

		                SimpleDateFormat newFormat = new SimpleDateFormat(newDateFormat);

		                formattedDate = newFormat.format(utilDate);

		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		            
		            Agent newAgent = new Agent(id, name, phone, address, district, debt);
		            AgencyScreenTable dataTable = new AgencyScreenTable(id, name, type, phone, address, district, debt, date);
		            Reception newReception = new Reception(id, id, typeId, Date.valueOf(formattedDate));
		            System.out.println(id + "" + typeId + "" + formattedDate);
		            table.getItems().add(dataTable);
		            
		            dataList.add(dataTable);
	
		            addToDatabase(newAgent);
		            addToDatabase(newReception);
		        } catch (NumberFormatException e) {
		            e.printStackTrace();
	
		       }
			    idTf.setText(String.valueOf(generatedId()));
		        nameTf.clear();
		        typeCbb.getSelectionModel().clearSelection();
		        phoneTf.clear();
		        addressTf.clear();
		        districtCbb.getSelectionModel().clearSelection();
		        dateDp.clear();
		        
    }}
	
	
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
}

                                      

