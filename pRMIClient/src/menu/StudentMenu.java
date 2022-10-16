package menu;

import service.StudentService;

public enum StudentMenu implements Menu {
  CREATE(() -> StudentService.getInstance().create(),"1"),
  READ(() -> StudentService.getInstance().read(),"2"),
  UPDATE(() -> StudentService.getInstance().update(),"3"),
  DELETE(() -> StudentService.getInstance().delete(),"4"),
  SEARCH(() -> StudentService.getInstance().search(),"5");

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
    System.out.println("\n-----------------MENU--------------------");
    System.out.println("1.Create");
    System.out.println("2.Read");
    System.out.println("3.Search");
    System.out.println("4.Update");
    System.out.println("5.Delete");
  }
}
