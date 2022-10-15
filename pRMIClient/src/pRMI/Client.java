package pRMI;

import global.MENU;
import global.READ;
import global.SEARCH;
import global.MenuInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import entity.Domain;

public class Client {
	private static Client client;
	private static ServerInterface stub;
	private final Scanner sc;

	private MenuInterface SI;

	private Client() throws RemoteException, NotBoundException {
		Registry clientRegistry = LocateRegistry.getRegistry(9000);
		stub = (ServerInterface) clientRegistry.lookup("Server");
		sc = new Scanner(System.in);
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

	public static ServerInterface getStub(){
		return stub;
	}

	public void start() {
		boolean isStop = false;
			while (!isStop) {
				MENU.printMenu();
				String choice = sc.next();
				
				Optional<MENU> optionalCRUD = Arrays.stream(MENU.values())
						.filter(menu -> menu.getKeyword().equals(choice))
						.findFirst();

				optionalCRUD.ifPresentOrElse(MENU::execute,
						() -> System.out.println("invalid enter"));

				if (choice.equals("6")) {
					isStop = true;
				}
			}

	}


	public void create() {

	}

	public void read() {
		READ.printMenu();
		String choice = sc.next();

		Optional<READ> optionalREAD = Arrays.stream(READ.values())
				.filter(read -> read.getKeyword().equals(choice))
				.findFirst();

		optionalREAD.ifPresentOrElse(READ::execute,
				() -> System.out.println("invalid enter"));

	}
	public void search() {
		SEARCH.printMenu();
		String choice = sc.next();

		Optional<SEARCH> optionalREAD = Arrays.stream(SEARCH.values())
				.filter(search -> search.getKeyword().equals(choice))
				.findFirst();

		System.out.println("enter your Id to search");

		optionalREAD.ifPresentOrElse(SEARCH::execute,
				() -> System.out.println("invalid enter"));

	}
	public void update() {

	}

	public void delete() {

	}

	public void quit() {
		sc.close();
		System.out.println("system terminated..");
	}
	public void showDomainList(List<Domain> domainList) {
		for (Domain domain : domainList) {
			System.out.println("------------------------------------------");
			domain.showAttributes();
			System.out.println(domain.toString());
		}
	}

	public void searchDomain(List<Domain> domainList, String domainId) {
		Optional<Domain> searchedDomain = domainList.stream()
				.filter(domain -> domain.match(domainId))
				.findFirst();

		searchedDomain.ifPresentOrElse(domain -> {
			System.out.println("------------Search Result------------");
			domain.showAttributes();
			System.out.println(domain);
		}, () -> System.out.println("your id is no matched"));
	}
	public String getScannerResult(){
		return sc.next();
	}
}
