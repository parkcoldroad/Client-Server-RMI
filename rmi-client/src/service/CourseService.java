package service;

import dto.CourseDto;
import exception.DuplicatedCourseIdException;
import exception.IllegalValueIdException;
import exception.NullDataException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import response.Response;
import rmi.Client;
import rmi.ClientStub;

public class CourseService {

  private static CourseService courseService;
  private final ClientStub clientStub;

  public static CourseService getInstance() {
    if (courseService == null) {
      courseService = new CourseService();
    }
    return courseService;
  }

  private CourseService() {
    this.clientStub = Client.getStub();
  }


  public Response<Boolean> createCourse(ArrayList<CourseDto> courseScannerResult) {
    try {
      return this.clientStub.createCourseData(courseScannerResult);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public Response<ArrayList<CourseDto>> printCoursesList() {
    try {
      return this.clientStub.getAllCourseData();
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public Response<Boolean> updateCourse(ArrayList<CourseDto> courseScannerResult) {
    try {
      return this.clientStub.updateCourseData(courseScannerResult);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public Response<Boolean> deleteCourse(String courseId) {
    try {
      return this.clientStub.deleteCourseData(courseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public Response<String> searchCourse(String courseId) {
    try {
      return this.clientStub.searchCourseData(courseId);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
