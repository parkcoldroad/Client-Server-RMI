package utils;

import dto.CourseDto;
import dto.Dto;
import java.util.ArrayList;
import java.util.List;

public class Message {
  public static void print(String result) {
    System.out.println(result);
  }
  public static void print(boolean result){
    if(result) {System.out.println("SUCCESS");}
    else System.out.println("FAIL");
  }
  public static void print(List<? extends Dto>  domainList){
    for(Dto dto: domainList){
      dto.print();
    }
  }

}

