package menu;

import java.io.IOException;
import pRMI.Client;
import service.CourseService;

public enum CourseMenu implements Menu {
  CREATE(() -> CourseService.getInstance().create(),"1"),
  READ(() -> CourseService.getInstance().read(),"2"),
  UPDATE(() -> CourseService.getInstance().update(),"3"),
  DELETE(() -> CourseService.getInstance().delete(),"4"),
  SEARCH(() -> CourseService.getInstance().search(),"5");


  private final Runnable runnable;
  private final String keyword;


  CourseMenu(Runnable runnable,String keyword) {
    this.runnable = runnable;
    this.keyword = keyword;
  }
  @Override
  public void execute() {
    this.runnable.run();
  }
  @Override
  public String getChoice() {
    return this.keyword;
  }

  public static void printMenu(){
    System.out.println("\n-----------------MENU--------------------");
    System.out.println("1.Create");
    System.out.println("2.Read");
    System.out.println("3.Search");
    System.out.println("4.Update");
    System.out.println("5.Delete");
  }
}
