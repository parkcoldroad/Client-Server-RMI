package dao;

import entity.Course;
import entity.Student;
import exception.NullDataException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class CourseDao {

  protected ArrayList<Course> courseList;

  private final BufferedWriter bufferedWriter;

  public CourseDao(String courseFileName) throws IOException {
    BufferedReader objCourseFile = new BufferedReader(new FileReader(courseFileName));
    this.courseList = new ArrayList<Course>();
    while (objCourseFile.ready()) {
      String courseInfo = objCourseFile.readLine();
      if (!courseInfo.equals("")) {
        this.courseList.add(new Course(courseInfo));
      }
    }
    FileWriter fw = new FileWriter(courseFileName, true);
    bufferedWriter = new BufferedWriter(fw);
    objCourseFile.close();
  }

  public ArrayList<Course> getAllCourseRecords() throws NullDataException {
    if (this.courseList.size() == 0) {
      throw new NullDataException("----------------- data is null... ------------------");
    }
    return this.courseList;
  }

  public String searchCourseRecords(String courseId) {
    for (Course course : courseList) {
      if (course.match(courseId)) {
        return course.toString();
      }
    }
    return "your courseId is not found";
  }
  public boolean createCourseRecords(String courseInfo) {
    try {
      bufferedWriter.newLine();
      bufferedWriter.write(courseInfo);
      bufferedWriter.newLine();
      bufferedWriter.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return this.courseList.add(new Course(courseInfo));
  }

  public boolean deleteCourseRecords(String courseId) {
    Optional<Course> optionalCourse = courseList.stream()
        .filter(course -> course.match(courseId))
        .findFirst();

    if (optionalCourse.isPresent()) {
      courseList.remove(optionalCourse.get());
      return true;
    }
    System.out.println("your studentId is not found");
    return false;
  }

}
