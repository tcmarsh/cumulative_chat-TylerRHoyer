import java.util.*;
import java.io.*;
import java.net.*;

public class Student {
	
	private String name;
	private Vector<PrintWriter> out;
	
	
	private static interface Listener extends Runnable {
		public void listen();
		public default void run() {
			while (true) listen();
		}
	}
	
	public Student(String name) {
		this.name = name;
	}
	
	public Student(Socket client) throws IOException {
		OutputStream output = client.getOutputStream();
		PrintWriter printer = new PrintWriter(output);
		out.add(printer);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out.get(0).println("ACK");
		name = in.readLine();
		
		Listener listener = () -> {
			try {
				in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
		new Thread(listener).start();
	}
	
	public int connect(Socket server) throws IOException {
		while (!server.isConnected()) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				System.out.println("Not Connected");
			}
		}
		OutputStream output = server.getOutputStream();
		PrintWriter printer = new PrintWriter(output);
		out.add(printer);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
		Listener listener = () -> {
			try {
				in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
		new Thread(listener).start();
		
		return out.size() - 1;
	}
	
	void speak(int groupID, String message) {
		out.get(groupID).println(message);
	}
	
	void listen(String message) {
		out.get(0).println(message);
	}
	
	public String toString() {
		return name;
	}
}
