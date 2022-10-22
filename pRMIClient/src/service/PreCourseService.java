package service;

import command.menu.PreCourseMenu;
import dto.PreCourseDto;
import exception.NullDataException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import rmi.Client;
import rmi.Stub;
import utils.Input;

public class PreCourseService {

  private static PreCourseService preCourseService;
  private final Stub stub;

  public static PreCourseService getInstance() {
    if (preCourseService == null) {
      preCourseService = new PreCourseService();
    }
    return preCourseService;
  }

  private PreCourseService() {
    this.stub = Client.getStub();
  }


  public String registerPreCourse(String courseId, String preCourseId) {
    try {
      return this.stub.createPreCourseData(courseId, preCourseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public ArrayList<PreCourseDto> readPreCoursesList() {
    try {
      return this.stub.getAllPreCourseData();
    } catch (RemoteException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean updatePreCourse(String courseId, String preCourseId) {
    try {
      return this.stub.updatePreCourseData(courseId, preCourseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean deletePreCourse(String courseId) {
    try {
     return this.stub.deletePreCourse(courseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

}
