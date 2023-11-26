package lab8;

import java.util.Objects;

public class Student implements Comparable<Student> {
	private String name, surname;
	private long id;
	private Double averageGrade;

	public Student(String name, String surname, long id, double averageGrade) {
		this.name = name;
		this.surname = surname;
		this.id = id;
		this.averageGrade = averageGrade;
	}

	public long getId() {
		return id;
	}

	public Double getAverageGrade() {
		return averageGrade;
	}

	@Override
	public int compareTo(Student stud) {
		if (averageGrade.equals(stud.averageGrade)) {
			if (surname.equals(stud.surname)) {
				return name.compareTo(stud.name);
			} else {
				return surname.compareTo(stud.surname);
			}
		}
		else {
			return averageGrade.compareTo(stud.averageGrade);
		}
	}

	// Exercitiul 5
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Student student = (Student) o;
		return id == student.id && Double.compare(student.averageGrade, averageGrade) == 0
				&& Objects.equals(name, student.name) && Objects.equals(surname, student.surname);
	}

	// Exercitiul 5
	@Override
	public int hashCode() {
		return Objects.hash(name, surname, id, averageGrade);
	}

	@Override
	public String toString() {
		return "Nume: " + surname + " Prenume: " + name + " Medie: " + averageGrade + " ID: " + id;
	}
}
