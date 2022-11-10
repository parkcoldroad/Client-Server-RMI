package response;

import java.io.Serializable;

public class FailedResponse<T> extends Response<T> implements Serializable {

  public FailedResponse( String message) {
    super(false, message, null);
  }
}
