package lab6.task1;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		Car firstCar = new Car();
		Car secondCar = new Car();
		Car thirdCar = new Car();

		firstCar.setPrice(35000);
		secondCar.setPrice(26000);
		thirdCar.setPrice(85000);

		firstCar.setYear(2018);
		secondCar.setYear(2015);
		thirdCar.setYear(2020);

		firstCar.setModel(Car.CarType.TOYOTA);
		secondCar.setModel(Car.CarType.FORD);
		thirdCar.setModel(Car.CarType.PORSCHE);

		Dealership dealer = new Dealership();

		System.out.println("===== Task3 =====");
		System.out.println("Final price for the TOYOTA car: " + dealer.getFinalPrice(firstCar));
		System.out.println("Final price for the FORD car: " + dealer.getFinalPrice(secondCar));
		System.out.println("Final price for the PORSCHE car: " + dealer.getFinalPrice(thirdCar));

		// Task 4
		// The client's proposed discounts for each car
		dealer.negociate(firstCar, new Offer() {
			@Override
			public int getDiscount(Car car) {
				return 1000;
			}
		});

		dealer.negociate(secondCar, new Offer() {
			@Override
			public int getDiscount(Car car) {
				return 2000;
			}
		});

		dealer.negociate(thirdCar, new Offer() {
			@Override
			public int getDiscount(Car car) {
				return 1500;
			}
		});

		// Task 5
		System.out.println("===== Task5 =====");
		ArrayList<Car> cars = new ArrayList<>(Arrays.asList(firstCar, secondCar, thirdCar));

		System.out.println("Initial list:");
		// lambda function to print the initial list
		cars.forEach((l) -> System.out.println("Car's price: " + dealer.getFinalPrice(l)));

		// lambda function to remove cars which are not in the budget
		cars.removeIf((l) -> dealer.getFinalPrice(l) > 70000);

		System.out.println("Final list:");
		cars.forEach((l) -> System.out.println("Car's price: " + dealer.getFinalPrice(l)));

		// Task4: The .class files generated at compile time
		// Car$CarType.class, Car.class, Dealership$BrandOffer.class, Dealership$DealerOffer.class,
		// Dealership$SpecialOffer.class, Dealership.class, Offer.class, Test$1.class, Test$2.class
		// Test$3.class, Test.class
	}
}
