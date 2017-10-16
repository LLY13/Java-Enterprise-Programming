package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ServerController implements Runnable{
	ServerSocket serverSocket;
	ArrayList <ServerThread> connectedClients = new ArrayList<ServerThread>();
	
	
	public ServerController() {
		 // construct ServerSocket
		try {
			serverSocket = new ServerSocket(5000);
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		Thread thread = new Thread(this);
    	thread.start();
	
	}

    @FXML
    private TextArea textArea;

    @FXML
    private TextField textFild;

    @FXML
    private Button btnSend;

    @FXML ListView<String> listView;
    
    @FXML
    void btnSendAcion(ActionEvent event) {
    	
    }
    
	public TextArea getTextArea() {
		return textArea;
	}
	public void setTextArea(TextArea textArea) {
		this.textArea = textArea;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try
		{
			while(true) // keep accepting new clients
			{
				Socket remoteClient = serverSocket.accept(); // block and wait for a connection from a client
								
				// construct a new server thread, to handle each client socket
				ServerThread st = new ServerThread(remoteClient,this,connectedClients);
				st.start();
				
				connectedClients.add(st);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}

