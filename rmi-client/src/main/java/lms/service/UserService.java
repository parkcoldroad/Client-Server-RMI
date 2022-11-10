package lms.service;

import lms.dto.UserDto;
import lms.exception.DuplicateUserIdException;
import lms.exception.IllegalValueIdException;
import lms.exception.NullDataException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import lms.rmi.Client;
import lms.rmi.ClientStub;

public class UserService {

  private static UserService userService;
  private final ClientStub clientStub;

  public static UserService getInstance() {
    if (userService == null) {
      userService = new UserService();
    }
    return userService;
  }

  private UserService() {
    this.clientStub = Client.getStub();
  }


  public ArrayList<UserDto> printUsersList() {
    try {
      return this.clientStub.getAllUserData();
    } catch (RemoteException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }


  public String searchUser(String UserId) throws IllegalValueIdException {
    try {
      return this.clientStub.searchUserData(UserId);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean updateUser(ArrayList<UserDto> UserScannerResult) throws DuplicateUserIdException {
    try {
      return this.clientStub.updateUserData(UserScannerResult);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean deleteUser(String userId) throws DuplicateUserIdException {
    try {
      return this.clientStub.deleteUserData(userId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

}
