package service;

import dto.StudentDto;
import exception.NullDataException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import rmi.Client;
import rmi.ClientStub;
import utils.Input;

public class StudentService {

  private static StudentService studentService;
  private final ClientStub clientStub;

  public static StudentService getInstance() {
    if (studentService == null) {
      studentService = new StudentService();
    }
    return studentService;
  }

  private StudentService() {
    this.clientStub = Client.getStub();
  }


  public ArrayList<StudentDto> printStudentsList() {
    try {
      return this.clientStub.getAllStudentData();
    } catch (RemoteException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }


  public String searchStudent(String studentId) {
    try {
      return this.clientStub.searchStudentData(studentId);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean updateStudent(ArrayList<StudentDto> studentScannerResult) {
    try {
      return this.clientStub.updateStudentData(studentScannerResult);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean deleteStudent() {
    try {
      return this.clientStub.deleteStudentData(Input.readLine());
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

}
