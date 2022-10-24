package service;

import dto.CourseDto;
import exception.NullDataException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import rmi.Client;
import rmi.Stub;

public class CourseService {

  private static CourseService courseService;
  private final Stub stub;

  public static CourseService getInstance() {
    if (courseService == null) {
      courseService = new CourseService();
    }
    return courseService;
  }

  private CourseService() {
    this.stub = Client.getStub();
  }


  public boolean createCourse(ArrayList<CourseDto> courseScannerResult) {
    try {
      return this.stub.createCourseData(courseScannerResult);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public ArrayList<CourseDto> printCoursesList() {
    try {
      return this.stub.getAllCourseData();
    } catch (RemoteException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean updateCourse(ArrayList<CourseDto> courseScannerResult) {
    try {
      return this.stub.updateCourseData(courseScannerResult);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean deleteCourse(String courseId) {
    try {
      return this.stub.deleteCourseData(courseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public String searchCourse(String courseId) {
    try {
      return this.stub.searchCourseData(courseId);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
