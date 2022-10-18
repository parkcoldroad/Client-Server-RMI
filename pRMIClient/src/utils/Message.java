package utils;

import java.rmi.RemoteException;

public class Message {
  public static void print(String result) throws RemoteException {
    System.out.println(result);
  }
  public static void print(boolean result) throws RemoteException {
    if(result) {System.out.println("SUCCESS");}
    else System.out.println("FAIL");
  }

}

