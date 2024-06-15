package controllers;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;


import Data_Access_Object.DAO_User;
import email.EmailProperty;
import email.EmailService;
import email.IJavaMail;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import models.User;

public class RecoveryAccountDialogController implements Initializable{
	@FXML
	private MFXButton cancelBtn;

	@FXML
	private MFXButton okBtn;
	
	@FXML
	private MFXTextField emailT;
	
	Stage window;
	
	AlertMessage alertMessage = new AlertMessage();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		okBtn.setOnMouseClicked(event -> {
			
				if (emailT.getText().isEmpty() ) {
					alertMessage.errorMessage("Please fill all blank fields!");
					return;
				} else {
					ArrayList<User> us =  DAO_User.getInstance().selectByCondition(" Status = true");
					System.out.print(us.size());
					for(int i = 0;i< us.size();i++) {
						
						if(us.get(i).getEmail().equals(emailT.getText())) {
							Random random = new Random();
					        
					        
					        int randomNumber = random.nextInt(900000) + 100000;
					        IJavaMail emailService = new EmailService();
					        EmailProperty.receive = emailT.getText();
					        EmailProperty.codeR = randomNumber;
					        try {
								FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/EnterEmailDialog.fxml"));
								Parent root = (Parent) loader.load();
								Stage stage = new Stage();
								stage.setScene(new Scene(root));
								stage.initModality(Modality.APPLICATION_MODAL);
								stage.setTitle("Managing Agents");
								stage.getIcons().add(new Image("/images/mylogo.png"));
								stage.showAndWait();
								
							} catch (Exception e) {
								alertMessage.errorMessage("Can't load the scene!");
								e.printStackTrace();
								return;
							}
					        
					        
						window = (Stage) okBtn.getScene().getWindow();
						window.close();
						return;
						}
					}
				}
			alertMessage.errorMessage("Email does not exist in the database!" );
			
		});

		cancelBtn.setOnMouseClicked(event -> {
			window = (Stage) cancelBtn.getScene().getWindow();
			window.close();
		});
		
	}
	
	
}
