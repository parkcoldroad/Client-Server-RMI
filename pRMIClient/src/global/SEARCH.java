package global;

import exception.NullDataException;
import java.rmi.RemoteException;
import pRMI.Client;

public enum SEARCH implements MenuInterface {
  SEARCHSTUDENT(() -> {
    try {
      Client.getInstance().searchDomain(
          Client.getStub().getAllStudentData(),
          Client.getInstance().getScannerResult());
    } catch (RemoteException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }, "1" ),
  SEARCHCOURSE(() -> {
    try {
      Client.getInstance().searchDomain(
          Client.getStub().getAllCourseData(),
          Client.getInstance().getScannerResult());
    } catch (RemoteException | NullDataException e) {
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
  public String getKeyword() {
    return this.keyword;
  }

  public static void printMenu() {
    System.out.println("\n-----------------Search Menu--------------------");
    System.out.println("1.Search Student");
    System.out.println("2.Search Course");
  }
}
