package dao;

import dto.StudentCourseDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentCourseDao {

  private Connection conn;

  private ResultSet rs = null;
  private String sql;

  public StudentCourseDao() {
    conn = DBConfig.getConnection();
  }

  public void createStudentCourse(String studentId, String courseId) {
    sql = "INSERT INTO StudentCourse(studentId,courseId) VALUES (?,?)";
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      pstmt.setString(1, studentId);
      pstmt.setString(2, courseId);

      pstmt.executeUpdate();
      pstmt.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public ArrayList<String> getCompletedCourseList(String studentId) {
    ArrayList<String> completedCourseList = new ArrayList<>();
    sql = "SELECT * from StudentCourse WHERE isCompleted = 1 AND studentId = " + studentId;
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      rs = pstmt.executeQuery();
      while (rs.next()) {
        String courseId = rs.getString("courseId");
        completedCourseList.add(courseId);
      }
      rs.close();
      pstmt.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return completedCourseList;
  }

  public ArrayList<StudentCourseDto> getStudentCourseData() {
    ArrayList<StudentCourseDto> studentCourseDtos = new ArrayList<>();
    sql = "SELECT * from StudentCourse INNER JOIN Course ON StudentCourse.courseId  = Course.courseId";
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      rs = pstmt.executeQuery();
      while (rs.next()) {
        StudentCourseDto studentCourseDto = new StudentCourseDto();
        String studentId = rs.getString("studentId");
        String courseId = rs.getString("courseId");
        String professorName = rs.getString("professorName");
        String courseName = rs.getString("courseName");
        studentCourseDto.setStudentId(studentId);
        studentCourseDto.setCourseId(courseId);
        studentCourseDto.setProfessorName(professorName);
        studentCourseDto.setCourseName(courseName);
        studentCourseDtos.add(studentCourseDto);
      }

      rs.close();
      pstmt.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return studentCourseDtos;
  }


  public boolean deleteStudentCourse(String studentId, String courseId) {
    try {
      sql = "DELETE FROM StudentCourse " + " WHERE studentId = ? AND courseId = ? ";
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      pstmt.setString(1, studentId);
      pstmt.setString(2, courseId);
      pstmt.executeUpdate();

      pstmt.close();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;

  }
}