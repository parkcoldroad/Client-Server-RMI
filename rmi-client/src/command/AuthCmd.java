package command;

import static command.StudentCmd.getStudentScannerResult;

import command.menu.AuthMenu;
import java.util.Arrays;
import service.AuthService;
import utils.Input;
import utils.Message;
import utils.Session;

public class AuthCmd {


  public static void initialize() {
    AuthMenu.printMenu();
    String choice = Input.readLine();
    Arrays.stream(AuthMenu.values())
        .filter(authMenu -> authMenu.getChoice().equals(choice))
        .findFirst()
        .ifPresentOrElse(AuthMenu::execute,
            () -> System.out.println("invalid enter"));
  }

  public static void signIn() {
    System.out.println("enter your studentId to sign in");
    String studentId = Input.readLine();
    System.out.println("enter your password");
    String password = Input.readLine();
    Session.getSession().register(AuthService.getInstance().signIn(studentId, password));
  }


  public static void signUp() {
    boolean signUpResult = AuthService.getInstance().signUp(getStudentScannerResult());
    Message.print(signUpResult);
    initialize();
  }
}
