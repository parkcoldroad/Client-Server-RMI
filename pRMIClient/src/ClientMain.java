import rmi.Client;

public class ClientMain {

	public static void main(String[] args) {
		Client client = Client.getInstance();
		client.start();
	}

}
