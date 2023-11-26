package lab4.database;

import lab4.people.Student;
import lab4.people.Teacher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Database {
	private final List<Student> students = new ArrayList<>();
	private final List<Teacher> teachers = new ArrayList<>();

	public List<Student> getStudents() {
		return students;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	// Singleton lazy instantiation
	private static Database instance = null;
	private Database() {}
	static int count = 0;
	public static Database getDatabase() {
		if (instance == null) {
			instance = new Database();
		}
		count++;
		return instance;
	}

	// Ex 2g
	public static int getNumberOfInstances() {
		return count;
	}

	public void addTeachers(List<Teacher> teachers) {
		this.teachers.addAll(teachers);
	}

	public void addStudents(List<Student> students) {
		this.students.addAll(students);
	}

	// Ex 2d - IV
	public List<Teacher> findTeachersBySubject(String subject) {
		// Creez o lista de profesori care predau o anumita materie
		List<Teacher> teacherList = new ArrayList<>();
		for (Teacher teacher : findAllTeachers())
			if (teacher.getSubjects().contains(subject))
				teacherList.add(teacher);

		return teacherList;
	}

	// Ex 2d - I
	public List<Student> findAllStudents() {
		return getStudents();
	}

	// Ex 2d - II
	public List<Teacher> findAllTeachers() {
		return getTeachers();
	}

	// Ex 2d - III
	public List<Student> getStudentsBySubject(String subject) {
		// Creez o lista de studenti care invata o anumita materie
		List<Student> studentList = new ArrayList<>();
		for (Student student : findAllStudents())
			if (student.getSubjects().containsKey(subject))
				studentList.add(student);

		return studentList;
	}

	// Ex 2d - V
	public List<Student> getStudentsByAverageGrade() {
		getStudents().sort(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				if (o1.averageGrade() > o2.averageGrade())
					return 1;
				else if (o1.averageGrade() < o2.averageGrade())
					return -1;
				else
					return 0;
			}
		});
		return getStudents();
	}

	// Ex 2d - VI
	public List<Student> getStudentsByGradeForSubject(String subject) {
		List<Student> studentList = new ArrayList<>();
		for (Student student : findAllStudents())
		if (student.getSubjects().containsKey(subject))
			studentList.add(student);

		Collections.sort(studentList, (a, b) -> a.getGradeForSubject(subject) - b.getGradeForSubject(subject));
		return studentList;
	}
}
