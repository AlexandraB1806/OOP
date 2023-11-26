package lab3.task4;

import lab3.task1.CandyBox;
import java.util.ArrayList;

public class CandyBag {
	private ArrayList<CandyBox> candyBag;

	public CandyBag(){}

	public CandyBag(ArrayList<CandyBox> candyBag){
		this.candyBag = candyBag;
	}

	public ArrayList<CandyBox> getCandyBag() {
		return candyBag;
	}
	public void setCandyBag(ArrayList<CandyBox> candyBag) {
		this.candyBag = candyBag;
	}
}
