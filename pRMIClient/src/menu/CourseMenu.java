package menu;

import service.CourseService;

public enum CourseMenu implements Menu {
  CREATE(() -> CourseService.getInstance().createCourse(),"1"),
  READ(() -> CourseService.getInstance().readCourses(),"2"),
  UPDATE(() -> CourseService.getInstance().updateCourse(),"3"),
  DELETE(() -> CourseService.getInstance().deleteCourse(),"4"),
  SEARCH(() -> CourseService.getInstance().searchCourse(),"5");


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
    System.out.println("3.Update");
    System.out.println("4.Delete");
    System.out.println("5.Search");
  }
}
