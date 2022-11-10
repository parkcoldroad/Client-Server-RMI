package lms.dto;

import java.io.Serializable;

public class EnrollmentDto extends Dto implements Serializable {
  private String userId;
  private String courseId;
  private String courseName;
  private String professorName;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getCourseId() {
    return courseId;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public String getProfessorName() {
    return professorName;
  }

  public void setProfessorName(String professorName) {
    this.professorName = professorName;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }
  @Override
  public String toString(){
    return userId + " " + courseId + " " + courseName + " " + professorName ;
  }

  @Override
  public void print() {
    System.out.println(userId + " " + courseId + " " + courseName + " " + professorName);
  }
}
