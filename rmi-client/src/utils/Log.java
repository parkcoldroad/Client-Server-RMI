package utils;

import dto.LogDto;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import rmi.Client;

public class Log {

  public static void createLog(String message) {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    StackTraceElement[] ste = new Throwable().getStackTrace();
    List<StackTraceElement> stackTraceElements = Arrays.asList(ste);
    String methodName = stackTraceElements.get(1).getMethodName();

    ArrayList<LogDto> logInfo = new ArrayList<>();
    LogDto logDto = new LogDto();
    logDto.setMethodName(methodName);
    logDto.setTimestamp(timestamp.toString());
    logDto.setMessage(message);
    logInfo.add(logDto);

    try {
      Client.getStub().createLog(logInfo);
    } catch (
        RemoteException e) {
      throw new RuntimeException(e);
    }

  }


  public static void readLog() {
    try {
      ArrayList<LogDto> logResults = Client.getStub().readLog();

      for (LogDto logDto : logResults) {
        System.out.println(logDto);
      }

    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

}
