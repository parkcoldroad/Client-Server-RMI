package command;

import static command.UserCmd.getUserScannerResult;

import command.menu.AuthMenu;
import java.util.Arrays;
import service.AuthService;
import utils.Input;
import utils.Log;
import utils.Session;

public class AuthCmd {


  public static void initialize() {
    AuthMenu.printMenu();
    String choice = Input.readLine();
    Arrays.stream(AuthMenu.values())
        .filter(authMenu -> authMenu.getChoice().equals(choice))
        .findFirst()
        .ifPresentOrElse(AuthMenu::execute,
            () -> {System.out.println("invalid enter"); initialize();});
  }

  public static void signIn() {
    System.out.println("enter your userId to sign in"); String userId = Input.readLine();
    System.out.println("enter your password"); String password = Input.readLine();
    Session.getSession().register(AuthService.getInstance().signIn(userId, password));
    Log.createLog("signInCompleted");
  }


  public static void signUp() {
    Session.getSession().register(AuthService.getInstance().signUp(getUserScannerResult()));
    Log.createLog("signUpCompleted");
  }
}
