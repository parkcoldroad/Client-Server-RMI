package pRMI;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import entity.Domain;
import exception.NullDataException;

public class Client {
	private static Client client;
	private Registry clientRegistry;
	private ServerInterface stub;
	private Scanner sc;

	private Client() throws RemoteException, NotBoundException {
		clientRegistry = LocateRegistry.getRegistry(9000);
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

	public void start() {
		boolean isStop = false;
			while (!isStop) {
				printMenu();
				
				String choice = sc.next();
				
				Optional<CRUD> optionalCRUD = Arrays.stream(CRUD.values())
						.filter(crud -> crud.getKeyword().equals(choice))
						.findFirst();

				optionalCRUD.ifPresentOrElse(crud -> crud.execute(),
						() -> System.out.println("invalid enter"));

				if (choice.equals("5")) {
					isStop = true;
				}
			}

	}

	private void printMenu() {
		System.out.println("\n-----------------MENU--------------------");
		System.out.println("1.Create");
		System.out.println("2.Read");
		System.out.println("3.Update");
		System.out.println("4.Delete");
		System.out.println("5.Quit");
	}

	public void create() {

	}

	public void readMenu() {
		System.out.println("\n-----------------Read Menu--------------------");
		System.out.println("1.Show StudentList");
		System.out.println("2.Show CourseList");
		System.out.println("3.Search Student");
		System.out.println("4.Search Course");

		int choice = sc.nextInt();
		readList(choice);
	}

	private void readList(int input) {
		try {
			if (input == 1)
				showDomainList(stub.getAllStudentData());
			else if (input == 2)
				showDomainList(stub.getAllCourseData());
			else if (input == 3) {
				System.out.println("enter your studentId to search");
				String studentId = sc.next();
				searchDomain(stub.getAllStudentData(), studentId);
			} else if (input == 4) {
				System.out.println("enter your studentId to search");
				String courseId = sc.next();
				searchDomain(stub.getAllCourseData(), courseId);
			} else {
				System.out.println("invalid enter");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullDataException e) {
			e.printStackTrace();
		}

	}

	private void showDomainList(List<Domain> domainList) {
		for (Domain domain : domainList) {
			System.out.println("------------------------------------------");
			domain.showAttributes();
			System.out.println(domain.toString());
		}
	}

	private void searchDomain(List<Domain> domainList, String domainId) {
		Optional<Domain> searchedDomain = domainList.stream().filter(domain -> domain.match(domainId)).findFirst();

		searchedDomain.ifPresentOrElse(domain -> {
			System.out.println("------------Search Result------------");
			domain.showAttributes();
			System.out.println(domain);
		}, () -> System.out.println("your id is no matched"));
	}

	public void update() {

	}

	public void delete() {

	}

	public void quit() {
		sc.close();
		System.out.println("system terminated..");
	}

}
