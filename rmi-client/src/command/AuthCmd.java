package command;

import static command.UserCmd.getUserScannerResult;

import command.menu.AuthMenu;
import dto.UserDto;
import exception.DuplicateUserIdException;
import exception.IllegalValueIdException;
import exception.IllegalValuePwException;
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
    try {
      Session.getSession().register(AuthService.getInstance().signIn(userId, password));
    } catch (IllegalValueIdException | IllegalValuePwException e) {
     System.out.println(e.getMessage());
     initialize();
    }
    Log.createLog("signInCompleted");
  }


  public static void signUp() {
    UserDto userDto = null;
    try {
      userDto = AuthService.getInstance().signUp(getUserScannerResult());
    } catch (DuplicateUserIdException e) {
      System.out.println(e.getMessage());
      initialize();
    }
    Session.getSession().register(userDto);
    Log.createLog("signUpCompleted");
  }
}
