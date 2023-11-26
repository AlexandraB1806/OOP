package lab8;

import java.util.Comparator;

public class PriorityQueueComparator implements Comparator<Student> {

	@Override
	public int compare(Student stud1, Student stud2) {
		return (int) (stud1.getId() - stud2.getId());
	}
}
