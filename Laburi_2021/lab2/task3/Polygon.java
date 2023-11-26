package lab2.task3;

public class Polygon {
	private int n;
	private Point[] points;

	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}

	public Point[] getPoints() {
		return points;
	}
	public void setPoints(Point[] points) {
		this.points = points;
	}

	public Polygon(int n) {
		points = new Point[n];
		// Aloc spatiu pentru fiecare punct si il initializez
		for(int i = 0; i < points.length; ++i)
			points[i] = new Point(0,0);
	}

	public Polygon(float[] pairs) {
		this(pairs.length / 2);

		// Actualizez coordonatele
		for(int i = 0; i < pairs.length / 2; ++i)
			points[i].changeCoords(pairs[2 * i], pairs[2 * i + 1]);
	}
}
