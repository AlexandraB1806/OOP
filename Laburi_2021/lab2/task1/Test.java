package lab2.task1;

public class Test {
	public static void main(String[] args) {
		Complex number1 = new Complex();
		Complex number2 = new Complex(2, 5);
		Complex number3 = new Complex(number2);

		number1.showNumber();
		number2.showNumber();
		number3.showNumber();

		number2.addWithComplex(number3);
		number2.showNumber();
	}
}
