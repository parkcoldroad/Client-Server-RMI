package lms.dto;

import java.io.Serializable;

public class LogDto extends Dto implements Serializable {

  private String userId;
  private String methodName;
  private String timestamp;
  private String message;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
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
    System.out.println(
        "userId : " + userId + " " + "Command: " + methodName + " " + "TimeStamp: "
            + timestamp + " " + "Message: " + message);
  }
}
