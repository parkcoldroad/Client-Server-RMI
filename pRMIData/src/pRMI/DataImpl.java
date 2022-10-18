package pRMI;

import dao.CourseDao;
import dao.EnrollmentDao;
import dao.StudentDao;
import entity.Course;
import entity.Enrollment;
import entity.Student;
import exception.NullDataException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class DataImpl extends UnicastRemoteObject implements DataInterface {

  private static DataImpl dataImpl;
  private StudentDao studentDao;
  private CourseDao courseDao;
  private EnrollmentDao enrollmentDao;
  private Registry registry;

  private static BufferedReader bufferedReader;

  private static BufferedWriter bufferedWriter;

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

  public static BufferedReader getBufferedReader(String fileName) {
    try {
      return bufferedReader = new BufferedReader(new FileReader(fileName));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public static BufferedWriter getBufferedWriter(String fileName) {
    try {
      return bufferedWriter = new BufferedWriter(new FileWriter(fileName,false));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  private DataImpl() throws RemoteException {
    try {
      registry = LocateRegistry.createRegistry(9123);
      this.studentDao = new StudentDao();
      this.courseDao = new CourseDao();
      this.enrollmentDao = new EnrollmentDao();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void start() {
    try {
      registry.bind("dataServer", dataImpl);
      System.out.println("data Server is ready");
    } catch (RemoteException | AlreadyBoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public ArrayList<Student> getAllStudentData() throws RemoteException {
    return studentDao.getAllStudentRecords();
  }

  @Override
  public ArrayList<Course> getAllCourseData() throws RemoteException {
    return courseDao.getAllCourseRecords();
  }

  @Override
  public ArrayList<Enrollment> getAllEnrollmentData() throws RemoteException {
    return enrollmentDao.getAllEnrollmentData();
  }

  @Override
  public String searchStudentData(String studentId) throws RemoteException {
    return studentDao.searchStudentRecords(studentId);
  }

  @Override
  public String searchCourseData(String courseId) throws RemoteException {
    return courseDao.searchCourseRecords(courseId);
  }

  @Override
  public boolean createStudentData(String studentInfo) throws RemoteException {
    return studentDao.createStudentRecords(studentInfo);
  }

  @Override
  public boolean createCourseData(String courseInfo) throws RemoteException {
    return courseDao.createCourseRecords(courseInfo);
  }

  @Override
  public boolean createEnrollment(String enrolmentInfo) throws RemoteException {
    return enrollmentDao.createEnrolment(enrolmentInfo);
  }

  @Override
  public boolean deleteStudentData(String studentId) throws RemoteException {
    return studentDao.deleteStudentRecords(studentId);
  }

  @Override
  public boolean deleteCourseData(String courseId) throws RemoteException {
    return courseDao.deleteCourseRecords(courseId);
  }

  @Override
  public boolean deleteEnrollment(String studentId) throws RemoteException {
    return enrollmentDao.deleteEnrolment(studentId);
  }


}
