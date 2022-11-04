package utils;

import command.AuthCmd;
import dto.UserDto;
import rmi.Client;

public class Session {

  public static Session session;
  private UserDto userDto;

  public static Session getSession() {
    if (session == null) {
      session = new Session();
    }
    return session;
  }

  public String getUserId() {
    return this.userDto.getUserId();
  }

  public void register(UserDto userDto) {
    if (userDto == null) {
      System.out.println("Invalid id or pw , Please re-enter.");
      AuthCmd.initialize();
    }
    this.userDto = userDto;
    System.out.printf("\n%s님 환영합니다!%n", this.userDto.getName());
    Client.start();
  }

  public void exit() {
    Log.createLog("logOutCompleted..");
    this.userDto = null;
    System.out.println("log out completed..");
    AuthCmd.initialize();
  }
}
