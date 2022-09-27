package pRMI;

public class Main {

	public static void main(String[] args) {
		Server server = Server.getInstance();
		server.start();
	}
}
