package dao;

import exception.NullDataException;
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
	
	public ArrayList<Domain> getAllCourseRecords() throws NullDataException {
		if(this.courseList.size()==0) throw new NullDataException("----------------- data is null... ------------------");
		return this.courseList;
	}
	
	public boolean addCourseRecords(String courseInfo) {
		if(this.courseList.add(new Course(courseInfo))) return true;
		else return false;
	}
}
