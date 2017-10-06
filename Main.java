// Unused import java.util.Random - I'm sure this was to time the messages, but since you're not using it, leaving the warning around invites the clutter and "smell" we were talking about last night.
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

	public static void main(String[] args) {
		SortedSet<Chat> groups = new TreeSet<>();
		
		Student[] students = new Student[] {
			new Student("Justin Behunin"),
			new Student("Kenyon Brown"),
			new Student("Austin Forsling"),
			new Student("Nicholas Goldberg"),
			new Student("Tyler Hoyer"),
			new Student("Steven Julien"),
			new Student("Jonathan Mirabile"),
			new Student("Christopher Nash"),
		};
		
		ThreadLocalRandom waitTime = ThreadLocalRandom.current();
		System.out.println("Shhh, the students are chatting...");
		try {
			// If you're Thread.sleep statements are a requirement, there are at least two points to consider.
			// One is that you've done a LOT of copy/paste to get that working. What if you wanted to change the wait time to something other than up to 1 second per?
			// Perhaps a suggestion here is to change your "10, 1000" to having two non-magic numbers.
			// Another, probably even better suggestion is to add a method that performs that call, possibly with the speaking - so you can say requestComment(Student, String) and have it do both the delay and the "speak" action.
			Chat nextChat = new Chat(2);
			students[0].joinChat(nextChat);
			students[1].joinChat(nextChat);
			students[0].speak("Hey, I'm Justin.");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[1].speak("I'm Kenyon.");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[0].speak("Have you started on the Chat assignment yet?");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[1].speak("Nope. You?");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[0].speak("Yeah. I started it last class.");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[1].speak("Hey, that's cheating!");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[0].speak("... I paid for it.");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[1].speak("Doesn't matter");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[0].speak("It isn't cheating anyways");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[1].speak("-.-");
			groups.add(nextChat);
			
			nextChat = new Chat(2);
			students[2].joinChat(nextChat);
			students[3].joinChat(nextChat);
			Thread.sleep(waitTime.nextLong(10,1000));
			students[2].speak("Greetings, friend! I'm Austin.");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[3].speak("I'm Kenyon.");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[2].speak("Did you like my presentation on events?");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[3].speak("Yeah, I thought you did a good job!");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[2].speak("Thanks!");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[3].speak("...");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[2].speak("...");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[3].speak("I should be going now");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[2].speak("I think so too");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[3].speak("Bye.");
			groups.add(nextChat);

			nextChat = new Chat(2);
			students[4].joinChat(nextChat);
			students[5].joinChat(nextChat);
			Thread.sleep(waitTime.nextLong(10,1000));
			students[4].speak("Hi Steven, I'm Tyler.");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[5].speak("Greetings and salvation upon your shoes!");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[4].speak("Uhhh, well that's a new one.");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[5].speak("We can't have you getting too comfortable, can we?");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[4].speak("Who is this \"we\" you speak of?");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[5].speak("Me and your ancesters.");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[4].speak("My ancesters? What the h-e-double hockysticks are you talking about?");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[5].speak("They like to hang out around you. You know, watch over you.");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[4].speak("You can't be serious.");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[5].speak("You're right, and I'm not Black either.");
			groups.add(nextChat);

			nextChat = new Chat(2);
			students[6].joinChat(nextChat);
			students[7].joinChat(nextChat);
			Thread.sleep(waitTime.nextLong(10,1000));
			students[6].speak("Yo yo yo, I'm Johnathan!");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[7].speak("My man! I'm Christopher!");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[6].speak("What chu' been up to, dog?");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[7].speak("Not much dog. Just stressing about this assignment due tonight.");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[6].speak("Why you stressen'?");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[7].speak("... It is due tonight");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[6].speak("So what?");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[7].speak("I have have to get it done. That's code word for scat, dog!");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[6].speak("Oh, alright. I just thought we were friends.");
			Thread.sleep(waitTime.nextLong(10,1000));
			students[7].speak("Just keep walking. Homework comes first.");
			groups.add(nextChat);
		} catch (InterruptedException e) {
			System.out.print("Something unexpected happened.");
		} finally {
			
		}

		System.out.println("The students have gone quiet. Lets see what they said...");
		for (Chat group : groups) {
			System.out.println(group);
		}
	}

}
