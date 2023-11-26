package lab2.task1;

public class Complex {
	private int real, imaginary;

	// Primul constructor
	public Complex(int real, int imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	// Al doilea constructor
	public Complex() {
		this(0, 0);
	}

	// Al treilea constructor -> copy constructor
	public Complex(Complex nr) {
		this.real = nr.real;
		this.imaginary = nr.imaginary;
	}

	public int getReal() {
		return real;
	}
	public void setReal(int real) {
		this.real = real;
	}

	public int getImaginary() {
		return imaginary;
	}
	public void setImaginary(int imaginary) {
		this.imaginary = imaginary;
	}

	public void addWithComplex(Complex nr) {
		this.real = this.real + nr.getReal();
		this.imaginary = this.imaginary + nr.getImaginary();
	}

	public void showNumber() {
		System.out.println(this.real + "+" + this.imaginary + "i");
	}
}
