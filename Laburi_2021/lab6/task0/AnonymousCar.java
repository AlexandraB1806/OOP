package lab6.task0;

public class AnonymousCar {
	public static Engine getEngine(int fuelCapacity) {
		return new Engine () {
			private int fuelCapacity = 11;

			public int getFuelCapacity() {
				return fuelCapacity;
			}
		};
	}
}
