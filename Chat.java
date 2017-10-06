import java.util.LinkedList;
import java.util.Vector;

public class Chat {
		
	private Vector<Student> students;
	private LinkedList<ChatEvent> events;

	public Chat() {
		students = new Vector<>();
		events = new LinkedList<>();
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

}	