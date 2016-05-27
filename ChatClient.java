//================================================================
// Name         : Nidhi Patel
// SID          : 31379144
// Course       : IT114
// Section      : 452
// Instructor 	: Maura Deek
// Assignment # : Programming Assignment 5
// Date        	:  04/22/2016
// Description  : This  is the client class for a
				 // a client/server chat application
//=================================================================
import java.io.*;
import java.net.*;

public class ChatClient
{
	private Socket socket;
	private BufferedReader  input;
	private PrintWriter output;
	String message,
		   server;
	int port;

	public ChatClient(String serverHost, int portNum)
	{
		try
		{
			server = serverHost;
			port = portNum;
			socket = new Socket(server, port);
		}
		catch(IOException ex)
		{
			System.out.println(ex);
		}
	}
	public void outputToServer(String message)
	{
		try
		{
			output = new PrintWriter(socket.getOutputStream(), true);
			output.println(message);
		}
		catch(IOException ex)
		{
			System.out.println(ex);
		}
	}

	public String inputFromServer()
	{
		try
		{
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			message = input.readLine();
		}
		catch(IOException ex)
		{
			System.out.println(ex);
		}
		return message;
	}
}