package lms.service;

import lms.dto.PreCourseDto;
import lms.exception.IllegalValueIdException;
import lms.exception.NullDataException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import lms.rmi.Client;
import lms.rmi.ClientStub;

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

  public ArrayList<String> searchPreCourse(String courseId) throws IllegalValueIdException {
    try {
      return this.clientStub.searchPreCourseData(courseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean updatePreCourse(String courseId, String preCourseId)
      throws IllegalValueIdException {
    try {
      return this.clientStub.updatePreCourseData(courseId, preCourseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean deletePreCourse(String courseId,String preCourseId)
      throws IllegalValueIdException {
    try {
     return this.clientStub.deletePreCourse(courseId,preCourseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

}
