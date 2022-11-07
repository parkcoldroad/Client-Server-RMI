package rmi;

import dto.CourseDto;
import dto.EnrollmentDto;
import dto.LogDto;
import dto.PreCourseDto;
import dto.UserDto;
import exception.DuplicateUserIdException;
import exception.IllegalValueIdException;
import exception.NullDataException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DataStub extends Remote {

  void createLog(ArrayList<LogDto> logList) throws RemoteException;

  ArrayList<LogDto> readLog() throws RemoteException;

  ArrayList<UserDto> signIn(String userId) throws RemoteException, IllegalValueIdException;

  UserDto createUserData(ArrayList<UserDto> userDtos) throws RemoteException, DuplicateUserIdException;

  boolean createCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException;

  String createEnrollment(String userId, String courseId) throws RemoteException;

  String createPreCourseData(String courseId, String precourseId) throws RemoteException;

  ArrayList<UserDto> getAllUserData() throws RemoteException, NullDataException;

  ArrayList<CourseDto> getAllCourseData() throws RemoteException, NullDataException;

  ArrayList<String> getCompletedCourseList(String userId) throws RemoteException;

  ArrayList<EnrollmentDto> getEnrollmentData(String userId) throws RemoteException, NullDataException;

  ArrayList<PreCourseDto> getAllPreCourseData() throws RemoteException, NullDataException;

  String searchUserData(String userId) throws RemoteException, IllegalValueIdException;

  String searchCourseData(String userId) throws RemoteException, IllegalValueIdException;

  ArrayList<String> searchPreCourse(String courseId) throws RemoteException, IllegalValueIdException;

  boolean updateUserData(ArrayList<UserDto> userDtos) throws RemoteException;

  boolean updateCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException;

  boolean updatePreCourseData(String courseId, String preCourseId) throws RemoteException;

  boolean deleteUserData(String userId) throws RemoteException;

  boolean deleteCourseData(String courseId) throws RemoteException;

  boolean deleteEnrollment(String userId, String courseId) throws RemoteException;

  boolean deletePreCourse(String courseId,String preCourseID) throws RemoteException;
}
