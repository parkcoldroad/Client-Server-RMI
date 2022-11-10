package response;

import java.io.Serializable;

public class Response<T> implements Serializable {
    boolean success;
    String message;
    T data;

  public Response(boolean success,String message,T data){
      this.success =success;
      this.message = message;
      this.data = data;
  }

  public boolean isSuccess() {
    return success;
  }

  public String getMessage() {
    return message;
  }

  public T getData() {
    return data;
  }
}
