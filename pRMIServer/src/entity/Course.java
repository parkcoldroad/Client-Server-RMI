package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

@SuppressWarnings("serial")
public class Course  implements Serializable {

  private String courseId;
  private String pLName;
  private String courseName;

  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public String getProfessorLastName() {
    return pLName;
  }

  public void setProfessorLastName(String pLName) {
    this.pLName = pLName;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }
}
