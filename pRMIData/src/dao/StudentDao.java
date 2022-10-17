package dao;

import entity.Student;
import exception.NullDataException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class StudentDao {

  protected ArrayList<Student> studentList;
  private final BufferedWriter bufferedWriter;

  public StudentDao(String sStudentFileName) throws IOException {
    BufferedReader objStudentFile = new BufferedReader(new FileReader(sStudentFileName));
    this.studentList = new ArrayList<Student>();
    while (objStudentFile.ready()) {
      String stuInfo = objStudentFile.readLine();
      if (!stuInfo.equals("")) {
        this.studentList.add(new Student(stuInfo));
      }
    }
    FileWriter fw = new FileWriter(sStudentFileName, true);
    bufferedWriter = new BufferedWriter(fw);
    objStudentFile.close();
  }

  public ArrayList<Student> getAllStudentRecords() throws NullDataException {
    if (this.studentList.size() == 0) {
      throw new NullDataException("----------------- data is null... ------------------");
    }
    return this.studentList;
  }

  public String searchStudentRecords(String studentId) {
    for (Student student : studentList) {
      if (student.match(studentId)) {
        return student.toString();
      }
    }
    return "your studentId is not found";
  }

  public boolean createStudentRecords(String studentInfo) {
    try {
      bufferedWriter.newLine();
      bufferedWriter.write(studentInfo);
      bufferedWriter.newLine();
      bufferedWriter.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return this.studentList.add(new Student(studentInfo));
  }

  public boolean deleteStudentRecords(String studentId) {
    Optional<Student> optionalStudent = studentList.stream()
        .filter(student -> student.match(studentId))
        .findFirst();

    if (optionalStudent.isPresent()) {
      studentList.remove(optionalStudent.get());
      return true;
    }
    System.out.println("your studentId is not found");
    return false;
  }

//  public boolean isRegisteredStudent (String sSID){
//    for (Domain domain : this.studentList) {
//      Student objStudent = (Student) domain;
//      if (objStudent.match(sSID)) {
//        return true;
//      }
//    }
//    return false;
//  }
}
