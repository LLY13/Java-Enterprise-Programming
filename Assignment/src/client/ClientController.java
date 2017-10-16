package client;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import server.ServerConstants;
import server.ServerThread;

public class ClientController implements Runnable{
	
	// define the socket and io streams
	boolean num = true;
	static Socket client;
	DataInputStream dis;
	static DataOutputStream dos;
	//InputStream inputStream;
	//OutputStream outputStream;
	static String nickName = "";
	ObservableList<String> nickNames = FXCollections.observableArrayList();
	GraphicsContext gc;
	
	Color color = Color.BLACK;
	int drawSize = 1;
	boolean line = true;
	boolean rect = false;
	boolean oval = false;
	double x1, x2, y1, y2;
	
	
	
	public ClientController() {
		try {
			client = new Socket("localhost",5000);
			dis = new DataInputStream(client.getInputStream());
			dos = new DataOutputStream(client.getOutputStream());
			
			
			// define a thread to take care of messages sent from the server
			Thread clientThread = new Thread(this);
			clientThread.start();
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	 @FXML
	    void mousePressed(MouseEvent event) throws IOException {
		 	gc = canvas.getGraphicsContext2D();
			gc.setStroke(color);
			gc.setLineWidth(drawSize);
			if(line){
			 	gc.beginPath();
			 	gc.lineTo(event.getX(), event.getY());
			 	try {
					dos.writeInt(ServerConstants.DRAW_BROADCAST_LINE_START);
					dos.writeUTF("" + event.getX() + "," + event.getY()
					+ "," + color + "," + drawSize);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 	gc.stroke();
			}
			if(rect || oval){
				x1 = event.getX();
				y1 = event.getY();
			}
	    }
	 
	 @FXML
	    void mouseDragged(MouseEvent event) throws IOException {
		 if(line){
		 	gc = canvas.getGraphicsContext2D();
			gc.setStroke(color);
			gc.setLineWidth(drawSize);
			gc.lineTo(event.getX(), event.getY());
			try {
				dos.writeInt(ServerConstants.DRAW_BROADCAST_LINE_END);
				dos.writeUTF("" + event.getX() + "," + event.getY()
				+ "," + color + "," + drawSize);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gc.stroke();
			}
	    }
	 
	 @FXML
	    void mouseReleased(MouseEvent event){
			 x2 = event.getX();
			 y2 = event.getY();
			 gc.setFill(color);
			 //calculate the coordinate of upper left
			 double x3, y3;
			 x3 = Math.min(x1, x2);
			 y3 = Math.min(y1, y2);
			if(rect){
				gc.fillRect(x3, y3, Math.abs(x2-x1), Math.abs(y2-y1));
				try {
					dos.writeInt(ServerConstants.DRAW_BROADCAST_RECT);
					dos.writeUTF("" + x3 + "," + y3 + "," + Math.abs(x2-x1) + "," + Math.abs(y2-y1)
					+ "," + color + "," + drawSize);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(oval){
				gc.fillOval(x3, y3, Math.abs(x2-x1), Math.abs(y2-y1));
				try {
					dos.writeInt(ServerConstants.DRAW_BROADCAST_OVAL);
					dos.writeUTF("" + x3 + "," + y3 + "," + Math.abs(x2-x1) + "," + Math.abs(y2-y1)
					+ "," + color + "," + drawSize);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    }
	 
	@FXML
    private Canvas canvas;
	

    @FXML
    private TextArea textArea;

    @FXML
    private TextField textFild;

    @FXML
    private Button btnSend;

    @FXML
    private ListView<String> listView;
    
    @FXML
    private TextField nickname;

    @FXML
    private Button btnConfirm;

    @FXML
    void btnConfirmAction(ActionEvent event) {
    	if(num){
	    	nickName = nickname.getText();
	    	if(nickName == null || nickName.trim().isEmpty()){
	    		nickName = client.getInetAddress()+":"+client.getPort();
	    	}
			try {
				dos.writeInt(server.ServerConstants.REGISTER_CLIENT);
				dos.writeUTF(nickName);
				dos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			num = false;
    	}
    }

    @FXML
    void btnSendAcion(ActionEvent event) {
    	try {
			if(textFild.getText().charAt(0) != '/'){
				dos.writeInt(server.ServerConstants.CHAT_MESSAGE); // determine the type of message to be sent
				dos.writeUTF(textFild.getText()); // message payload
				textFild.setText("");
				dos.flush(); // force the message to be sent (sometimes data can be buffered)
			}else{
				dos.writeInt(server.ServerConstants.PRIVATE_MESSAGE);
				dos.writeUTF(textFild.getText());
				textArea.appendText(textFild.getText());
				textFild.setText("/" + listView.getSelectionModel().getSelectedItem() + ":  ");
				dos.flush(); // force the message to be sent (sometimes data can be buffered)
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
    }
    // delete closed client
    static void closeWindow(){
    	try {
    		if(nickName == "" || nickName ==null){
    			nickName = "" + client.getInetAddress()+":"+client.getPort();
    		}
			dos.writeInt(server.ServerConstants.EXIT_MESSAGE);
			dos.writeUTF(nickName);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void privateChat(MouseEvent event) {
    	textFild.setText("/" + listView.getSelectionModel().getSelectedItem() + ":  ");
    }
    


    
    public void run()
	{
		while(true)
		{
			try {
				int messageType = dis.readInt(); // receive a message from the server, determine message type based on an integer
				// decode message and process
				switch(messageType)
				{
					case server.ServerConstants.CHAT_BROADCAST:
						textArea.appendText(dis.readUTF()+"\n");
						break;
					case server.ServerConstants.REGISTER_CLIENT:
						String name = dis.readUTF();
						Platform.runLater(new Runnable() {
						    public void run() {
						    	nickNames.add(name);
						    	listView.setItems(nickNames);
						    }
						});
						break;
					case server.ServerConstants.PRIVATE_MESSAGE:
						textArea.appendText(dis.readUTF()+"\n");
						break;
					case server.ServerConstants.EXIT_MESSAGE:
						String deleteName = dis.readUTF();
						Platform.runLater(new Runnable() {
						    public void run() {
						    	nickNames.remove(deleteName);
						    	listView.setItems(nickNames);
						    }
						});
						break;
					case server.ServerConstants.DRAW_BROADCAST_RECT:
						String rect = dis.readUTF();
						String[] rects = rect.split(",");
						gc = canvas.getGraphicsContext2D();
						String[] colorString = rects[4].split("x");
						Color col = Color.web(colorString[1]);
						gc.setFill(col);
						gc.setLineWidth(Integer.parseInt(rects[5]));
						gc.fillRect(Double.parseDouble(rects[0]), Double.parseDouble(rects[1]), 
								Double.parseDouble(rects[2]), Double.parseDouble(rects[3]));
					 	break;
					case server.ServerConstants.DRAW_BROADCAST_OVAL:
						String oval = dis.readUTF();
						String[] ovals = oval.split(",");
						gc = canvas.getGraphicsContext2D();
						String[] colorString1 = ovals[4].split("x");
						Color col1 = Color.web(colorString1[1]);
						gc.setFill(col1);
						gc.setLineWidth(Integer.parseInt(ovals[5]));
						gc.fillOval(Double.parseDouble(ovals[0]), Double.parseDouble(ovals[1]), 
								Double.parseDouble(ovals[2]), Double.parseDouble(ovals[3]));
					 	break;
					case server.ServerConstants.DRAW_BROADCAST_LINE_START:
						String start = dis.readUTF();
						String starts[] = start.split(",");
						gc = canvas.getGraphicsContext2D();
						String[] colorString2 = starts[2].split("x");
						Color col2 = Color.web(colorString2[1]);
						gc.beginPath();
						gc.setStroke(col2);
						gc.setLineWidth(Integer.parseInt(starts[3]));
						gc.lineTo(Double.parseDouble(starts[0]), Double.parseDouble(starts[1]));
						gc.stroke();
						break;
					case server.ServerConstants.DRAW_BROADCAST_LINE_END:
						String end = dis.readUTF();
						String ends[] = end.split(",");
						gc = canvas.getGraphicsContext2D();
						String[] colorString3 = ends[2].split("x");
						Color col3 = Color.web(colorString3[1]);
						gc.setStroke(col3);
						gc.setLineWidth(Integer.parseInt(ends[3]));
						gc.lineTo(Double.parseDouble(ends[0]), Double.parseDouble(ends[1]));
						gc.stroke();
						break;
						
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
	}
    
    @FXML
    void btnColor1(ActionEvent event) {
    	color =  Color.BLACK;
    }

    @FXML
    void btnColor10(ActionEvent event) {
    	color = Color.web("#beee88");
    }

    @FXML
    void btnColor11(ActionEvent event) {
    	color = Color.web("#ffb9fc");
    }

    @FXML
    void btnColor12(ActionEvent event) {
    	color = Color.web("#f8f018");
    }

    @FXML
    void btnColor13(ActionEvent event) {
    	color = Color.web("#1cab42");
    }

    @FXML
    void btnColor14(ActionEvent event) {
    	color = Color.web("#ffffff");
    }

    @FXML
    void btnColor2(ActionEvent event) {
    	color = Color.web("#6b5c5c");
    }

    @FXML
    void btnColor3(ActionEvent event) {
    	color = Color.web("#ddd9d9");
    }

    @FXML
    void btnColor4(ActionEvent event) {
    	color = Color.web("#f40000");
    }

    @FXML
    void btnColor5(ActionEvent event) {
    	color = Color.web("#001dff");
    }

    @FXML
    void btnColor6(ActionEvent event) {
    	color = Color.web("#03fb52");
    }

    @FXML
    void btnColor7(ActionEvent event) {
    	color = Color.web("#ff9203");
    }

    @FXML
    void btnColor8(ActionEvent event) {
    	color = Color.web("#ff00f6");
    }

    @FXML
    void btnColor9(ActionEvent event) {
    	color = Color.web("#02b6f8");
    }

    //set the size of pencil
    @FXML
    void btnSizeMiuns(ActionEvent event) {
    	drawSize--;
    	if(drawSize <= 1){
    		drawSize = 1;
    	}
    }
    
    @FXML
    void btnEraser(ActionEvent event) {
    	color = Color.WHITE;
    }

    @FXML
    void btnSizePlus(ActionEvent event) {
    	drawSize++;
    }
    
    @FXML
    void btnClear(ActionEvent event) {
    	gc = canvas.getGraphicsContext2D();
    	gc.setFill(Color.WHITE);
    	gc.fillRect(0, 0, 1000, 1000);
		try {
			dos.writeInt(ServerConstants.DRAW_BROADCAST_RECT);
			dos.writeUTF("" + 0 + "," + 0 + "," + 1000 + "," + 1000
			+ "," + Color.WHITE + "," + drawSize);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @FXML
    void btnLine(ActionEvent event) {
    	line = true;
    	rect = false;
    	oval = false;
    }

    @FXML
    void btnRect(ActionEvent event) {
    	line = false;
    	rect = true;
    	oval = false;
    }
    
    @FXML
    void btnOval(ActionEvent event) {
    	line = false;
    	rect = false;
    	oval = true;
    }
    
    //save image to server
    @FXML
    void btnSave(ActionEvent event) throws IOException {
    	dos.writeInt(server.ServerConstants.DRAW_SAVE);
    	String fileName = "D://image/snapshot" + new Date().getTime() + ".png";
    	File file = new File(fileName);
    	WritableImage writableImage = new WritableImage(768, 431);
        canvas.snapshot(null, writableImage);
        RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
        ImageIO.write(renderedImage, "png", file);
        
        OutputStream outputStream = client.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] buf = new byte[1024];
        int length = 0;
        while((length = fileInputStream.read(buf))!=-1){
        	outputStream.write(buf);
        }
        fileInputStream.close();
    }

}

