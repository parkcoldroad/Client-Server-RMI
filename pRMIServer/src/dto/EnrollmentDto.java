package dto;

import java.io.Serializable;

public class EnrollmentDto implements Serializable {
  private String studentId;
  private String courseId;
  private String courseName;
  private String professorName;

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
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
    return studentId + " " + courseId + " " + courseName + " " + professorName ;
  }
}
