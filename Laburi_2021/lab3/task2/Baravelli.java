package lab3.task2;

import lab3.task1.CandyBox;
import static java.lang.Math.PI;

public class Baravelli extends CandyBox {
	private float radius, height;

	public Baravelli() {}
	public Baravelli(String flavor, String origin, float radius, float height) {
		super(flavor, origin);
		this.radius = radius;
		this.height = height;
	}

	@Override
	public float getVolume() {
		return (float)(PI * (radius * radius) * height);
	}

	@Override
	public String toString() {
		return super.toString()
				+ " has volume "
				+ getVolume();
	}

	public void printBaravelliDim() {
		System.out.println("Radius = " + radius + "\nHeight = " + height);
	}

	public float getRadius() {
		return radius;
	}
	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
}
