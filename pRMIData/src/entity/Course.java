package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

@SuppressWarnings("serial")
public class Course implements Serializable {
	protected String courseId;
	protected String pLName;
	protected String courseName;
	protected ArrayList<String> preCourseIdList;

	public Course(String inputString) {
		StringTokenizer stringTokenizer = new StringTokenizer(inputString);
		this.courseId = stringTokenizer.nextToken();
		this.pLName = stringTokenizer.nextToken();
		this.courseName = stringTokenizer.nextToken();
		preCourseIdList = new ArrayList<String>();
		while (stringTokenizer.hasMoreTokens()) {
			this.preCourseIdList.add(stringTokenizer.nextToken());
		}
	}

	public String getCoursesId() {
		return this.courseId;
	}
	
	public ArrayList<String> getPreCoursesId() {
		return this.preCourseIdList;
	}

	@Override
	public String toString() {
		String stringReturn = this.courseId + " " + this.pLName + " " + this.courseName;
		for (int i = 0; i < this.preCourseIdList.size(); i++) {
			stringReturn = stringReturn + " " + this.preCourseIdList.get(i).toString();
		}
		return stringReturn;
	}
}
