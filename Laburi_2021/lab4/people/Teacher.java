package lab4.people;

import lab4.database.Database;

import java.util.List;

public class Teacher {
	private String firstName;
	private String lastName;
	private List<String> subjects;

	public Teacher(String firstName, String lastName, List<String> subjects) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.subjects = subjects;
	}

	// Ex 2c -> copy constructor
	public Teacher(Teacher teacher) {
		this.firstName = teacher.firstName;
		this.lastName = teacher.lastName;
		this.subjects = teacher.subjects;
	}

	@Override
	public String toString() {
		return "Teacher: " + firstName + " " + lastName + "\n"
				+ "Subjects: " + subjects + "\n";
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

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	// Ex 2e
	// I
	public List<Teacher> getAllTeachers() {
		Database dtb = Database.getDatabase();
		return dtb.findAllTeachers();
	}

	// II
	public List<Teacher> getTeachersBySubject(String subject) {
		Database dtb = Database.getDatabase();
		return dtb.findTeachersBySubject(subject);
	}

	// III
	public List<Student> getAllStudents() {
		Database dtb = Database.getDatabase();
		return dtb.findAllStudents();
	}

	// IV
	public List<Student> getStudentsBySubject(String subject) {
		Database dtb = Database.getDatabase();
		return dtb.getStudentsBySubject(subject);
	}

	// V
	public List<Student> getStudentsByAverageGrade() {
		Database dtb = Database.getDatabase();
		return dtb.getStudentsByAverageGrade();
	}

	// VI
	public List<Student> getStudentsByGradeForSubject(String subject) {
		Database dtb = Database.getDatabase();
		return dtb.getStudentsByGradeForSubject(subject);
	}
}
