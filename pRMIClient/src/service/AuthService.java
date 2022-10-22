package service;

import dto.StudentDto;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import rmi.Client;
import rmi.Stub;

public class AuthService {

  private static AuthService authService;
  private final Stub stub;

  public static AuthService getInstance() {
    if (authService == null) {
      authService = new AuthService();
    }
    return authService;
  }

  private AuthService() {
    this.stub = Client.getStub();
  }

  public boolean signIn(String studentId, String password){
    try {
      return this.stub.signIn(studentId,password);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean signUp(ArrayList<StudentDto> studentScannerResult) {
    try {
      return this.stub.createStudentData(studentScannerResult);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
