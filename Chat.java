import java.util.LinkedList;

public class Chat implements Comparable<Chat>{
		
	private int roomSize;
	private int curSize;
	private Student[] students;
	private LinkedList<ChatEvent> events;

	public Chat(int roomSize) {
		this.roomSize = roomSize;
		this.students = new Student[roomSize];
		this.events = new LinkedList<ChatEvent>();
		this.curSize = 0;
	};
	
	//Where the heck are my C++ "friends"? This sucks.
	protected void addStudent(Student newStudent) throws Error {
		if (curSize < roomSize) {
			events.add(new ChatEvent(newStudent, ChatEventType.CONNECTION_OPEN, "[USER CONNECTED]"));
			students[curSize++] = newStudent;
		} else {
			throw new Error("This chat room is already full");
		}
	}
	
	protected void removeStudent(Student oldStudent) {
		for (int i = 0; i < this.roomSize; i++) {
			if (students[i] == oldStudent) {
				if (i == curSize) {
					students[--curSize] = null;
				} else {
					students[i] = students[--curSize];
					students[curSize] = null;
				}
				events.add(new ChatEvent(oldStudent, ChatEventType.CONNECTION_CLOSE, "[USER DISCONNECTED]"));
			}
		}
	}
	
	protected void addMessage(Student sender, String message) {
		events.add(new ChatEvent(sender, ChatEventType.MESSAGE, message));
	}
	
	public String toString() {
		String dialog = "";
		for (ChatEvent event : events) {
			dialog += event;
		}
		return dialog;
	}
	
	public int compareTo(Chat other) {
		return this.students[0].toString().compareTo(other.students[0].toString());
	}
}	