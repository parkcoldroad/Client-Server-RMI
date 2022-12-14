package rmi;

import dto.CourseDto;
import dto.EnrollmentDto;
import dto.LogDto;
import dto.PreCourseDto;
import dto.UserDto;
import exception.DuplicateEnrollmentException;
import exception.DuplicateUserIdException;
import exception.DuplicatedCourseIdException;
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

  boolean createCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException, DuplicatedCourseIdException;

  String createEnrollment(String userId, String courseId) throws RemoteException, DuplicateEnrollmentException;

  String createPreCourseData(String courseId, String precourseId) throws RemoteException;

  ArrayList<UserDto> getAllUserData() throws RemoteException, NullDataException;

  ArrayList<CourseDto> getAllCourseData() throws RemoteException, NullDataException;

  ArrayList<String> getCompletedCourseList(String userId) throws RemoteException;

  ArrayList<EnrollmentDto> getEnrollmentData(String userId) throws RemoteException, NullDataException;

  ArrayList<PreCourseDto> getAllPreCourseData() throws RemoteException, NullDataException;

  String searchUserData(String userId) throws RemoteException, IllegalValueIdException;

  String searchCourseData(String userId) throws RemoteException, IllegalValueIdException;

  ArrayList<String> searchPreCourse(String courseId) throws RemoteException;

  boolean updateUserData(ArrayList<UserDto> userDtos) throws RemoteException, DuplicateUserIdException;

  boolean updateCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException, DuplicatedCourseIdException;

  boolean updatePreCourseData(String courseId, String preCourseId)
      throws RemoteException, IllegalValueIdException;

  boolean deleteUserData(String userId) throws RemoteException, DuplicateUserIdException;

  boolean deleteCourseData(String courseId) throws RemoteException, DuplicatedCourseIdException;

  boolean deleteEnrollment(String userId, String courseId) throws RemoteException, IllegalValueIdException;

  boolean deletePreCourse(String courseId,String preCourseID) throws RemoteException, IllegalValueIdException;
}
