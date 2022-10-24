package command;

import static command.StudentCmd.getStudentScannerResult;

import command.menu.AuthMenu;
import java.util.Arrays;
import rmi.Client;
import service.AuthService;
import utils.Input;
import utils.Message;

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

    boolean signInResult = AuthService.getInstance().signIn(studentId, password);
    if (signInResult) {
      Client.start();
    } else {
      System.out.println("SignIn failed");
      initialize();
    }

  }


  public static void signUp() {
    boolean signUpResult = AuthService.getInstance().signUp(getStudentScannerResult());
    Message.print(signUpResult);
    initialize();
  }


}
