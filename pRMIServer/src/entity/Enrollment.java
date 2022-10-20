package entity;

import java.io.Serializable;
import java.util.StringTokenizer;

public class Enrollment implements Serializable {

  private String studentId;
  private String courseId;
  private boolean isCompleted;

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public boolean isCompleted() {
    return isCompleted;
  }

  public void setCompleted(boolean completed) {
    isCompleted = completed;
  }
}
