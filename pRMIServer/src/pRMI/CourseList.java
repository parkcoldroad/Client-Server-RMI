package pRMI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CourseList {
protected ArrayList<Course> courseList;
	
	public CourseList(String courseFileName) throws FileNotFoundException, IOException {
		BufferedReader objCourseFile = new BufferedReader(new FileReader(courseFileName));
		this.courseList = new ArrayList<Course>();
		while (objCourseFile.ready()) {
			String courseInfo = objCourseFile.readLine();
			if (!courseInfo.equals("")) {
				this.courseList.add(new Course(courseInfo));
			}
		}
		objCourseFile.close();
	}
	
	public ArrayList<Course> getAllCourseRecords() {
		return this.courseList;
	}
}
