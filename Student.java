public class Student {
	
	private String name;
	private ChatWindow client;
	
	public Student(String name) {
		this.name = name;
	}
	
	void listen(Chat chat, String message) {
		if (client != null)
			client.onRecieve(message, chat);
	}
	
	void setClient(ChatWindow client) {
		this.client = client;
	}
	
	public String toString() {
		return name;
	}
}
