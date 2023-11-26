package lab2.task3;

public class TestPoint {
	public static void main(String[] args) {
		Point number = new Point(10.1f, 10f);

		System.out.println("Coordonatele punctului inainte de modificare: " +
				number);

		number.changeCoords(100.9f,100f);
		System.out.println("Coordonatele punctului dupa modificare: " +
				number);
	}
}
