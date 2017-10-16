package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServerThread extends Thread{
	DataInputStream dis;
	DataOutputStream dos;
	
	Socket remoteClient;
	ServerController server;
	ObservableList<String> nickNames = FXCollections.observableArrayList();
	
	String nickName;
	
	ArrayList<ServerThread> connectedClients; // keep track of all the other clients connected to the Server
	
	public ServerThread(Socket remoteClient, ServerController server, ArrayList<ServerThread> connectedClients)
	{
		this.remoteClient = remoteClient;
		this.connectedClients = connectedClients;
		try {
			this.dis = new DataInputStream(remoteClient.getInputStream());
			this.dos = new DataOutputStream(remoteClient.getOutputStream());
			this.server = server;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void updateList(){
		Platform.runLater(new Runnable() {
		    public void run() {
		    	nickNames.clear();
				for(ServerThread item : connectedClients){
					nickNames.add(item.nickName);
				}
		    	server.listView.setItems(nickNames);
		    }
		});
	}
	
	
	
	
	
	
	
	
	public void run()
	{
		while(true) // main protocol decode loop
		{
			try {
				int mesgType = dis.readInt(); // read the type of message from the client (must be an integer)
				System.err.println(mesgType);
				// decode the message type based on the integer sent from the client
				switch(mesgType)
				{
					case ServerConstants.CHAT_MESSAGE:
						String data = dis.readUTF();
						System.err.println(data);
						for(ServerThread client: connectedClients)
						{
							if(client.equals(this)) // don't send the message to the client that sent the message 
							{
								if(this.nickName == "" || this.nickName ==null){
									this.nickName = "" + remoteClient.getInetAddress()+":"+remoteClient.getPort();
								}
								server.getTextArea().appendText(this.nickName + ": "+ data + "\n");
							}
						}
						for(ServerThread client: connectedClients)
						{
							client.dos.writeInt(ServerConstants.CHAT_BROADCAST);
							if(this.nickName == "" || this.nickName ==null){
								this.nickName = "" + remoteClient.getInetAddress()+":"+remoteClient.getPort();
							}
							client.dos.writeUTF(this.nickName + ": "+ data);
						}
						
						break;
					case ServerConstants.REGISTER_CLIENT:
						//develop code to handle new client registrations
						nickName = dis.readUTF();
						if(this.nickName == "" || this.nickName ==null){
							this.nickName = "" + remoteClient.getInetAddress()+":"+remoteClient.getPort();
						}
						updateList();
						//broadcast this registration to all other clients connected to the server (similar to the CHAT_BROADCAST message sent to each client above)
						server.getTextArea().appendText("Welcome " + nickName + "\n");
						for(ServerThread client: connectedClients)
						{
							client.dos.writeInt(ServerConstants.CHAT_BROADCAST);
							client.dos.writeUTF("Welcome " + nickName);
						}
						// give this nickName to all other clients
						for(ServerThread otherClient: connectedClients)
						{
							if(!otherClient.equals(this)) // don't send the message to the client that sent the message in the first place
							{
								otherClient.dos.writeInt(ServerConstants.REGISTER_CLIENT);
								otherClient.dos.writeUTF(nickName);
								this.dos.writeInt(ServerConstants.REGISTER_CLIENT);
								this.dos.writeUTF(otherClient.nickName);
							}
						}

						break;
					case ServerConstants.PRIVATE_MESSAGE:
						String privateData = dis.readUTF();
						String[] privateDatas = privateData.split(":");
						for(ServerThread client: connectedClients)
						{
							if(privateDatas[0].equals("/" + client.nickName)) // don't send the message to the client that sent the message in the first place
							{
								client.dos.writeInt(ServerConstants.PRIVATE_MESSAGE);
								client.dos.writeUTF("/" + nickName + ": " + privateDatas[1]);
								break;
							}
						}
						break;
					case ServerConstants.EXIT_MESSAGE:
						String exitName = dis.readUTF();
						for(ServerThread otherClient: connectedClients)
						{
							if(!otherClient.equals(exitName)) // don't send the message to the client that sent the message in the first place
							{
								otherClient.dos.writeInt(ServerConstants.EXIT_MESSAGE);
								otherClient.dos.writeUTF(exitName);
							}
						}
						Platform.runLater(new Runnable() {
							public void run() {
								nickNames.remove(exitName);
								server.listView.setItems(nickNames);
							}
						});
						break;
					case ServerConstants.DRAW_BROADCAST_RECT:
						String rect = dis.readUTF();
						for(ServerThread otherclient: connectedClients)
						{
							if(!otherclient.equals(this)){
								otherclient.dos.writeInt(ServerConstants.DRAW_BROADCAST_RECT);
								otherclient.dos.writeUTF(rect);
							}
						}
						break;
					case ServerConstants.DRAW_BROADCAST_OVAL:
						String oval = dis.readUTF();
						for(ServerThread otherclient: connectedClients)
						{
							if(!otherclient.equals(this)){
								otherclient.dos.writeInt(ServerConstants.DRAW_BROADCAST_OVAL);
								otherclient.dos.writeUTF(oval);
							}
						}
						break;
					case ServerConstants.DRAW_BROADCAST_LINE_START:
						String start = dis.readUTF();
						for(ServerThread otherclient: connectedClients)
						{
							if(!otherclient.equals(this)){
								otherclient.dos.writeInt(ServerConstants.DRAW_BROADCAST_LINE_START);
								otherclient.dos.writeUTF(start);
							}
						}
						break;
					case ServerConstants.DRAW_BROADCAST_LINE_END:
						String end = dis.readUTF();
						for(ServerThread otherclient: connectedClients)
						{
							if(!otherclient.equals(this)){
								otherclient.dos.writeInt(ServerConstants.DRAW_BROADCAST_LINE_END);
								otherclient.dos.writeUTF(end);
							}
						}
						break;
					case ServerConstants.DRAW_SAVE:
						InputStream inputStream = remoteClient.getInputStream();
						FileOutputStream fileOutputStream = new FileOutputStream(
								"D://imageServer/snapshot" + new Date().getTime() + ".png");
						byte[] buf = new byte[1024];
				        int length = 0;
				        while((length = inputStream.read(buf))!=-1){
				        	fileOutputStream.write(buf);
				        }
				        fileOutputStream.close();
						
						
				}			
				
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return;
			}
		}
	}
}
