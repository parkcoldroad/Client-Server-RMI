package command;

import dto.UserDto;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import response.Response;
import rmi.Client;
import utils.Log;
import utils.Message;
import utils.Session;

public class Cmd {

  public static void validateResponse(Response<?> response) {
    if (response.isSuccess()) {//success
      Object responseData = response.getData();
      if (responseData instanceof Boolean) {
        boolean data = (Boolean) responseData;
        Message.print(data);
        Log.createLog(response.getMessage());
        Client.goMain();
      } else if (responseData instanceof String) {
        String data = (String) responseData;
        Message.print(data);
        Log.createLog(response.getMessage());
        Client.goMain();
      } else if (responseData instanceof UserDto) {
        Session.getSession().register((UserDto) responseData);
        Log.createLog(response.getMessage());
      } else {
        List data = (ArrayList) responseData;
        Message.print(data);
        Log.createLog(response.getMessage());
        Client.goMain();
      }
    } else {//fail
      Message.print(response.getMessage());
      try {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        List<StackTraceElement> stackTraceElements = Arrays.asList(ste);
        String className = stackTraceElements.get(1).getClassName();
        Class<?> selfClass = Class.forName(className);//get class Info that invoke validateResponse method
        Method initializeMethod = selfClass.getDeclaredMethod("initialize");
        initializeMethod.invoke(selfClass);
      } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException |
               ClassNotFoundException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
