package lab2.task3;

public class TestPolygon {
	public static void main(String[] args) {
		Polygon polygon1 = new Polygon(4);
		float[] pairs = new float[]{5.6f, 1.3f, 2.9f, 3.1f, 4f, 5f, 8.9f, 7f};
		Polygon polygon2 = new Polygon(pairs);
		int i, j;

		System.out.println("Poligonul initializat cu 0:");
		System.out.print("{");
		for (i = 0; i < polygon1.getPoints().length - 1; ++i) {
			System.out.print(polygon1.getPoints()[i]);
			System.out.print(", ");
		}
		System.out.print(polygon1.getPoints()[i]);
		System.out.print("}");

		System.out.println("\n");

		System.out.println("Poligonul actualizat:");
		System.out.print("{");
		for (j = 0; j < polygon2.getPoints().length - 1; ++j) {
			System.out.print(polygon2.getPoints()[j]);
			System.out.print(", ");
		}
		System.out.print(polygon2.getPoints()[j]);
		System.out.print("}");
	}
}
