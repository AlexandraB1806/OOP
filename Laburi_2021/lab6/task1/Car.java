package lab6.task1;

public class Car {
	private int price, year;
	enum CarType {
		TOYOTA,
		FORD,
		PORSCHE
	}
	private CarType model;

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

	public CarType getModel() {
		return model;
	}
	public void setModel(CarType model) {
		this.model = model;
	}
}
