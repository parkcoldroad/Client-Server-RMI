package pRMI;

import dao.CourseDao;
import dao.EnrollmentDao;
import dto.CourseDto;
import dto.EnrollmentDto;
import dto.StudentDto;
import exception.NullDataException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements ClientInterface {

  private static Server clientServer;
  private final Registry clientserverRegistry;

  private CourseDao courseDao;
//  private StudentDao studentDao;
  private EnrollmentDao enrollmentDao;

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
//      studentDao = new StudentDao();
      enrollmentDao = new EnrollmentDao();
    } catch (RemoteException | AlreadyBoundException e) {
      e.printStackTrace();
    }

  }


  @Override
  public boolean createStudentData(ArrayList<StudentDto> studentDtos) throws RemoteException {
    return false;
  }

  @Override
  public boolean createCourseData(ArrayList<CourseDto> courseData) throws RemoteException {
    return courseDao.createCourseRecord(courseData);
  }

  @Override
  public boolean createEnrollment(ArrayList<EnrollmentDto> enrollmentDtos) throws RemoteException {
    return false;
  }
  

  @Override
  public ArrayList<StudentDto> getAllStudentData() throws RemoteException, NullDataException {
//    ArrayList<Student> allStudentData = dataServer.getAllStudentData();
    ArrayList<StudentDto> studentDtos = new ArrayList<>();
//
//    for (Student student : allStudentData) {
//      StudentDto studentDto = new StudentDto();
//
//      studentDto.setStudentId(student.getId());
//      studentDto.setName(student.getName());
//      studentDto.setDepartment(student.getDepartment());
//      studentDto.setCompletedCoursesList(student.getCompletedCourses());
//
//      studentDtos.add(studentDto);
//    }
    return studentDtos;
  }

  @Override
  public ArrayList<CourseDto> getAllCourseData() throws RemoteException, NullDataException {
    return  courseDao.readAllCourseRecords();
  }

  @Override
  public ArrayList<EnrollmentDto> getAllEnrollmentData() throws RemoteException, NullDataException {
    return null;
  }

  @Override
  public String searchStudentData(String studentId) throws RemoteException {
//    return dataServer.searchStudentData(studentId);
    return "";
  }

  @Override
  public String searchCourseData(String courseId) throws RemoteException {
    return courseDao.searchCourseRecord(courseId);
  }

  @Override
  public boolean updateStudentData(ArrayList<StudentDto> studentDtos) throws RemoteException {
    return false;
  }

  @Override
  public boolean updateCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException {
    return courseDao.updateCourseRecord(courseDtos);
  }

  @Override
  public boolean deleteStudentData(String studentId) throws RemoteException {
//    return dataServer.deleteStudentData(studentId);
    return  true;
  }


  @Override
  public boolean deleteCourseData(String courseId) throws RemoteException {
    return courseDao.deleteCourseRecord(courseId);
  }


  @Override
  public boolean deleteEnrollment(String studentId) throws RemoteException {
    return false;
  }
}
