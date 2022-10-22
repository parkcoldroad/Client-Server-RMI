package service;

import command.menu.StudentMenu;
import dto.StudentDto;
import exception.NullDataException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import rmi.Client;
import rmi.Stub;
import utils.Input;

public class StudentService {

  private static StudentService studentService;
  private final Stub stub;

  public static StudentService getInstance() {
    if (studentService == null) {
      studentService = new StudentService();
    }
    return studentService;
  }

  private StudentService() {
    this.stub = Client.getStub();
  }

  public void initialize() {
    StudentMenu.printMenu();
    String choice = Input.readLine();
    Arrays.stream(StudentMenu.values())
        .filter(studentMenu -> studentMenu.getChoice().equals(choice))
        .findFirst()
        .ifPresentOrElse(StudentMenu::execute,
            () -> System.out.println("invalid enter"));
  }

  public boolean createStudent(ArrayList<StudentDto> studentScannerResult) {
    try {
      return this.stub.createStudentData(studentScannerResult);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public ArrayList<StudentDto> printStudentsList() {
    try {
      return this.stub.getAllStudentData();
    } catch (RemoteException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }


  public String searchStudent(String studentId) {
    try {
      return this.stub.searchStudentData(studentId);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean updateStudent(ArrayList<StudentDto> studentScannerResult) {
    try {
      return this.stub.updateStudentData(studentScannerResult);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean deleteStudent() {
    try {
      return this.stub.deleteStudentData(Input.readLine());
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

}
