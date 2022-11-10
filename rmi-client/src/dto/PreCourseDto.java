package dto;

import java.io.Serializable;

public class PreCourseDto extends Dto implements Serializable {
  private static final long serialVersionUID = 1L;
  private String courseId;
  private String preCourseId;

  public String getCourseId() {
    return courseId;
  }

  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }

  public String getPreCourseId() {
    return preCourseId;
  }

  public void setPreCourseId(String preCourseId) {
    this.preCourseId = preCourseId;
  }

  @Override
  public String toString(){
    return courseId + " " + preCourseId;
  }

  @Override
  public void print() {
    System.out.println(courseId + " " + preCourseId);
  }
}
