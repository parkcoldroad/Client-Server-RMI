package pRMI;

import java.io.IOException;

public class ClientMain {

	public static void main(String[] args) throws IOException {
		Client client = Client.getInstance();
		client.start();
	}

}
