package rmi;

import dto.CourseDto;
import dto.EnrollmentDto;
import dto.PreCourseDto;
import dto.StudentDto;
import exception.NullDataException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Stub extends Remote {

  boolean signIn(String studentId, String password) throws RemoteException;

  boolean createStudentData(ArrayList<StudentDto> studentDtos) throws RemoteException;

  boolean createCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException;

  String createEnrollment(String studentId, String courseId) throws RemoteException;

  String createPreCourseData(String courseId, String precourseId) throws RemoteException;

  ArrayList<StudentDto> getAllStudentData() throws RemoteException, NullDataException;

  ArrayList<CourseDto> getAllCourseData() throws RemoteException, NullDataException;

  ArrayList<EnrollmentDto> getEnrollmentData(String studentId)
      throws RemoteException, NullDataException;

  ArrayList<PreCourseDto> getAllPreCourseData() throws RemoteException, NullDataException;

  String searchStudentData(String studentId) throws RemoteException;

  String searchCourseData(String courseId) throws RemoteException;

  boolean updateStudentData(ArrayList<StudentDto> studentDtos) throws RemoteException;

  boolean updateCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException;

  boolean updatePreCourseData(String courseId, String preCourseId) throws RemoteException;

  boolean deleteStudentData(String studentId) throws RemoteException;

  boolean deleteCourseData(String courseId) throws RemoteException;

  boolean deleteEnrollment(String studentId, String courseId) throws RemoteException;

  boolean deletePreCourse(String courseId) throws RemoteException;
}
