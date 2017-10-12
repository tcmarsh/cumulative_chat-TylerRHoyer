import java.util.*;
import java.io.*;
import java.net.*;

public class Chat implements Runnable {
		
	private Vector<Student> students;
	private LinkedList<ChatEvent> events;
	private ServerSocket listener;
	private boolean isRunning = true;

	public Chat() {
		students = new Vector<>();
		events = new LinkedList<>();
		addMessage(null, "[SERVER STARTED]");
		Thread listenerThread = new Thread(this);
		listenerThread.start();
	};
	
	void close() {
		isRunning = false;
		addMessage(null, "[SERVER SHUTDOWN]");
		try {
			listener.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//How is this being called?
	void removeStudent(Student oldStudent) {
		students.remove(oldStudent);
		ChatEvent newEvent = new ChatEvent(oldStudent, "[USER DISCONNECTED]");
		events.add(newEvent);
		addMessage(oldStudent, newEvent.toString());
	}
	
	void addMessage(Student sender, String message) {
		ChatEvent newEvent = new ChatEvent(sender, message);
		events.add(newEvent);
		for (Student student : students)
			student.listen(newEvent.toString());
	}
	
	public void run() {
        try {
    		listener = new ServerSocket(8090);
            while (isRunning) {
                Student newStudent = new Student(listener.accept());
	            students.add(newStudent);
	    		addMessage(newStudent, "[USER CONNECTED]");
            }
        } catch (IOException e) {
			e.printStackTrace();
        }
	}

}	