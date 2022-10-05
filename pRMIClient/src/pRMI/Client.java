package pRMI;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

import entity.Course;
import entity.Student;

public class Client {
	private static Client client;
	private Registry clientRegistry;
	private ServerInterface stub;
	private Scanner sc;

	private Client() throws RemoteException, NotBoundException {
		clientRegistry = LocateRegistry.getRegistry(9000);
		stub = (ServerInterface) clientRegistry.lookup("Server");
		sc = new Scanner(System.in);
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
		boolean isStop = false;
		while (!isStop) {
			printMenu();
			int choice = sc.nextInt();

			Optional<CRUD> optionalCRUD = Arrays.stream(CRUD.values())
			.filter(crud -> crud.ordinal() == choice)
			.findFirst();

			optionalCRUD.ifPresentOrElse(crud -> crud.execute(),
					() -> System.out.println("invalid enter"));
			
			if(choice == 4) {
				isStop = true;
			}
		}

	}

	private void printMenu() {
		System.out.println("\n-----------------MENU--------------------");
		System.out.println("0.Create");
		System.out.println("1.Read");
		System.out.println("2.Update");
		System.out.println("3.Delete");
		System.out.println("4.Quit");
	}

	public void create() {

	}

	public void read() {
		System.out.println("\n-----------------Read Menu--------------------");
		System.out.println("1.Read StudentList");
		System.out.println("2.Read CourseList");
		System.out.println("3.Search Student");
		System.out.println("4.Search Course");
		
		int choice = sc.nextInt();
		readList(choice);
	}

	private void readList(int input) {
		try {
			if (input == 1) printStudentList();
			else if (input == 2) printCoursesList();
			else if (input == 3) searchStudent();
			else if (input == 4) searchCourse();
			else {
				System.out.println("invalid enter");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void printCoursesList() throws RemoteException {
		ArrayList<Course> courseList = stub.getAllCourseData();

		System.out.println("강좌번호  교수        강좌이름          선이수강좌번호");
		for (Course course : courseList) {
			System.out.println("------------------------------------------");
			System.out.println(course.toString());
		}
	}

	private void printStudentList() throws RemoteException {
		ArrayList<Student> studentList = stub.getAllStudentData();

		System.out.println("학번      이름           전공   수강과목");
		for (Student student : studentList) {
			System.out.println("------------------------------------------");
			System.out.println(student.toString());
		}
	}

	private void searchStudent() throws RemoteException {
		System.out.println("enter your studentId to search");
		
		String studentId = sc.next();
		ArrayList<Student> studentList = stub.getAllStudentData();

		Optional<Student> searchedStudent = studentList.stream()
				.filter(student -> student.getStudentId().equals(studentId))
				.findFirst();
		
		searchedStudent.ifPresentOrElse(
				System.out::println,
				() -> System.out.println("your studentId is no matched")
			);
	}

	private void searchCourse() throws RemoteException {
		System.out.println("enter your courseId to search");
		
		String courseId = sc.next();
		ArrayList<Course> courseList = stub.getAllCourseData();

		Optional<Course> searchedCourse = courseList.stream()
				.filter(course -> course.getCoursesId().equals(courseId))
				.findFirst();
		
		searchedCourse.ifPresentOrElse(course -> System.out.println(course),
				() -> System.out.println("your courseId is no matched"));
	}
	
	public void update() {

	}

	public void delete() {

	}

	public void quit() {
		sc.close();
		System.out.println("system terminated..");
	}

}
