package service;

import dto.UserDto;
import exception.DuplicateUserIdException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import response.Response;
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

  public Response<UserDto> signIn(String userId, String password) {
    try {
      return this.clientStub.signIn(userId,password);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public Response<UserDto> signUp(ArrayList<UserDto> userScannerResult){
    try {
      return this.clientStub.createUserData(userScannerResult);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
