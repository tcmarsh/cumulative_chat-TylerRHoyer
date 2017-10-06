import java.util.LinkedList;

public class Chat implements Comparable<Chat>{
		
	private int roomSize;
	private int curSize;
	// Chat links to Student which links back to Chat - this is a violation of the Single Responsibility Principle, and overall doesn't even seem to serve much of a purpose
	// RESPONSE Ah, as of yet it doesn't, but there is a method to my madness! I wrote in the student class of clients and servers.
	private Student[] students;
	// You've put the chat response into the Chat instead of into the Student: -10 pts (doesn't match project requirements)
	// RESPONSE Actually, each student has a Chat object called "History" that satisfies this requirement. In this version, there is one History object per student and each History only contains their responses. I just opted to not use the History, but it is there for the "project requirements."
	
	// Chat responses are not a unique collection by Student: -5 pts (non-unique, collection and not by student are points lost elsewhere)
	// RESPONSE Oh, the way I interpreted that statement in the requirements was different. I thought that you didn't want duplicate responses (so I kept all the conversations unique when I wrote them).
	// RESPONSE If I had thought about it some more and asked myself why you added that requirement, I would have realized your wanted me to use a unique collection for student responses. (Which I could have done with the History chat object.)
    private LinkedList<ChatEvent> events;

	public Chat(int roomSize) {
		this.roomSize = roomSize;
		this.students = new Student[roomSize];
		this.events = new LinkedList<ChatEvent>();
		this.curSize = 0;
	};
	
	// That comment's not very useful - I see you're wondering about friends, the concept doesn't exist in Java
	// Where the heck are my C++ "friends"? This sucks.
	// RESPONSE I think it is very useful. It brightens the mood of the reader, who may be having a bad day. Despite popular belief, programmers are not robots. Shocking, right?
	protected void addStudent(Student newStudent) throws Error {
		if (curSize < roomSize) {
			events.add(new ChatEvent(newStudent, ChatEventType.CONNECTION_OPEN, "[USER CONNECTED]"));
			students[curSize++] = newStudent;
		} else {
			throw new Error("This chat room is already full");
		}
	}
	
	// The C++ method to manage an array used is very unfriendly and prone to errors - you'd be far better off
	// (if you have to implement something with the array) doing other controls, as ArrayList -> Iterator and similar.
	// USE THE CONSTRUCTS of the language or paradigm you're working with, Java is not simply "another way to write C++" or similar - it's a different language, with different strengths and weaknesses.
	// One of the things you should strive to learn about any language you're learning is how the structures and algorithms you're already used to apply in the language you're working with.
	// RESPONSE Aww man, but then you get the Java overhead... I just think Java is flawed by design.
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
	
	// You're not doing null checks in your compareTo method, how do you know that there are exactly the correct number of students in a chat?
	// Good catch. It works as long as it isn't an empty chat, and if it was an empty chat, which server would be running the chat object?
	// Well, I guess a server could continue to run a server object even if it isn't connected as a client to it, but I don't see a need to do that for this project's purpose.
	public int compareTo(Chat other) {
		return this.students[0].toString().compareTo(other.students[0].toString());
	}
}	
