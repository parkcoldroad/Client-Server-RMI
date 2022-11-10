package lms.rmi;

import lms.command.menu.MainMenu;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Optional;
import lms.utils.Input;

public class Client {

  private static Client client;
  private static ClientStub clientStub;


  private Client() throws RemoteException, NotBoundException {
    Registry clientRegistry = LocateRegistry.getRegistry(14001);
    clientStub = (ClientStub) clientRegistry.lookup("Server");
  }

  public static void initialize() {
      try {
        client = new Client();
      } catch (RemoteException | NotBoundException e) {
        e.printStackTrace();
      }
  }

  public static ClientStub getStub() {
    return clientStub;
  }


  public static void goMain() {
      MainMenu.printMenu();
      String input = Input.readLine();

      Optional<MainMenu> optionalMainMenu = Arrays.stream(MainMenu.values())
          .filter(mainMenu -> mainMenu.getChoice().equals(input))
          .findFirst();

      optionalMainMenu.ifPresentOrElse(MainMenu::execute,
          () -> {System.out.println("invalid enter"); goMain();});
  }

  public static void quit() {
    Input.close();
    System.out.println("system terminated..");
    System.exit(0);
  }


}


