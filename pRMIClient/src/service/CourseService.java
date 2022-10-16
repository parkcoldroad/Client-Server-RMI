package service;

import entity.Domain;
import exception.NullDataException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import menu.CourseMenu;
import pRMI.Client;
import pRMI.ServerInterface;
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
    try {
      String choice = Client.getBufferedReader().readLine().trim();
      Arrays.stream(CourseMenu.values())
          .filter(courseMenu -> courseMenu.getChoice().equals(choice))
          .findFirst()
          .ifPresentOrElse(CourseMenu::execute,
              () -> System.out.println("invalid enter"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
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
      List<Domain> courseList = this.stub.getAllCourseData();
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

  }

  @Override
  public void search() {
    try {
      List<Domain> courseList = this.stub.getAllCourseData();
      String courseId = Client.getBufferedReader().readLine().trim();

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
    String courseId = Client.getBufferedReader().readLine().trim();
    System.out.println("professor Last Name : ");
    String professorLName = Client.getBufferedReader().readLine().trim();
    System.out.println("Course Name : ");
    String courseName = Client.getBufferedReader().readLine().trim();
    System.out.println("preCoursedList : ");
    String preCourseList = Client.getBufferedReader().readLine().trim();
    return courseId + " " + professorLName + " " + courseName + " " + preCourseList;
  }
}
