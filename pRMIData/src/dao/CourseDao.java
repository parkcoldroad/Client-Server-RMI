package dao;

import entity.Course;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import pRMI.DataImpl;

public class CourseDao {

  protected ArrayList<Course> courseList;
  private final String courseFileName = "pRMIData/src/data/Courses.txt";

  public CourseDao()  {refreshCourseInfo();}


  public boolean createCourseRecords(String courseInfo) {
    this.courseList.add(new Course(courseInfo));

    for (Course course : courseList) {
      writeCourseFile(course.toString());
    }
    return true;
  }



  public ArrayList<Course> getAllCourseRecords() {
    return this.courseList = refreshCourseInfo();
  }

  public String searchCourseRecords(String courseId) {
    for (Course course : courseList) {
      if (course.match(courseId)) {
        return course.toString();
      }
    }
    return "your courseId is not found";
  }

  public boolean deleteCourseRecords(String courseId) {
    Optional<Course> optionalCourse = courseList.stream()
        .filter(course -> course.match(courseId))
        .findFirst();

    if (optionalCourse.isPresent()) {
      courseList.remove(optionalCourse.get());

      for (Course course : courseList) {
        writeCourseFile(course.toString());
      }
      return true;
    }
    System.out.println("your studentId is not found");
    return false;
  }

  private ArrayList<Course> refreshCourseInfo() {
    BufferedReader objCourseFile = DataImpl.getBufferedReader(courseFileName);
    this.courseList = new ArrayList<>();
    try {
      while (objCourseFile.ready()) {
        String courseInfo = objCourseFile.readLine();
        if (!courseInfo.equals("")) {
          this.courseList.add(new Course(courseInfo));
        }
      }
      objCourseFile.close();
      return courseList;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void writeCourseFile(String courseInfo) {
    try {
      BufferedWriter bufferedWriter = DataImpl.getBufferedWriter(courseFileName);
      bufferedWriter.newLine();
      bufferedWriter.write(courseInfo);
      bufferedWriter.newLine();
      bufferedWriter.flush();
      bufferedWriter.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
