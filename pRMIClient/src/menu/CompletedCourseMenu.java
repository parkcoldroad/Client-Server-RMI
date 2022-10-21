package menu;

import service.CompletedCourseService;
import service.PreCourseService;

public enum CompletedCourseMenu implements Menu {
  REGISTER_COMPLETED_COURSE(() -> CompletedCourseService.getInstance().registerCompletedCourse(),"1"),
  DISPLAY_COMPLETED_COURSE(() -> CompletedCourseService.getInstance().printCompletedCoursesList(),"2");


  private final Runnable runnable;
  private final String keyword;


  CompletedCourseMenu(Runnable runnable,String keyword) {
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
    System.out.println("\n-----------------CompletedCourse MENU--------------------");
    System.out.println("1.Register CompletedCourse");
    System.out.println("2.Display CompletedCourse ");
  }
}
