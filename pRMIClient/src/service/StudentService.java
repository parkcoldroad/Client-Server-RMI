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

  public void createStudent() {
    try {
      boolean result = this.stub.createStudentData(getStudentScannerResult());
       Message.print(result);
    } catch (IOException e) {throw new RuntimeException(e);}
  }

  public void printStudentsList() {
    try {
      ArrayList<StudentDto> studentList = this.stub.getAllStudentData();
      for (StudentDto studentDto: studentList){
        System.out.println(studentDto.toString());
      }
    } catch (RemoteException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }

  public void searchStudent() {
    try {
      System.out.println("enter your studentId to search");
      String studentId = Input.readLine();
      String seachedResult = this.stub.searchStudentData(studentId);
      System.out.println(seachedResult);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void updateStudent() {
    try {
      boolean result = this.stub.updateStudentData(getStudentScannerResult());
      Message.print(result);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void deleteStudent() {
    try {
      System.out.println("enter your studentId to delete");
      boolean result = this.stub.deleteStudentData(Input.readLine());
      Message.print(result);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public void registerCompletedCourse() {
    System.out.println("enter your studentId to register completed courses");  String studentId = Input.readLine();
    System.out.println("enter courseId that you had completed");  String courseId = Input.readLine();
    try {
      String result = this.stub.registerCompletedCourse(studentId,courseId);
      Message.print(result);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }



  public  ArrayList<StudentDto> getStudentScannerResult() throws IOException {
    System.out.println("------------Student Information------------");
    System.out.println("Student Id : "); String studentId = Input.readLine();
    System.out.println("Student Password : "); String password =Input.readLine();
    System.out.println("Student Name : "); String studentName = Input.readLine();
    System.out.println("Student Department : "); String department =Input.readLine();
    System.out.println("Student Gender : "); String gender =Input.readLine();

    ArrayList<StudentDto> studentDtos = new ArrayList<>();
    StudentDto studentDto = new StudentDto();
    studentDto.setStudentId(studentId);
    studentDto.setPassword(password);
    studentDto.setName(studentName);
    studentDto.setDepartment(department);
    studentDto.setGender(gender);

    studentDtos.add(studentDto);
    return studentDtos;
  }

}
