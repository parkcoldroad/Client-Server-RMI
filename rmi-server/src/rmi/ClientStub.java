package rmi;

import dto.CourseDto;
import dto.EnrollmentDto;
import dto.LogDto;
import dto.PreCourseDto;
import dto.UserDto;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import response.Response;

public interface ClientStub extends Remote {

  void createLog(ArrayList<LogDto> logDto) throws RemoteException;

  Response<ArrayList<LogDto>> readLog() throws RemoteException;

  Response<UserDto> signIn(String userId, String password) throws RemoteException;

  Response<UserDto> createUserData(ArrayList<UserDto> userDtos) throws RemoteException;

  Response<Boolean> createCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException;

  Response<String> createEnrollment(String userId, String courseId) throws RemoteException;

  Response<String>  createPreCourseData(String courseId, String precourseId) throws RemoteException;

  Response<ArrayList<UserDto>> getAllUserData() throws RemoteException;

  Response<ArrayList<CourseDto>> getAllCourseData() throws RemoteException;

  Response<ArrayList<EnrollmentDto>> getEnrollmentData(String userId) throws RemoteException;

  Response<ArrayList<PreCourseDto>> getAllPreCourseData() throws RemoteException;

  Response<String> searchUserData(String userId) throws RemoteException;

  Response<String> searchCourseData(String courseId) throws RemoteException;

  Response<ArrayList<String>>  searchPreCourseData(String courseId) throws RemoteException;

  Response<Boolean> updateUserData(ArrayList<UserDto> userDtos) throws RemoteException;

  Response<Boolean> updateCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException;

  Response<Boolean> updatePreCourseData(String courseId, String preCourseId) throws RemoteException;

  Response<Boolean> deleteUserData(String userId) throws RemoteException;

  Response<Boolean> deleteCourseData(String courseId) throws RemoteException;

  Response<Boolean> deleteEnrollment(String userId, String courseId) throws RemoteException;

  Response<Boolean> deletePreCourse(String courseId,String preCourseId) throws RemoteException;
}
