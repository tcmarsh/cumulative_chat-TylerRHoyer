
public class ChatEvent {
	
	private String content;
	private Student student;
	private java.util.Date time;
	
	public ChatEvent(Student student, String content) {
		this.student = student;
		this.content = content;
		time = new java.util.Date();
	}
	
	public String toString() {
		return "(" + time + ") " + student + ": " + content + "\n";
	}
}