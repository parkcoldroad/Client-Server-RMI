package pRMI;

import dto.CourseDto;
import dto.EnrolmentDto;
import dto.StudentDto;
import entity.Student;
import exception.NullDataException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ClientServer extends UnicastRemoteObject implements ClientInterface {

  private static ClientServer clientServer;
  private final Registry clientserverRegistry;
  private DataServer dataServer = DataServer.getInstance();

  private ClientServer() throws RemoteException {
    clientserverRegistry = LocateRegistry.createRegistry(14000);
  }

  public static ClientServer getInstance() {
    if (clientServer == null) {
      try {
        clientServer = new ClientServer();
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
    return clientServer;
  }

  public void start() {
    try {
      clientserverRegistry.bind("Server", clientServer);
      System.out.println("Client-Server is ready");
    } catch (RemoteException | AlreadyBoundException e) {
      e.printStackTrace();
    }

  }

  @Override
  public ArrayList<StudentDto> getAllStudentData() throws RemoteException, NullDataException {
    ArrayList<Student> allStudentData = dataServer.getAllStudentData();
    ArrayList<StudentDto> studentDtos = new ArrayList<>();

    for(int i=0;i< allStudentData.size();i++){
      studentDtos.get(i).setStudentId(allStudentData.get(i).getId());
      studentDtos.get(i).setName(allStudentData.get(i).getName());
      studentDtos.get(i).setDepartment(allStudentData.get(i).getDepartment());
      studentDtos.get(i).setCompletedCoursesList(allStudentData.get(i).getCompletedCourses());
    }
    return  studentDtos;
  }

  @Override
  public ArrayList<CourseDto> getAllCourseData() throws RemoteException, NullDataException {
    return null;
  }

  @Override
  public ArrayList<EnrolmentDto> getAllEnrolmentData() throws RemoteException, NullDataException {
    return null;
  }

  @Override
  public String searchStudentData(String studentId) throws RemoteException {
    return null;
  }

  @Override
  public String searchCourseData(String courseId) throws RemoteException {
    return null;
  }

  @Override
  public boolean createStudentData(String studentInfo) throws RemoteException {
    return false;
  }

  @Override
  public boolean createCourseData(String courseInfo) throws RemoteException {
    return false;
  }

  @Override
  public boolean createEnrolment(String enrolmentInfo) throws RemoteException {
    return false;
  }

  @Override
  public boolean deleteStudentData(String studentId) throws RemoteException {
    return false;
  }

  @Override
  public boolean deleteCourseData(String courseId) throws RemoteException {
    return false;
  }

  @Override
  public boolean deleteEnrolment(String studentId) throws RemoteException {
    return false;
  }
}
