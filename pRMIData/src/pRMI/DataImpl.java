package pRMI;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dao.CourseDao;
import dao.StudentDao;
import entity.Course;
import entity.Student;

@SuppressWarnings("serial")
public class DataImpl extends UnicastRemoteObject implements DataInterface {

	private static DataImpl dataImpl;
	private StudentDao studentDao;
	private CourseDao courseDao;
	private Registry registry;

	public static DataImpl getInstance() {
		if (dataImpl == null) {
			try {
				dataImpl = new DataImpl();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return dataImpl;

	}

	private DataImpl() throws RemoteException {
		try {
			registry = LocateRegistry.createRegistry(9123);
			studentDao = new StudentDao("src/pRMI/Students.txt");
			courseDao = new CourseDao("src/pRMI/Courses.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start() {
		try {
			registry.bind("dataServer", dataImpl);
			System.out.println("data Server is ready");
		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Student> getAllStudentData() throws RemoteException {
		return studentDao.getAllStudentRecords();
	}

	@Override
	public ArrayList<Course> getAllCourseData() throws RemoteException {
		return courseDao.getAllCourseRecords();
	}

}
