package pRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import entity.Domain;
import exception.NullDataException;

public interface DataInterface extends Remote{
	public ArrayList<Domain> getAllStudentData() throws RemoteException, NullDataException;
	public ArrayList<Domain> getAllCourseData() throws RemoteException, NullDataException;
	public boolean createStudentData(String studentInfo) throws RemoteException;
	public boolean createCourseData(String courseInfo) throws RemoteException;
	public void deleteStudentData(String studentId) throws RemoteException;
	public void deleteCourseData(String courseId) throws RemoteException;
	}
