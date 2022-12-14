package dao;

import com.mysql.jdbc.MysqlErrorNumbers;
import dto.CourseDto;
import exception.DuplicateUserIdException;
import exception.DuplicatedCourseIdException;
import exception.IllegalValueIdException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDao {

  private Connection conn = null;

  private ResultSet rs = null;
  private String sql;

  public CourseDao() {
    conn = DBConfig.getConnection();
  }


  public boolean createCourseRecord(ArrayList<CourseDto> courseList)
      throws DuplicatedCourseIdException {
    sql = "INSERT INTO Course(courseId,professorName,courseName) VALUES (?,?,?)";
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      for (CourseDto courseDto : courseList) {
        pstmt.setString(1, courseDto.getCourseId());
        pstmt.setString(2, courseDto.getpLName());
        pstmt.setString(3, courseDto.getCourseName());
      }

      pstmt.executeUpdate();
      pstmt.close();
      return true;
    } catch (SQLException e) {
      if (e.getErrorCode() == MysqlErrorNumbers.ER_DUP_ENTRY) {
        throw new DuplicatedCourseIdException("duplicate courseId, please enter another Id");
      } else {
        throw new RuntimeException(e);
      }
    }
  }


  public ArrayList<CourseDto> readAllCourseRecords() {
    ArrayList<CourseDto> courseDtos = new ArrayList<>();
    sql = "SELECT * from Course";
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      rs = pstmt.executeQuery();
      while (rs.next()) {
        CourseDto courseDto = new CourseDto();
        String courseId = rs.getString("courseId");
        String professorName = rs.getString("professorName");
        String courseName = rs.getString("courseName");
        courseDto.setCourseId(courseId);
        courseDto.setpLName(professorName);
        courseDto.setCourseName(courseName);
        courseDtos.add(courseDto);
      }

      rs.close();
      pstmt.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return courseDtos;
  }

  public String searchCourseRecord(String courseId) throws IllegalValueIdException {
    sql = "SELECT * from Course WHERE courseId = '" + courseId + "' ";
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      rs = pstmt.executeQuery();
      if(!rs.next()) {
        throw new IllegalValueIdException("invalid id is entered to search");
      }

      String courseid = rs.getString("courseId");
      String professorName = rs.getString("professorName");
      String courseName = rs.getString("courseName");

      rs.close();
      pstmt.close();
      return courseid + " " + professorName + " " + courseName;
    } catch (SQLException e) {
      throw new RuntimeException();
    }

  }

  public boolean updateCourseRecord(ArrayList<CourseDto> courseList)
      throws DuplicatedCourseIdException {
    String courseId = null;
    String professorName = null;
    String courseName = null;

    for (CourseDto courseDto : courseList) {
      courseId = courseDto.getCourseId();
      professorName = courseDto.getpLName();
      courseName = courseDto.getCourseName();
    }

    try {
      sql = "UPDATE Course SET courseName= '" + courseName + "'," + "professorName = '" + professorName
          + "' WHERE courseId ='" + courseId + "' ";
      PreparedStatement pstmt ;
      pstmt = (PreparedStatement) conn.prepareStatement(sql);
      int state = pstmt.executeUpdate();
      pstmt.close();
      if (state == 0) {
        throw new DuplicatedCourseIdException("invalid courseId is entered to update");
      }
      return true;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  public boolean deleteCourseRecord(String courseId) throws DuplicatedCourseIdException {
    try {
      sql = "DELETE FROM Course " + " WHERE courseId = ? ";
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      pstmt.setString(1, courseId);
      int state = pstmt.executeUpdate();
      pstmt.close();
      if (state == 0) {
        throw new DuplicatedCourseIdException("invalid courseId is entered to delete");
      }
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}

