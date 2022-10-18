package pRMI;

import entity.Course;
import entity.Enrollment;
import entity.Student;
import exception.NullDataException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class DataServer extends UnicastRemoteObject implements DataInterface {

  private DataInterface data;
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
      data = (DataInterface) dataServerRegistry.lookup("dataServer");
      System.out.println("Data-Server is ready");
    } catch (RemoteException | NotBoundException e) {
      e.printStackTrace();
    }

  }

  @Override
  public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException {
    return data.getAllStudentData();
  }

  @Override
  public ArrayList<Course> getAllCourseData() throws RemoteException, NullDataException {
    return data.getAllCourseData();
  }

  @Override
  public ArrayList<Enrollment> getAllEnrollmentData() throws RemoteException, NullDataException {
    return data.getAllEnrollmentData();
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
  public boolean createStudentData(String studentInfo) throws RemoteException {
    return data.createStudentData(studentInfo);
  }

  @Override
  public boolean createCourseData(String courseInfo) throws RemoteException {
    return data.createCourseData(courseInfo);
  }

  @Override
  public boolean createEnrollment(String studentId) throws RemoteException {
    return data.createEnrollment(studentId);
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
  public boolean deleteEnrollment(String studentId) throws RemoteException {
    return data.deleteEnrollment(studentId);
  }

}
