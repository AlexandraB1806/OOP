package lab5.task1;

import java.util.Random;

public class RandomOutTask implements Task{
	private int number;

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	Random rand = new Random();

	public RandomOutTask() {
		this.number = rand.nextInt(100000);
	}
	@Override
	public void execute() {
		System.out.println("Numarul generat random este: " + number);
	}
}
