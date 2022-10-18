package pRMI;

import dto.CourseDto;
import dto.EnrolmentDto;
import dto.StudentDto;
import exception.NullDataException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInterface extends Remote {

	ArrayList<StudentDto> getAllStudentData() throws RemoteException, NullDataException;

	ArrayList<CourseDto> getAllCourseData() throws RemoteException, NullDataException;

	ArrayList<EnrolmentDto> getAllEnrolmentData() throws RemoteException, NullDataException;

	String searchStudentData(String studentId) throws RemoteException;

	String searchCourseData(String courseId) throws RemoteException;

	boolean createStudentData(String studentInfo) throws RemoteException;

	boolean createCourseData(String courseInfo) throws RemoteException;
	boolean createEnrolment(String enrolmentInfo) throws RemoteException;

	boolean deleteStudentData(String studentId) throws RemoteException;

	boolean deleteCourseData(String courseId) throws RemoteException;
	boolean deleteEnrolment(String studentId) throws RemoteException;
}
