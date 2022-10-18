package dao;

import entity.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import pRMI.DataImpl;

public class StudentDao {

  protected ArrayList<Student> studentList;
  private final String studentFileName = "pRMIData/src/data/Students.txt";

  public StudentDao() {
    refreshStudentInfo();
  }

  public void createStudentRecords(String studentInfo) {
    this.studentList.add(new Student(studentInfo));

    for (Student student : studentList) {
      writeStudentFile(student.toString());
    }
  }

  public ArrayList<Student> getAllStudentRecords() {
    return this.studentList = refreshStudentInfo();
  }

  public String searchStudentRecords(String studentId) {
    for (Student student : studentList) {
      if (student.match(studentId)) {
        return student.toString();
      }
    }
    return "your studentId is not found";
  }

  public boolean deleteStudentRecords(String studentId) {
    Optional<Student> optionalStudent = studentList.stream()
        .filter(student -> student.match(studentId))
        .findFirst();

    if (optionalStudent.isPresent()) {
      studentList.remove(optionalStudent.get());

      for (Student student : studentList) {
        writeStudentFile(student.toString());
      }
      return true;
    }
    return false;
  }

  private ArrayList<Student> refreshStudentInfo() {
    BufferedReader objStudentFile = DataImpl.getBufferedReader(studentFileName);
    this.studentList = new ArrayList<>();

    try {
      while (objStudentFile.ready()) {
        String stuInfo = objStudentFile.readLine();
        if (!stuInfo.equals("")) {
          this.studentList.add(new Student(stuInfo));
        }
      }
      objStudentFile.close();
      return studentList;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void writeStudentFile(String studentInfo) {
    try {
      BufferedWriter bufferedWriter = DataImpl.getBufferedWriter(studentFileName);
      bufferedWriter.newLine();
      bufferedWriter.write(studentInfo);
      bufferedWriter.newLine();
      bufferedWriter.flush();
      bufferedWriter.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
