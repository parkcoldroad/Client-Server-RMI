package service;

import dto.CourseDto;
import dto.EnrollmentDto;
import exception.NullDataException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import menu.EnrollmentMenu;
import pRMI.Client;
import pRMI.ClientInterface;
import utils.Input;
import utils.Message;

public class EnrollmentService {

  private static EnrollmentService enrollmentService;
  private final ClientInterface stub;

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

  public void applyCourse() {
    try {
      System.out.println("------------Courses List------------");

      ArrayList<CourseDto> allCourseData = this.stub.getAllCourseData();
      for(CourseDto courseDto: allCourseData){
        System.out.println(courseDto);
      }
      System.out.println("\n enter studentId, courseId to apply");
      System.out.println("StudentId : ");  String studentId = Input.readLine();
      System.out.println("CourseId : ");String courseId = Input.readLine();

      String result = this.stub.createEnrollment(studentId,courseId);
      Message.print(result);
    } catch (IOException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }

  public void displayApplyHistory() {
    try {
      ArrayList<EnrollmentDto> enrollmentDtos = this.stub.getAllEnrollmentData();
      for (EnrollmentDto enrollmentDto : enrollmentDtos){
        System.out.println(enrollmentDto);
      }
    } catch (RemoteException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }


  public void removeApplyHistory() {
    try {
      System.out.println("enter your studentId , courseId to delete");
      System.out.println("StudentId : ");  String studentId = Input.readLine();
      System.out.println("CourseId : ");String courseId = Input.readLine();
      boolean result = this.stub.deleteEnrollment(studentId,courseId);
      Message.print(result);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public void registerCompletedCourse(){

  }
}
