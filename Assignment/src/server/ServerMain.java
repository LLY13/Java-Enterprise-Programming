package server;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ServerMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
    	URL fxmlUrl = this.getClass()
    			.getClassLoader()
    			.getResource("server/ServerUI.fxml");
        Pane mainPane = FXMLLoader.<Pane>load(fxmlUrl);
        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();     
	}
	
	
	
	public static void main(String[] args) {
        Application.launch(args);
    }

}
