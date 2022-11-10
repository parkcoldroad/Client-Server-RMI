package response;

import java.io.Serializable;

public class SuccessResponse<T> extends Response<T> implements Serializable {

  public SuccessResponse(T data) {
    super(true, "SUCCESS", data);
  }
}
