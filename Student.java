
public class Student {
	
	// Student has only name, not "first name"/"last name", and also has no "score": -10 pts
	// RESPONSE Forgot about that score part. What the heck is it used for anyways? I don't see a purpose for it now or in the future. You want me to add a variable that is never used? Isn't that a smell?
	// RESPONSE You will notice that the students do have a first name and last name in the chat. They are stored together. 
	// RESPONSE In my database design class, it is considered a bad practice to separate the two because then multiple columns are referring to the same "object," a name.
	// RESPONSE There are arguments for and against. You will notice that this method makes ordering by name easier.
	private String name;
	
	// Single Responsibility Principle again - using the Chat object to represent to disparate functionalities may seem "clever", but is in reality more likely to cause bugs or misunderstanding, AND makes it so that you have more than one reason why that class could change. This introduces issues from a maintainability standpoint.
	// RESPONSE How can a server work without maintaining a connection with a client? How can a client work without maintaining a connection to a server?
	// RESPONSE There must be a copy of messages on both sides, so it will take additional management.
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
