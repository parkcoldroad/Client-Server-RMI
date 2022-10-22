package command;

import command.menu.PreCourseMenu;
import dto.PreCourseDto;
import java.util.ArrayList;
import java.util.Arrays;
import service.PreCourseService;
import utils.Input;
import utils.Message;

public class PreCourseCmd {

  public static void initialize() {
    PreCourseMenu.printMenu();
    String choice = Input.readLine();
    Arrays.stream(PreCourseMenu.values())
        .filter(preCourseMenu -> preCourseMenu.getChoice().equals(choice))
        .findFirst()
        .ifPresentOrElse(PreCourseMenu::execute,
            () -> System.out.println("invalid enter"));
  }


  public static void registerPreCourse() {
    System.out.println("enter your courseId to register preCourse"); String courseId = Input.readLine();
    System.out.println("enter your preCourseId"); String preCourseId = Input.readLine();
    String createdResult = PreCourseService.getInstance().registerPreCourse(courseId,preCourseId);
    Message.print(createdResult);
  }

  public static void readPreCoursesList() {
    ArrayList<PreCourseDto> preCourseList = PreCourseService.getInstance().readPreCoursesList();
    Message.print(preCourseList);
  }

  public static void updatePreCourse() {
    System.out.println("enter your courseId to update preCourse");
    String courseId = Input.readLine();
    String precourseId = Input.readLine();
    boolean result = PreCourseService.getInstance().updatePreCourse(courseId,precourseId);
    Message.print(result);
  }

  public static void deletePreCourse() {
    System.out.println("enter your precourseId to delete");
    String courseId = Input.readLine();
    boolean result = PreCourseService.getInstance().deletePreCourse(courseId);
    Message.print(result);
  }
}
