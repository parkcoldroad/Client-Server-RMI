package log;

import dto.LogDto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class LogDao {

  private String message;
  private String methodName;
  private String timestamp;

  private String logInfo;

  private final String fileName = "rmi-data/src/log/logs.txt";
  private final BufferedWriter fw;

  private final BufferedReader bufferedReader;

  public LogDao() {
    try {
      fw = new BufferedWriter(new FileWriter(fileName, true));
      bufferedReader = new BufferedReader(new FileReader(fileName));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  public void createLog(ArrayList<LogDto> logList) {
    for (LogDto logDto : logList) {
      message = logDto.getMessage();
      methodName = logDto.getMethodName();
      timestamp = logDto.getTimestamp();
    }
    try {
      fw.write(message + " " + methodName + " " + timestamp);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  public ArrayList<LogDto> readLog() {
    ArrayList<LogDto> logList = new ArrayList<>();
    LogDto logDto = new LogDto();

    try {
      while (bufferedReader.ready()) {
        logInfo = bufferedReader.readLine();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    StringTokenizer stringTokenizer = new StringTokenizer(logInfo);
    this.message = stringTokenizer.nextToken();
    this.methodName = stringTokenizer.nextToken();
    this.timestamp = stringTokenizer.nextToken();

    logDto.setMessage(message);
    logDto.setMethodName(methodName);
    logDto.setTimestamp(timestamp);
    logList.add(logDto);

    return logList;
  }

}
