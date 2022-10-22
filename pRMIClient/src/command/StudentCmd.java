package command;

import command.menu.StudentMenu;
import dto.StudentDto;
import java.util.ArrayList;
import java.util.Arrays;
import service.StudentService;
import utils.Input;
import utils.Message;

public class StudentCmd {

  public static void initialize() {
    StudentMenu.printMenu();
    String choice = Input.readLine();
    Arrays.stream(StudentMenu.values())
        .filter(studentMenu -> studentMenu.getChoice().equals(choice))
        .findFirst()
        .ifPresentOrElse(StudentMenu::execute,
            () -> System.out.println("invalid enter"));
  }

  public static void createStudent() {
    boolean result = StudentService.getInstance().createStudent(getStudentScannerResult());
    Message.print(result);
  }

  public static void printStudentsList() {
    ArrayList<StudentDto> studentList = StudentService.getInstance().printStudentsList();
    Message.print(studentList);
  }


  public static void searchStudent() {
    System.out.println("enter your studentId to search");
    String studentId = Input.readLine();
    String seachedResult = StudentService.getInstance().searchStudent(studentId);
    Message.print(seachedResult);
  }

  public static void updateStudent() {
    boolean result = StudentService.getInstance().updateStudent(getStudentScannerResult());
    Message.print(result);
  }

  public static void deleteStudent() {
    System.out.println("enter your studentId to delete");
    boolean result = StudentService.getInstance().deleteStudent();
    Message.print(result);
  }


  public static ArrayList<StudentDto> getStudentScannerResult() {
    System.out.println("------------Student Information------------");
    System.out.println("Student Id : "); String studentId = Input.readLine();
    System.out.println("Student Password : "); String password = Input.readLine();
    System.out.println("Student Name : "); String studentName = Input.readLine();
    System.out.println("Student Department : "); String department = Input.readLine();
    System.out.println("Student Gender : "); String gender = Input.readLine();

    ArrayList<StudentDto> studentDtos = new ArrayList<>();
    StudentDto studentDto = new StudentDto();
    studentDto.setStudentId(studentId);
    studentDto.setPassword(password);
    studentDto.setName(studentName);
    studentDto.setDepartment(department);
    studentDto.setGender(gender);

    studentDtos.add(studentDto);
    return studentDtos;
  }
}
