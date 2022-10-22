package service;

import java.util.Arrays;
import command.menu.PreCourseMenu;
import rmi.Client;
import rmi.Stub;
import utils.Input;

public class AuthService {

  private static AuthService authService;
  private final Stub stub;

  public static AuthService getInstance() {
    if (authService == null) {
      authService = new AuthService();
    }
    return authService;
  }

  private AuthService() {
    this.stub = Client.getStub();
  }

  public void initialize() {
    PreCourseMenu.printMenu();
    String choice = Input.readLine();
    Arrays.stream(PreCourseMenu.values())
        .filter(preCourseMenu -> preCourseMenu.getChoice().equals(choice))
        .findFirst()
        .ifPresentOrElse(PreCourseMenu::execute,
            () -> System.out.println("invalid enter"));
  }

}
