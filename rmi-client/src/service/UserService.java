package service;

import dto.UserDto;
import exception.DuplicateUserIdException;
import exception.IllegalValueIdException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import response.Response;
import rmi.Client;
import rmi.ClientStub;

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


  public Response<ArrayList<UserDto>> printUsersList() {
    try {
      return this.clientStub.getAllUserData();
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }


  public Response<String> searchUser(String UserId)  {
    try {
      return this.clientStub.searchUserData(UserId);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Response<Boolean> updateUser(ArrayList<UserDto> UserScannerResult) {
    try {
      return this.clientStub.updateUserData(UserScannerResult);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Response<Boolean> deleteUser(String userId)  {
    try {
      return this.clientStub.deleteUserData(userId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

}
