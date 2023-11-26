package lab8;

import java.util.*;

public class TestStudent {
	public static void main(String[] args) {
		ArrayList<Student> listOfStudents = new ArrayList<>();

		listOfStudents.add(new Student("Andreea", "Popa", 212660, 5.91));
		listOfStudents.add(new Student("Tudor", "Diaconu", 546738, 7.65));
		listOfStudents.add(new Student("Catalina", "Manea", 999641, 10));
		listOfStudents.add(new Student("Catalina", "Popa", 342555, 10));
		listOfStudents.add(new Student("Tudor", "Mihalcea", 621670, 5.91));

		// Exercitiul 2
		System.out.println("Task2: Sortare crescatoare utilizand Collections.sort:");

		Collections.sort(listOfStudents);
		for (Student student : listOfStudents) {
			System.out.println(student);
		}

		System.out.println("======================================================");
		// Exercitiul 3
		System.out.println("Task3: Sortare descrescatoare utilizand sort din interfata List + functie lambda:");

		listOfStudents.sort((student1, student2) ->
				(int) (student2.getAverageGrade() - student1.getAverageGrade()));
		for (Student student : listOfStudents) {
			System.out.println(student);
		}

		System.out.println("======================================================");

		// Exercitiul 4
		System.out.println("Task4: Sortare dupa ID folosind Coada de prioritati:");

		PriorityQueue<Student> priorityQueue = new PriorityQueue<>(new PriorityQueueComparator());
		priorityQueue.addAll(listOfStudents);

		while(!priorityQueue.isEmpty()) {
			System.out.println(priorityQueue.poll());
		}

		System.out.println("======================================================");

		// Exercitiul 6
		System.out.println("Task6: Afisare continut colectie HashMap:");

		HashMap<Student, LinkedList<String>> studentMap = new HashMap<>();

		LinkedList<String> subjectsFirstYr = new LinkedList<>(Arrays.asList("SD", "MN", "USO", "ELTH", "M1"));
		studentMap.put(listOfStudents.get(0), subjectsFirstYr);
		studentMap.put(listOfStudents.get(1), subjectsFirstYr);

		LinkedList<String> subjectsSecondYr = new LinkedList<>(Arrays.asList("POO", "IOCLA", "AA", "TS", "EEA"));
		studentMap.put(listOfStudents.get(2), subjectsSecondYr);
		studentMap.put(listOfStudents.get(3), subjectsSecondYr);
		studentMap.put(listOfStudents.get(4), subjectsSecondYr);

		for (var iter : studentMap.entrySet()) {
			System.out.println(iter.getKey());
			System.out.println("Materii: " + iter.getValue());
		}

		System.out.println("======================================================");
	}
}
