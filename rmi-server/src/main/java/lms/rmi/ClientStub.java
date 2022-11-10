package lms.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import lms.dto.CourseDto;
import lms.dto.EnrollmentDto;
import lms.dto.LogDto;
import lms.dto.PreCourseDto;
import lms.dto.UserDto;
import lms.exception.DuplicateEnrollmentException;
import lms.exception.DuplicateUserIdException;
import lms.exception.DuplicatedCourseIdException;
import lms.exception.IllegalValueIdException;
import lms.exception.IllegalValuePwException;
import lms.exception.NullDataException;

public interface ClientStub extends Remote {

  void createLog(ArrayList<LogDto> logDto) throws RemoteException;

  ArrayList<LogDto> readLog() throws RemoteException;

  UserDto signIn(String userId, String password) throws RemoteException, IllegalValueIdException, IllegalValuePwException;

  UserDto createUserData(ArrayList<UserDto> userDtos) throws RemoteException, DuplicateUserIdException;

  boolean createCourseData(ArrayList<CourseDto> courseDtos)
      throws RemoteException, DuplicatedCourseIdException;

  String createEnrollment(String userId, String courseId)
      throws RemoteException, IllegalValueIdException, DuplicateEnrollmentException;

  String createPreCourseData(String courseId, String precourseId) throws RemoteException;

  ArrayList<UserDto> getAllUserData() throws RemoteException, NullDataException;

  ArrayList<CourseDto> getAllCourseData() throws RemoteException, NullDataException;

  ArrayList<EnrollmentDto> getEnrollmentData(String userId)
      throws RemoteException, NullDataException;

  ArrayList<PreCourseDto> getAllPreCourseData() throws RemoteException, NullDataException;

  String searchUserData(String userId) throws RemoteException, IllegalValueIdException;

  String searchCourseData(String courseId) throws RemoteException, IllegalValueIdException;

  ArrayList<String>  searchPreCourseData(String courseId) throws RemoteException, IllegalValueIdException;

  boolean updateUserData(ArrayList<UserDto> userDtos)
      throws RemoteException, DuplicateUserIdException;

  boolean updateCourseData(ArrayList<CourseDto> courseDtos)
      throws RemoteException, DuplicatedCourseIdException;

  boolean updatePreCourseData(String courseId, String preCourseId)
      throws RemoteException, IllegalValueIdException;

  boolean deleteUserData(String userId) throws RemoteException, DuplicateUserIdException;

  boolean deleteCourseData(String courseId) throws RemoteException, DuplicatedCourseIdException;

  boolean deleteEnrollment(String userId, String courseId)
      throws RemoteException, IllegalValueIdException;

  boolean deletePreCourse(String courseId,String preCourseId)
      throws RemoteException, IllegalValueIdException;
}
