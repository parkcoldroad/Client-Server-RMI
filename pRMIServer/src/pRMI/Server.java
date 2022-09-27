package pRMI;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Server extends UnicastRemoteObject implements ServerInterface {

	protected Server() throws RemoteException {
		super();
	}

	private static DataInterface data;
	int result;

	public static void main(String[] args) {

		try {
			Server server = new Server();
			Registry registry1 = LocateRegistry.createRegistry(9000);
			registry1.bind("Server", server);
			System.out.println("Server ready");

			Registry registry2 = LocateRegistry.getRegistry(9123);
			data = (DataInterface) registry2.lookup("dataServer");

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Student> getAllStudentData() throws RemoteException {
		return data.getAllStudentData();
	}

	@Override
	public ArrayList<Course> getAllCourseData() throws RemoteException {
		return data.getAllCourseData();
	}

}
