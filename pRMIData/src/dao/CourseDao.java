package dao;

import exception.NullDataException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import entity.Course;
import entity.Domain;
import java.util.Optional;

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
	
	public boolean createCourseRecords(String courseInfo) {
		return this.courseList.add(new Course(courseInfo));
	}
	public void deleteCourseRecords(String courseId) {
		Optional<Domain> optionalStudent = courseList.stream()
				.filter(course -> course.match(courseId))
				.findFirst();

		optionalStudent.ifPresentOrElse(
				course -> courseList.remove(course),
				() -> System.out.println("your courseId is not found"));
	}

}
