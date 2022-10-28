package utils;

import command.AuthCmd;
import dto.StudentDto;
import rmi.Client;

public class Session {

  public static Session session;
  private StudentDto studentDto;

  public static Session getSession() {
    if (session == null) {
      session = new Session();
    }
    return session;
  }

  public String getStudentId() {
    return this.studentDto.getStudentId();
  }

  public void register(StudentDto studentDto) {
    if (studentDto == null) {
      System.out.println("sign in failed..");
      AuthCmd.initialize();
    }
    this.studentDto = studentDto;
    System.out.printf("\n%s님 환영합니다!%n", this.studentDto.getName());
    Client.start();
  }

  public void exit() {
    Log.createLog("logOutCompleted..");
    this.studentDto = null;
    System.out.println("log out completed..");
    AuthCmd.initialize();
  }
}
