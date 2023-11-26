package lab5.task3;

public class Operation implements Minus, Plus, Mult, Div {
	private float number;

	public float getNumber() {
		return number;
	}
	public void setNumber(float number) {
		this.number = number;
	}

	public Operation(float number) {
		this.number = number;
	}

	@Override
	public void minus(float number) {
		this.number = this.number - number;
	}

	@Override
	public void plus(float number) {
		this.number = this.number + number;
	}

	@Override
	public void div(float number) {
		// Verific impartirea la 0
		if (number == 0f)
			return;
		this.number = this.number / number;
	}

	@Override
	public void mult(float number) {
		this.number = this.number * number;
	}
}
