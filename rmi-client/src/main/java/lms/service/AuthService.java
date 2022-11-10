package lms.service;

import lms.dto.UserDto;
import lms.exception.DuplicateUserIdException;
import lms.exception.IllegalValueIdException;
import lms.exception.IllegalValuePwException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import lms.rmi.Client;
import lms.rmi.ClientStub;

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

  public UserDto signIn(String userId, String password) throws IllegalValueIdException, IllegalValuePwException {
    try {
      return this.clientStub.signIn(userId,password);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public UserDto signUp(ArrayList<UserDto> userScannerResult) throws DuplicateUserIdException {
    try {
      return this.clientStub.createUserData(userScannerResult);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}