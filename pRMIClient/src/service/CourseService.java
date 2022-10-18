package service;

import dto.CourseDto;
import exception.NullDataException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import menu.CourseMenu;
import pRMI.Client;
import pRMI.ClientInterface;
import utils.Input;
import utils.Message;

public class CourseService {

  private static CourseService courseService;
  private final ClientInterface stub;

  public static CourseService getInstance() {
    if (courseService == null) {
      courseService = new CourseService();
    }
    return courseService;
  }

  private CourseService() {
    this.stub = Client.getStub();
  }

  public void initialize() {
    CourseMenu.printMenu();
    String choice = Input.readLine();
    Arrays.stream(CourseMenu.values())
        .filter(courseMenu -> courseMenu.getChoice().equals(choice))
        .findFirst()
        .ifPresentOrElse(CourseMenu::execute,
            () -> System.out.println("invalid enter"));
  }

  public void create() {
    try {
      boolean result = this.stub.createCourseData(getCreationCourseScannerResult());
      Message.print(result);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void read() {
    try {
      ArrayList<CourseDto> courseList = this.stub.getAllCourseData();
      for (CourseDto courseDto : courseList){
        System.out.println(courseDto.toString());
      }
    } catch (RemoteException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }

  public void update() {

  }

  public void delete() {
    try {
      System.out.println("enter your courseId to delete");
      boolean result = this.stub.deleteCourseData(Input.readLine());
      Message.print(result);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public void search() {
    try {
      System.out.println("enter your courseId to search");
      String courseId = Input.readLine();
      String seachedResult = this.stub.searchCourseData(courseId);
      System.out.println(seachedResult);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public String getCreationCourseScannerResult() throws IOException {
    System.out.println("------------Course Information------------");
    System.out.println("Course Id : ");
    String courseId = Input.readLine();
    System.out.println("professor Last Name : ");
    String professorLName = Input.readLine();
    System.out.println("Course Name : ");
    String courseName = Input.readLine();
    System.out.println("preCoursedList : ");
    String preCourseList = Input.readLine();
    return courseId + " " + professorLName + " " + courseName + " " + preCourseList;
  }
}
