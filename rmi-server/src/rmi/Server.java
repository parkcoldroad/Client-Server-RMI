package rmi;

import dto.CourseDto;
import dto.EnrollmentDto;
import dto.LogDto;
import dto.PreCourseDto;
import dto.UserDto;
import exception.NullDataException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Optional;

public class Server extends UnicastRemoteObject implements ClientStub {

  private static Server clientServer;
  private final Registry clientserverRegistry;

  private DataServer dataServer;

  private Server() throws RemoteException {
    clientserverRegistry = LocateRegistry.createRegistry(14000);
  }

  public static Server getInstance() {
    if (clientServer == null) {
      try {
        clientServer = new Server();
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
    return clientServer;
  }

  public void start() {
    try {
      clientserverRegistry.bind("Server", clientServer);
      dataServer = DataServer.getInstance();
      System.out.println("Server is ready");

    } catch (RemoteException | AlreadyBoundException e) {
      e.printStackTrace();
    }

  }

  @Override
  public ArrayList<LogDto> readLog() throws RemoteException {
    return dataServer.readLog();
  }

  @Override
  public void createLog(ArrayList<LogDto> logDto) throws RemoteException {
    dataServer.createLog(logDto);
  }


  @Override
  public UserDto signIn(String userId, String password) throws RemoteException {
    ArrayList<UserDto> userList = dataServer.signIn(userId);
    if(userList == null){
      return null;
    }

    Optional<UserDto> OptionalUser = userList.stream()
        .filter(user -> user.getUserId().equals(userId) && user.getPassword().equals(password))
        .findFirst();

    return OptionalUser.orElse(null);
  }

  public UserDto createUserData(ArrayList<UserDto> userDtos) throws RemoteException {
    return dataServer.createUserData(userDtos);
  }

  @Override
  public boolean createCourseData(ArrayList<CourseDto> courseData) throws RemoteException {
    return dataServer.createCourseData(courseData);
  }

  public String createEnrollment(String userId, String courseId) throws RemoteException {
    boolean isReady = true;
    ArrayList<String> preCourseLists = dataServer.searchPreCourse(courseId);
    ArrayList<String> completedCourseList = dataServer.getCompletedCourseList(userId);

    for (String precourse : preCourseLists) {
      isReady = completedCourseList.contains(precourse);
    }
    if (isReady) {
      return dataServer.createEnrollment(userId, courseId);
    }
    return "Enrollment is failed.. you didn't take preCourses";
  }

  @Override
  public String createPreCourseData(String courseId, String precourseId) throws RemoteException {
    return dataServer.createPreCourseData(courseId, precourseId);
  }

  public ArrayList<UserDto> getAllUserData() throws RemoteException, NullDataException {
    return dataServer.getAllUserData();
  }

  @Override
  public ArrayList<CourseDto> getAllCourseData() throws RemoteException, NullDataException {
    return dataServer.getAllCourseData();
  }

  @Override
  public ArrayList<EnrollmentDto> getEnrollmentData(String userId) throws RemoteException, NullDataException {
    return dataServer.getEnrollmentData(userId);
  }

  @Override
  public ArrayList<PreCourseDto> getAllPreCourseData() throws RemoteException, NullDataException {
    return dataServer.getAllPreCourseData();
  }

  public String searchUserData(String userId) throws RemoteException {
    return dataServer.searchUserData(userId);
  }

  @Override
  public String searchCourseData(String courseId) throws RemoteException {
    return dataServer.searchCourseData(courseId);
  }

  @Override
  public ArrayList<String> searchPreCourseData(String courseId) throws RemoteException {
    return dataServer.searchPreCourse(courseId);
  }

  public boolean updateUserData(ArrayList<UserDto> userDtos) throws RemoteException {
    return dataServer.updateUserData(userDtos);
  }

  @Override
  public boolean updateCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException {
    return dataServer.updateCourseData(courseDtos);
  }

  @Override
  public boolean updatePreCourseData(String courseId, String preCourseId) throws RemoteException {
    return dataServer.updatePreCourseData(courseId, preCourseId);
  }

  public boolean deleteUserData(String userId) throws RemoteException {
    return dataServer.deleteUserData(userId);
  }


  @Override
  public boolean deleteCourseData(String courseId) throws RemoteException {
    return dataServer.deleteCourseData(courseId);
  }


  public boolean deleteEnrollment(String userId, String courseId) throws RemoteException {
    return dataServer.deleteEnrollment(userId, courseId);
  }

  @Override
  public boolean deletePreCourse(String courseId, String preCourseId) throws RemoteException {
    return dataServer.deletePreCourse(courseId, preCourseId);
  }
}
