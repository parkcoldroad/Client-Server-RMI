package dao;

import entity.Enrollment;
import exception.NullDataException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class EnrollmentDao {

  private ArrayList<Enrollment> enrollmentList;
  private final BufferedWriter bufferedWriter;

  public EnrollmentDao() throws IOException {
    String EnrolmentFileName = "pRMIData/src/data/Enrollment.txt";
    BufferedReader objEnrolmentFile = new BufferedReader(new FileReader(EnrolmentFileName));
    this.enrollmentList = new ArrayList<Enrollment>();
    while (objEnrolmentFile.ready()) {
      String enrolmentInfo = objEnrolmentFile.readLine();
      if (!enrolmentInfo.equals("")) {
        this.enrollmentList.add(new Enrollment(enrolmentInfo));
      }
    }
    FileWriter fw = new FileWriter(EnrolmentFileName, true);
    bufferedWriter = new BufferedWriter(fw);
    objEnrolmentFile.close();
  }

  public boolean createEnrolment(String enrolementInfo) {
    try {
      bufferedWriter.newLine();
      bufferedWriter.write(enrolementInfo);
      bufferedWriter.newLine();
      bufferedWriter.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
     this.enrollmentList.add(new Enrollment(enrolementInfo));
    return true;
  }

  public ArrayList<Enrollment> getAllEnrollmentData() {
    if (this.enrollmentList.size() == 0) {
      try {
        throw new NullDataException("----------------- data is null... ------------------");
      } catch (NullDataException e) {
        throw new RuntimeException(e);
      }
    }
    return this.enrollmentList;
  }

  public boolean deleteEnrolment(String studentId) {
    Optional<Enrollment> optionalEnrolment = enrollmentList.stream()
        .filter(enrollment -> enrollment.match(studentId))
        .findFirst();

    if (optionalEnrolment.isPresent()) {
      enrollmentList.remove(optionalEnrolment.get());

      for (Enrollment enrollment : enrollmentList) {
        try {
          bufferedWriter.newLine();
          bufferedWriter.write(enrollment.toString());
          bufferedWriter.newLine();
          bufferedWriter.flush();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      return true;
    }
    return false;
  }
}
