package lab3.task6;

import lab3.task1.CandyBox;
import lab3.task4.CandyBag;

public class Area {
	private CandyBag cutieGigel;
	private int number;
	private String street;

	public Area() {}
	public Area(CandyBag cutieGigel, int number, String street) {
		this.cutieGigel = cutieGigel;
		this.number = number;
		this.street = street;
	}

	public void getBirthdayCard() {
		System.out.println("Address: Street " + street + " Number " + number + "\nLa multi ani!");
		for (CandyBox index : cutieGigel.getCandyBag()) {
			System.out.println(index);
		}
	}

	public CandyBag getCutieGigel() {
		return cutieGigel;
	}
	public void setCutieGigel(CandyBag cutieGigel) {
		this.cutieGigel = cutieGigel;
	}

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
}
