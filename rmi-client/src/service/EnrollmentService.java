package service;

import dto.EnrollmentDto;
import java.rmi.RemoteException;
import java.util.ArrayList;
import response.Response;
import rmi.Client;
import rmi.ClientStub;
import utils.Session;

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


  public Response<String> applyCourse(String courseId) {
    try {
      return this.clientStub.createEnrollment(Session.getSession().getUserId(), courseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public Response<ArrayList<EnrollmentDto>> displayApplyHistory() {
    try {
      return this.clientStub.getEnrollmentData(Session.getSession().getUserId());
    } catch (RemoteException  e) {
      throw new RuntimeException(e);
    }
  }


  public Response<Boolean> removeApplyHistory(String courseId) {
    try {
      return this.clientStub.deleteEnrollment(Session.getSession().getUserId(), courseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }
}
