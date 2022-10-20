package menu;

import service.StudentService;

public enum StudentMenu implements Menu {
  CREATE(() -> StudentService.getInstance().createStudentInformation(),"1"),
  READ(() -> StudentService.getInstance().readStudentInformation(),"2"),
  UPDATE(() -> StudentService.getInstance().updateStudentInformation(),"3"),
  DELETE(() -> StudentService.getInstance().deleteStudentInformation(),"4"),
  SEARCH(() -> StudentService.getInstance().searchStudentInformation(),"5");

  private final Runnable runnable;
  private final String keyword;


  StudentMenu(Runnable runnable,String keyword) {
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
    System.out.println("\n-----------------Student MENU--------------------");
    System.out.println("1.Create");
    System.out.println("2.Read");
    System.out.println("3.Update");
    System.out.println("4.Delete");
    System.out.println("5.Search");
  }
}
