package lms.utils;

import lms.command.AuthCmd;
import lms.dto.UserDto;
import lms.rmi.Client;

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
    this.userDto = userDto;
    System.out.printf("\n%s님 환영합니다!%n",this.userDto.getName());
    Client.goMain();
  }

  public void exit() {
    Log.createLog("logOutCompleted..");
    this.userDto = null;
    System.out.println("lms.log out completed..");
    AuthCmd.initialize();
  }
}
