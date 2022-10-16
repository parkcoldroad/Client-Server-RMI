package utils;

import entity.Domain;
import java.rmi.RemoteException;
import java.util.List;

public class Message {
  public static void print(boolean result) throws RemoteException {
    if(result) {System.out.println("SUCCESS");}
    else System.out.println("FAIL");
  }

  public static void print(List<? extends Domain> domainList) {
    for (Domain domain : domainList) {
      System.out.println("------------------------------------------");
      domain.showAttributes();
      System.out.println(domain);
    }
  }

  public static void print(Domain domain) {
    System.out.println("------------Search Result------------");
    domain.showAttributes();
    System.out.println(domain);
  }
}

