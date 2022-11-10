package lms.command;

import lms.command.menu.PreCourseMenu;
import lms.dto.PreCourseDto;
import lms.exception.IllegalValueIdException;
import java.util.ArrayList;
import java.util.Arrays;
import lms.rmi.Client;
import lms.service.PreCourseService;
import lms.utils.Input;
import lms.utils.Log;
import lms.utils.Message;

public class PreCourseCmd {

  public static void initialize() {
    PreCourseMenu.printMenu();
    String choice = Input.readLine();
    Arrays.stream(PreCourseMenu.values())
        .filter(preCourseMenu -> preCourseMenu.getChoice().equals(choice))
        .findFirst()
        .ifPresentOrElse(PreCourseMenu::execute,
            () -> {
              System.out.println("invalid enter");
              initialize();
            });
  }


  public static void registerPreCourse() {
    System.out.println("enter your courseId to register preCourse");
    String courseId = Input.readLine();
    System.out.println("enter your preCourseId");
    String preCourseId = Input.readLine();
    String createdResult = PreCourseService.getInstance().registerPreCourse(courseId, preCourseId);
    Message.print(createdResult);
    Log.createLog("preCourseRegistrationiscompleted");
    Client.goMain();
  }

  public static void readPreCoursesList() {
    ArrayList<PreCourseDto> preCourseList = PreCourseService.getInstance().readPreCoursesList();
    Message.print(preCourseList);
    Log.createLog("readPreCourse");
    Client.goMain();
  }

  public static void searchPreCourse() {
    System.out.println("enter your courseId to search preCourse");
    String courseId = Input.readLine();
    ArrayList<String> searchedResult = null;
    try {
      searchedResult = PreCourseService.getInstance().searchPreCourse(courseId);
    } catch (IllegalValueIdException e) {
      System.out.println(e.getMessage());
      initialize();
    }
    Message.print(searchedResult.toString());
    Log.createLog("searchPreCourse");
    Client.goMain();
  }

  public static void updatePreCourse() {
    System.out.println("enter your courseId to update preCourse");
    String courseId = Input.readLine();
    System.out.println("enter your preCourseId to apply");
    String precourseId = Input.readLine();
    boolean result = false;
    try {
      result = PreCourseService.getInstance().updatePreCourse(courseId, precourseId);
    } catch (IllegalValueIdException e) {
      System.out.println(e.getMessage());
      initialize();
    }
    Message.print(result);
    Log.createLog("updatePreCourse");
    Client.goMain();
  }

  public static void deletePreCourse() {
    System.out.println("enter your courseId to delete");
    String courseId = Input.readLine();
    System.out.println("enter your preCourseId to delete");
    String preCourseId = Input.readLine();
    boolean result = false;
    try {
      result = PreCourseService.getInstance().deletePreCourse(courseId, preCourseId);
    } catch (IllegalValueIdException e) {
      System.out.println(e.getMessage());
      initialize();
    }
    Message.print(result);
    Log.createLog("deletePreCourse");
    Client.goMain();
  }
}
