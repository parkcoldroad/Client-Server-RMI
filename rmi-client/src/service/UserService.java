package service;

import dto.UserDto;
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


  public String searchUser(String UserId) {
    try {
      return this.clientStub.searchUserData(UserId);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean updateUser(ArrayList<UserDto> UserScannerResult) {
    try {
      return this.clientStub.updateUserData(UserScannerResult);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean deleteUser(String userId) {
    try {
      return this.clientStub.deleteUserData(userId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

}