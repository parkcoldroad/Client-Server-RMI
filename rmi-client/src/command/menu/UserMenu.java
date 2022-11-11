package command.menu;

import command.UserCmd;
import rmi.Client;

public enum UserMenu implements Menu {
  READ(UserCmd::printUsersList, "1"),
  UPDATE(UserCmd::updateUser, "2"),
  DELETE(UserCmd::deleteUser, "3"),
  SEARCH(UserCmd::searchUser, "4"),
  GO_HOME(Client::goMain,"5");

  private final Runnable runnable;
  private final String keyword;


  UserMenu(Runnable runnable, String keyword) {
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
    System.out.println("\n-----------------User MENU--------------------");
    System.out.println("1.Display User ");
    System.out.println("2.Update User ");
    System.out.println("3.Delete User ");
    System.out.println("4.Search User ");
    System.out.println("5.Go MainMenu");
  }
}
