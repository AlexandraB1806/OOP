package lab1.task2;

import java.util.ArrayList;

public class Course {
	private String title, description;
	private ArrayList<Student> students;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public ArrayList<Student> filterYear(Integer inputYear) {
		// Creez o lista de studenti
		ArrayList<Student> studentList = new ArrayList<>();
		for (Student st : students) {
			if (st.getYear().equals(inputYear)) {
				// Adaug studentul in lista de studenti creata
				studentList.add(st);
			}
		}
		return studentList;
	}
}
