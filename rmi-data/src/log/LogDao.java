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

  private ArrayList<LogDto> logList;
  private LogDto logDto;

  String fileName = "rmi-data/src/log/logs.txt";

  public LogDao() {
    logList = new ArrayList<>();
  }


  public void createLog(ArrayList<LogDto> logList) {
    for (LogDto logDto : logList) {
      message = logDto.getMessage();
      methodName = logDto.getMethodName();
      timestamp = logDto.getTimestamp();
    }
    try {
      BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, true));
      fw.write(message + " " + methodName + " " + timestamp);
      fw.newLine();
      fw.close();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  public ArrayList<LogDto> readLog() {
    logList.clear();
    try {
      BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

      while (bufferedReader.ready()) {
        logDto = new LogDto();
        logInfo = bufferedReader.readLine();

        StringTokenizer stringTokenizer = new StringTokenizer(logInfo);
        this.message = stringTokenizer.nextToken();
        this.methodName = stringTokenizer.nextToken();
        this.timestamp = stringTokenizer.nextToken();

        logDto.setMessage(message);
        logDto.setMethodName(methodName);
        logDto.setTimestamp(timestamp);

        if (!logInfo.equals("")) {
            logList.add(logDto);
        }
      }

      bufferedReader.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return logList;
  }
}
