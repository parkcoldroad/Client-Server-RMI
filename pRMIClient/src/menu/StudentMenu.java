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
    System.out.println("\n-----------------STUDENT MENU--------------------");
    System.out.println("1.Create Student ");
    System.out.println("2.Display Student ");
    System.out.println("3.Update Student ");
    System.out.println("4.Delete Student ");
    System.out.println("5.Search Student ");
  }
}
