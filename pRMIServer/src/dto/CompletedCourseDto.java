package dto;

import java.io.Serializable;

public class CompletedCourseDto implements Serializable {
  private String studentId;
  private String completedCourseId;


  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public String getCompletedCourseId() {
    return completedCourseId;
  }

  public void setCompletedCourseId(String completedCourseId) {
    this.completedCourseId = completedCourseId;
  }

  @Override
  public String toString(){
    return studentId + " " + completedCourseId;
  }
}
