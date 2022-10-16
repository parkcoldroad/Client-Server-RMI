package service;

import entity.Course;
import entity.Domain;
import exception.NullDataException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import menu.CourseMenu;
import pRMI.Client;
import pRMI.ServerInterface;
import utils.Input;
import utils.Message;

public class CourseService implements Service {

  private static CourseService courseService;
  private final ServerInterface stub;

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

  @Override
  public void create() {
    try {
      boolean result = this.stub.createCourseData(getCreationCourseScannerResult());
      Message.print(result);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void read() {
    try {
      List<Course> courseList = this.stub.getAllCourseData();
      Message.print(courseList);
    } catch (RemoteException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void update() {

  }

  @Override
  public void delete() {
    try {
      System.out.println("enter your Id to delete");
      boolean result = this.stub.deleteCourseData(Input.readLine());
      Message.print(result);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void search() {
    try {
      List<Course> courseList = this.stub.getAllCourseData();
      System.out.println("enter your Id to search");
      String courseId = Input.readLine();

      Domain searchedCourse = courseList.stream()
          .filter(course -> course.match(courseId))
          .findFirst()
          .orElseThrow(() -> new NullDataException("your id is no matched"));

      Message.print(searchedCourse);
    } catch (NullDataException | IOException e) {
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
