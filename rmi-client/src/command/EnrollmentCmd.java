package command;

import command.menu.EnrollmentMenu;
import dto.EnrollmentDto;
import java.util.ArrayList;
import java.util.Arrays;
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
            () -> System.out.println("invalid enter"));
  }

  public static void applyCourse() {
    System.out.println("------------Courses List------------");
    CourseCmd.printCoursesList();

    System.out.println("\n enter studentId, courseId to apply");
    System.out.println("StudentId : "); String studentId = Input.readLine();
    System.out.println("CourseId : "); String courseId = Input.readLine();

    String result = EnrollmentService.getInstance().applyCourse(studentId, courseId);
    Message.print(result);
  }

  public static void displayApplyHistory() {
    System.out.println("enter studentId to display applying List");
    String studentId = Input.readLine();
    ArrayList<EnrollmentDto> enrollmentList = EnrollmentService.getInstance().displayApplyHistory(studentId);
    Message.print(enrollmentList);
  }


  public static void removeApplyHistory() {
    System.out.println("enter your studentId , courseId to delete");
    System.out.println("StudentId : "); String studentId = Input.readLine();
    System.out.println("CourseId : "); String courseId = Input.readLine();
    boolean result = EnrollmentService.getInstance().removeApplyHistory(studentId, courseId);
    Message.print(result);
  }
}
