package pRMI;

import entity.Course;
import entity.Student;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import exception.NullDataException;

public interface ServerInterface extends Remote{
	public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException;
	public ArrayList<Course> getAllCourseData() throws RemoteException, NullDataException;
	public boolean createStudentData(String studentInfo) throws RemoteException;
	public boolean createCourseData(String courseInfo) throws RemoteException;
	public boolean deleteStudentData(String studentId) throws RemoteException;
	public boolean deleteCourseData(String courseId) throws RemoteException;
}
