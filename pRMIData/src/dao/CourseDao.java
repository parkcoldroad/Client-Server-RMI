package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import entity.Course;

public class CourseDao {
protected ArrayList<Course> courseList;
	
	public CourseDao(String courseFileName) throws FileNotFoundException, IOException {
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
