package service;

import dto.PreCourseDto;
import exception.NullDataException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import menu.PreCourseMenu;
import pRMI.Client;
import pRMI.ClientInterface;
import utils.Input;
import utils.Message;

public class LoginService {

  private static LoginService loginService;
  private final ClientInterface stub;

  public static LoginService getInstance() {
    if (loginService == null) {
      loginService = new LoginService();
    }
    return loginService;
  }

  private LoginService() {
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


  public void registerPreCourse() {
    try {
      System.out.println("enter your courseId to register preCourse");
      String courseId = Input.readLine();
      System.out.println("enter your precourseId");
      String precourseId = Input.readLine();
      String createdResult = this.stub.createPreCourseData(courseId, precourseId);
      System.out.println(createdResult);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void readPreCoursesList() {
    try {
      ArrayList<PreCourseDto> preCourseList = this.stub.getAllPreCourseData();
      for (PreCourseDto preCourseDto : preCourseList) {
        System.out.println(preCourseDto);
      }
    } catch (RemoteException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }

  public void updatePreCourse() {
    try {
      System.out.println("enter your courseId to update preCourse");
      String courseId = Input.readLine();
      String precourseId = Input.readLine();

      boolean result = this.stub.updatePreCourseData(courseId, precourseId);
      Message.print(result);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void deletePreCourse() {
    try {
      System.out.println("enter your precourseId to delete");
      String courseId = Input.readLine();

      boolean result = this.stub.deletePreCourse(courseId);
      Message.print(result);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

}
