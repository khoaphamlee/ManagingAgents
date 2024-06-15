package controllers;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


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

public class EnterEmailDialogController implements Initializable{
	@FXML
	private MFXButton cancelBtn;

	@FXML
	private MFXButton okBtn;
	
	@FXML
	private MFXButton resendBtn;
	
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
					if(Integer.parseInt(emailT.getText()) == EmailProperty.codeR) {
						try {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/NewPasswordDialog.fxml"));
							Parent root = (Parent) loader.load();
							Stage stage = new Stage();
							stage.setScene(new Scene(root));
							stage.initModality(Modality.APPLICATION_MODAL);
							stage.setTitle("Managing Agents");
							stage.getIcons().add(new Image("/images/mylogo.png"));
							stage.setResizable(false);
							stage.showAndWait();
							
						} catch (Exception e) {
							alertMessage.errorMessage("Can't load the scene!");
							e.printStackTrace();
							return;
						}
					}
					else {
						alertMessage.errorMessage("Code is expired or incorrect!");
					}
					
				}
			
			window = (Stage) okBtn.getScene().getWindow();
			window.close();
		});

		cancelBtn.setOnMouseClicked(event -> {
			window = (Stage) cancelBtn.getScene().getWindow();
			window.close();
		});
		
		
		resendBtn.setOnMouseClicked(event -> {
			
			Random random = new Random();
			int randomNumber = random.nextInt(900000) + 100000;
			IJavaMail emailService2 = new EmailService();
			emailService2.send(EmailProperty.receive, "Mã khôi phục tài khoản của bạn", "" + randomNumber	);
	        EmailProperty.codeR = randomNumber;
	        TimerTask task = new TimerTask() {
	            @Override
	            public void run() {
	                System.out.println("Sự kiện đã xảy ra sau 5 phút.");
	                EmailProperty.codeR =0;
	            }
	        };

	        
	        int delay = 5 * 60 * 1000; // 5 phút

	        Timer timer = new Timer();
	        timer.schedule(task, delay);
		});
		
		IJavaMail emailService = new EmailService();
		emailService.send(EmailProperty.receive, "Mã khôi phục tài khoản của bạn ", "" + EmailProperty.codeR	);
		
		

        
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Sự kiện đã xảy ra sau 5 phút.");
                EmailProperty.codeR =0;
            }
        };

        
        int delay = 5 * 60 * 1000; // 5 phút

        Timer timer = new Timer();
        timer.schedule(task, delay);
	}
	
	
}
