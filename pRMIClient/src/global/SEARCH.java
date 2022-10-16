package global;

import exception.NullDataException;
import java.io.IOException;
import java.rmi.RemoteException;
import pRMI.Client;

public enum SEARCH implements MenuInterface {
  SEARCHSTUDENT(() -> {
    try {
      Client.getInstance().searchDomain(
          Client.getStub().getAllStudentData(),
          Client.getBufferedReader().readLine().trim());
    } catch (NullDataException | IOException e) {
      throw new RuntimeException(e);
    }
  }, "1" ),
  SEARCHCOURSE(() -> {
    try {
      Client.getInstance().searchDomain(
          Client.getStub().getAllCourseData(),
          Client.getBufferedReader().readLine().trim());
    } catch (NullDataException | IOException e) {
      throw new RuntimeException(e);
    }
  }, "2");

  private final Runnable runnable;
  private final String keyword;


  SEARCH(Runnable runnable, String keyword) {
    this.runnable = runnable;
    this.keyword = keyword;
  }

  @Override
  public void execute() {
    this.runnable.run();
  }

  @Override
  public String getChoice() {
    return this.keyword;
  }

  public static void printMenu() {
    System.out.println("\n-----------------Search Menu--------------------");
    System.out.println("1.Search Student");
    System.out.println("2.Search Course");
  }
}
