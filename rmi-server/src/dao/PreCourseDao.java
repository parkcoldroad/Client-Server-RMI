package dao;

import dto.PreCourseDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PreCourseDao {

  private Connection conn ;

  private ResultSet rs = null;
  private String sql;

  public PreCourseDao() {
    conn = DBConfig.getConnection();
  }


  public String createPreCourseRecord(String courseId, String preCourseId) {
    sql = "INSERT INTO PreCourse(courseId,precourseId) VALUES (?,?)";
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      pstmt.setString(1, courseId);
      pstmt.setString(2, preCourseId);

      pstmt.executeUpdate();
      pstmt.close();
      return "Precourse registration is completed";
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }


  public ArrayList<PreCourseDto> readAllPreCourseRecords() {
    ArrayList<PreCourseDto> preCourseDtos = new ArrayList<>();
    sql = "SELECT * from PreCourse";
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      rs = pstmt.executeQuery();
      while (rs.next()) {
        PreCourseDto preCourseDto = new PreCourseDto();
        String courseId = rs.getString("courseId");
        String precourseId = rs.getString("precourseId");
        preCourseDto.setCourseId(courseId);
        preCourseDto.setPreCourseId(precourseId);
        preCourseDtos.add(preCourseDto);
      }

      rs.close();
      pstmt.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return preCourseDtos;
  }


  public ArrayList<String> searchPreCourse(String courseId) {
    ArrayList<String> preCourseList = new ArrayList<>();
    sql = "SELECT * from PreCourse WHERE courseId = " + courseId;
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      rs = pstmt.executeQuery();
      while (rs.next()) {
        String precourseId = rs.getString("precourseId");
        preCourseList.add(precourseId);
      }

      rs.close();
      pstmt.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return preCourseList;
  }

  public boolean updatePreCourseRecord(String courseId, String preCourseId) {
    try {
      sql = "UPDATE PreCourse SET precourseId= '" + preCourseId + "' WHERE courseId = " + courseId;
      PreparedStatement pstmt = null;
      pstmt = (PreparedStatement) conn.prepareStatement(sql);
      pstmt.executeUpdate();
      pstmt.close();
      return true;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  public boolean deleteCourseRecord(String courseId) {
    try {
      sql = "DELETE FROM PreCourse " + " WHERE courseId = ? ";
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      pstmt.setString(1, courseId);
      pstmt.executeUpdate();

      pstmt.close();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}

