package response;

import java.io.Serializable;

public class FailedResponse<T> extends Response<T> implements Serializable {
  private static final long serialVersionUID = 1L;
  public FailedResponse(String message) {
    super(false, message, null);
  }
}
