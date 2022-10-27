package service;

import dto.StudentDto;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import rmi.Client;
import rmi.ClientStub;

public class AuthService {

  private static AuthService authService;
  private final ClientStub clientStub;

  public static AuthService getInstance() {
    if (authService == null) {
      authService = new AuthService();
    }
    return authService;
  }

  private AuthService() {
    this.clientStub = Client.getStub();
  }

  public StudentDto signIn(String studentId, String password){
    try {
      return this.clientStub.signIn(studentId,password);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean signUp(ArrayList<StudentDto> studentScannerResult) {
    try {
      return this.clientStub.createStudentData(studentScannerResult);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
