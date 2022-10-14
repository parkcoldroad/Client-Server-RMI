package pRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import entity.Domain;
import exception.NullDataException;

public interface ServerInterface extends Remote{
	public ArrayList<Domain> getAllStudentData() throws RemoteException, NullDataException;
	
	public ArrayList<Domain> getAllCourseData() throws RemoteException, NullDataException;
}
