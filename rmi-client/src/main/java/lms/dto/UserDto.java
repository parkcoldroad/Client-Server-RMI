package lms.dto;

import java.io.Serializable;

public class UserDto extends Dto implements Serializable {

  private static final long serialVersionUID = 1L;
  private String userId;
  private String name;
  private String department;
  private String password;
  private String gender;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
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
    return this.userId + " " + this.name + " " + this.department + " " + this.gender;
  }

  @Override
  public void print() {
    System.out.println(this.userId + " " + this.name + " " + this.department + " " + this.gender);
  }
}
