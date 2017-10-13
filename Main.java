import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		Student[] students = new Student[] {
			new Student(null, "Justin Behunin"),
			new Student(null, "Kenyon Brown"),
			new Student(null, "Austin Forsling"),
			new Student(null, "Nicholas Goldberg"),
			new Student(null, "Tyler Hoyer"),
			new Student(null, "Steven Julien"),
			new Student(null, "Jonathan Mirabile"),
			new Student(null, "Christopher Nash"),
		};
		
		ChatWindow window = new ChatWindow();
	
		Chat nextChat = new Chat();
		window.connect("localhost");
		students[0].connect("localhost");
		students[1].connect("localhost");
		Thread.sleep(1000);
		students[0].speak(0, "Hey, I'm Justin.");
		students[1].speak(0, "I'm Kenyon.");
		students[0].speak(0, "Have you started on the Chat assignment yet?");
		students[1].speak(0, "Nope. You?");
		students[0].speak(0, "Yeah. I started it last class.");
		students[1].speak(0, "Hey, that's cheating!");
		students[0].speak(0, "... I paid for it.");
		students[1].speak(0, "Doesn't matter");
		students[0].speak(0, "It isn't cheating anyways");
		students[1].speak(0, "-.-");
		Thread.sleep(10000);
		nextChat.close();
		
		/*
		nextChat = new Chat();
		window.joinGroup(nextChat);
		nextChat.addStudent(students[2]);
		nextChat.addStudent(students[3]);
		nextChat.addMessage(students[2], "Greetings, friend! I'm Austin.");
		nextChat.addMessage(students[3], "I'm Kenyon.");
		nextChat.addMessage(students[2], "Did you like my presentation on events?");
		nextChat.addMessage(students[3], "Yeah, I thought you did a good job!");
		nextChat.addMessage(students[2], "Thanks!");
		nextChat.addMessage(students[3], "...");
		nextChat.addMessage(students[2], "...");
		nextChat.addMessage(students[3], "I should be going now");
		nextChat.addMessage(students[2], "I think so too");
		nextChat.addMessage(students[3], "Bye.");

		nextChat = new Chat();
		window.joinGroup(nextChat);
		nextChat.addStudent(students[4]);
		nextChat.addStudent(students[5]);
		nextChat.addMessage(students[4], "Hi Steven, I'm Tyler.");
		nextChat.addMessage(students[5], "Greetings and salvation upon your shoes!");
		nextChat.addMessage(students[4], "Uhhh, well that's a new one.");
		nextChat.addMessage(students[5], "We can't have you getting too comfortable, can we?");
		nextChat.addMessage(students[4], "Who is this \"we\" you speak of?");
		nextChat.addMessage(students[5], "Me and your ancesters.");
		nextChat.addMessage(students[4], "My ancesters? What the h-e-double hockysticks are you talking about?");
		nextChat.addMessage(students[5], "They like to hang out around you. You know, watch over you.");
		nextChat.addMessage(students[4], "You can't be serious.");
		nextChat.addMessage(students[5], "You're right, and I'm not Black either.");

		nextChat = new Chat();
		window.joinGroup(nextChat);
		nextChat.addStudent(students[6]);
		nextChat.addStudent(students[7]);
		nextChat.addMessage(students[6], "Yo yo yo, I'm Johnathan!");
		nextChat.addMessage(students[7], "My man! I'm Christopher!");
		nextChat.addMessage(students[6], "What chu' been up to, dog?");
		nextChat.addMessage(students[7], "Not much dog. Just stressing about this assignment due tonight.");
		nextChat.addMessage(students[6], "Why you stressen'?");
		nextChat.addMessage(students[7], "... It is due tonight");
		nextChat.addMessage(students[6], "So what?");
		nextChat.addMessage(students[7], "I have have to get it done. That's code word for scat, dog!");
		nextChat.addMessage(students[6], "Oh, alright. I just thought we were friends.");
		nextChat.addMessage(students[7], "Just keep walking. Homework comes first.");
		*/
	}

}
