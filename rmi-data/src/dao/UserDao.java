package dao;

import com.mysql.jdbc.MysqlErrorNumbers;
import dto.UserDto;
import exception.DuplicateUserIdException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

  private Connection conn;

  private ResultSet rs = null;
  private String sql;

  public UserDao() {
    conn = DBConfig.getConnection();
  }


  public ArrayList<UserDto> signIn(String userId) throws DuplicateUserIdException {
    ArrayList<UserDto> userDtos = new ArrayList<>();
    sql = "SELECT * from User WHERE userId ='" + userId + "' ";

    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
      UserDto userDto = new UserDto();

      rs = pstmt.executeQuery();
      rs.next();
      String userid = rs.getString("userId");
      String userName = rs.getString("userName");
      String department = rs.getString("department");
      String password = rs.getString("password");
      String gender = rs.getString("gender");

      userDto.setUserId(userid);
      userDto.setName(userName);
      userDto.setDepartment(department);
      userDto.setPassword(password);
      userDto.setGender(gender);

      userDtos.add(userDto);

      rs.close();
      pstmt.close();
      return userDtos;
    } catch (SQLException e) {
      throw new RuntimeException();
    }
  }


  public UserDto createUserRecords(ArrayList<UserDto> userList)
      throws DuplicateUserIdException {
    sql = "INSERT INTO user(userId,userName,department,password,gender) VALUES (?,?,?,?,?)";
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      for (UserDto userDto : userList) {
        pstmt.setString(1, userDto.getUserId());
        pstmt.setString(2, userDto.getName());
        pstmt.setString(3, userDto.getDepartment());
        pstmt.setString(4, userDto.getPassword());
        pstmt.setString(5, userDto.getGender());
      }

      pstmt.executeUpdate();
      pstmt.close();
      return userList.stream().findFirst().get();
    } catch (SQLException e) {
      if (e.getErrorCode() == MysqlErrorNumbers.ER_DUP_ENTRY) {
        throw new DuplicateUserIdException("duplicate id");
      } else {
        throw new RuntimeException(e);
      }
    }
  }

  public ArrayList<UserDto> readAllUserRecords() {
    ArrayList<UserDto> userDtos = new ArrayList<>();
    sql = "SELECT * from user";
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      rs = pstmt.executeQuery();
      while (rs.next()) {
        UserDto userDto = new UserDto();
        String userId = rs.getString("userId");
        String userName = rs.getString("userName");
        String department = rs.getString("department");
        String gender = rs.getString("gender");
        userDto.setUserId(userId);
        userDto.setName(userName);
        userDto.setDepartment(department);
        userDto.setGender(gender);
        userDtos.add(userDto);
      }

      rs.close();
      pstmt.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return userDtos;
  }

  public String searchUserRecords(String userId) {
    sql = "SELECT * from user WHERE userId = " + userId;
    String result;
    try {
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      rs = pstmt.executeQuery();
      rs.next();
      String userid = rs.getString("userId");
      String userName = rs.getString("userName");
      String department = rs.getString("department");
      String gender = rs.getString("gender");

      rs.close();
      pstmt.close();
      return userid + " " + userName + " " + department + " " + gender;
    } catch (SQLException e) {
      throw new RuntimeException();
    }

  }

  public boolean updateUserRecord(ArrayList<UserDto> userList) {
    String userid = null;
    String userName = null;
    String department = null;
    String password = null;
    String gender = null;

    for (UserDto userDto : userList) {
      userid = userDto.getUserId();
      userName = userDto.getName();
      department = userDto.getDepartment();
      password = userDto.getPassword();
      gender = userDto.getGender();
    }

    try {
      sql = "UPDATE User SET userName = '" + userName + "'," + "department = '" + department
          + "'," + "password = '" + password + "'," + "gender = '" + gender
          + "' WHERE userId ='" + userid + "' ";
      PreparedStatement pstmt = null;
      pstmt = (PreparedStatement) conn.prepareStatement(sql);
      pstmt.executeUpdate();
      pstmt.close();
      return true;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  public boolean deleteUserRecords(String userId) {
    try {
      sql = "DELETE FROM user " + " WHERE userId = ? ";
      PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

      pstmt.setString(1, userId);
      pstmt.executeUpdate();

      pstmt.close();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
