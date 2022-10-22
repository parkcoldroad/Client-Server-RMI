package service;

import command.menu.EnrollmentMenu;
import dto.EnrollmentDto;
import exception.NullDataException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import rmi.Client;
import rmi.Stub;
import utils.Input;

public class EnrollmentService {

  private static EnrollmentService enrollmentService;
  private final Stub stub;

  public static EnrollmentService getInstance() {
    if (enrollmentService == null) {
      enrollmentService = new EnrollmentService();
    }
    return enrollmentService;
  }

  private EnrollmentService() {
    this.stub = Client.getStub();
  }

  public void initialize() {
    EnrollmentMenu.printMenu();
    String choice = Input.readLine();
    Arrays.stream(EnrollmentMenu.values())
        .filter(enrollmentMenu -> enrollmentMenu.getChoice().equals(choice))
        .findFirst()
        .ifPresentOrElse(EnrollmentMenu::execute,
            () -> System.out.println("invalid enter"));
  }

  public String applyCourse(String studentId, String courseId) {
    try {
      return this.stub.createEnrollment(studentId, courseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public ArrayList<EnrollmentDto> displayApplyHistory(String studentId) {
    try {
      return this.stub.getEnrollmentData(studentId);
    } catch (RemoteException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }


  public boolean removeApplyHistory(String studentId,String courseId) {
    try {
      return this.stub.deleteEnrollment(studentId, courseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }
}
