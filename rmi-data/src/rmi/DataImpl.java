package rmi;

import dao.CourseDao;
import dao.EnrollmentDao;
import dao.PreCourseDao;
import dao.StudentDao;
import dto.CourseDto;
import dto.EnrollmentDto;
import dto.LogDto;
import dto.PreCourseDto;
import dto.StudentDto;
import exception.DuplicateUserIdException;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import log.LogDao;

@SuppressWarnings("serial")
public class DataImpl extends UnicastRemoteObject implements DataStub {

  private static DataImpl dataImpl;
  private StudentDao studentDao;
  private CourseDao courseDao;
  private EnrollmentDao enrollmentDao;
  private PreCourseDao preCourseDao;

  private LogDao logDao;
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
      studentDao = new StudentDao();
      enrollmentDao = new EnrollmentDao();
      preCourseDao = new PreCourseDao();
      logDao = new LogDao();
      System.out.println("data Server is ready");
    } catch (RemoteException | AlreadyBoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean createStudentData(ArrayList<StudentDto> studentDtos) throws RemoteException {
    try {
      return studentDao.createStudentRecords(studentDtos);
    } catch (DuplicateUserIdException e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  public void createLog(ArrayList<LogDto> logList) {
    logDao.createLog(logList);
  }

  @Override
  public ArrayList<LogDto> readLog() {return logDao.readLog();
  }

  @Override
  public ArrayList<StudentDto> signIn(String studentId, String password) throws RemoteException {
    try {
      return studentDao.signIn(studentId);
    } catch (DuplicateUserIdException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean createCourseData(ArrayList<CourseDto> courseData) throws RemoteException {
    return courseDao.createCourseRecord(courseData);
  }

  public String createEnrollment(String studentId, String courseId) throws RemoteException {
    enrollmentDao.createEnrollment(studentId, courseId);
    return "Enrollment is completed";
  }

  @Override
  public String createPreCourseData(String courseId, String precourseId) throws RemoteException {
    return preCourseDao.createPreCourseRecord(courseId, precourseId);
  }

  @Override
  public ArrayList<StudentDto> getAllStudentData() throws RemoteException {
    return studentDao.readAllStudentRecords();
  }

  @Override
  public ArrayList<CourseDto> getAllCourseData() throws RemoteException {
    return courseDao.readAllCourseRecords();
  }

  @Override
  public ArrayList<String> getCompletedCourseList(String studentId) throws RemoteException {
    return enrollmentDao.getCompletedCourseList(studentId);
  }

  @Override
  public ArrayList<EnrollmentDto> getEnrollmentData(String studentId) throws RemoteException {
    return enrollmentDao.getEnrollmentData(studentId);
  }

  @Override
  public ArrayList<PreCourseDto> getAllPreCourseData() throws RemoteException {
    return preCourseDao.readAllPreCourseRecords();
  }

  @Override
  public String searchStudentData(String studentId) throws RemoteException {
    return studentDao.searchStudentRecords(studentId);
  }

  @Override
  public String searchCourseData(String courseId) throws RemoteException {
    return courseDao.searchCourseRecord(courseId);
  }

  @Override
  public ArrayList<String> searchPreCourse(String courseId) throws RemoteException {
    return preCourseDao.searchPreCourse(courseId);
  }

  @Override
  public boolean updateStudentData(ArrayList<StudentDto> studentDtos) throws RemoteException {
    return studentDao.updateStudentRecord(studentDtos);
  }

  @Override
  public boolean updateCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException {
    return courseDao.updateCourseRecord(courseDtos);
  }

  @Override
  public boolean updatePreCourseData(String courseId, String preCourseId) throws RemoteException {
    return preCourseDao.updatePreCourseRecord(courseId, preCourseId);
  }

  @Override
  public boolean deleteStudentData(String studentId) throws RemoteException {
    return studentDao.deleteStudentRecords(studentId);
  }


  @Override
  public boolean deleteCourseData(String courseId) throws RemoteException {
    return courseDao.deleteCourseRecord(courseId);
  }


  public boolean deleteEnrollment(String studentId, String courseId) throws RemoteException {
    return enrollmentDao.deleteEnrollment(studentId, courseId);
  }

  @Override
  public boolean deletePreCourse(String courseId) throws RemoteException {
    return preCourseDao.deleteCourseRecord(courseId);
  }

}
