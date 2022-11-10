package dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import dto.EnrollmentDto;
import exception.DuplicateEnrollmentException;
import exception.IllegalValueIdException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnrollmentDao {

  private Connection conn;

  private ResultSet rs = null;
  private String sql;

  public EnrollmentDao() {
    conn = DBConfig.getConnection();
  }

  public void createEnrollment(String userId, String courseId) throws DuplicateEnrollmentException {
    sql = "INSERT INTO Enrollment(userId,courseId) VALUES (?,?)";
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      pstmt.setString(1, userId);
      pstmt.setString(2, courseId);

      pstmt.executeUpdate();
      pstmt.close();

    } catch (MySQLIntegrityConstraintViolationException e) {
      e.printStackTrace();
      throw new DuplicateEnrollmentException("This course has already been registered");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public ArrayList<String> getCompletedCourseList(String userId) {
    ArrayList<String> completedCourseList = new ArrayList<>();
    sql = "SELECT * from Enrollment WHERE isCompleted = 1 AND userId ='" + userId + "' ";
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

  public ArrayList<EnrollmentDto> getEnrollmentData(String userId) {
    ArrayList<EnrollmentDto> enrollmentDtos = new ArrayList<>();
    sql =
        "SELECT * from Enrollment INNER JOIN Course ON Enrollment.courseId  = Course.courseId AND userId ='"
            + userId + "' ";
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      rs = pstmt.executeQuery();
      while (rs.next()) {
        EnrollmentDto enrollmentDto = new EnrollmentDto();
        String userid = rs.getString("userId");
        String courseId = rs.getString("courseId");
        String professorName = rs.getString("professorName");
        String courseName = rs.getString("courseName");
        enrollmentDto.setUserId(userid);
        enrollmentDto.setCourseId(courseId);
        enrollmentDto.setProfessorName(professorName);
        enrollmentDto.setCourseName(courseName);
        enrollmentDtos.add(enrollmentDto);
      }

      rs.close();
      pstmt.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return enrollmentDtos;
  }


  public boolean deleteEnrollment(String userId, String courseId) throws IllegalValueIdException {
    try {
      sql = "DELETE FROM Enrollment " + " WHERE userId = ? AND courseId = ? ";
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      pstmt.setString(1, userId);
      pstmt.setString(2, courseId);
      int state = pstmt.executeUpdate();
      pstmt.close();

      if (state == 0) {
        throw new IllegalValueIdException("invalid courseId is entered to delete");
      }
      return true;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}