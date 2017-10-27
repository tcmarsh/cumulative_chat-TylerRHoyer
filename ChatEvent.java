
public class ChatEvent {
	
	private String content;
	private String source;
	private java.util.Date time;
	
	public ChatEvent(String source, String content) {
		this.source = source;
		this.content = content;
		time = new java.util.Date();
	}
	
	public String toString() {
		return "(" + time + ") " + source + ": " + content;
	}
}