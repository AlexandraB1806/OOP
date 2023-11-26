package lab3.task2;

import lab3.task1.CandyBox;

public class ChocAmor extends CandyBox {
	private float length;

	public ChocAmor() {}
	public ChocAmor(String flavor, String origin, float length) {
		super(flavor, origin);
		this.length = length;
	}

	@Override
	public float getVolume() {
		return length * length * length;
	}

	@Override
	public String toString() {
		return super.toString()
				+ " has volume "
				+ getVolume();
	}

	public void printChocAmorDim(){
		System.out.println("Length = " + length);
	}

	public float getLength() {
		return length;
	}
	public void setLength(float length) {
		this.length = length;
	}
}
