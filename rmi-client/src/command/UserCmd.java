package command;

import static command.Cmd.validateResponse;

import command.menu.UserMenu;
import dto.UserDto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import response.Response;
import service.UserService;
import utils.Input;

public class UserCmd implements Serializable {
  private static final long serialVersionUID = 1L;
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
    Response<ArrayList<UserDto>> printResponse = UserService.getInstance().printUsersList();
    validateResponse(printResponse);
  }


  public static void searchUser() {
    System.out.println("enter your userId to search");String userId = Input.readLine();
    Response<String> searchResponse = UserService.getInstance().searchUser(userId);
    validateResponse(searchResponse);
  }

  public static void updateUser() {
    Response<Boolean> updateResponse = UserService.getInstance().updateUser(getUserScannerResult());
    validateResponse(updateResponse);
  }

  public static void deleteUser() {
    System.out.println("enter userId to delete");String userId = Input.readLine();
    Response<Boolean> deleteResponse = UserService.getInstance().deleteUser(userId);
    validateResponse(deleteResponse);
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
