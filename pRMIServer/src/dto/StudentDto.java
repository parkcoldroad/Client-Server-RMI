package dto;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentDto implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String studentId;
	protected String name;
	protected String department;
	protected ArrayList<String> completedCoursesList;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public ArrayList<String> getCompletedCoursesList() {
		return completedCoursesList;
	}

	public void setCompletedCoursesList(ArrayList<String> completedCoursesList) {
		this.completedCoursesList = completedCoursesList;
	}
}
