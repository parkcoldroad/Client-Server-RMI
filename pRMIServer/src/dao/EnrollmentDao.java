package dao;

import entity.Enrollment;
import exception.NullDataException;
import java.util.ArrayList;

public class EnrollmentDao {

  private ArrayList<Enrollment> enrollmentList;

  public EnrollmentDao() {

  }

  public boolean createEnrolment(String enrolementInfo) {
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
//    Optional<Enrollment> optionalEnrolment = enrollmentList.stream()
//        .filter(enrollment -> enrollment.match(studentId))
//        .findFirst();
//
//    if (optionalEnrolment.isPresent()) {
//      enrollmentList.remove(optionalEnrolment.get());
//
//      return true;
//    }
//    return false;
//  }
    return true;
  }
}