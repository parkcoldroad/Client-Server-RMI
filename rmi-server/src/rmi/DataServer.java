package rmi;

import dto.CourseDto;
import dto.EnrollmentDto;
import dto.PreCourseDto;
import dto.StudentDto;
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
  public ArrayList<StudentDto> signIn(String studentId, String password) throws RemoteException {
    return data.signIn(studentId,password);
  }

  @Override
  public boolean createStudentData(ArrayList<StudentDto> studentDtos) throws RemoteException {
    return data.createStudentData(studentDtos);
  }

  @Override
  public boolean createCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException {
    return data.createCourseData(courseDtos);
  }

  @Override
  public String createEnrollment(String studentId, String courseId) throws RemoteException {
    return data.createEnrollment(studentId,courseId);
  }

  @Override
  public String createPreCourseData(String courseId, String precourseId) throws RemoteException {
    return data.createPreCourseData(courseId,precourseId);
  }

  @Override
  public ArrayList<StudentDto> getAllStudentData() throws RemoteException, NullDataException {
    return data.getAllStudentData();
  }

  @Override
  public ArrayList<CourseDto> getAllCourseData() throws RemoteException, NullDataException {
    return data.getAllCourseData();
  }

  @Override
  public ArrayList<String> getCompletedCourseList(String studentId) throws RemoteException {
    return data.getCompletedCourseList(studentId);
  }

  @Override
  public ArrayList<EnrollmentDto> getEnrollmentData(String studentId) throws RemoteException, NullDataException {
    return data.getEnrollmentData(studentId);
  }

  @Override
  public ArrayList<PreCourseDto> getAllPreCourseData() throws RemoteException, NullDataException {
    return data.getAllPreCourseData();
  }

  @Override
  public String searchStudentData(String studentId) throws RemoteException {
    return data.searchStudentData(studentId);
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
  public boolean updateStudentData(ArrayList<StudentDto> studentDtos) throws RemoteException {
    return data.updateStudentData(studentDtos);
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
  public boolean deleteStudentData(String studentId) throws RemoteException {
    return data.deleteStudentData(studentId);
  }

  @Override
  public boolean deleteCourseData(String courseId) throws RemoteException {
    return data.deleteCourseData(courseId);
  }

  @Override
  public boolean deleteEnrollment(String studentId, String courseId) throws RemoteException {
    return data.deleteEnrollment(studentId,courseId);
  }

  @Override
  public boolean deletePreCourse(String courseId) throws RemoteException {
    return data.deletePreCourse(courseId);
  }
}
