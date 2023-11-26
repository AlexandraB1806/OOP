package lab1.task2;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		Course course = new Course();
		course.setTitle("POO");
		course.setDescription("First Lab");

		ArrayList<Student> students = new ArrayList<>();

		Student stud1 = new Student();
		stud1.setName("Amalia");
		stud1.setYear(2);
		students.add(stud1);

		Student stud2 = new Student();
		stud2.setName("Bogdan");
		stud2.setYear(2);
		students.add(stud2);

		Student stud3 = new Student();
		stud3.setName("Matei");
		stud3.setYear(1);
		students.add(stud3);

		Student stud4 = new Student();
		stud4.setName("Georgiana");
		stud4.setYear(3);
		students.add(stud4);

		course.setStudents(students);

		// Task2
		System.out.println((course.filterYear(Integer.parseInt(args[0]))).toString());

		// Task3
		Student stud5 = new Student();
		stud5.setName("Diana");
		stud5.setYear(4);
		students.add(stud5);

		Student stud6 = new Student();
		stud6.setName("Diana");
		stud6.setYear(4);
		students.add(stud6);

		course.setStudents(students);

		System.out.println(stud5.equals(stud6));
	}
}
