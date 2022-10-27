package dto;

import java.io.Serializable;

public class LogDto extends Dto implements Serializable {

  private String studentId;
  private String methodName;
  private String timestamp;
  private String message;

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void print() {
    System.out.println("Command: " +  methodName + " " + "TimeStamp" + timestamp+ " " + "message" + message) ;
  }
}
