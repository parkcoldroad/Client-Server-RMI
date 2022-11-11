package command;

import static command.Cmd.validateResponse;
import static command.UserCmd.getUserScannerResult;

import command.menu.AuthMenu;
import dto.UserDto;
import java.util.Arrays;
import response.Response;
import service.AuthService;
import utils.Input;

public class AuthCmd{

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
    Response<UserDto> signInResponse = AuthService.getInstance().signIn(userId, password);
    validateResponse(signInResponse);
  }


  public static void signUp() {
    Response<UserDto> signUpResponse = AuthService.getInstance().signUp(getUserScannerResult());
    validateResponse(signUpResponse);
  }
}
