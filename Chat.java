import java.util.*;
import java.io.*;
import java.net.*;
import java.lang.*;

public class Chat implements Runnable {
		
	private Vector<Student> students;
	private LinkedList<ChatEvent> events;

	public Chat() {
		students = new Vector<>();
		events = new LinkedList<>();
		Thread listenerThread = new Thread(this);
		listenerThread.start();
	};
	
	void addStudent(Student newStudent) throws Error {
		students.add(newStudent);
		events.add(new ChatEvent(newStudent, ChatEventType.CONNECTION_OPEN, "[USER CONNECTED]"));
		for (ChatEvent event : events) {
			newStudent.listen(this, event.toString());
		}
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
		for (Student student : students) {
			student.listen(this, newEvent.toString());
		}
	}
	
	public void run() {
		ServerSocket listener = new ServerSocket(8090);
        try {
            while (true) {
				Student newStudent = new Student(listener.accept());
                addStudent(newStudent);
            }
        }
        finally {
            listener.close();
        }
	}

}	