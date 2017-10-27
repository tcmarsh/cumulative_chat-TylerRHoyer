import java.io.*;
import java.net.Socket;
import java.util.Vector;

public class Client {
	private Window clientWindow;
	
	private Vector<Socket> sockets;
	private Vector<PrintWriter> out;
	private Vector<BufferedReader> in;
	
	public Client() {
		sockets = new Vector<>();
		out = new Vector<>();
		in = new Vector<>();
		clientWindow = new Window(this);
	}
	
	public void send(int serverID, String msg) {
		out.get(serverID).println(msg);
	}
	
	public void recieve(int serverID, String msg) throws IOException {
		clientWindow.display(serverID, msg);
	}
	
	int connect(String name, String address) throws IOException {
		int serverID = out.size();
		Socket newSocket = new Socket(address, 8090);
		sockets.add(newSocket);
		out.add(serverID, new PrintWriter(newSocket.getOutputStream()));
		in.add(serverID, new BufferedReader(new InputStreamReader(newSocket.getInputStream())));
		
		String response = in.get(serverID).readLine();
		if (response.equals("ACK\n")) {
			out.get(serverID).println(name);
		}
		
		return clientWindow.addTab(serverID, address);
	}
	
	void disconnect(int serverID) {
		try {
			sockets.get(serverID).close();
		} catch (IOException e) {
			System.out.println("Unable to close connection");
			e.printStackTrace();
		}
	}
	
}
