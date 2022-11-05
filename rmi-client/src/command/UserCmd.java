package command;

import command.menu.UserMenu;
import dto.UserDto;
import java.util.ArrayList;
import java.util.Arrays;
import rmi.Client;
import service.UserService;
import utils.Input;
import utils.Message;
import utils.Log;

public class UserCmd {

  public static void initialize() {
    UserMenu.printMenu();
    String choice = Input.readLine();
    Arrays.stream(UserMenu.values())
        .filter(userMenu -> userMenu.getChoice().equals(choice))
        .findFirst()
        .ifPresentOrElse(UserMenu::execute,
            () ->  {System.out.println("invalid enter"); initialize();});
  }

  public static void printUsersList() {
    ArrayList<UserDto> userList = UserService.getInstance().printUsersList();
    Message.print(userList);
    Log.createLog("printList");
    Client.goMain();
  }


  public static void searchUser() {
    System.out.println("enter your userId to search");
    String userId = Input.readLine();
    String seachedResult = UserService.getInstance().searchUser(userId);
    Message.print(seachedResult);
    Log.createLog("searchuser");
    Client.goMain();
  }

  public static void updateUser() {
    boolean result = UserService.getInstance().updateUser(getUserScannerResult());
    Message.print(result);
    Log.createLog("updateuser");
    Client.goMain();
  }

  public static void deleteUser() {
    System.out.println("enter userId to delete");
    String userId = Input.readLine();
    boolean result = UserService.getInstance().deleteUser(userId);
    Message.print(result);
    Log.createLog("deleteuser");
    Client.goMain();
  }


  public static ArrayList<UserDto> getUserScannerResult() {
    System.out.println("------------user Information------------");
    System.out.println("user Id : "); String userId = Input.readLine();
    System.out.println("user Password : "); String password = Input.readLine();
    System.out.println("user Name : "); String userName = Input.readLine();
    System.out.println("Department : "); String department = Input.readLine();
    System.out.println("Gender : "); String gender = Input.readLine();

    ArrayList<UserDto> userDtos = new ArrayList<>();
    UserDto userDto = new UserDto();
    userDto.setUserId(userId);
    userDto.setPassword(password);
    userDto.setName(userName);
    userDto.setDepartment(department);
    userDto.setGender(gender);

    userDtos.add(userDto);
    return userDtos;
  }
}
