package command;

import command.menu.CourseMenu;
import dto.CourseDto;
import java.util.ArrayList;
import java.util.Arrays;
import rmi.Client;
import service.CourseService;
import utils.Input;
import utils.Log;
import utils.Message;

public class CourseCmd {

  public static void initialize() {
    CourseMenu.printMenu();
    String choice = Input.readLine();
    Arrays.stream(CourseMenu.values())
        .filter(courseMenu -> courseMenu.getChoice().equals(choice))
        .findFirst()
        .ifPresentOrElse(CourseMenu::execute,
            () ->  {System.out.println("invalid enter"); initialize();});
  }

  public static void createCourse() {
    boolean result = CourseService.getInstance().createCourse(getCourseScannerResult());
    Message.print(result);
    Log.createLog("createCourse");
    Client.goMain();
  }

  public static void printCoursesList() {
    ArrayList<CourseDto> courseList = CourseService.getInstance().printCoursesList();
    Message.print(courseList);
    Log.createLog("printCourseList");
    Client.goMain();
  }

  public static void updateCourse() {
    boolean result =  CourseService.getInstance().updateCourse(getCourseScannerResult());
    Message.print(result);
    Log.createLog("updateCourse");
    Client.goMain();
  }

  public static void deleteCourse() {
    System.out.println("enter your courseId to delete");
    String courseId = Input.readLine();
    boolean result = CourseService.getInstance().deleteCourse(courseId);
    Message.print(result);
    Log.createLog("deleteCourse");
    Client.goMain();
  }

  public static void searchCourse() {
    System.out.println("enter your courseId to search");
    String courseId = Input.readLine();
    String seachedResult = CourseService.getInstance().searchCourse(courseId);
    Message.print(seachedResult);
    Log.createLog("searchCourse");
    Client.goMain();
  }

  public static ArrayList<CourseDto> getCourseScannerResult() {
    System.out.println("------------Course Information------------");
    System.out.println("Course Id : ");
    String courseId = Input.readLine();
    System.out.println("professor Last Name : ");
    String professorLName = Input.readLine();
    System.out.println("Course Name : ");
    String courseName = Input.readLine();

    ArrayList<CourseDto> courseDtos = new ArrayList<>();
    CourseDto courseDto = new CourseDto();
    courseDto.setCourseId(courseId);
    courseDto.setCourseName(courseName);
    courseDto.setpLName(professorLName);

    courseDtos.add(courseDto);
    return courseDtos;
  }
}
