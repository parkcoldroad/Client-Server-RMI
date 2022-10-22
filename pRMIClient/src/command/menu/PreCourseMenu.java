package command.menu;

import command.PreCourseCmd;

public enum PreCourseMenu implements Menu {
  REGISTER_PRECOURSE(PreCourseCmd::registerPreCourse, "1"),
  READ_PRECOURSE(PreCourseCmd::readPreCoursesList, "2"),
  UPDATE_PRECOURSE(PreCourseCmd::updatePreCourse, "3"),
  DELETE_PRECOURSE(PreCourseCmd::deletePreCourse, "4");


  private final Runnable runnable;
  private final String keyword;


  PreCourseMenu(Runnable runnable, String keyword) {
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
    System.out.println("\n-----------------PreCourse MENU--------------------");
    System.out.println("1.Register PreCourse");
    System.out.println("2.Read PreCourse ");
    System.out.println("3.Update PreCourse");
    System.out.println("4.Delete PreCourse");
  }
}
