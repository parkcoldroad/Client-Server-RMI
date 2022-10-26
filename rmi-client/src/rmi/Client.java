package rmi;

import command.menu.MainMenu;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Optional;
import utils.Input;

public class Client {

  private static Client client;
  private static ClientStub clientStub;


  private Client() throws RemoteException, NotBoundException {
    Registry clientRegistry = LocateRegistry.getRegistry(14000);
    clientStub = (ClientStub) clientRegistry.lookup("Server");
  }

  public static void initialize() {
    if (client == null) {
      try {
        client = new Client();
      } catch (RemoteException | NotBoundException e) {
        e.printStackTrace();
      }
    }
  }

  public static ClientStub getStub() {
    return clientStub;
  }


  public static void start() {
    boolean isStop = false;
    while (!isStop) {
      MainMenu.printMenu();
      String input = Input.readLine();

      Optional<MainMenu> optionalCRUD = Arrays.stream(MainMenu.values())
          .filter(mainMenu -> mainMenu.getChoice().equals(input))
          .findFirst();

      optionalCRUD.ifPresentOrElse(MainMenu::execute,
          () -> System.out.println("invalid enter"));

      if (input.equals("6")) {
        isStop = true;
      }
    }
  }

  public static void quit() {
    Input.close();
    System.out.println("system terminated..");
    System.exit(0);
  }


}


