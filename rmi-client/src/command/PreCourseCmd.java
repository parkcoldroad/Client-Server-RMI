package command;

import static command.Cmd.validateResponse;

import command.menu.PreCourseMenu;
import dto.PreCourseDto;
import java.util.ArrayList;
import java.util.Arrays;
import response.Response;
import service.PreCourseService;
import utils.Input;

public class PreCourseCmd {

  public static void initialize() {
    PreCourseMenu.printMenu();
    String choice = Input.readLine();
    Arrays.stream(PreCourseMenu.values())
        .filter(preCourseMenu -> preCourseMenu.getChoice().equals(choice))
        .findFirst()
        .ifPresentOrElse(PreCourseMenu::execute,
            () -> {System.out.println("invalid enter"); initialize();});
  }


  public static void registerPreCourse() {
    System.out.println("enter your courseId to register preCourse");String courseId = Input.readLine();
    System.out.println("enter your preCourseId");String preCourseId = Input.readLine();
    Response<String> registerResponse = PreCourseService.getInstance().registerPreCourse(courseId, preCourseId);
    validateResponse(registerResponse);
  }

  public static void readPreCoursesList() {
    Response<ArrayList<PreCourseDto>> readResponse = PreCourseService.getInstance().readPreCoursesList();
    validateResponse(readResponse);
  }

  public static void searchPreCourse() {
    System.out.println("enter your courseId to search preCourse");
    String courseId = Input.readLine();
    Response<ArrayList<String>> searchResponse = PreCourseService.getInstance().searchPreCourse(courseId);
    validateResponse(searchResponse);
  }

  public static void updatePreCourse() {
    System.out.println("enter your courseId to update preCourse");String courseId = Input.readLine();
    System.out.println("enter your preCourseId to apply");String precourseId = Input.readLine();
    Response<Boolean> updateResposne = PreCourseService.getInstance().updatePreCourse(courseId, precourseId);
    validateResponse(updateResposne);
  }

  public static void deletePreCourse() {
    System.out.println("enter your courseId to delete");String courseId = Input.readLine();
    System.out.println("enter your preCourseId to delete");String preCourseId = Input.readLine();
    Response<Boolean> deleteResponse = PreCourseService.getInstance().deletePreCourse(courseId, preCourseId);
    validateResponse(deleteResponse);
  }


}
