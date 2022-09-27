package pRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DataInterface extends Remote{
	public ArrayList<Student> getAllStudentData() throws RemoteException;
	
	public ArrayList<Course> getAllCourseData() throws RemoteException;
}
