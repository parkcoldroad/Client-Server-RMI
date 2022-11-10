package utils;

import dto.LogDto;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import response.Response;
import rmi.Client;

public class Log implements Serializable {
  private static final long serialVersionUID = 1L;
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
      Response<ArrayList<LogDto>> readResponse = Client.getStub().readLog();
      for (LogDto logDto : readResponse.getData()) {
        logDto.print();
      }
      Client.goMain();
    } catch (RemoteException e) {
      throw new RuntimeException(e);
    }
  }

}
