package command.menu;

import command.StudentCmd;

public enum StudentMenu implements Menu {
  READ(StudentCmd::printStudentsList, "1"),
  UPDATE(StudentCmd::updateStudent, "2"),
  DELETE(StudentCmd::deleteStudent, "3"),
  SEARCH(StudentCmd::searchStudent, "4");

  private final Runnable runnable;
  private final String keyword;


  StudentMenu(Runnable runnable, String keyword) {
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

  public static void printMenu() {
    System.out.println("\n-----------------STUDENT MENU--------------------");
    System.out.println("1.Display Student ");
    System.out.println("2.Update Student ");
    System.out.println("3.Delete Student ");
    System.out.println("4.Search Student ");
  }
}
