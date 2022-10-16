package global;

import exception.NullDataException;
import java.rmi.RemoteException;
import pRMI.Client;

public enum READ implements MenuInterface {
  SHOWSTUDENT(() -> {
    try {
      Client.getInstance().readDomainList(Client.getStub().getAllStudentData());
    } catch (RemoteException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }, "1"),
  SHOWCOURSE(() -> {
    try {
      Client.getInstance().readDomainList(Client.getStub().getAllCourseData());
    } catch (RemoteException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }, "2");

  private final Runnable runnable;
  private final String keyword;


  READ(Runnable runnable, String keyword) {
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
    System.out.println("\n-----------------Read Menu--------------------");
    System.out.println("1.Show StudentList");
    System.out.println("2.Show CourseList");
  }
}
