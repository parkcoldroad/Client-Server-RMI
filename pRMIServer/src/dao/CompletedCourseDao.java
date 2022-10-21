package dao;

import dto.CompletedCourseDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompletedCourseDao {

  private Connection conn = null;

  private ResultSet rs = null;
  private String sql;

  public CompletedCourseDao() {
    conn = DBConfig.getConnection();
  }


  public String createCompletedCourse(String studentId, String completedCourseId) {
    sql = "INSERT INTO CompletedCourse(studentId,completedCourseId) VALUES (?,?)";
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      pstmt.setString(1, studentId);
      pstmt.setString(2, completedCourseId);

      pstmt.executeUpdate();
      pstmt.close();
      return "CompletedCourse registration is completed";
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }


  public ArrayList<CompletedCourseDto> readCompletedCourse() {
    ArrayList<CompletedCourseDto> completedCourseDtos = new ArrayList<>();
    sql = "SELECT * from CompletedCourse";
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      rs = pstmt.executeQuery();
      while (rs.next()) {
        CompletedCourseDto completedCourseDto = new CompletedCourseDto();
        String studentId = rs.getString("studentId");
        String completedcourseId = rs.getString("completedcourseId");
        completedCourseDto.setStudentId(studentId);
        completedCourseDto.setCompletedCourseId(completedcourseId);
        completedCourseDtos.add(completedCourseDto);
      }

      rs.close();
      pstmt.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return completedCourseDtos;
  }

}

