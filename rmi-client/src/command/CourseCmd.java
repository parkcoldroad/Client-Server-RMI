package command;

import static command.Cmd.validateResponse;

import command.menu.CourseMenu;
import dto.CourseDto;
import java.util.ArrayList;
import java.util.Arrays;
import response.Response;
import service.CourseService;
import utils.Input;

public class CourseCmd {

  public static void initialize() {
    CourseMenu.printMenu();
    String choice = Input.readLine();
    Arrays.stream(CourseMenu.values())
        .filter(courseMenu -> courseMenu.getChoice().equals(choice))
        .findFirst()
        .ifPresentOrElse(CourseMenu::execute,
            () -> {System.out.println("invalid enter");initialize();});
  }

  public static void createCourse() {
    Response<Boolean> createResponse = CourseService.getInstance().createCourse(getCourseScannerResult());
    validateResponse( createResponse);
  }


  public static void printCoursesList() {
    Response<ArrayList<CourseDto>> printResponse = CourseService.getInstance().printCoursesList();
    validateResponse( printResponse);
  }

  public static void updateCourse() {
    Response<Boolean> updateResponse = CourseService.getInstance().updateCourse(getCourseScannerResult());
    validateResponse( updateResponse);
  }

  public static void deleteCourse() {
    System.out.println("enter your courseId to delete"); String courseId = Input.readLine();
    Response<Boolean> deleteResponse = CourseService.getInstance().deleteCourse(courseId);
    validateResponse( deleteResponse);
  }

  public static void searchCourse() {
    System.out.println("enter your courseId to search");String courseId = Input.readLine();
    Response<String> searchResponse = CourseService.getInstance().searchCourse(courseId);
    validateResponse( searchResponse);
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
