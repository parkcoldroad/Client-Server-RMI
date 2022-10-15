package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

@SuppressWarnings("serial")
public class Course extends Domain implements Serializable {
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

	@Override
	public boolean match(String courseId) {
		return this.courseId.equals(courseId);
	}

	@Override
	public String getName() {
		return this.courseName;
	}

	@Override
	public String getId() {
		return this.courseId;
	}

	@Override
	public void showAttributes() {
		System.out.println("강좌번호	교수		강좌이름							선이수강좌번호		");
	}
}
