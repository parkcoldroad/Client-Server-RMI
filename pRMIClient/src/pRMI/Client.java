package pRMI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Client {

	public static void main(String[] args) throws IOException {
		ServerInterface server;

		BufferedReader objreader = new BufferedReader(new InputStreamReader(System.in));
		try {
			Registry registry = LocateRegistry.getRegistry(9000);
			server = (ServerInterface) registry.lookup("Server");

			System.out.println("-----------------MENU--------------------");
			System.out.println("1.List Students");
			System.out.println("2.List Courses");

			String choice = objreader.readLine().trim();

			if (choice.equals("1")) {
				ArrayList<Student> studentList = server.getAllStudentData();
				
				System.out.println("학번      이름           전공   수강과목");
				for (Student student : studentList) {
					System.out.println("------------------------------------------");
					System.out.println(student.toString());
				}

			} else if (choice.equals("2")) {
				ArrayList<Course> courseList = server.getAllCourseData();
				
				System.out.println("강좌번호  교수        강좌이름          선이수강좌번호");
				for (Course course : courseList) {
					System.out.println("------------------------------------------");
					System.out.println(course.toString());
				}
			}

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
