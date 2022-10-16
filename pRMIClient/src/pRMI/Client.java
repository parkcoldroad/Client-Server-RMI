package pRMI;

import menu.MainMenu;
import menu.Menu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import entity.Domain;

public class Client {

	private static Client client;
	private static ServerInterface stub;
	private static BufferedReader bufferedReader;

	private Menu SI;

	private Client() throws RemoteException, NotBoundException {
		Registry clientRegistry = LocateRegistry.getRegistry(9000);
		stub = (ServerInterface) clientRegistry.lookup("Server");
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	}

	public static Client getInstance() {
		if (client == null) {
			try {
				client = new Client();
			} catch (RemoteException | NotBoundException e) {
				e.printStackTrace();
			}
		}
		return client;
	}

	public static ServerInterface getStub() {
		return stub;
	}

	public static BufferedReader getBufferedReader() {
		return bufferedReader;
	}

	public void start() throws IOException {
		boolean isStop = false;
		while (!isStop) {
			MainMenu.printMenu();
			String choice = bufferedReader.readLine().trim();

			Optional<MainMenu> optionalCRUD = Arrays.stream(MainMenu.values())
					.filter(mainMenu -> mainMenu.getChoice().equals(choice))
					.findFirst();

			optionalCRUD.ifPresentOrElse(MainMenu::execute,
					() -> System.out.println("invalid enter"));

			if (choice.equals("6")) {
				isStop = true;
			}
		}
	}

	public void quit() {
		try {
			bufferedReader.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println("system terminated..");
	}
}


