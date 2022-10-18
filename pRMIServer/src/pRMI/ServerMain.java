package pRMI;

public class ServerMain {

  public static void main(String[] args) {
    DataServer dataserver = DataServer.getInstance();
    ClientServer clientserver = ClientServer.getInstance();

    dataserver.start();
    clientserver.start();
  }
}
