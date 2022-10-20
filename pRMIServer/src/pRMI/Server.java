package pRMI;

import dao.PreCourseDao;
import dao.StudentDao;
import dto.CourseDto;
import dto.EnrollmentDto;
import dto.PreCourseDto;
import dto.StudentDto;
import exception.NullDataException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import dao.CourseDao;
import dao.EnrollmentDao;

public class Server extends UnicastRemoteObject implements ClientInterface {

  private static Server clientServer;
  private final Registry clientserverRegistry;

  private CourseDao courseDao;
  private StudentDao studentDao;
  private EnrollmentDao enrollmentDao;

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
      enrollmentDao = new EnrollmentDao();
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
  public String createEnrollment(String studentId, String courseId) throws RemoteException {
    //조건 , 성공 , 실패
    enrollmentDao.createEnrollment(studentId, courseId);
    return "Enrollment is completed";
  }

  @Override
  public String createPreCourseData(String courseId, String precourseId) throws RemoteException {
     return preCourseDao.createPreCourseRecord(courseId,precourseId);
  }


  @Override
  public ArrayList<StudentDto> getAllStudentData() throws RemoteException, NullDataException {
    return studentDao.readAllStudentRecords();
  }

  @Override
  public ArrayList<CourseDto> getAllCourseData() throws RemoteException, NullDataException {
    return courseDao.readAllCourseRecords();
  }

  @Override
  public ArrayList<EnrollmentDto> getAllEnrollmentData() throws RemoteException, NullDataException {
    return enrollmentDao.getAllEnrollmentData();
  }

  @Override
  public ArrayList<PreCourseDto> getAllPreCourseData() throws RemoteException, NullDataException {
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
    return preCourseDao.updatePreCourseRecord(courseId,preCourseId);
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
  public boolean deleteEnrollment(String studentId, String courseId) throws RemoteException {
    return enrollmentDao.deleteEnrollment(studentId, courseId);
  }

  @Override
  public boolean deletePreCourse(String courseId) throws RemoteException {
    return preCourseDao.deleteCourseRecord(courseId);
  }
}
