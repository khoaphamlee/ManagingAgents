package application;
	
import controllers.LoginScreenController;
import controllers.MainScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	/*public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}*/
	
	public void start(Stage primaryStage) throws Exception {
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/views/LoginScreen.fxml"));
        Parent loginRoot = loginLoader.load();
        
        LoginScreenController loginController = loginLoader.getController();
        
        MainScreenController mainScreenController = new MainScreenController();
        
        loginController.setMainScreenController(mainScreenController);
        primaryStage.setTitle("JavaFX App");
        primaryStage.setScene(new Scene(loginRoot));
        primaryStage.show();
    }

	public static void main(String[] args) {
		launch(args);
	}
}
