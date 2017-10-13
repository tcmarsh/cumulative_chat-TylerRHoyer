import java.util.*;
import java.io.*;
import java.net.*;

public class Chat extends Thread {
		
	private Vector<Student> students;
	private LinkedList<ChatEvent> events;
	private ServerSocket listener;
	private boolean isRunning = true;

	public Chat() {
		students = new Vector<>();
		events = new LinkedList<>();
		addMessage(null, "[SERVER STARTED]");
		this.start();
	};
	
	void close() {
		isRunning = false;
		addMessage(null, "[SERVER SHUTDOWN]");
		System.out.println("Shutting Down Server");
		try {
			listener.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void removeStudent(Student oldStudent) {
		students.remove(oldStudent);
		ChatEvent newEvent = new ChatEvent(oldStudent, "[USER DISCONNECTED]");
		events.add(newEvent);
		addMessage(oldStudent, newEvent.toString());
	}
	
	void addMessage(Student sender, String message) {
		System.out.println("Server relaying message");
		ChatEvent newEvent = new ChatEvent(sender, message);
		events.add(newEvent);
		for (Student student : students)
			student.listen(newEvent.toString());
	}
	
	public void run() {
        try {
    		listener = new ServerSocket(8090);
            while (isRunning) {
            	System.out.println("server waiting for connections");
            	try {
	                Student newStudent = new Student(this, listener.accept());
		            students.add(newStudent);
		    		addMessage(newStudent, "[USER CONNECTED]");
            	} catch (IOException e) {
            		//It is okay... the server is probably shutting down... probably...
            		this.interrupt();
            	}
            }
        } catch (IOException e) {
			e.printStackTrace();
        }
	}

}	