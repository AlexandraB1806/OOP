package lab6.task0;

/**
 * Testes internal normal class
 * and anonymous class.
 */
public class Test {
	public static void main(String[] args) {
		// Internal normal class
		Car car = new Car();
		Car.OttoEngine firstEngine = car.getEngine();
		Car.OttoEngine secondEngine = car.new OttoEngine(10);

		System.out.println("Internal normal class:");
		System.out.println(firstEngine.getFuelCapacity());
		System.out.println(secondEngine.getFuelCapacity());

		// Anonymous class
		AnonymousCar anonCar = new AnonymousCar();
		// The type is Engine
		Engine firstAnonEngine = AnonymousCar.getEngine(10);
		System.out.println("Anonymous class:");
		System.out.println(firstAnonEngine.getFuelCapacity());
	}
}
