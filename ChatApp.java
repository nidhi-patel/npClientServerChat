//================================================================
// Name         : Nidhi Patel
// SID          : 31379144
// Course       : IT114
// Section      : 452
// Instructor 	: Maura Deek
// Assignment # : Programming Assignment 5
// Date        	:  04/22/2016
// Description  : This program is a client/server chat application
//=================================================================
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import java.io.*;
import java.net.*;

public class ChatApp extends Application
{

	public static void main(String[]args)
	{
		launch(args);
	}
	public void start(Stage primaryStage) throws Exception
	{
		/***************SERVER STAGE************************/
		Stage serverStage = new Stage();
		serverStage.setTitle("Sever");
		serverStage.setResizable(false);
		GridPane serverLayout = new GridPane();

		serverLayout.setPadding(new Insets(15));
		serverLayout.setHgap(5);
		serverLayout.setVgap(5);
		serverLayout.getColumnConstraints().add(new ColumnConstraints(20));
		serverLayout.getColumnConstraints().add(new ColumnConstraints(20));

		TextArea serverDisplay = new TextArea();
		serverDisplay.setEditable(false);

		TextField serverInput = new TextField();
		serverInput.setEditable(true);

		serverLayout.add(serverDisplay,0,0,3,1);
		serverLayout.add(serverInput,0,1,3,1);

		Scene server = new Scene(serverLayout);
		serverStage.setScene(server);
		serverStage.show();

		/***************CLIENT STAGE************************/
		Stage clientStage = new Stage();
		clientStage.setTitle("Client");
		clientStage.setResizable(false);
		GridPane clientLayout = new GridPane();

		clientLayout.setPadding(new Insets(15));
		clientLayout.setHgap(5);
		clientLayout.setVgap(5);
		clientLayout.getColumnConstraints().add(new ColumnConstraints(20));
		clientLayout.getColumnConstraints().add(new ColumnConstraints(20));

		TextArea clientDisplay = new TextArea();
		clientDisplay.setEditable(false);

		TextField clientInput = new TextField();
		clientInput.setEditable(true);

		clientLayout.add(clientDisplay,0,0,3,1);
		clientLayout.add(clientInput,0,1,3,1);

		Scene client = new Scene(clientLayout);
		clientStage.setScene(client);
		clientStage.show();



 		ChatServer newServer = new ChatServer(8000);

		ChatClient newClient= new ChatClient("localhost",8000);

		newServer.acceptConn();

		serverInput.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			public void handle(KeyEvent keyPressed){
				if(keyPressed.getCode() == KeyCode.ENTER){
					newServer.outputToClient(serverInput.getText());
					serverDisplay.appendText("\nMe: " + serverInput.getText());
					clientDisplay.appendText("\nServer: " + newClient.inputFromServer());
					serverInput.setText("");
				}
			}
		});

		clientInput.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			public void handle(KeyEvent keyPressed){
				if(keyPressed.getCode() == KeyCode.ENTER){
					newClient.outputToServer(clientInput.getText());
					clientDisplay.appendText("\nMe: " + clientInput.getText());
					serverDisplay.appendText("\nClient: " + newServer.inputFromClient());
					clientInput.setText("");
				}
			}
		});

	}
}










