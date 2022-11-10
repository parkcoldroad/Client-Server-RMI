package dao;

import dto.PreCourseDto;
import exception.IllegalValueIdException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PreCourseDao {

  private Connection conn;

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
      return "PreCourse registration is completed";
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

  public ArrayList<String> searchPreCourse(String courseId) throws IllegalValueIdException {
    ArrayList<String> preCourseList = new ArrayList<>();
    sql = "SELECT * from PreCourse WHERE courseId ='" + courseId + "' ";
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      rs = pstmt.executeQuery();
      if (!rs.next()) {
        throw new IllegalValueIdException("invalid preCourseId is entered to search");
      }

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

  public boolean updatePreCourseRecord(String courseId, String preCourseId)
      throws IllegalValueIdException {
    try {
      sql = "UPDATE PreCourse SET precourseId= '" + preCourseId + "' WHERE courseId ='" + courseId
          + "' ";
      PreparedStatement pstmt = null;
      pstmt = (PreparedStatement) conn.prepareStatement(sql);
      int state = pstmt.executeUpdate();
      pstmt.close();
      if (state == 0) {
        throw new IllegalValueIdException("invalid preCourseId is entered to update");
      }
      return true;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  public boolean deletePreCourseRecord(String courseId, String preCourseId)
      throws IllegalValueIdException {
    try {
      sql = "DELETE FROM PreCourse " + " WHERE courseId = ? AND precourseId = ?";
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      pstmt.setString(1, courseId);
      pstmt.setString(2, preCourseId);
      int state = pstmt.executeUpdate();
      pstmt.close();

      if (state == 0) {
        throw new IllegalValueIdException("invalid preCourseId is entered to delete");
      }
      return true;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}

