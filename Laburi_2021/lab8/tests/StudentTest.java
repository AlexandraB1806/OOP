package lab8.tests;

import lab8.Student;
import org.junit.jupiter.api.*;

public class StudentTest {
	private Student student;

	@BeforeEach
	public void setUp() {
		this.student = new Student("Alexandra", "Bobocu", 101010, 10);
	}

	@AfterEach
	public void clean() {
		this.student = null;
	}

	@Test
	@DisplayName("Test equals")
	public void testEquals() {
		Assertions.assertEquals(student, new Student("Alexandra", "Bobocu", 101010, 10));
		Assertions.assertNotEquals(student, new Student("Alexandra", "Bobocu", 123456, 10));
	}

	@Test
	@DisplayName("Test compareTo")
	public void testCompareTo() {
		Assertions.assertEquals(0, student.compareTo(new Student("Alexandra", "Bobocu",
				101010, 10)));
		Assertions.assertNotEquals(0, student.compareTo(new Student("Alexandra", "Ionescu",
				101010, 10)));
	}

	@Test
	@DisplayName("Test toString")
	public void testToString() {
		Assertions.assertEquals(student.toString(),
				"Nume: Bobocu Prenume: Alexandra Medie: 10.0 ID: 101010");
		Assertions.assertNotEquals(student.toString(),
				"Nume: Bobocu, Prenume: Alexandra, Medie: 10.0, ID: 101010");
	}
}
