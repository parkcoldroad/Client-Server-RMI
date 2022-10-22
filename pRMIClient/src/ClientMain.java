import command.AuthCmd;
import rmi.Client;

public class ClientMain {

  public static void main(String[] args) {
    Client.getInstance();
    AuthCmd.initialize();
  }

}
