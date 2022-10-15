package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Student extends Domain implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String studentId;
	protected String name;
	protected String department;
	protected ArrayList<String> completedCoursesList;

	public Student(String inputString) {
		StringTokenizer stringTokenizer = new StringTokenizer(inputString);
		this.studentId = stringTokenizer.nextToken();
		this.name = stringTokenizer.nextToken();
		this.department = stringTokenizer.nextToken();
		this.completedCoursesList = new ArrayList<>();
		while (stringTokenizer.hasMoreTokens()) {
			this.completedCoursesList.add(stringTokenizer.nextToken());
		}
	}

	public ArrayList<String> getCompletedCourses() {
		return this.completedCoursesList;
	}

	@Override
	public boolean match(String studentId) {
		return this.studentId.equals(studentId);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getId() {
		return this.studentId;
	}

	@Override
	public String toString() {
		String stringReturn = this.studentId + " " + this.name + " " + this.department;
		for (String s : this.completedCoursesList) {
			stringReturn = stringReturn + " " + s;
		}
		return stringReturn;
	}

	@Override
	public void showAttributes() {
		System.out.println("학번		  이름			전공		신청과목");
	}
}
