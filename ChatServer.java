//==============================================================================
// Name         : Nidhi Patel
// Date        	:  04/22/2016
// Description  : This  is the server class for a client/server chat application
//==============================================================================
import java.io.*;
import java.net.*;

public class ChatServer
{
	private ServerSocket server;
	private Socket socket;
	private BufferedReader  input;
	private PrintWriter output;
	String message;
	int port;

	public ChatServer(int portNum)
	{
		try
		{
			port = portNum;
			server = new ServerSocket(port);
		}
		catch(IOException ex)
		{
			System.out.println(ex);
		}
}

	public void acceptConn()
	{
		try{
			socket = server.accept();
		}
		catch(IOException ex)
		{
			System.out.println(ex);
		}
	}

	public String inputFromClient()
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

	public String outputToClient(String message)
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
		return message;
	}
}
