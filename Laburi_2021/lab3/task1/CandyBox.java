package lab3.task1;

import java.util.Objects;

public class CandyBox {
	private String flavor, origin;

	public CandyBox() {}
	public CandyBox(String flavor, String origin){
		this.flavor = flavor;
		this.origin = origin;
	}

	public float getVolume() {
		return 0.f;
	}

	@Override
	public String toString() {
		return "The "+ origin
				+ " " + flavor;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CandyBox candyBox = (CandyBox) o;
		return Objects.equals(flavor, candyBox.flavor) && Objects.equals(origin, candyBox.origin);
	}

	@Override
	public int hashCode() {
		return Objects.hash(flavor, origin);
	}

	public String getFlavor() {
		return flavor;
	}
	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
}
