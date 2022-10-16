package service;

import entity.Domain;
import exception.NullDataException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import menu.CourseMenu;
import menu.StudentMenu;
import pRMI.Client;
import pRMI.ServerInterface;
import utils.Message;

public class StudentService implements Service{

  private static StudentService studentService;
  private final ServerInterface stub;

  public static StudentService getInstance(){
    if(studentService == null){
      studentService = new StudentService();
    }
    return studentService;
  }

  private StudentService() {
    this.stub = Client.getStub();
  }

  public void initialize(){
    StudentMenu.printMenu();
    try {
      String choice = Client.getBufferedReader().readLine().trim();
      Arrays.stream(CourseMenu.values())
          .filter(courseMenu -> courseMenu.getChoice().equals(choice))
          .findFirst()
          .ifPresentOrElse(CourseMenu::execute,
              () -> System.out.println("invalid enter"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void create() {
    try {
      boolean result = this.stub.createStudentData(getCreationStudentScannerResult());
       Message.print(result);
    } catch (IOException e) {throw new RuntimeException(e);}
  }

  @Override
  public void read() {
    try {
      List<Domain> studentList =this.stub.getAllStudentData();
      Message.print(studentList);
    } catch (RemoteException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void update() {

  }

  @Override
  public void delete() {

  }

  @Override
  public void search() {
    try {
      List<Domain> studentList = this.stub.getAllStudentData();
      String studentId = Client.getBufferedReader().readLine().trim();

      Domain searchedStudent = studentList.stream()
          .filter(student -> student.match(studentId))
          .findFirst()
          .orElseThrow(() -> new NullDataException("your id is no matched"));

      Message.print(searchedStudent);
    } catch (NullDataException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static String getCreationStudentScannerResult() throws IOException {
    System.out.println("------------Student Information------------");
    System.out.println("Student Id : "); String studentId = Client.getBufferedReader().readLine().trim();
    System.out.println("Student Name : "); String studentName = Client.getBufferedReader().readLine().trim();
    System.out.println("Student Department : "); String department = Client.getBufferedReader().readLine().trim();
    System.out.println("Student Completed Course List : "); String completedCourses = Client.getBufferedReader().readLine().trim();
    return studentId + " " + studentName + " " + department + " " + completedCourses;
  }

}
