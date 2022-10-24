package service;

import dto.EnrollmentDto;
import exception.NullDataException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import rmi.Client;
import rmi.ClientStub;

public class EnrollmentService {

  private static EnrollmentService enrollmentService;
  private final ClientStub clientStub;

  public static EnrollmentService getInstance() {
    if (enrollmentService == null) {
      enrollmentService = new EnrollmentService();
    }
    return enrollmentService;
  }

  private EnrollmentService() {
    this.clientStub = Client.getStub();
  }


  public String applyCourse(String studentId, String courseId) {
    try {
      return this.clientStub.createEnrollment(studentId, courseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public ArrayList<EnrollmentDto> displayApplyHistory(String studentId) {
    try {
      return this.clientStub.getEnrollmentData(studentId);
    } catch (RemoteException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }


  public boolean removeApplyHistory(String studentId,String courseId) {
    try {
      return this.clientStub.deleteEnrollment(studentId, courseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }
}
