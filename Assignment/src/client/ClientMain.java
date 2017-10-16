package client;

import java.net.URL;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ClientMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
    	URL fxmlUrl = this.getClass()
    			.getClassLoader()
    			.getResource("client/ClientUI.fxml");
        Pane mainPane = FXMLLoader.<Pane>load(fxmlUrl);
        primaryStage.setTitle("Client");
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();    
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                ClientController.closeWindow();
            }
        }); 
	}
	
	public static void main(String[] args) {
        Application.launch(args);
    }

}
