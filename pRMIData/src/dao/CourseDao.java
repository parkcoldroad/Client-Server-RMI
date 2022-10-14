package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import entity.Course;
import entity.Domain;

public class CourseDao {
protected ArrayList<Domain> courseList;
	
	public CourseDao(String courseFileName) throws FileNotFoundException, IOException {
		BufferedReader objCourseFile = new BufferedReader(new FileReader(courseFileName));
		this.courseList = new ArrayList<Domain>();
		while (objCourseFile.ready()) {
			String courseInfo = objCourseFile.readLine();
			if (!courseInfo.equals("")) {
				this.courseList.add(new Course(courseInfo));
			}
		}
		objCourseFile.close();
	}
	
	public ArrayList<Domain> getAllCourseRecords() {
		return this.courseList;
	}
}
