package rmi;

import dto.CourseDto;
import dto.EnrollmentDto;
import dto.LogDto;
import dto.PreCourseDto;
import dto.UserDto;
import exception.NullDataException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DataServer extends UnicastRemoteObject implements DataStub {

  private DataStub data;
  private static DataServer dataServer;
  private final Registry dataServerRegistry;

  private DataServer() throws RemoteException {
    dataServerRegistry = LocateRegistry.getRegistry(9123);
  }

  public static DataServer getInstance() {
    if (dataServer == null) {
      try {
        dataServer = new DataServer();
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
    return dataServer;
  }

  public void start() {
    try {
      data = (DataStub) dataServerRegistry.lookup("dataServer");
      System.out.println("Data-Server is ready");
    } catch (RemoteException | NotBoundException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void createLog(ArrayList<LogDto> logDto) throws RemoteException {
    data.createLog(logDto);
  }

  @Override
  public ArrayList<LogDto> readLog() throws RemoteException {
    return data.readLog();
  }

  @Override
  public ArrayList<UserDto> signIn(String userId) throws RemoteException {
    return data.signIn(userId);
  }

  @Override
  public UserDto createUserData(ArrayList<UserDto> userDtos) throws RemoteException {
    return data.createUserData(userDtos);
  }

  @Override
  public boolean createCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException {
    return data.createCourseData(courseDtos);
  }

  @Override
  public String createEnrollment(String userId, String courseId) throws RemoteException {
    return data.createEnrollment(userId,courseId);
  }

  @Override
  public String createPreCourseData(String courseId, String preCourseId) throws RemoteException {
    return data.createPreCourseData(courseId,preCourseId);
  }

  @Override
  public ArrayList<UserDto> getAllUserData() throws RemoteException, NullDataException {
    return data.getAllUserData();
  }

  @Override
  public ArrayList<CourseDto> getAllCourseData() throws RemoteException, NullDataException {
    return data.getAllCourseData();
  }

  @Override
  public ArrayList<String> getCompletedCourseList(String userId) throws RemoteException {
    return data.getCompletedCourseList(userId);
  }

  @Override
  public ArrayList<EnrollmentDto> getEnrollmentData(String userId) throws RemoteException, NullDataException {
    return data.getEnrollmentData(userId);
  }

  @Override
  public ArrayList<PreCourseDto> getAllPreCourseData() throws RemoteException, NullDataException {
    return data.getAllPreCourseData();
  }

  @Override
  public String searchUserData(String userId) throws RemoteException {
    return data.searchUserData(userId);
  }

  @Override
  public String searchCourseData(String courseId) throws RemoteException {
    return data.searchCourseData(courseId);
  }

  @Override
  public ArrayList<String> searchPreCourse(String courseId) throws RemoteException {
    return data.searchPreCourse(courseId);
  }

  @Override
  public boolean updateUserData(ArrayList<UserDto> userDtos) throws RemoteException {
    return data.updateUserData(userDtos);
  }

  @Override
  public boolean updateCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException {
    return data.updateCourseData(courseDtos);
  }

  @Override
  public boolean updatePreCourseData(String courseId, String preCourseId) throws RemoteException {
    return data.updatePreCourseData(courseId,preCourseId);
  }

  @Override
  public boolean deleteUserData(String userId) throws RemoteException {
    return data.deleteUserData(userId);
  }

  @Override
  public boolean deleteCourseData(String courseId) throws RemoteException {
    return data.deleteCourseData(courseId);
  }

  @Override
  public boolean deleteEnrollment(String userId, String courseId) throws RemoteException {
    return data.deleteEnrollment(userId,courseId);
  }

  @Override
  public boolean deletePreCourse(String courseId,String preCourseId) throws RemoteException {
    return data.deletePreCourse(courseId,preCourseId);
  }
}
