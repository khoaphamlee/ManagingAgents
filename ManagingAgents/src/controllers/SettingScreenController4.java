package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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


import Data_Access_Object.DAO_Role;
import Data_Access_Object.DAO_User;
import io.github.palexdev.materialfx.beans.BiPredicateBean;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXFilterPane;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.BooleanFilter;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.Role;

import models.User;

public class SettingScreenController4 implements Initializable {
	Stage window;
	private boolean canChange;
	private int timeChange;
	private AlertMessage alertMessage = new AlertMessage();

	private ObservableList<User> userList = null;

	@FXML
	private MFXButton addBtn;

	@FXML
	private MFXButton homepageBtn,agencyBtn ,goodsBtn ,exportBtn ,reportBtn ,settingBtn;
	
	@FXML
	private MFXButton agencyTypeBtn,districtBtn,unitBtn,staffBtn;

	@FXML
	private MFXButton admissionBtn;

	@FXML
	private ImageView admissionImg;

	

	@FXML
	private ImageView contactImg;

	@FXML
	private AnchorPane darkPane;

	@FXML
	private AnchorPane detailMenuPane;

	@FXML
	private MFXButton doctorBtn;

	@FXML
	private ImageView doctorImg;

	

	@FXML
	private MFXButton editBtn;

	@FXML
	private ImageView homeImage;

	

	@FXML
	private MFXButton medicineBtn;

	@FXML
	private ImageView medicineImg;

	

	@FXML
	private ImageView menuImg;

	@FXML
	private ImageView menuImg1;

	@FXML
	private AnchorPane menuPane;

	@FXML
	private AnchorPane pane4;

	@FXML
	private MFXButton pateintBtn;

	@FXML
	private ImageView pateintImg;

	

	

	@FXML
	private ImageView reportImg;

	@FXML
	private MFXCheckbox report_ckb;

	@FXML
	private MFXButton subclinicalBtn;

	@FXML
	private ImageView subclinicalImg;

	

	@FXML
	private MFXButton userBtn;

	@FXML
	private ImageView userImg;

	@FXML
	private MFXTableView<User> userTable;

	@FXML
	private MFXTextField user_address;

	@FXML
	private MFXCheckbox user_ckb;

	@FXML
	private MFXTextField user_email;

	@FXML
	private MFXTextField user_id;

	@FXML
	private MFXTextField user_name;

	@FXML
	private MFXTextField user_password;

	@FXML
	private MFXTextField user_phone;

	@FXML
	private MFXTextField user_position;

	@FXML
	private MFXTextField user_username;
	
	
	@FXML
	private MFXCheckbox r1,r2,r3,r4,r5;
	
	@FXML
	private MFXComboBox<String> em_status;
	
	ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");

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
		setupTabChange();
		setupButton();

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

		em_status.setItems(statusList);
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
			userList = DAO_User.getInstance().selectAll2();
			userTable.setItems(userList);
			
		}

		// Reload screen
		public void reloadTable() {
			userList.clear();
			loadTable();
		}

		@SuppressWarnings("unchecked")
		public void setupTable() {
			// Initialize columns
			MFXTableColumn<User> idColumn = new MFXTableColumn<>("Id", true, Comparator.comparing(User::getUser_Id));
			MFXTableColumn<User> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(User::getName));
			MFXTableColumn<User> emailColumn = new MFXTableColumn<>("Email", true, Comparator.comparing(User::getEmail));
			MFXTableColumn<User> phoneColumn = new MFXTableColumn<>("Phone", true,Comparator.comparing(User::getSDT));
			MFXTableColumn<User> addressColumn = new MFXTableColumn<>("Address", true,Comparator.comparing(User::getAddress));
			//MFXTableColumn<User> positionColumn = new MFXTableColumn<>("Position", true,Comparator.comparing(User::getIdRole));
			MFXTableColumn<User> usernameColumn = new MFXTableColumn<>("Username", true,Comparator.comparing(User::getUserName));
			MFXTableColumn<User> passwordColumn = new MFXTableColumn<>("Password", true,Comparator.comparing(User::getPassword));
			MFXTableColumn<User> statusColumn = new MFXTableColumn<>("Status", true, Comparator.comparing(User::getStatus));
			

			// Set row cell factories
			idColumn.setRowCellFactory(col -> new MFXTableRowCell<>(User::getUser_Id));
			nameColumn.setRowCellFactory(col -> new MFXTableRowCell<>(User::getName));
			emailColumn.setRowCellFactory(col -> new MFXTableRowCell<>(User::getEmail));
			phoneColumn.setRowCellFactory(col -> new MFXTableRowCell<>(User::getSDT));
			addressColumn.setRowCellFactory(col -> new MFXTableRowCell<>(User::getAddress));
			//positionColumn.setRowCellFactory(col -> new MFXTableRowCell<>(User::getIdRole));
			usernameColumn.setRowCellFactory(col -> new MFXTableRowCell<>(User::getUserName));
			passwordColumn.setRowCellFactory(col -> new MFXTableRowCell<>(User::getPassword));
			statusColumn.setRowCellFactory(col -> new MFXTableRowCell<>(User::getStatus));
			
			
			
			idColumn.setMaxWidth(35);
			idColumn.setMinWidth(35);
			
			nameColumn.setMaxWidth(70);
			nameColumn.setMinWidth(70);
			
			statusColumn.setMaxWidth(70);
			statusColumn.setMinWidth(70);
			// Add columns to the table
			userTable.getTableColumns().addAll(idColumn, nameColumn, emailColumn, phoneColumn, addressColumn, usernameColumn, passwordColumn,statusColumn);
					
			// Add filters to the table
			userTable.getFilters().addAll(new IntegerFilter<>("Id", User::getUser_Id),
					new StringFilter<>("Name", User::getName), new StringFilter<>("Email", User::getEmail),
					new StringFilter<>("Phone", User::getSDT), new StringFilter<>("Address", User::getAddress),
					new StringFilter<>("Username", User::getUserName),
					new StringFilter<>("Password", User::getPassword),
					new BooleanFilter<>("Status", User::getStatus));
			
			loadTable();
			
			// select row
			user_id.setEditable(false);
			userTable.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue != null) {
					newValue.values().stream().findFirst().ifPresent(p -> {
						
						Role role = DAO_Role.getInstance().selectByCondition2(" User_Id = " + p.getUser_Id());
						
						
						user_id.setText(String.valueOf(p.getUser_Id()));
						user_name.setText(p.getName());
						user_email.setText(p.getEmail());
						user_phone.setText(p.getSDT());
						user_address.setText(p.getAddress());
						
						user_username.setText(p.getUserName());	
						user_password.setText(p.getPassword());
						
						user_position.setText(role.getPosition());
						if(role.isRole1()) r1.setSelected(true);
						else {
							r1.setSelected(false);
						}
						if(role.isRole2()) r2.setSelected(true);
						else r2.setSelected(false);
						if(role.isRole3()) r3.setSelected(true);
						else r3.setSelected(false);
						if(role.isRole4()) r4.setSelected(true);
						else r4.setSelected(false);
						if(role.isRole5()) r5.setSelected(true);
						else r5.setSelected(false);
						
						if(p.getStatus()) {
							 em_status.setText(statusList.get(0));
						}
						else {
							em_status.setText(statusList.get(1));
						}
					});
				}
			});
		}

		public void setupButton() {

			editBtn.setOnMouseClicked(event -> {
				if (user_id.getText().isEmpty()) {
					alertMessage.errorMessage("Please select the object you want to change information!");
					return;
				}
				if (user_name.getText().isEmpty() || user_email.getText().isEmpty()
						|| user_phone.getText().isEmpty() || user_address.getText().isEmpty()
						|| user_position.getText().isEmpty() || user_username.getText().isEmpty()
						|| user_password.getText().isEmpty()) {
					alertMessage.errorMessage("Please fill all blank fields!");
					return;
				} else {
					int temp = 0;
					Role role = DAO_Role.getInstance().selectByCondition2(" User_Id = " + user_id.getText());
					
					if(r1.isSelected()) {
						role.setRole1(true);
						
					}
					else {
						role.setRole1(false);
					}
					if(r2.isSelected()) role.setRole2(true);
					else {
						role.setRole2(false);
					}
					if(r3.isSelected()) role.setRole3(true);
					else {
						role.setRole3(false);
					}
					if(r4.isSelected()) role.setRole4(true);
					else {
						role.setRole4(false);
					}
					
					if(r5.isSelected()) role.setRole5(true);
					else {
						role.setRole5(false);
					}
					
					
					
					Boolean status = true;
					if(em_status.getText().equals("Active")) {
						
					}
					else status = false;
					role.setPosition(user_position.getText());
					
					
					LocalDate slDate = LocalDate.now();
					Date birth = Date.valueOf(slDate);
					User user = new User(Integer.parseInt(user_id.getText()),
							user_username.getText(), user_password.getText(), user_email.getText(),
							user_name.getText(), user_address.getText(), birth,user_phone.getText(),null,status);
					DAO_User.getInstance().Update(user);
					//
					DAO_Role.getInstance().Update(role);
					reloadTable(); 
					alertMessage.successMessage("Information changed successfully!");
					
					
					
					if(role.getUserId() == CurrentUser.userId) {
					
					if(!role.isRole1()) agencyBtn.setDisable(true);
					else agencyBtn.setDisable(false);
					if(!role.isRole2()) goodsBtn.setDisable(true);
					else goodsBtn.setDisable(false);
					if(!role.isRole3()) exportBtn.setDisable(true);
					else exportBtn.setDisable(false);
					if(!role.isRole4()) reportBtn.setDisable(true);
					else reportBtn.setDisable(false);
					if(!role.isRole5()) settingBtn.setDisable(true);
					else settingBtn.setDisable(false);
										
					
					
					
					CurrentUser.userAddress = user.getAddress();
					CurrentUser.userBirth = user.getBirthDate();
					CurrentUser.userEmail = user.getEmail();
					CurrentUser.userGender = user.getGender();
					CurrentUser.userId = user.getUser_Id();
					CurrentUser.userName = user.getName();
					CurrentUser.userPassword = user.getPassword();
					CurrentUser.userSDT = user.getSDT()	;
					}
				}
			});

			addBtn.setOnMouseClicked(event -> {
				LocalDate slDate = LocalDate.of(2004,5,8);
				Date birthdate = Date.valueOf(slDate);
				
				User user = new User(0, null, null, null, null, null, birthdate, null,null,true);
				
				
				
				
				
				
				DAO_User.getInstance().Add(user);
				reloadTable();
Role role = new Role(0,false,false,false,false,false,"",userList.getLast().getUser_Id());
				
				DAO_Role.getInstance().Add(role);
				User p = userList.getLast();
				user_id.setText(String.valueOf(p.getUser_Id()));
				user_name.setText("...");
				user_email.setText("...");
				user_phone.setText("...");
				user_address.setText("...");
				
				user_username.setText("...");	
				user_password.setText("...");
				
				user_position.setText(role.getPosition());
				if(role.isRole1()) r1.setSelected(true);
				else {
					r1.setSelected(false);
				}
				if(role.isRole2()) r2.setSelected(true);
				else r2.setSelected(false);
				if(role.isRole3()) r3.setSelected(true);
				else r3.setSelected(false);
				if(role.isRole4()) r4.setSelected(true);
				else r4.setSelected(false);
				if(role.isRole5()) r5.setSelected(true);
				else r5.setSelected(false);
				
				if(p.getStatus()) {
					 em_status.setValue(statusList.get(0));
				}
				else {
					em_status.setValue(statusList.get(1));
				}
				
				
				alertMessage.successMessage("New object added successfully!");
			});

			
		}
}
