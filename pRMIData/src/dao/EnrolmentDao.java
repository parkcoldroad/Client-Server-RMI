package dao;

import entity.Enrolment;
import exception.NullDataException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class EnrolmentDao {

  private ArrayList<Enrolment> enrolmentList;
  private final BufferedWriter bufferedWriter;

  public EnrolmentDao() throws IOException {
    String EnrolmentFileName = "pRMIData/src/entity/Enrolment.txt";
    BufferedReader objEnrolmentFile = new BufferedReader(new FileReader(EnrolmentFileName));
    this.enrolmentList = new ArrayList<Enrolment>();
    while (objEnrolmentFile.ready()) {
      String enrolmentInfo = objEnrolmentFile.readLine();
      if (!enrolmentInfo.equals("")) {
        this.enrolmentList.add(new Enrolment(enrolmentInfo));
      }
    }
    FileWriter fw = new FileWriter(EnrolmentFileName, true);
    bufferedWriter = new BufferedWriter(fw);
    objEnrolmentFile.close();
  }

  public void createEnrolment(String enrolementInfo) {
    try {
      bufferedWriter.newLine();
      bufferedWriter.write(enrolementInfo);
      bufferedWriter.newLine();
      bufferedWriter.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
     this.enrolmentList.add(new Enrolment(enrolementInfo));
  }

  public ArrayList<Enrolment> getEnrolment() {
    if (this.enrolmentList.size() == 0) {
      try {
        throw new NullDataException("----------------- data is null... ------------------");
      } catch (NullDataException e) {
        throw new RuntimeException(e);
      }
    }
    return this.enrolmentList;
  }

  public boolean deleteEnrolment(String studentId) {
    Optional<Enrolment> optionalEnrolment = enrolmentList.stream()
        .filter(enrolment -> enrolment.match(studentId))
        .findFirst();

    if (optionalEnrolment.isPresent()) {
      enrolmentList.remove(optionalEnrolment.get());

      for (Enrolment enrolment : enrolmentList) {
        try {
          bufferedWriter.newLine();
          bufferedWriter.write(enrolment.toString());
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
