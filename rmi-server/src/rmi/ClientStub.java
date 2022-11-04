package rmi;

import dto.CourseDto;
import dto.EnrollmentDto;
import dto.LogDto;
import dto.PreCourseDto;
import dto.UserDto;
import exception.NullDataException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientStub extends Remote {

  void createLog(ArrayList<LogDto> logDto) throws RemoteException;

  ArrayList<LogDto> readLog() throws RemoteException;

  UserDto signIn(String userId, String password) throws RemoteException;

  UserDto createUserData(ArrayList<UserDto> userDtos) throws RemoteException;

  boolean createCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException;

  String createEnrollment(String userId, String courseId) throws RemoteException;

  String createPreCourseData(String courseId, String precourseId) throws RemoteException;

  ArrayList<UserDto> getAllUserData() throws RemoteException, NullDataException;

  ArrayList<CourseDto> getAllCourseData() throws RemoteException, NullDataException;

  ArrayList<EnrollmentDto> getEnrollmentData(String userId)
      throws RemoteException, NullDataException;

  ArrayList<PreCourseDto> getAllPreCourseData() throws RemoteException, NullDataException;

  String searchUserData(String userId) throws RemoteException;

  String searchCourseData(String courseId) throws RemoteException;

  ArrayList<String>  searchPreCourseData(String courseId) throws RemoteException;

  boolean updateUserData(ArrayList<UserDto> userDtos) throws RemoteException;

  boolean updateCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException;

  boolean updatePreCourseData(String courseId, String preCourseId) throws RemoteException;

  boolean deleteUserData(String userId) throws RemoteException;

  boolean deleteCourseData(String courseId) throws RemoteException;

  boolean deleteEnrollment(String userId, String courseId) throws RemoteException;

  boolean deletePreCourse(String courseId,String preCourseId) throws RemoteException;
}
