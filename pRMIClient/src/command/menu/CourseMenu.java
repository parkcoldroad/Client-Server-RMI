package command.menu;

import command.CourseCmd;
import command.PreCourseCmd;

public enum CourseMenu implements Menu {
  CREATE(CourseCmd::createCourse,"1"),
  READ(CourseCmd::printCoursesList,"2"),
  UPDATE(CourseCmd::updateCourse,"3"),
  DELETE(CourseCmd::deleteCourse,"4"),
  SEARCH(CourseCmd::searchCourse,"5"),
  ADMINISTRATE_PRECOURSE(PreCourseCmd::initialize,"6");


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
    System.out.println("\n-----------------COURSE MENU--------------------");
    System.out.println("1.Create Course");
    System.out.println("2.Display Course ");
    System.out.println("3.Update Course");
    System.out.println("4.Delete Course");
    System.out.println("5.Search Course");
    System.out.println("6.Administrate PreCourse");
  }
}
