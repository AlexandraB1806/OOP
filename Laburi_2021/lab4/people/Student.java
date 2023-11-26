package lab4.people;

import lab4.database.Database;

import java.util.*;
import java.util.stream.Collectors;

public class Student {
	private String firstName;
	private String lastName;
	private Map<String, Integer> subjects;

	public Student(String firstName, String lastName, Map<String, Integer> subjects) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.subjects = subjects;
	}

	// Ex 2b -> copy constructor
	public Student(Student stud) {
		this.firstName = stud.firstName;
		this.lastName = stud.lastName;
		this.subjects = stud.subjects;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Map<String, Integer> getSubjects() {
		return subjects;
	}

	public void setSubjects(HashMap<String, Integer> subjects) {
		this.subjects = subjects;
	}

	// Ex 2b
	public double averageGrade() {
		int sum = 0, numOfSubjects = 0;
		for (Map.Entry<String, Integer> subject : getSubjects().entrySet()) {
			sum = sum + subject.getValue();
			numOfSubjects++;
		}
		return (double)(sum/numOfSubjects);
	}

	// Ex 2f - I
	public List<Teacher> getAllTeachers() {
		Database dtb = Database.getDatabase();
		List<Teacher> teacherList = dtb.findAllTeachers();
		List<Teacher> deepCopyTeacherList = new ArrayList<>();
		for (var teacher : teacherList) {
			deepCopyTeacherList.add(new Teacher(teacher));
		}
		return Collections.unmodifiableList(deepCopyTeacherList);
	}

	// Ex 2b
	public int getGradeForSubject(String subject) {
		if (getSubjects().containsKey(subject))
			return getSubjects().get(subject);
		return 0; // nu exista materia deci nu avem ce nota returna
	}

	@Override
	public String toString() {
		return "Student: " + firstName + " " + lastName + "\n"
				+ "Subjects: " + subjects + "\n";
	}

	// Ex 2f - II
	public List<Teacher> getTeachersBySubject(String subject) {
		Database dtb = Database.getDatabase();
		List<Teacher> teacherList = dtb.findTeachersBySubject(subject);
		List<Teacher> deepCopyTeacherList = new ArrayList<>();
		for (var teacher : teacherList) {
			deepCopyTeacherList.add(new Teacher(teacher));
		}
		return Collections.unmodifiableList(deepCopyTeacherList);
	}

	// Ex 2f - III
	public List<Student> getAllStudents() {
		Database dtb = Database.getDatabase();
		List<Student> studentList = dtb.findAllStudents();
		List<Student> deepCopyStudentList = new ArrayList<>();
		for (var student: studentList) {
			deepCopyStudentList.add(new Student(student));
		}
		return Collections.unmodifiableList(deepCopyStudentList);
	}

	// Ex 2f - IV
	public List<Student> getStudentsBySubject(String subject) {
		Database dtb = Database.getDatabase();
		List<Student> studentList = dtb.getStudentsBySubject(subject);
		List<Student> deepCopyStudentList = new ArrayList<>();
		for (var student: studentList) {
			deepCopyStudentList.add(new Student(student));
		}
		return Collections.unmodifiableList(deepCopyStudentList);
	}

	// Ex 2f - V
	public List<Student> getStudentsByAverageGrade() {
		Database dtb = Database.getDatabase();
		List<Student> studentList = dtb.getStudentsByAverageGrade();
		List<Student> deepCopyStudentList = new ArrayList<>();
		for (var student: studentList) {
			deepCopyStudentList.add(new Student(student));
		}
		return Collections.unmodifiableList(deepCopyStudentList);
	}

	// Ex 2f - VI
	public List<Student> getStudentsByGradeForSubject(String subject) {
		Database dtb = Database.getDatabase();
		List<Student> studentList = dtb.getStudentsByGradeForSubject(subject);
		List<Student> deepCopyStudentList = new ArrayList<>();
		for (var student: studentList) {
			deepCopyStudentList.add(new Student(student));
		}
		return Collections.unmodifiableList(deepCopyStudentList);
	}
}
