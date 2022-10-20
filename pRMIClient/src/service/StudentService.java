package service;

import dto.StudentDto;
import exception.NullDataException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import menu.StudentMenu;
import pRMI.Client;
import pRMI.ClientInterface;
import utils.Input;
import utils.Message;

public class StudentService{

  private static StudentService studentService;
  private final ClientInterface stub;

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

  public void create() {
//    try {
//      boolean result = this.stub.createStudentData(getCreationStudentScannerResult());
//       Message.print(result);
//    } catch (IOException e) {throw new RuntimeException(e);}
  }

  public void read() {
    try {
      ArrayList<StudentDto> studentList = this.stub.getAllStudentData();
      for (StudentDto studentDto: studentList){
        System.out.println(studentDto.toString());
      }
    } catch (RemoteException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }

  public void update() {

  }

  public void delete() {
    try {
      System.out.println("enter your studentId to delete");
      boolean result = this.stub.deleteStudentData(Input.readLine());
      Message.print(result);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

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
