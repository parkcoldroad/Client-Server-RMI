package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import entity.Domain;
import entity.Student;
import exception.NullDataException;

public class StudentDao {
	protected ArrayList<Domain> studentList;
	
	public StudentDao(String sStudentFileName) throws FileNotFoundException, IOException {
		BufferedReader objStudentFile = new BufferedReader(new FileReader(sStudentFileName));
		this.studentList = new ArrayList<Domain>();
		while (objStudentFile.ready()) {
			String stuInfo = objStudentFile.readLine();
			if (!stuInfo.equals("")) {
				this.studentList.add(new Student(stuInfo));
			}
		}
		objStudentFile.close();
	}

	public ArrayList<Domain> getAllStudentRecords() throws NullDataException{
		if(this.studentList.size()==0) throw new NullDataException("----------------- data is null... ------------------");
		return this.studentList;
	}

	public boolean addStudentRecords(String studentInfo) {
		if(this.studentList.add(new Student(studentInfo))) return true;
		else return false;
	}
//	public boolean isRegisteredStudent(String sSID) {
//		for (int i = 0; i < this.vStudent.size(); i++) {
//			Student objStudent = (Student) this.vStudent.get(i);
//			if (objStudent.match(sSID)) {
//				return true;
//			}
//		}
//		return false;
//	}
}
