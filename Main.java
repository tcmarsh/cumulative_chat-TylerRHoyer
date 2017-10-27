public class Main {

	public static void main(String[] args) throws InterruptedException {

		Client client = new Client();
		Server server = new Server();

		Thread.sleep(10000);

		client.disconnect(0);
		server.disconnect();
	}

}
