package rmi;

import dao.CourseDao;
import dao.EnrollmentDao;
import dao.PreCourseDao;
import dao.UserDao;
import dto.CourseDto;
import dto.EnrollmentDto;
import dto.LogDto;
import dto.PreCourseDto;
import dto.UserDto;
import exception.DuplicateEnrollmentException;
import exception.DuplicateUserIdException;
import exception.DuplicatedCourseIdException;
import exception.IllegalValueIdException;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import log.Log;

@SuppressWarnings("serial")
public class DataImpl extends UnicastRemoteObject implements DataStub {

  private static DataImpl dataImpl;
  private UserDao userDao;
  private CourseDao courseDao;
  private EnrollmentDao enrollmentDao;
  private PreCourseDao preCourseDao;

  private Log log;
  private Registry registry;

  public static DataImpl getInstance() {
    if (dataImpl == null) {
      try {
        dataImpl = new DataImpl();
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
    return dataImpl;

  }

  private DataImpl() throws RemoteException {
    try {
      registry = LocateRegistry.createRegistry(9123);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void start() {
    try {
      registry.bind("dataServer", dataImpl);

      courseDao = new CourseDao();
      userDao = new UserDao();
      enrollmentDao = new EnrollmentDao();
      preCourseDao = new PreCourseDao();
      log = new Log();
      System.out.println("data Server is ready");
    } catch (RemoteException | AlreadyBoundException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void createLog(ArrayList<LogDto> logList) {
    log.createLog(logList);
  }

  @Override
  public ArrayList<LogDto> readLog() {
    return log.readLog();
  }

  @Override
  public ArrayList<UserDto> signIn(String userId) throws RemoteException, IllegalValueIdException {
      return userDao.signIn(userId);
  }


  @Override
  public UserDto createUserData(ArrayList<UserDto> userDtos) throws RemoteException, DuplicateUserIdException {
      return userDao.createUserRecords(userDtos);
  }

  @Override
  public boolean createCourseData(ArrayList<CourseDto> courseData) throws RemoteException, DuplicatedCourseIdException {
    return courseDao.createCourseRecord(courseData);
  }

  public String createEnrollment(String userId, String courseId) throws RemoteException, DuplicateEnrollmentException {
      return enrollmentDao.createEnrollment(userId, courseId);
  }

  @Override
  public String createPreCourseData(String courseId, String preCourseId) throws RemoteException {
    return preCourseDao.createPreCourseRecord(courseId, preCourseId);
  }

  @Override
  public ArrayList<UserDto> getAllUserData() throws RemoteException {
    return userDao.readAllUserRecords();
  }

  @Override
  public ArrayList<CourseDto> getAllCourseData() throws RemoteException {
    return courseDao.readAllCourseRecords();
  }

  @Override
  public ArrayList<String> getCompletedCourseList(String userId) throws RemoteException {
    return enrollmentDao.getCompletedCourseList(userId);
  }

  @Override
  public ArrayList<EnrollmentDto> getEnrollmentData(String userId) throws RemoteException {
    return enrollmentDao.getEnrollmentData(userId);
  }

  @Override
  public ArrayList<PreCourseDto> getAllPreCourseData() throws RemoteException {
    return preCourseDao.readAllPreCourseRecords();
  }

  @Override
  public String searchUserData(String userId) throws RemoteException, IllegalValueIdException {
      return userDao.searchUserRecords(userId);
  }

  @Override
  public String searchCourseData(String userId) throws RemoteException, IllegalValueIdException {
      return courseDao.searchCourseRecord(userId);
  }

  @Override
  public ArrayList<String> searchPreCourse(String courseId) throws RemoteException {
    return preCourseDao.searchPreCourse(courseId);
  }

  @Override
  public boolean updateUserData(ArrayList<UserDto> userDtos) throws RemoteException, DuplicateUserIdException {
    return userDao.updateUserRecord(userDtos);
  }

  @Override
  public boolean updateCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException, DuplicatedCourseIdException {
    return courseDao.updateCourseRecord(courseDtos);
  }

  @Override
  public boolean updatePreCourseData(String courseId, String preCourseId) throws RemoteException, IllegalValueIdException {
    return preCourseDao.updatePreCourseRecord(courseId, preCourseId);
  }

  @Override
  public boolean deleteUserData(String userId) throws RemoteException, DuplicateUserIdException {
    return userDao.deleteUserRecords(userId);
  }


  @Override
  public boolean deleteCourseData(String courseId) throws RemoteException, DuplicatedCourseIdException {
    return courseDao.deleteCourseRecord(courseId);
  }


  public boolean deleteEnrollment(String userId, String courseId) throws RemoteException, IllegalValueIdException {
    return enrollmentDao.deleteEnrollment(userId, courseId);
  }

  @Override
  public boolean deletePreCourse(String courseId, String preCourseId) throws RemoteException, IllegalValueIdException {
    return preCourseDao.deletePreCourseRecord(courseId, preCourseId);
  }

}
