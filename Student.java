
public class Student {
	
	// Student has only name, not "first name"/"last name", and also has no "score": -10 pts
	private String name;
	// Single Responsibility Principle again - using the Chat object to represent to disparate functionalities may seem "clever", but is in reality more likely to cause bugs or misunderstanding, AND makes it so that you have more than one reason why that class could change. This introduces issues from a maintainability standpoint.
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
