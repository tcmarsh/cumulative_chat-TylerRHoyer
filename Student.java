import java.util.*;
import java.io.*;
import java.net.*;
import java.lang.*;

public class Student implements Runnable {
	
	private String name;
	private ChatWindow client;
	private PrintWriter out;
	private BufferedReader in;
	
	public Student(String name) {
		this.name = name;
	}
	
	public Student(Socket client) {
		out = new PrintWriter(client.getOutputStream(), true);
		in = new BufferedReader(client.getInputStream(), true);
		Thread listenerThread = new Thread(this);
		listenerThread.start();
	}
	
	void listen(Chat chat, String message) {
		if (client != null)
			client.onRecieve(message, chat);
	}
	
	void setClient(ChatWindow client) {
		this.client = client;
	}
	
	public String toString() {
		return name;
	}
	
	public void run() {
        out.println(new Date().toString());
		while (true) {
			String message = in.readLine();
		}
	}
}
