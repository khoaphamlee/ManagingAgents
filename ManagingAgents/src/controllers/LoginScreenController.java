package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Database.JDBCUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginScreenController implements Initializable {

    @FXML
    private MFXButton loginBtn;

    @FXML
    private AnchorPane login_form;

    @FXML
    private MFXPasswordField password;

    @FXML
    private MFXTextField username;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    @FXML
	private Label recoveryL;
    private double x = 0;
    private double y = 0;
    
    private MainScreenController mainScreenController = new MainScreenController();
    
    
    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }
    
    public void loginAdmin() {
    	String sql = "SELECT * FROM USER WHERE Username = ? and Password = ?";
    	
    	connect = JDBCUtil.getConnection();
    	
    	try {
    		prepare = connect.prepareStatement(sql);
    		prepare.setString(1, username.getText());
    		prepare.setString(2, password.getText());
    		
    		result = prepare.executeQuery();
    		Alert alert;
    		
    		if (username.getText().isEmpty() || password.getText().isEmpty()) {
    			alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Please fill all blank fields");
    			alert.showAndWait();
    		}
    		else {
    			if (result.next()) {
    				alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("Information Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Successfully Login");
        			alert.showAndWait();
        			CurrentUser.userId = result.getInt("User_Id");
					
					CurrentUser.userName = result.getString("UserName");
					CurrentUser.userPassword = result.getString("Password");
					CurrentUser.userEmail = result.getString("Email");
					CurrentUser.userName = result.getString("Name");
					CurrentUser.userAddress = result.getString("Address");
					CurrentUser.userBirth = result.getDate("BirthDate");
					CurrentUser.userSDT = result.getString("SDT");
					CurrentUser.userGender = result.getString("Gender");
        			loginBtn.getScene().getWindow().hide();
        			
        			String loggedInUsername = username.getText();
        			
        			if (mainScreenController != null) {
        			    mainScreenController.setupUsernameLabel(loggedInUsername);
        			} else {
        			    System.out.println("MainScreenController is null!");
        			}
        			
        			//mainScreenController.setupUsernameLabel(loggedInUsername);
        			Parent root = FXMLLoader.load(getClass().getResource("/views/MainScreen.fxml"));
        			Stage stage = new Stage();
        			Scene scene = new Scene(root);
        			
        			root.setOnMousePressed((MouseEvent event) -> {
        				x = event.getSceneX();
        				y = event.getSceneY();
        			});
        			
        			root.setOnMouseDragged((MouseEvent event) -> {
        				stage.setX(event.getScreenX() - x);
        				stage.setY(event.getScreenY() - y);
        			});
        				
        			stage.setTitle("Managing Agents");
        			stage.getIcons().add(new Image("/images/mylogo.png"));
        			stage.setScene(scene);
        			//stage.setFullScreen(true);
        			stage.show();
    			}
    			else {
    				alert = new Alert(AlertType.ERROR);
        			alert.setTitle("Error Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Wrong Username/Password");
        			alert.showAndWait();
    			}
    			
    		}
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	//mainScreenController.setupUsernameLabel(username.getText());
    }
    
   /* public void sendUsernameToMainScreen(String username) {
    	 FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainScreen.fxml"));
    	    Parent root = null;
    	    try {
    	        root = loader.load();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }

    	    MainScreenController mainScreenController = loader.getController();
    	    mainScreenController.setupScreen();

    	    Stage stage = new Stage();
    	    Scene scene = new Scene(root);
    	    stage.initStyle(StageStyle.TRANSPARENT); 
    	    stage.setScene(scene);
    	    stage.show();
    }*/
    public void initialize(URL url, ResourceBundle rb) {
    	recoveryL.setOnMouseClicked(event->{
			if (event.getSource() == recoveryL) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/RecoveryAccountDialog.fxml"));
					Parent root = (Parent) loader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setTitle("Managing Agents");
					stage.getIcons().add(new Image("/images/mylogo.png"));
					stage.setResizable(false);
					stage.showAndWait();
					
				} catch (Exception e) {
					AlertMessage alertMessage = new AlertMessage();
					alertMessage.errorMessage("Can't load the scene!");
					e.printStackTrace();
					return;
				}
			}
		});
    }
}
