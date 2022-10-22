package service;

import dto.CourseDto;
import dto.StudentCourseDto;
import exception.NullDataException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import menu.StudentCourseMenu;
import pRMI.Client;
import pRMI.ClientInterface;
import utils.Input;
import utils.Message;

public class StudentCourseService {

  private static StudentCourseService studentCourseService;
  private final ClientInterface stub;

  public static StudentCourseService getInstance() {
    if (studentCourseService == null) {
      studentCourseService = new StudentCourseService();
    }
    return studentCourseService;
  }

  private StudentCourseService() {
    this.stub = Client.getStub();
  }

  public void initialize() {
    StudentCourseMenu.printMenu();
    String choice = Input.readLine();
    Arrays.stream(StudentCourseMenu.values())
        .filter(studentCourseMenu -> studentCourseMenu.getChoice().equals(choice))
        .findFirst()
        .ifPresentOrElse(StudentCourseMenu::execute,
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

      String result = this.stub.createStudentCourse(studentId,courseId);
      Message.print(result);
    } catch (IOException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }

  public void displayApplyHistory() {
    try {
      ArrayList<StudentCourseDto> studentCourseDtos = this.stub.getStudentCourseData();
      for (StudentCourseDto studentCourseDto : studentCourseDtos){
        System.out.println(studentCourseDto);
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
      boolean result = this.stub.deleteStudentCourse(studentId,courseId);
      Message.print(result);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }
}
