package lms.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {

  private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

  public static void close(){
    try {
      bufferedReader.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static String readLine() {
    try {
      return bufferedReader.readLine().trim();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }




}
