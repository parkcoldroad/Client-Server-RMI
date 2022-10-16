package global;

import exception.NullDataException;
import java.io.IOException;
import java.rmi.RemoteException;
import pRMI.Client;

public enum CREATE implements MenuInterface {
  CREATESTUDENT(() -> {
    try {
      Client.getInstance().createDomain(
          Client.getStub().createStudentData(
              getCreationStudentScannerResult()));
    } catch (IOException e) {throw new RuntimeException(e);}}, "1"),
  CREATECOURSE(() -> {
    try {
      Client.getInstance().createDomain(
          Client.getStub().createCourseData(
              getCreationCourseScannerResult()));
    } catch (IOException e) {throw new RuntimeException(e);}}, "2");

  private final Runnable runnable;
  private final String keyword;


  CREATE(Runnable runnable, String keyword) {
    this.runnable = runnable;
    this.keyword = keyword;
  }

  @Override
  public void execute() {
    this.runnable.run();
  }

  @Override
  public String getChoice() {
    return this.keyword;
  }

  public static void printMenu() {
    System.out.println("\n-----------------Create Menu--------------------");
    System.out.println("1.Create Student");
    System.out.println("2.Create Course");
  }

  public static String getCreationStudentScannerResult() throws IOException {
    System.out.println("------------Student Information------------");
    System.out.println("Student Id : "); String studentId = Client.getBufferedReader().readLine().trim();
    System.out.println("Student Name : "); String studentName = Client.getBufferedReader().readLine().trim();
    System.out.println("Student Department : "); String department = Client.getBufferedReader().readLine().trim();
    System.out.println("Student Completed Course List : "); String completedCourses = Client.getBufferedReader().readLine().trim();
    return studentId + " " + studentName + " " + department + " " + completedCourses;
  }

  public static String getCreationCourseScannerResult() throws IOException {
    System.out.println("------------Course Information------------");
    System.out.println("Course Id : "); String courseId = Client.getBufferedReader().readLine().trim();
    System.out.println("professor Last Name : "); String professorLName =Client.getBufferedReader().readLine().trim();
    System.out.println("Course Name : "); String courseName = Client.getBufferedReader().readLine().trim();
    System.out.println("preCoursedList : "); String preCourseList = Client.getBufferedReader().readLine().trim();
    return courseId + " " + professorLName + " " + courseName + " " + preCourseList;
  }
}
