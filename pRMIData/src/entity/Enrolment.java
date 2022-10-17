package entity;

import java.io.Serializable;

public class Enrolment extends Domain implements Serializable {
private String studentId;

  @Override
  public boolean match(String domainId) {
    return false;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public String getId() {
    return null;
  }

  @Override
  public String toString() {
    return null;
  }

  @Override
  public void showAttributes() {

  }
}
