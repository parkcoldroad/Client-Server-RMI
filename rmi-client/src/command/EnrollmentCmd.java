package command;

import static command.Cmd.validateResponse;

import command.menu.EnrollmentMenu;
import dto.CourseDto;
import dto.EnrollmentDto;
import java.util.ArrayList;
import java.util.Arrays;
import response.Response;
import service.CourseService;
import service.EnrollmentService;
import utils.Input;
import utils.Message;

public class EnrollmentCmd {

  public static void initialize() {
    EnrollmentMenu.printMenu();
    String choice = Input.readLine();
    Arrays.stream(EnrollmentMenu.values())
        .filter(enrollmentMenu -> enrollmentMenu.getChoice().equals(choice))
        .findFirst()
        .ifPresentOrElse(EnrollmentMenu::execute,
            () ->  {System.out.println("invalid enter"); initialize();});
  }

  public static void applyCourse() {
    System.out.println("------------Courses List------------");
    Response<ArrayList<CourseDto>> courseResponse = CourseService.getInstance().printCoursesList();
    Message.print(courseResponse.getData());

    System.out.println("\n enter courseId to apply");
    System.out.println("CourseId : "); String courseId = Input.readLine();
    Response<String> applyResponse = EnrollmentService.getInstance().applyCourse(courseId);
    validateResponse(applyResponse);
  }

  public static void displayApplyHistory() {
    Response<ArrayList<EnrollmentDto>> displayResponse = EnrollmentService.getInstance().displayApplyHistory();
    validateResponse(displayResponse);
  }


  public static void removeApplyHistory() {
    System.out.println("enter your courseId to delete");
    System.out.println("CourseId : "); String courseId = Input.readLine();
    Response<Boolean> removeResponse = EnrollmentService.getInstance().removeApplyHistory(courseId);
    validateResponse(removeResponse);
  }

}
