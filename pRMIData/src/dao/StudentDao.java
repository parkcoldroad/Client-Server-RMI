package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import entity.Domain;
import entity.Student;
import exception.NullDataException;
import java.util.Locale.Builder;
import java.util.Optional;

public class StudentDao {

  protected ArrayList<Domain> studentList;

  public StudentDao(String sStudentFileName) throws FileNotFoundException, IOException {
    BufferedReader objStudentFile = new BufferedReader(new FileReader(sStudentFileName));
    this.studentList = new ArrayList<Domain>();
    while (objStudentFile.ready()) {
      String stuInfo = objStudentFile.readLine();
      if (!stuInfo.equals("")) {
        this.studentList.add(new Student(stuInfo));
      }
    }
    objStudentFile.close();
  }

  public ArrayList<Domain> getAllStudentRecords() throws NullDataException {
    if (this.studentList.size() == 0) {
      throw new NullDataException("----------------- data is null... ------------------");
    }
    return this.studentList;
  }

  public boolean createStudentRecords(String studentInfo) {
    return this.studentList.add(new Student(studentInfo));
  }

  public void deleteStudentRecords(String studentId) {
    Optional<Domain> optionalStudent = studentList.stream()
        .filter(student -> student.match(studentId))
        .findFirst();

    optionalStudent.ifPresentOrElse(
        student -> studentList.remove(student),
        () -> System.out.println("your studentId is not found"));
  }

  public boolean isRegisteredStudent (String sSID){
    for (Domain domain : this.studentList) {
      Student objStudent = (Student) domain;
      if (objStudent.match(sSID)) {
        return true;
      }
    }
    return false;
  }
}
