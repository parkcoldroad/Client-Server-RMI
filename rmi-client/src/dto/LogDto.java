package dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class LogDto extends Dto implements Serializable {

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public String getCommandType() {
    return commandType;
  }

  public void setCommandType(String commandType) {
    this.commandType = commandType;
  }

  public SimpleDateFormat getSimpleDateFormat() {
    return simpleDateFormat;
  }

  public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
    this.simpleDateFormat = simpleDateFormat;
  }

  private String studentId;
  private String commandType;
  private SimpleDateFormat simpleDateFormat;

  public void print() {
    System.out.println("ID : " + studentId + " " +"Command Type: " +  commandType + " " + "TimeStamp" + simpleDateFormat);
  }
}
