package dao;

import dto.StudentDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDao {

  private Connection conn = null;

  private ResultSet rs = null;
  private String sql;

  public StudentDao() {
    conn = DBConfig.getConnection();
  }

  public boolean createStudentRecords(ArrayList<StudentDto> studentList) {
    sql = "INSERT INTO Student(studentId,studentName,department,password,gender) VALUES (?,?,?,?,?)";
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      for (StudentDto studentDto : studentList) {
        pstmt.setString(1, studentDto.getStudentId());
        pstmt.setString(2, studentDto.getName());
        pstmt.setString(3, studentDto.getDepartment());
        pstmt.setString(4, studentDto.getPassword());
        pstmt.setString(5, studentDto.getGender());
      }

      pstmt.executeUpdate();
      pstmt.close();
      return true;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public ArrayList<StudentDto> readAllStudentRecords() {
    ArrayList<StudentDto> studentDtos = new ArrayList<>();
    sql = "SELECT * from Student";
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      rs = pstmt.executeQuery();
      while (rs.next()) {
        StudentDto studentDto = new StudentDto();
        String studentId = rs.getString("studentId");
        String studentName = rs.getString("studentName");
        String department = rs.getString("department");
        String password = rs.getString("password");
        String gender = rs.getString("gender");
        studentDto.setStudentId(studentId);
        studentDto.setName(studentName);
        studentDto.setDepartment(department);
        studentDto.setPassword(password);
        studentDto.setGender(gender);
        studentDtos.add(studentDto);
      }

      rs.close();
      pstmt.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return studentDtos;
  }

  public String searchStudentRecords(String studentId) {
    sql = "SELECT * from Student WHERE StudentId = " + studentId;
    String result;
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      rs = pstmt.executeQuery();
      rs.next();
      String studentid = rs.getString("studentId");
      String studentName = rs.getString("studentName");
      String department = rs.getString("department");
      String password = rs.getString("password");
      String gender = rs.getString("gender");

      rs.close();
      pstmt.close();
      return studentid + " " + studentName + " " + department + " " + password + " " + gender;
    } catch (SQLException e) {
      throw new RuntimeException();
    }

  }

  public boolean updateStudentRecord(ArrayList<StudentDto> studentList) {
    String studentid = null;
    String studentName = null;
    String department = null;
    String password = null;
    String gender = null;

    for (StudentDto studentDto : studentList) {
      studentid = studentDto.getStudentId();
      studentName = studentDto.getName();
      department = studentDto.getDepartment();
      password = studentDto.getPassword();
      gender = studentDto.getGender();
    }

    try {
      sql = "UPDATE Student SET "
          + "studentName= '" + studentName
          + "'," + "department = '" + department
          + "'," + "password = '" + password
          + "'," + "gender = '" + gender
          + "' WHERE studentId = " + studentid;
      PreparedStatement pstmt = null;
      pstmt = (PreparedStatement) conn.prepareStatement(sql);
      pstmt.executeUpdate();
      pstmt.close();
      return true;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  public boolean deleteStudentRecords(String studentId) {
    try {
      sql = "DELETE FROM Student " + " WHERE studentId = ? ";
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      pstmt.setString(1, studentId);
      pstmt.executeUpdate();

      pstmt.close();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
