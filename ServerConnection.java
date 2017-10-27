import java.io.*;
import java.net.*;

public class ServerConnection extends Thread {
	
	private String name;
	private PrintWriter out;
	private Socket socket;
	private BufferedReader in;
	private Server server;
	
	//Server Constructor
	public ServerConnection(Server server, Socket client) throws IOException {
		System.out.println("Creating Server Connection");
		out = new PrintWriter(client.getOutputStream());
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		out.println("ACK");
		out.flush();
		
		this.socket = client;
		this.server = server;
		System.out.println("Got to read");
		this.name = in.readLine();
		System.out.println("Passed read");
		this.start();
	}

	public void send(String msg) {
		out.println(msg);
	}
	
	@Override
	public void run() {
		try {
			while (socket.isClosed() == false) {
				if (in.ready()) {
					server.addMessage(name, in.readLine());
				}
			}
		} catch (IOException e) {
			System.out.println("A message was not read correctly.");
			e.printStackTrace();
		}
	}
	
	public void disconnect() throws IOException {
		socket.close();
	}
	
	@Override
	public String toString() {
		return name;
	}
}
