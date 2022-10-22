package pRMI;

import dto.CourseDto;
import dto.StudentCourseDto;
import dto.PreCourseDto;
import dto.StudentDto;
import exception.NullDataException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInterface extends Remote {

  ArrayList<StudentDto> getAllStudentData() throws RemoteException, NullDataException;

  ArrayList<CourseDto> getAllCourseData() throws RemoteException, NullDataException;

  ArrayList<StudentCourseDto> getStudentCourseData() throws RemoteException, NullDataException;

  ArrayList<PreCourseDto> getAllPreCourseData() throws RemoteException, NullDataException;

  String searchStudentData(String studentId) throws RemoteException;

  String searchCourseData(String courseId) throws RemoteException;

  boolean updateStudentData(ArrayList<StudentDto> studentDtos) throws RemoteException;

  boolean updateCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException;

  boolean updatePreCourseData(String courseId, String preCourseId) throws RemoteException;

  boolean createStudentData(ArrayList<StudentDto> studentDtos) throws RemoteException;

  boolean createCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException;

  String createStudentCourse(String studentId, String courseId) throws RemoteException;

  String createPreCourseData(String courseId, String precourseId) throws RemoteException;

  boolean deleteStudentData(String studentId) throws RemoteException;

  boolean deleteCourseData(String courseId) throws RemoteException;

  boolean deleteStudentCourse(String studentId, String courseId) throws RemoteException;

  boolean deletePreCourse(String courseId) throws RemoteException;
}
