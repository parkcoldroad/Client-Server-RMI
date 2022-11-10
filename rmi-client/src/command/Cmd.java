package command;

import dto.UserDto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import response.Response;
import rmi.Client;
import utils.Log;
import utils.Message;
import utils.Session;

public class Cmd implements Serializable {
  private static final long serialVersionUID = 1L;

  public static void validateResponse(Response<?> response) {
    if (response.isSuccess()) {
      Object responseData = response.getData();
      if (responseData instanceof Boolean) {
        boolean data = (Boolean) responseData;
        Message.print(data);
      } else if (responseData instanceof String) {
        String data = (String) responseData;
        Message.print(data);
      } else if (responseData instanceof UserDto) {
        UserDto userData = (UserDto) responseData;
        Session.getSession().register((UserDto) response.getData());
      } else {
        List data = (ArrayList) responseData;
        Message.print(data);
      }
    } else {
      Message.print(response.getMessage());
    }
    Log.createLog(response.getMessage());
    Client.goMain();
  }
}
