package pRMI;

import global.CREATE;
import global.MENU;
import global.READ;
import global.SEARCH;
import global.MenuInterface;
import java.awt.SystemColor;
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
import java.util.Scanner;

import entity.Domain;

public class Client {
	private static Client client;
	private static ServerInterface stub;
	private static BufferedReader bufferedReader;

	private MenuInterface SI;

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

	public static ServerInterface getStub(){
		return stub;
	}

	public static BufferedReader getBufferedReader() { return bufferedReader;}

	public void start() throws IOException {
		boolean isStop = false;
			while (!isStop) {
				MENU.printMenu();
				String choice = bufferedReader.readLine().trim();
				
				Optional<MENU> optionalCRUD = Arrays.stream(MENU.values())
						.filter(menu -> menu.getChoice().equals(choice))
						.findFirst();

				optionalCRUD.ifPresentOrElse(MENU::execute,
						() -> System.out.println("invalid enter"));

				if (choice.equals("6")) {
					isStop = true;
				}
			}

	}


	public void create() throws IOException {
		CREATE.printMenu();
		String choice = bufferedReader.readLine().trim();

		Optional<CREATE> optionalCREATE = Arrays.stream(CREATE.values())
				.filter(create -> create.getChoice().equals(choice))
				.findFirst();

		optionalCREATE.ifPresentOrElse(CREATE::execute,
				() -> System.out.println("invalid enter"));
	}

	public void read() throws IOException {
		READ.printMenu();
		String choice = bufferedReader.readLine().trim();

		Optional<READ> optionalREAD = Arrays.stream(READ.values())
				.filter(read -> read.getChoice().equals(choice))
				.findFirst();

		optionalREAD.ifPresentOrElse(READ::execute,
				() -> System.out.println("invalid enter"));

	}
	public void search() throws IOException {
		SEARCH.printMenu();
		String choice = bufferedReader.readLine().trim();

		Optional<SEARCH> optionalREAD = Arrays.stream(SEARCH.values())
				.filter(search -> search.getChoice().equals(choice))
				.findFirst();

		System.out.println("enter your Id to search");

		optionalREAD.ifPresentOrElse(SEARCH::execute,
				() -> System.out.println("invalid enter"));

	}
	public void update() {

	}

	public void delete() {

	}

	public void quit() throws IOException {
		bufferedReader.close();
		System.out.println("system terminated..");
	}
	public void createDomain(boolean successorfail) throws RemoteException {
		if(successorfail) {System.out.println("SUCCESS");}
		else System.out.println("FAIL");
	}

	public void readDomainList(List<Domain> domainList) {
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

	public void deleteDomain(){

	}

//	public void updateDomain(){
//
//	}

	public void makeReservations(){

	}

}
