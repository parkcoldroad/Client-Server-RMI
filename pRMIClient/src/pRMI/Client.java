package pRMI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import entity.Course;
import entity.Student;

public class Client {
	private static Client client;
	private Registry clientRegistry;
	private ServerInterface stub;
	private BufferedReader objreader = new BufferedReader(new InputStreamReader(System.in));

	private Client() throws RemoteException, NotBoundException {
		clientRegistry = LocateRegistry.getRegistry(9000);
		stub = (ServerInterface) clientRegistry.lookup("Server");
	}

	public static Client getInstance() {
		if (client == null) {
			try {
				client = new Client();
			} catch (RemoteException | NotBoundException e) {
				e.printStackTrace();
			}
		}
		return client;
	}
	
	public void start() {
		System.out.println("-----------------MENU--------------------");
		System.out.println("1.List Students");
		System.out.println("2.List Courses");

		String choice;
		try {
			choice = objreader.readLine().trim();
			if (choice.equals("1")) {
				ArrayList<Student> studentList = stub.getAllStudentData();

				System.out.println("학번      이름           전공   수강과목");
				for (Student student : studentList) {
					System.out.println("------------------------------------------");
					System.out.println(student.toString());
				}

			} else if (choice.equals("2")) {
				ArrayList<Course> courseList = stub.getAllCourseData();

				System.out.println("강좌번호  교수        강좌이름          선이수강좌번호");
				for (Course course : courseList) {
					System.out.println("------------------------------------------");
					System.out.println(course.toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
