
public class Student {
	
	private String name;
	private Chat history;
	private Chat chat;
	
	public Student(String name) {
		this.name = name;
		this.history = new Chat(1);
		this.history.addStudent(this);
	}
	
	public Chat getChat() {
		return this.chat;
	}
	
	public void joinChat(Chat chat) {
		if (this.chat != null) {
			if (this.chat != chat) {
				this.chat.removeStudent(this);
				this.chat = chat;
				chat.addStudent(this);
			}
		} else {
			this.chat = chat;
			chat.addStudent(this);
		}
	}
	
	public void leaveChat() {
		if (this.chat != null) {
			this.chat.removeStudent(this);
			this.chat = null;
		}
	}
	
	public void speak(String message) {
		this.history.addMessage(this, message);
		if (this.chat != null) {
			this.chat.addMessage(this, message);
		}
	}
	
	public String toString() {
		return name;
	}
}
