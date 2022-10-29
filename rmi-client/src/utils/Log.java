package utils;

import dto.LogDto;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import rmi.Client;

public class Log {

  public static void createLog(String message) {

    String userId = Session.getSession().getUserId();
    SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd.HH:mm:ss");
    String timeStamp = date.format(new Date());

    StackTraceElement[] ste = new Throwable().getStackTrace();
    List<StackTraceElement> stackTraceElements = Arrays.asList(ste);
    String methodName = stackTraceElements.get(1).getMethodName();

    ArrayList<LogDto> logInfo = new ArrayList<>();
    LogDto logDto = new LogDto();
    logDto.setUserId(userId);
    logDto.setMethodName(methodName);
    logDto.setTimestamp(timeStamp);
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
        logDto.print();
      }

    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

}
