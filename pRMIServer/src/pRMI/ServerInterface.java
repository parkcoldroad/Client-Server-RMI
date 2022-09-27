package pRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface extends Remote{
	public ArrayList<Student> getAllStudentData() throws RemoteException;
	
	public ArrayList<Course> getAllCourseData() throws RemoteException;
}
