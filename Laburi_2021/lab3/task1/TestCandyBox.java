package lab3.task1;
import lab3.task2.*;
import lab3.task4.CandyBag;
import lab3.task6.Area;

import java.util.ArrayList;

public class TestCandyBox {
	public static void main(String[] args) {
		// Testare Task1
		CandyBox box = new CandyBox("Vanilla", "Romania");
		System.out.println(box);
		System.out.println();

		// Testare Task3
		Lindt fromLindt1 = new Lindt("Chocolate", "France", 2, 5, 3);
		System.out.println(fromLindt1);
		Lindt fromLindt2 = new Lindt("Chocolate", "France", 2, 5, 3);
		System.out.println(fromLindt1.equals(fromLindt2));
		System.out.println();

		Baravelli fromBaravelli = new Baravelli("Oreo", "Italy", 4, 3);
		// Testare Task5
		fromBaravelli.printBaravelliDim();
		System.out.println();

		ChocAmor fromChocAmor = new ChocAmor("Caramel", "Spain", 6);

		// Testare Task4
		ArrayList<CandyBox> candyBag = new ArrayList<>();
		candyBag.add(fromLindt1);
		candyBag.add(fromBaravelli);
		candyBag.add(fromChocAmor);

		CandyBag fullBag = new CandyBag(candyBag);
		for (CandyBox i : fullBag.getCandyBag()) {
			System.out.println(i);
		}
		System.out.println();

		// Testare Task6
		Area area = new Area(fullBag,20, "Dacia");
		area.getBirthdayCard();
	}
}
