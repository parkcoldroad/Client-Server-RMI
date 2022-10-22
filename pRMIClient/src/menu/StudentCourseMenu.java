package menu;

import service.StudentCourseService;

public enum StudentCourseMenu implements Menu {
  APPLY_COURSE(() -> StudentCourseService.getInstance().applyCourse(),"1"),
  DISPLAY_APPLY_HISTORY(() -> StudentCourseService.getInstance().displayApplyHistory(),"2"),
  REMOVE_APPLY_HISTORY(() -> StudentCourseService.getInstance().removeApplyHistory(),"3");


  private final Runnable runnable;
  private final String keyword;


  StudentCourseMenu(Runnable runnable,String keyword) {
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
    System.out.println("\n-----------------ENROLLMENT MENU--------------------");
    System.out.println("1.Apply Course");
    System.out.println("2.Display Apply History");
    System.out.println("3.Remove Apply History");
  }
}
