package pRMI;

import dao.PreCourseDao;
import dao.StudentDao;
import dto.CourseDto;
import dto.StudentCourseDto;
import dto.PreCourseDto;
import dto.StudentDto;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import dao.CourseDao;
import dao.StudentCourseDao;

public class Server extends UnicastRemoteObject implements ClientInterface {

  private static Server clientServer;
  private final Registry clientserverRegistry;

  private CourseDao courseDao;
  private StudentDao studentDao;
  private StudentCourseDao studentCourseDao;
  private PreCourseDao preCourseDao;

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
      System.out.println("Server is ready");

      courseDao = new CourseDao();
      studentDao = new StudentDao();
      studentCourseDao = new StudentCourseDao();
      preCourseDao = new PreCourseDao();
    } catch (RemoteException | AlreadyBoundException e) {
      e.printStackTrace();
    }

  }


  @Override
  public boolean createStudentData(ArrayList<StudentDto> studentDtos) throws RemoteException {
    return studentDao.createStudentRecords(studentDtos);
  }

  @Override
  public boolean createCourseData(ArrayList<CourseDto> courseData) throws RemoteException {
    return courseDao.createCourseRecord(courseData);
  }

  @Override
  public String createStudentCourse(String studentId, String courseId) throws RemoteException {
    boolean isReady = true;
    ArrayList<String> preCourseLists = preCourseDao.searchPreCourse(courseId);
    ArrayList<String> completedCourseList = studentCourseDao.getCompletedCourseList(studentId);

    for (String precourse : preCourseLists) {
      isReady = completedCourseList.contains(precourse);
    }
    if (isReady) {
      studentCourseDao.createStudentCourse(studentId, courseId);
      return "Enrollment is completed";
    }
    return "Enrollment is failed";
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
  public ArrayList<StudentCourseDto> getStudentCourseData() throws RemoteException {
    return studentCourseDao.getStudentCourseData();
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


  @Override
  public boolean deleteStudentCourse(String studentId, String courseId) throws RemoteException {
    return studentCourseDao.deleteStudentCourse(studentId, courseId);
  }

  @Override
  public boolean deletePreCourse(String courseId) throws RemoteException {
    return preCourseDao.deleteCourseRecord(courseId);
  }
}
