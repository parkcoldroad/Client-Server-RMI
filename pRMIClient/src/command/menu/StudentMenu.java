package command.menu;

import command.StudentCmd;

public enum StudentMenu implements Menu {
  CREATE(StudentCmd::createStudent, "1"),
  READ(StudentCmd::printStudentsList, "2"),
  UPDATE(StudentCmd::updateStudent, "3"),
  DELETE(StudentCmd::deleteStudent, "4"),
  SEARCH(StudentCmd::searchStudent, "5");

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
    System.out.println("1.Create Student ");
    System.out.println("2.Display Student ");
    System.out.println("3.Update Student ");
    System.out.println("4.Delete Student ");
    System.out.println("5.Search Student ");
  }
}
