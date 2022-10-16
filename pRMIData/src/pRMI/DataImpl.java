package pRMI;

import dao.CourseDao;
import dao.StudentDao;
import entity.Course;
import entity.Student;
import exception.NullDataException;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

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
			studentDao = new StudentDao("pRMIData/src/entity/Students.txt");
			courseDao = new CourseDao("pRMIData/src/entity/Courses.txt");
		} catch (IOException e) {
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
	public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException {
		return studentDao.getAllStudentRecords();
	}

	@Override
	public ArrayList<Course> getAllCourseData() throws RemoteException, NullDataException {
		return courseDao.getAllCourseRecords();
	}

	@Override
	public boolean createStudentData(String studentInfo) throws RemoteException {
		return studentDao.createStudentRecords(studentInfo);
	}

	@Override
	public boolean createCourseData(String courseInfo) throws RemoteException {
		return courseDao.createCourseRecords(courseInfo);
	}

	@Override
	public boolean deleteStudentData(String studentId) throws RemoteException {
		return studentDao.deleteStudentRecords(studentId);
	}

	@Override
	public boolean deleteCourseData(String courseId) throws RemoteException {
		return courseDao.deleteCourseRecords(courseId);
	}


}
