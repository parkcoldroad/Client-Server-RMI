package service;

import dto.CourseDto;
import exception.DuplicatedCourseIdException;
import exception.IllegalValueIdException;
import exception.NullDataException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
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


  public boolean createCourse(ArrayList<CourseDto> courseScannerResult)
      throws DuplicatedCourseIdException {
    try {
      return this.clientStub.createCourseData(courseScannerResult);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public ArrayList<CourseDto> printCoursesList() {
    try {
      return this.clientStub.getAllCourseData();
    } catch (RemoteException | NullDataException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean updateCourse(ArrayList<CourseDto> courseScannerResult)
      throws DuplicatedCourseIdException {
    try {
      return this.clientStub.updateCourseData(courseScannerResult);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean deleteCourse(String courseId) throws DuplicatedCourseIdException {
    try {
      return this.clientStub.deleteCourseData(courseId);
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

  public String searchCourse(String courseId) throws IllegalValueIdException {
    try {
      return this.clientStub.searchCourseData(courseId);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
