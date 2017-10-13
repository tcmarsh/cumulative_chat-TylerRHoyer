import java.util.*;
import java.io.*;
import java.net.*;

public class Student {
	
	private String name;
	private Vector<PrintWriter> out;
	private Vector<Socket> sockets;
	private ChatWindow client;
	
	//Client constructor
	public Student(ChatWindow client, String name) {
		this.name = name;
		this.client = client;
		sockets = new Vector<>();
		out = new Vector<>();
	}
	
	//Server Constructor
	public Student(Chat server, Socket client) throws IOException {
    	System.out.println("server got request, setting up student");
		
		out = new Vector<>();
		out.add(new PrintWriter(client.getOutputStream()));
		
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		out.get(0).println("ACK");
		out.get(0).flush();
		
		name = in.readLine();
		
		Student student = this;
		
		(new Thread () {
			public void run() {
				try {
					server.addMessage(student, in.readLine());
				} catch (IOException e) {
					if (e.getLocalizedMessage() == "Connection reset") {
						System.out.println("Client Disconnected");
						server.removeStudent(student);
						this.interrupt();
					} else {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	//Client connecting to Server
	public int connect(String address) throws IOException {
		Socket server = new Socket(address, 8090);
		sockets.add(server);
		while (!server.isConnected()) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				System.out.println("Not Connected");
			}
		}
		
		int id = out.size();
		out.add(new PrintWriter(server.getOutputStream()));
		BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));

		String response = in.readLine();
		if (!response.equals("ACK")) {
			return id;
		}
		out.get(id).println(name);
		out.get(id).flush();
		if (client != null) {
			(new Thread () {
				public void run() {
					try {
						while (true) {
							System.out.println("Waiting");
							client.onRecieve(in.readLine(), id);
						}
					} catch (IOException e) {
						System.out.println("Closing client socket");
						if (e.getLocalizedMessage() == "Connection reset") {
							System.out.println("Server Disconnected");
							try {
								server.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							this.interrupt();
						} else {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
		
		return id;
	}
	
	void disconnect(int id) {
		out.get(id).close();
	}
	
	void speak(int groupID, String message) {
		System.out.println("Sending message: " + message);
		out.get(groupID).println(message);
		out.get(groupID).flush();
		try {
			sockets.get(groupID).getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void listen(String message) {
		out.get(0).println(message);
		out.get(0).flush();
	}
	
	public String toString() {
		return name;
	}
}
