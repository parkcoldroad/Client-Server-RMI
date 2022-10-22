package rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Optional;
import command.menu.AuthMenu;
import command.menu.MainMenu;
import utils.Input;

public class Client {

  private static Client client;
  private static Stub stub;


  private Client() throws RemoteException, NotBoundException {
    Registry clientRegistry = LocateRegistry.getRegistry(14000);
    stub = (Stub) clientRegistry.lookup("Server");
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

  public static Stub getStub() {
    return stub;
  }


  public void start() {
    boolean isStop = false;
    while (!isStop) {
      MainMenu.printMenu();
      String input = Input.readLine();

      Optional<MainMenu> optionalCRUD = Arrays.stream(MainMenu.values())
          .filter(mainMenu -> mainMenu.getChoice().equals(input))
          .findFirst();

      optionalCRUD.ifPresentOrElse(MainMenu::execute,
          () -> System.out.println("invalid enter"));

      if (input.equals("4")) {
        isStop = true;
      }
    }
  }

  public void quit() {
    Input.close();
    System.out.println("system terminated..");
    System.exit(0);
  }

  public void run() {
    AuthMenu.printMenu();
    String input = Input.readLine();

    Optional<AuthMenu> optionalLogin = Arrays.stream(AuthMenu.values())
        .filter(authMenu -> authMenu.getChoice().equals(input))
        .findFirst();

    optionalLogin.ifPresentOrElse(AuthMenu::execute,
        () -> System.out.println("invalid enter"));
  }
}


