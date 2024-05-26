package application;
	
<<<<<<< HEAD
import javafx.application.Application;
import javafx.stage.Stage;
=======
import controllers.LoginScreenController;
import controllers.MainScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
<<<<<<< HEAD
	public void start(Stage primaryStage) {
=======
	/*public void start(Stage primaryStage) {
>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
<<<<<<< HEAD
	}
	
=======
	}*/
	
	public void start(Stage primaryStage) throws Exception {
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/views/LoginScreen.fxml"));
        Parent loginRoot = loginLoader.load();
        
        LoginScreenController loginController = loginLoader.getController();
        
        MainScreenController mainScreenController = new MainScreenController();
        
        loginController.setMainScreenController(mainScreenController);
        
        primaryStage.setScene(new Scene(loginRoot));
        primaryStage.show();
    }

>>>>>>> 52fe9a22f1c598089616153029f388f2f7a639c3
	public static void main(String[] args) {
		launch(args);
	}
}
