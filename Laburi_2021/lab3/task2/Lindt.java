package lab3.task2;

import lab3.task1.CandyBox;

public class Lindt extends CandyBox {
	private float length, width, height;

	public Lindt() {}
	public Lindt(String flavor, String origin, float length, float width, float height){
		super(flavor, origin);
		this.length = length;
		this.width = width;
		this.height = height;
	}

	@Override
	public float getVolume() {
		return length * width * height;
	}

	@Override
	public String toString() {
		return super.toString()
				+ " has volume "
				+ getVolume();
	}

	public void printLindtDim(){
		System.out.println("Width = " + width + "\nHeight = " + height + "\nLength = " + length);
	}

	public float getLength() {
		return length;
	}
	public void setLength(float length) {
		this.length = length;
	}

	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
}
