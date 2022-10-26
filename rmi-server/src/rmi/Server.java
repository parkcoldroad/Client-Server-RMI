package rmi;

import dto.CourseDto;
import dto.EnrollmentDto;
import dto.LogDto;
import dto.PreCourseDto;
import dto.StudentDto;
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
  public boolean signIn(String studentId, String password) throws RemoteException {
    ArrayList<StudentDto> studentList = dataServer.signIn(studentId,password);

    Optional<StudentDto> OptionalStudent = studentList.stream()
        .filter(student -> student.getStudentId().equals(studentId) && student.getPassword().equals(password))
        .findFirst();

    return OptionalStudent.isPresent();
  }

  @Override
  public boolean createStudentData(ArrayList<StudentDto> studentDtos) throws RemoteException {
    return dataServer.createStudentData(studentDtos);
  }
  @Override
  public boolean createCourseData(ArrayList<CourseDto> courseData) throws RemoteException {
    return dataServer.createCourseData(courseData);
  }

  public String createEnrollment(String studentId, String courseId) throws RemoteException {
    boolean isReady = true;
    ArrayList<String> preCourseLists = dataServer.searchPreCourse(courseId);
    ArrayList<String> completedCourseList = dataServer.getCompletedCourseList(studentId);

    for (String precourse : preCourseLists) {
      isReady = completedCourseList.contains(precourse);
    }
    if (isReady) {
      return  dataServer.createEnrollment(studentId, courseId);
    }
    return "Enrollment is failed.. you didn't take preCourses";
  }

  @Override
  public String createPreCourseData(String courseId, String precourseId) throws RemoteException {
    return dataServer.createPreCourseData(courseId, precourseId);
  }

  @Override
  public ArrayList<StudentDto> getAllStudentData() throws RemoteException, NullDataException {
    return dataServer.getAllStudentData();
  }

  @Override
  public ArrayList<CourseDto> getAllCourseData() throws RemoteException, NullDataException {
    return dataServer.getAllCourseData();
  }

  @Override
  public ArrayList<EnrollmentDto> getEnrollmentData(String studentId) throws RemoteException, NullDataException {
    return dataServer.getEnrollmentData(studentId);
  }

  @Override
  public ArrayList<PreCourseDto> getAllPreCourseData() throws RemoteException, NullDataException {
    return dataServer.getAllPreCourseData();
  }

  @Override
  public String searchStudentData(String studentId) throws RemoteException {
    return dataServer.searchStudentData(studentId);
  }

  @Override
  public String searchCourseData(String courseId) throws RemoteException {
    return dataServer.searchCourseData(courseId);
  }

  @Override
  public boolean updateStudentData(ArrayList<StudentDto> studentDtos) throws RemoteException {
    return dataServer.updateStudentData(studentDtos);
  }

  @Override
  public boolean updateCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException {
    return dataServer.updateCourseData(courseDtos);
  }

  @Override
  public boolean updatePreCourseData(String courseId, String preCourseId) throws RemoteException {
    return dataServer.updatePreCourseData(courseId, preCourseId);
  }

  @Override
  public boolean deleteStudentData(String studentId) throws RemoteException {
    return dataServer.deleteStudentData(studentId);
  }


  @Override
  public boolean deleteCourseData(String courseId) throws RemoteException {
    return dataServer.deleteCourseData(courseId);
  }


  public boolean deleteEnrollment(String studentId, String courseId) throws RemoteException {
    return dataServer.deleteEnrollment(studentId, courseId);
  }

  @Override
  public boolean deletePreCourse(String courseId) throws RemoteException {
    return dataServer.deletePreCourse(courseId);
  }
}
