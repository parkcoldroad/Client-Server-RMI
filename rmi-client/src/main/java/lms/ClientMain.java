package lms;

import lms.command.AuthCmd;
import lms.rmi.Client;

public class ClientMain {

  public static void main(String[] args) {
    Client.initialize();
    AuthCmd.initialize();
  }

}
