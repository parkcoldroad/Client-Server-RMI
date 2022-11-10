package response;

import java.io.Serializable;

public class SuccessResponse<T> extends Response<T> implements Serializable {
  private static final long serialVersionUID = 1L;

  public SuccessResponse(T data) {
    super(true, "SUCCESS", data);
  }
}
