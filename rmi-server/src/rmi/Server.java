package rmi;

import dto.CourseDto;
import dto.EnrollmentDto;
import dto.LogDto;
import dto.PreCourseDto;
import dto.UserDto;
import exception.DuplicateEnrollmentException;
import exception.DuplicateUserIdException;
import exception.DuplicatedCourseIdException;
import exception.IllegalValueIdException;
import exception.NullDataException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Optional;
import response.FailedResponse;
import response.Response;
import response.SuccessResponse;

public class Server extends UnicastRemoteObject implements ClientStub {

  private static Server clientServer;
  private final Registry clientserverRegistry;

  private DataServer dataServer;

  private Server() throws RemoteException {
    clientserverRegistry = LocateRegistry.createRegistry(14000);
  }

  public static Server getInstance() {
    if (clientServer == null) {
      try {
        clientServer = new Server();
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
    return clientServer;
  }

  public void start() {
    try {
      clientserverRegistry.bind("Server", clientServer);
      dataServer = DataServer.getInstance();
      System.out.println("Server is ready");

    } catch (RemoteException | AlreadyBoundException e) {
      e.printStackTrace();
    }

  }

  @Override
  public Response<ArrayList<LogDto>> readLog() throws RemoteException {
     return new SuccessResponse<>(dataServer.readLog());
  }

  @Override
  public void createLog(ArrayList<LogDto> logDto) throws RemoteException {
    dataServer.createLog(logDto);
  }


  @Override
  public Response<UserDto> signIn(String userId, String password) {
    ArrayList<UserDto> userList;
    try {
      userList = dataServer.signIn(userId);
    } catch (RemoteException | IllegalValueIdException e) {
      e.printStackTrace();
      return new FailedResponse<>(e.getMessage());
    }

    Optional<UserDto> OptionalUser = userList.stream()
        .filter(user -> user.getUserId().equals(userId) && user.getPassword().equals(password))
        .findFirst();

    if (OptionalUser.isPresent()) {
      return new SuccessResponse<>(OptionalUser.get());
    } else {
      return new FailedResponse<>("your pw is invalid");
    }
  }

  public Response<UserDto> createUserData(ArrayList<UserDto> userDtos) throws RemoteException {
    try {
      return new SuccessResponse<>(dataServer.createUserData(userDtos));
    } catch (DuplicateUserIdException e) {
      return new FailedResponse<>(e.getMessage());
    }
  }

  @Override
  public Response<Boolean> createCourseData(ArrayList<CourseDto> courseData) throws RemoteException {
    try {
      return new SuccessResponse<>(dataServer.createCourseData(courseData));
    } catch (DuplicatedCourseIdException e) {
      return new FailedResponse<>(e.getMessage());
    }
  }

  public Response<String> createEnrollment(String userId, String courseId) throws RemoteException {
    boolean isReady = true;
    String enrollmentResult = null;

    ArrayList<String> preCourseLists = dataServer.searchPreCourse(courseId);

    ArrayList<String> completedCourseList = dataServer.getCompletedCourseList(userId);
    for (String precourse : preCourseLists) {
      isReady = completedCourseList.contains(precourse);
    }
    if (isReady) {
      try {
        enrollmentResult = dataServer.createEnrollment(userId, courseId);
      } catch (DuplicateEnrollmentException e) {
        return new FailedResponse<>(e.getMessage());
      }
    }
    return new SuccessResponse<>(enrollmentResult);
  }

  @Override
  public Response<String> createPreCourseData(String courseId, String precourseId) throws RemoteException {
    return new SuccessResponse<>(dataServer.createPreCourseData(courseId, precourseId));
  }

  public Response<ArrayList<UserDto>> getAllUserData() throws RemoteException {
    try {
      return new SuccessResponse<>(dataServer.getAllUserData());
    } catch (NullDataException e) {
      return new FailedResponse<>(e.getMessage());
    }
  }

  @Override
  public Response<ArrayList<CourseDto>> getAllCourseData() throws RemoteException {
    try {
      return new SuccessResponse<>(dataServer.getAllCourseData());
    } catch (NullDataException e) {
      return new FailedResponse<>(e.getMessage());
    }
  }

  @Override
  public Response<ArrayList<EnrollmentDto>> getEnrollmentData(String userId) throws RemoteException {
    try {
      return new SuccessResponse<>(dataServer.getEnrollmentData(userId));
    } catch (NullDataException e) {
      return new FailedResponse<>(e.getMessage());
    }
  }

  @Override
  public Response<ArrayList<PreCourseDto>> getAllPreCourseData() throws RemoteException {
    try {
      return new SuccessResponse<>(dataServer.getAllPreCourseData());
    } catch (NullDataException e) {
      return new FailedResponse<>(e.getMessage());
    }
  }

  public Response<String> searchUserData(String userId) throws RemoteException {
    try {
      return new SuccessResponse<>(dataServer.searchUserData(userId));
    } catch (IllegalValueIdException e) {
      return new FailedResponse<>(e.getMessage());
    }
  }

  @Override
  public Response<String> searchCourseData(String courseId) throws RemoteException {
    try {
      return new SuccessResponse<>(dataServer.searchCourseData(courseId));
    } catch (IllegalValueIdException e) {
      return new FailedResponse<>(e.getMessage());
    }
  }

  @Override
  public Response<ArrayList<String>> searchPreCourseData(String courseId) throws RemoteException {
      return new SuccessResponse<>(dataServer.searchPreCourse(courseId));
  }

  public Response<Boolean> updateUserData(ArrayList<UserDto> userDtos) throws RemoteException {
    try {
      return new SuccessResponse<>(dataServer.updateUserData(userDtos));
    } catch (DuplicateUserIdException e) {
      return new FailedResponse<>(e.getMessage());
    }
  }

  @Override
  public Response<Boolean> updateCourseData(ArrayList<CourseDto> courseDtos) throws RemoteException {
    try {
      return new SuccessResponse<>(dataServer.updateCourseData(courseDtos));
    } catch (DuplicatedCourseIdException e) {
      return new FailedResponse<>(e.getMessage());
    }
  }

  @Override
  public Response<Boolean> updatePreCourseData(String courseId, String preCourseId) throws RemoteException {
    try {
      return new SuccessResponse<>(dataServer.updatePreCourseData(courseId, preCourseId));
    } catch (IllegalValueIdException e) {
      return new FailedResponse<>(e.getMessage());
    }
  }

  public Response<Boolean> deleteUserData(String userId) throws RemoteException {
    try {
      return new SuccessResponse<>(dataServer.deleteUserData(userId));
    } catch (DuplicateUserIdException e) {
      return new FailedResponse<>(e.getMessage());
    }
  }


  @Override
  public Response<Boolean> deleteCourseData(String courseId) throws RemoteException {
    try {
      return new SuccessResponse<>(dataServer.deleteCourseData(courseId));
    } catch (DuplicatedCourseIdException e) {
      return new FailedResponse<>(e.getMessage());
    }
  }


  public Response<Boolean> deleteEnrollment(String userId, String courseId) throws RemoteException {
    try {
      return new SuccessResponse<>(dataServer.deleteEnrollment(userId, courseId));
    } catch (IllegalValueIdException e) {
      return new FailedResponse<>(e.getMessage());
    }
  }

  @Override
  public Response<Boolean> deletePreCourse(String courseId, String preCourseId) throws RemoteException {
    try {
      return new SuccessResponse<>(dataServer.deletePreCourse(courseId, preCourseId));
    } catch (IllegalValueIdException e) {
      return new FailedResponse<>(e.getMessage());
    }
  }
}
