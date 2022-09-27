package pRMI;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Data extends UnicastRemoteObject implements DataInterface{
	protected Data() throws RemoteException {
		super();
	}

	private static StudentList studentList;
	private static CourseList courseList;

	public static void main(String[] args) {
		try {
			Data dataServer = new Data();
			Registry registry = LocateRegistry.createRegistry(9123);
			registry.bind("dataServer",dataServer);
			System.out.println("data Server is ready");
				
			studentList = new StudentList("src/pRMI/Students.txt");
			courseList = new CourseList("src/pRMI/Courses.txt");
		}
		catch(IOException | AlreadyBoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<Student> getAllStudentData() {
		return studentList.getAllStudentRecords();
	}

	@Override
	public ArrayList<Course> getAllCourseData() throws RemoteException {
		return courseList.getAllCourseRecords();
	}

}
