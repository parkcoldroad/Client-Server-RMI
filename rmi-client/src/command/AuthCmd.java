package command;

import static command.Cmd.validateResponse;
import static command.UserCmd.getUserScannerResult;

import command.menu.AuthMenu;
import dto.UserDto;
import java.util.Arrays;
import response.Response;
import rmi.Client;
import service.AuthService;
import utils.Input;
import utils.Log;
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
            () -> {System.out.println("invalid enter"); initialize();});
  }

  public static void signIn() {
    System.out.println("enter your userId to sign in"); String userId = Input.readLine();
    System.out.println("enter your password"); String password = Input.readLine();
    Response<UserDto> signInResponse = AuthService.getInstance().signIn(userId, password);

    if (signInResponse.isSuccess()){
      Session.getSession().register(signInResponse.getData());
      Log.createLog(signInResponse.getMessage());
    }else{
      Message.print(signInResponse.getMessage());
      initialize();
    }
  }


  public static void signUp() {
    Response<UserDto> signUpResponse = AuthService.getInstance().signUp(getUserScannerResult());
    if (signUpResponse.isSuccess()){
      Session.getSession().register(signUpResponse.getData());
      Log.createLog(signUpResponse.getMessage());
    }else{
      Message.print(signUpResponse.getMessage());
      initialize();
    }
  }
}
