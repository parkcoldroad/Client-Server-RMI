package dao;

import com.mysql.jdbc.MysqlErrorNumbers;
import dto.StudentDto;
import exception.DuplicateUserIdException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDao {

  private Connection conn;

  private ResultSet rs = null;
  private String sql;

  public StudentDao() {
    conn = DBConfig.getConnection();
  }


  public ArrayList<StudentDto> signIn(String studentId) throws DuplicateUserIdException {
    ArrayList<StudentDto> studentDtos = new ArrayList<>();
    sql = "SELECT * from Student WHERE StudentId = " + studentId;

    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
      StudentDto studentDto = new StudentDto();

      rs = pstmt.executeQuery();
      rs.next();
      String studentid = rs.getString("studentId");
      String password = rs.getString("password");
      String studentName = rs.getString("studentName");

      studentDto.setStudentId(studentid);
      studentDto.setPassword(password);
      studentDto.setName(studentName);
      studentDtos.add(studentDto);

      rs.close();
      pstmt.close();
      return studentDtos;
    } catch (SQLException e) {
      if(e.getErrorCode() == MysqlErrorNumbers.ER_DUP_ENTRY)
        throw new DuplicateUserIdException("your id is duplicated");
      else throw new RuntimeException();
    }
  }


  public boolean createStudentRecords(ArrayList<StudentDto> studentList)
      throws DuplicateUserIdException {
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
      if(e.getErrorCode() == MysqlErrorNumbers.ER_DUP_ENTRY)
        throw new DuplicateUserIdException("duplicate id");
      else throw new RuntimeException(e);
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
        String gender = rs.getString("gender");
        studentDto.setStudentId(studentId);
        studentDto.setName(studentName);
        studentDto.setDepartment(department);
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
      String gender = rs.getString("gender");

      rs.close();
      pstmt.close();
      return studentid + " " + studentName + " " + department + " " + gender;
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
