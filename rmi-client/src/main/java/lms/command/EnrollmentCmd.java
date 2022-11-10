package lms.command;

import lms.command.menu.EnrollmentMenu;
import lms.dto.CourseDto;
import lms.dto.EnrollmentDto;
import lms.exception.DuplicateEnrollmentException;
import lms.exception.IllegalValueIdException;
import java.util.ArrayList;
import java.util.Arrays;
import lms.rmi.Client;
import lms.service.CourseService;
import lms.service.EnrollmentService;
import lms.utils.Input;
import lms.utils.Log;
import lms.utils.Message;

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
    ArrayList<CourseDto> courseList = CourseService.getInstance().printCoursesList();
    Message.print(courseList);

    System.out.println("\n enter courseId to apply");
    System.out.println("CourseId : "); String courseId = Input.readLine();
    String result = null;
    try {
      result = EnrollmentService.getInstance().applyCourse(courseId);
    } catch (IllegalValueIdException | DuplicateEnrollmentException e) {
      System.out.println(e.getMessage());
      initialize();
    }
    Message.print(result);
    Log.createLog("Enrollmentiscompleted");
    Client.goMain();
  }

  public static void displayApplyHistory() {
    ArrayList<EnrollmentDto> enrollmentList = EnrollmentService.getInstance().displayApplyHistory();
    Message.print(enrollmentList);
    Log.createLog("displayEnrollmentHistory");
    Client.goMain();
  }


  public static void removeApplyHistory() {
    System.out.println("enter your courseId to delete");
    System.out.println("CourseId : "); String courseId = Input.readLine();
    boolean result = false;
    try {
      result = EnrollmentService.getInstance().removeApplyHistory(courseId);
    } catch (IllegalValueIdException e) {
      System.out.println(e.getMessage());
      initialize();
    }
    Message.print(result);
    Log.createLog("removeEnrollmentHistory");
    Client.goMain();
  }
}
