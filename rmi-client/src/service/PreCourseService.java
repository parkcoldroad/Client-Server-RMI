package service;

import dto.PreCourseDto;
import exception.NullDataException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import rmi.Client;
import rmi.ClientStub;

public class PreCourseService {

  private static PreCourseService preCourseService;
  private final ClientStub clientStub;

  public static PreCourseService getInstance() {
    if (preCourseService == null) {
      preCourseService = new PreCourseService();
    }
    return preCourseService;
  }

  private PreCourseService() {
    this.clientStub = Client.getStub();
  }


  public String registerPreCourse(String courseId, String preCourseId) {
    try {
      return this.clientStub.createPreCourseData(courseId, preCourseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public ArrayList<PreCourseDto> readPreCoursesList() {
    try {
      return this.clientStub.getAllPreCourseData();
    } catch (RemoteException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean updatePreCourse(String courseId, String preCourseId) {
    try {
      return this.clientStub.updatePreCourseData(courseId, preCourseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean deletePreCourse(String courseId,String preCourseId) {
    try {
     return this.clientStub.deletePreCourse(courseId,preCourseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

}
