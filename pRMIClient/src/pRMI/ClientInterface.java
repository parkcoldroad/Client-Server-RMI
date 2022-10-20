package pRMI;

import dto.CourseDto;
import dto.EnrollmentDto;
import dto.StudentDto;
import exception.NullDataException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInterface extends Remote {

	ArrayList<StudentDto> getAllStudentData() throws RemoteException, NullDataException;

	ArrayList<CourseDto> getAllCourseData() throws RemoteException, NullDataException;

	ArrayList<EnrollmentDto> getAllEnrollmentData() throws RemoteException, NullDataException;

	String searchStudentData(String studentId) throws RemoteException;

	String searchCourseData(String courseId) throws RemoteException;

	boolean updateStudentData(ArrayList<StudentDto> studentDtos) throws RemoteException;
	boolean updateCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException;

	boolean createStudentData(ArrayList<StudentDto> studentDtos) throws RemoteException;

	boolean createCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException;
	String createEnrollment(String studentId,String courseId) throws RemoteException;

	boolean deleteStudentData(String studentId) throws RemoteException;

	boolean deleteCourseData(String courseId) throws RemoteException;
	boolean deleteEnrollment(String studentId, String courseId) throws RemoteException;
}
