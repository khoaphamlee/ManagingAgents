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
import io.github.palexdev.materialfx.controls.MFXPasswordField;
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

public class NewPasswordDialogController implements Initializable{
	@FXML
	private MFXButton cancelBtn;

	@FXML
	private MFXButton okBtn;
	
	@FXML
	private MFXPasswordField password1 , password2;
	
	Stage window;
	
	AlertMessage alertMessage = new AlertMessage();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		okBtn.setOnMouseClicked(event -> {
			
				if (password1.getText().isEmpty() || password2.getText().isEmpty()  || !password1.getText().equals(password2.getText())) {
					alertMessage.errorMessage("Please fill all blank fields and 2 password must be same!");
					return;
				} else {
					ArrayList<User> us =  DAO_User.getInstance().selectByCondition(" Status = true");
					System.out.print(us.size());
					for(int i = 0;i< us.size();i++) {
						
						if(us.get(i).getEmail().equals(EmailProperty.receive)) {
							us.get(i).setPassword(password1.getText());
							DAO_User.getInstance().Update(us.get(i));
							alertMessage.successMessage("Changed password!");
						}
				}
				}
				window = (Stage) okBtn.getScene().getWindow();
				window.close();
			
		});

		cancelBtn.setOnMouseClicked(event -> {
			window = (Stage) cancelBtn.getScene().getWindow();
			window.close();
		});
		
	}
	
	
}
