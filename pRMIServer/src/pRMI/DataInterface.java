package pRMI;

import entity.Course;
import entity.Enrollment;
import entity.Student;
import exception.NullDataException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DataInterface extends Remote {

   ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException;

   ArrayList<Course> getAllCourseData() throws RemoteException, NullDataException;

   ArrayList<Enrollment> getAllEnrollmentData() throws RemoteException, NullDataException;

   String searchStudentData(String studentId) throws RemoteException;

   String searchCourseData(String courseId) throws RemoteException;

   boolean createStudentData(String studentInfo) throws RemoteException;

   boolean createCourseData(String courseInfo) throws RemoteException;
   boolean createEnrollment(String enrolmentInfo) throws RemoteException;

   boolean deleteStudentData(String studentId) throws RemoteException;

   boolean deleteCourseData(String courseId) throws RemoteException;
   boolean deleteEnrollment(String studentId) throws RemoteException;
}
