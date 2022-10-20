package dto;

import java.io.Serializable;

public class StudentDto implements Serializable {

  private static final long serialVersionUID = 1L;
  private String studentId;
  private String name;
  private String department;
  private String password;
  private String gender;

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @Override
  public String toString() {
    return this.studentId + " " + this.name + " " + this.department + " " + this.gender;
  }
}
