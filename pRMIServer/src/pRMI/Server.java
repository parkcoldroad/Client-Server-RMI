package pRMI;

import entity.Course;
import entity.Student;
import exception.NullDataException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements ServerInterface {

	private DataInterface data;
	private static Server server;
	private final Registry serverRegistry;
	private final Registry dataServerRegistry;

	private Server() throws RemoteException {
		serverRegistry = LocateRegistry.createRegistry(9000);
		dataServerRegistry = LocateRegistry.getRegistry(9123);
	}

	public static Server getInstance() {
		if (server == null) {
			try {
				server = new Server();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return server;
	}

	public void start() {
		try {
			data = (DataInterface) dataServerRegistry.lookup("dataServer");
			serverRegistry.bind("Server", server);

			System.out.println("Server is ready");
		} catch (RemoteException | NotBoundException | AlreadyBoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException {
		return data.getAllStudentData();
	}

	@Override
	public ArrayList<Course> getAllCourseData() throws RemoteException, NullDataException {
		return data.getAllCourseData();
	}

	@Override
	public boolean createStudentData(String studentInfo) throws RemoteException {
		return data.createStudentData(studentInfo);
	}

	@Override
	public boolean createCourseData(String courseInfo) throws RemoteException {
		return data.createCourseData(courseInfo);
	}

	@Override
	public boolean deleteStudentData(String studentId) throws RemoteException {
		return data.deleteStudentData(studentId);
	}

	@Override
	public boolean deleteCourseData(String courseId) throws RemoteException {
		return data.deleteCourseData(courseId);
	}

}
