import rmi.DataServer;
import rmi.Server;

public class ServerMain {

  public static void main(String[] args) {
    DataServer dataserver = DataServer.getInstance();
    Server server = Server.getInstance();
    dataserver.start();
    server.start();
  }
}
