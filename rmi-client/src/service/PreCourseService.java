package service;

import dto.PreCourseDto;
import java.rmi.RemoteException;
import java.util.ArrayList;
import response.Response;
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


  public Response<String> registerPreCourse(String courseId, String preCourseId) {
    try {
      return this.clientStub.createPreCourseData(courseId, preCourseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public Response<ArrayList<PreCourseDto>> readPreCoursesList() {
    try {
      return this.clientStub.getAllPreCourseData();
    } catch (RemoteException  e) {
      throw new RuntimeException(e);
    }
  }

  public Response<ArrayList<String>> searchPreCourse(String courseId) {
    try {
      return this.clientStub.searchPreCourseData(courseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public Response<Boolean> updatePreCourse(String courseId, String preCourseId) {
    try {
      return this.clientStub.updatePreCourseData(courseId, preCourseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public Response<Boolean> deletePreCourse(String courseId,String preCourseId) {
    try {
     return this.clientStub.deletePreCourse(courseId,preCourseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

}
