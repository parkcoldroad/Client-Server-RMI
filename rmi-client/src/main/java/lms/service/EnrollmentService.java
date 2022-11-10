package lms.service;

import lms.dto.EnrollmentDto;
import lms.exception.DuplicateEnrollmentException;
import lms.exception.IllegalValueIdException;
import lms.exception.NullDataException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import lms.rmi.Client;
import lms.rmi.ClientStub;
import lms.utils.Session;

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


  public String applyCourse(String courseId)
      throws IllegalValueIdException, DuplicateEnrollmentException {
    try {
      return this.clientStub.createEnrollment(Session.getSession().getUserId(), courseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public ArrayList<EnrollmentDto> displayApplyHistory() {
    try {
      return this.clientStub.getEnrollmentData(Session.getSession().getUserId());
    } catch (RemoteException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }


  public boolean removeApplyHistory(String courseId) throws IllegalValueIdException {
    try {
      return this.clientStub.deleteEnrollment(Session.getSession().getUserId(), courseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }
}
