package service;

import dto.CompletedCourseDto;
import dto.CourseDto;
import dto.PreCourseDto;
import exception.NullDataException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import menu.CompletedCourseMenu;
import menu.CourseMenu;
import pRMI.Client;
import pRMI.ClientInterface;
import utils.Input;
import utils.Message;

public class CompletedCourseService {

  private static CompletedCourseService completedCourseService;
  private final ClientInterface stub;

  public static CompletedCourseService getInstance() {
    if (completedCourseService == null) {
      completedCourseService = new CompletedCourseService();
    }
    return completedCourseService;
  }

  private CompletedCourseService() {
    this.stub = Client.getStub();
  }

  public void initialize() {
    CompletedCourseMenu.printMenu();
    String choice = Input.readLine();
    Arrays.stream(CompletedCourseMenu.values())
        .filter(completedCourseMenu -> completedCourseMenu.getChoice().equals(choice))
        .findFirst()
        .ifPresentOrElse(CompletedCourseMenu::execute,
            () -> System.out.println("invalid enter"));
  }

  public void registerCompletedCourse() {
    try {
      System.out.println("enter your studentId");
      String studentId = Input.readLine();
      System.out.println("enter your completedCourseId");
      String completedCourseId = Input.readLine();
      String createdResult = this.stub.registerCompletedCourse(studentId, completedCourseId);
      System.out.println(createdResult);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void printCompletedCoursesList() {
    try {
      ArrayList<CompletedCourseDto> completedCourseList = this.stub.readCompletedCourse();
      for (CompletedCourseDto completedCourseDto : completedCourseList) {
        System.out.println(completedCourseDto);
      }
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }

  }
}
