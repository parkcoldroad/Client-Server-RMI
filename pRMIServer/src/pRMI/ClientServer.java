package pRMI;

import dto.CourseDto;
import dto.EnrollmentDto;
import dto.StudentDto;
import entity.Course;
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
  private final DataServer dataServer = DataServer.getInstance();

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

    for (Student student : allStudentData) {
      StudentDto studentDto = new StudentDto();

      studentDto.setStudentId(student.getId());
      studentDto.setName(student.getName());
      studentDto.setDepartment(student.getDepartment());
      studentDto.setCompletedCoursesList(student.getCompletedCourses());

      studentDtos.add(studentDto);
    }
    return studentDtos;
  }

  @Override
  public ArrayList<CourseDto> getAllCourseData() throws RemoteException, NullDataException {
    ArrayList<Course> allCourseData = dataServer.getAllCourseData();
    ArrayList<CourseDto> courseDtos = new ArrayList<>();

    for (Course course : allCourseData) {
      CourseDto courseDto = new CourseDto();

      courseDto.setCourseId(course.getId());
      courseDto.setpLName(course.getProfessorLastName());
      courseDto.setCourseName(course.getName());
      courseDto.setPreCourseIdList(course.getPreCoursesIdList());

      courseDtos.add(courseDto);
    }
    return courseDtos;
  }

  @Override
  public ArrayList<EnrollmentDto> getAllEnrollmentData() throws RemoteException, NullDataException {
    return null;
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
  public boolean createStudentData(String studentInfo) throws RemoteException {
    return dataServer.createStudentData(studentInfo);
  }

  @Override
  public boolean createCourseData(String courseInfo) throws RemoteException {
    return dataServer.createCourseData(courseInfo);
  }

  @Override
  public boolean createEnrollment(String enrollmentInfo) throws RemoteException {
    return false;
  }

  @Override
  public boolean deleteStudentData(String studentId) throws RemoteException {
    return dataServer.deleteStudentData(studentId);
  }

  @Override
  public boolean deleteCourseData(String courseId) throws RemoteException {
    return dataServer.deleteCourseData(courseId);
  }

  @Override
  public boolean deleteEnrollment(String studentId) throws RemoteException {
    return false;
  }
}
