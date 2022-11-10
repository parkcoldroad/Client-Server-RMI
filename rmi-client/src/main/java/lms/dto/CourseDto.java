package lms.dto;

import java.io.Serializable;

public class CourseDto extends Dto implements Serializable {

  protected String courseId;
  protected String pLName;
  protected String courseName;


  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public String getpLName() {
    return pLName;
  }

  public void setpLName(String pLName) {
    this.pLName = pLName;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }


  public String toString() {
    return this.courseId + " " + this.pLName + " " + this.courseName;
  }

  @Override
  public void print() {
    System.out.println(this.courseId + " " + this.pLName + " " + this.courseName);
  }
}
