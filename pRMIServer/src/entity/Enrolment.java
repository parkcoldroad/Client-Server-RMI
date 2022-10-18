package entity;

import java.io.Serializable;
import java.util.StringTokenizer;

public class Enrolment extends Domain implements Serializable {

  private String studentId;
  private String studentName;
  private String courseId;
  private String courseName;

  public Enrolment(String inputString) {
    StringTokenizer stringTokenizer = new StringTokenizer(inputString);
    this.studentId = stringTokenizer.nextToken();
    this.studentName = stringTokenizer.nextToken();
    this.courseId = stringTokenizer.nextToken();
    this.courseName = stringTokenizer.nextToken();
    }
  @Override
  public boolean match(String domainId) {
    return false;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public String getId() {
    return null;
  }

  @Override
  public String toString() {
    return null;
  }

  @Override
  public void showAttributes() {

  }
}
