import java.util.*;
import java.io.*;
import java.net.*;

public class Server extends Thread {
		
	private Vector<ServerConnection> connections;
	private LinkedList<ChatEvent> events;
	private ServerSocket listener;

	public Server() {
		connections = new Vector<>();
		events = new LinkedList<>();
		addMessage(null, "[SERVER STARTED]");

		addMessage("Justin", "Hey, I'm Justin.");
		addMessage("Kenyon", "I'm Kenyon.");
		addMessage("Justin", "Have you started on the Chat assignment yet?");
		addMessage("Kenyon", "Nope. You?");
		
		this.start();
	};
	
	void disconnect() {
		addMessage(null, "[SERVER SHUTDOWN]");
		try {
			listener.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void removeStudent(ServerConnection oldConnection) {
		connections.remove(oldConnection);
		addMessage(oldConnection.getName(), "[USER DISCONNECTED]");
	}
	
	void addMessage(String sender, String message) {
		ChatEvent newEvent = new ChatEvent(sender, message);
		events.add(newEvent);
		for (ServerConnection connection : connections) {
			connection.send(newEvent.toString());
		}
	}
	
	@Override
	public void run() {
        System.out.println("Server has started running");
        try {
    		listener = new ServerSocket(8090);
            while (listener.isClosed() == false) {
            	System.out.println("Server is Listening");
	            ServerConnection newConnection = new ServerConnection(this, listener.accept());
            	System.out.println("Server is Responding");
		        connections.add(newConnection);
		    	addMessage(newConnection.getName(), "[USER CONNECTED]");
            }
        } catch (IOException e) {
			e.printStackTrace();
        } finally {
        	disconnect();
        }
        System.out.println("Server has stopped running");
	}

}	