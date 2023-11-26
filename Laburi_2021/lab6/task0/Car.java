package lab6.task0;

public class Car {
	class OttoEngine implements Engine {
		private int fuelCapacity;

		public OttoEngine(int fuelCapacity) {
			this.fuelCapacity = fuelCapacity;
		}

		public int getFuelCapacity() {
			return fuelCapacity;
		}
	}

	public OttoEngine getEngine() {
		OttoEngine engine = new OttoEngine(11);
		return engine;
	}
}
