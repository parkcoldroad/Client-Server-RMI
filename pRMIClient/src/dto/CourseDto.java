package dto;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class CourseDto implements Serializable {
	protected String courseId;
	protected String pLName;
	protected String courseName;
	protected ArrayList<String> preCourseIdList;


	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getpLName() {
		return pLName;
	}

	public void setpLName(String pLName) {
		this.pLName = pLName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public ArrayList<String> getPreCourseIdList() {
		return preCourseIdList;
	}

	public void setPreCourseIdList(ArrayList<String> preCourseIdList) {
		this.preCourseIdList = preCourseIdList;
	}
}
