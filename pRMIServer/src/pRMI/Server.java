package pRMI;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import entity.Domain;
import exception.NullDataException;

@SuppressWarnings("serial")
public class Server extends UnicastRemoteObject implements ServerInterface {

	private DataInterface data;
	private static Server server;
	private Registry serverRegistry;
	private Registry dataServerRegistry;
	int result;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Domain> getAllStudentData() throws RemoteException, NullDataException {
		return data.getAllStudentData();
	}

	@Override
	public ArrayList<Domain> getAllCourseData() throws RemoteException, NullDataException {
		return data.getAllCourseData();
	}

}
