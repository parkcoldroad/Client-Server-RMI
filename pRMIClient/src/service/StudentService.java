package service;

import entity.Student;
import exception.NullDataException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import menu.StudentMenu;
import pRMI.Client;
import pRMI.ServerInterface;
import utils.Input;
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
    String choice =Input.readLine();
    Arrays.stream(StudentMenu.values())
        .filter(studentMenu -> studentMenu.getChoice().equals(choice))
        .findFirst()
        .ifPresentOrElse(StudentMenu::execute,
            () -> System.out.println("invalid enter"));
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
      List<Student> studentList = this.stub.getAllStudentData();
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
    try {
      System.out.println("enter your studentId to delete");
      boolean result = this.stub.deleteStudentData(Input.readLine());
      Message.print(result);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void search() {
    try {
      System.out.println("enter your studentId to search");
      String studentId = Input.readLine();
      String seachedResult = this.stub.searchStudentData(studentId);
      System.out.println(seachedResult);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static String getCreationStudentScannerResult() throws IOException {
    System.out.println("------------Student Information------------");
    System.out.println("Student Id : "); String studentId = Input.readLine();
    System.out.println("Student Name : "); String studentName = Input.readLine();
    System.out.println("Student Department : "); String department =Input.readLine();
    System.out.println("Student Completed Course List : "); String completedCourses = Input.readLine();
    return studentId + " " + studentName + " " + department + " " + completedCourses;
  }

}
