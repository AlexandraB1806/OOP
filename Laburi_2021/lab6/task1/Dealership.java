package lab6.task1;

import java.util.Random;

/**
 * Contains 3 private internal classes for
 * 3 different types of discount;
 * Contains a method which calculates the
 * price of a car after applying different
 * types of discount
 */
public class Dealership {
	// Task2: the 3 offers
	// First offer
	private static class BrandOffer implements Offer {
		@Override
		public int getDiscount(Car car) {
			if (car.getModel().equals(Car.CarType.FORD))
				return 5;
			else if (car.getModel().equals(Car.CarType.TOYOTA))
				return 7;
			else if (car.getModel().equals(Car.CarType.PORSCHE))
				return 3;
			return 0;
		}
	}

	// Second offer
	private static class DealerOffer implements Offer {
		@Override
		public int getDiscount(Car car) {
			return (2021 - car.getYear()) * 100;
		}
	}

	// Third offer
	private static class SpecialOffer implements Offer {
		@Override
		public int getDiscount(Car car) {
			Random discount = new Random(car.getPrice());
			return (int)discount.nextInt(0,300);
		}
	}

	// Create instances of the private inner classes
	private final BrandOffer discountBrand = new BrandOffer();
	private final DealerOffer discountDealer = new DealerOffer();
	private final SpecialOffer discountSpecial = new SpecialOffer();

	// Calculates the final price after applying discounts
	public int getFinalPrice(Car car) {
		return car.getPrice() - discountBrand.getDiscount(car) * car.getPrice() / 100
				- discountDealer.getDiscount(car)
				- discountSpecial.getDiscount(car);
	}

	public void negociate(Car car, Offer offer) {
		Random rand = new Random(car.getPrice());
		int isDiscount = (int)rand.nextInt(0,2);

		Dealership dealer = new Dealership();
		int finalPrice = dealer.getFinalPrice(car);

		System.out.println("===== Task4 =====");
		System.out.println("Initial price: " + car.getPrice() + " euros");
		System.out.println("Applying Brand discount: "
				+ discountBrand.getDiscount(car) * car.getPrice() / 100 + " euros");
		System.out.println("Applying Dealer discount: " + discountDealer.getDiscount(car) + " euros");
		System.out.println("Applying Special discount: " + discountSpecial.getDiscount(car) + " euros");
		System.out.println("Final price: " + finalPrice + " euros");
		// Client discount is applied
		if (isDiscount == 1) {
			System.out.println("Applying Client discount: " + offer.getDiscount(car) + " euros");
			finalPrice = finalPrice - offer.getDiscount(car);
			System.out.println("Final price after negotiation: " + finalPrice + " euros");
		}
		// Client discount is not applied
		else {
			System.out.println("Client discount rejected!");
		}
	}
}
