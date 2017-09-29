
public class ChatEvent {
	
	private ChatEventType type;
	private String content;
	private Student student;
	private java.util.Date time;
	
	public ChatEvent(Student student, ChatEventType type, String content) {
		this.student = student;
		this.type = type;
		this.content = content;
		time = new java.util.Date();
	}
	
	public ChatEventType getType() {
		return type;
	}
	
	public String toString() {
		return "(" + time + ") " + student + ": " + content + "\n";
	}
}