package service;

import dto.UserDto;
import exception.DuplicateUserIdException;
import exception.IllegalValueIdException;
import exception.NullDataException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import rmi.Client;
import rmi.ClientStub;
import utils.Input;

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
