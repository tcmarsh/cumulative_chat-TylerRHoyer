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
		Thread listenerThread = new Thread(this);
		listenerThread.start();
	};
	
	void close() {
		isRunning = false;
		try {
			listener.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void addStudent(Student newStudent) throws Error {
		students.add(newStudent);
		events.add(new ChatEvent(newStudent, ChatEventType.CONNECTION_OPEN, "[USER CONNECTED]"));
		
		for (ChatEvent event : events)
			newStudent.listen(event.toString());
	}
	
	void removeStudent(Student oldStudent) {
		students.remove(oldStudent);
		ChatEvent newEvent = new ChatEvent(oldStudent, ChatEventType.CONNECTION_CLOSE, "[USER DISCONNECTED]");
		events.add(newEvent);
		addMessage(oldStudent, newEvent.toString());
	}
	
	void addMessage(Student sender, String message) {
		ChatEvent newEvent = new ChatEvent(sender, ChatEventType.MESSAGE, message);
		events.add(newEvent);
		
		for (Student student : students)
			student.listen(newEvent.toString());
	}
	
	public void run() {
        try {
    		listener = new ServerSocket(8090);
            while (isRunning)
                addStudent(new Student(listener.accept()));
        } catch (IOException e) {
			e.printStackTrace();
        }
	}

}	