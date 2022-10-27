package command;

import command.menu.EnrollmentMenu;
import dto.EnrollmentDto;
import java.util.ArrayList;
import java.util.Arrays;
import service.EnrollmentService;
import utils.Input;
import utils.Log;
import utils.Message;

public class EnrollmentCmd {

  public static void initialize() {
    EnrollmentMenu.printMenu();
    String choice = Input.readLine();
    Arrays.stream(EnrollmentMenu.values())
        .filter(enrollmentMenu -> enrollmentMenu.getChoice().equals(choice))
        .findFirst()
        .ifPresentOrElse(EnrollmentMenu::execute,
            () -> System.out.println("invalid enter"));
  }

  public static void applyCourse() {
    System.out.println("------------Courses List------------");
    CourseCmd.printCoursesList();

    System.out.println("\n enter courseId to apply");
    System.out.println("CourseId : "); String courseId = Input.readLine();
    String result = EnrollmentService.getInstance().applyCourse(courseId);
    Message.print(result);
    Log.createLog("Enrollmentiscompleted");
  }

  public static void displayApplyHistory() {
    ArrayList<EnrollmentDto> enrollmentList = EnrollmentService.getInstance().displayApplyHistory();
    Message.print(enrollmentList);
    Log.createLog("displayEnrollmentHistory");
  }


  public static void removeApplyHistory() {
    System.out.println("enter your courseId to delete");
    System.out.println("CourseId : "); String courseId = Input.readLine();
    boolean result = EnrollmentService.getInstance().removeApplyHistory(courseId);
    Message.print(result);
    Log.createLog("removeEnrollmentHistory");
  }
}
