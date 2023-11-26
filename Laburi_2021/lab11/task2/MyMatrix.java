package lab11.task2;

import java.util.ArrayList;

public class MyMatrix implements Summable {
	private ArrayList<ArrayList<Integer>> matrix;

	public MyMatrix(ArrayList<ArrayList<Integer>> matrix) {
		this.matrix = matrix;
	}

	public ArrayList<ArrayList<Integer>> getMatrix() {
		return matrix;
	}

	@Override
	public void addValue(Summable value) {
		if (value instanceof MyMatrix) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					int aux1 = matrix.get(i).get(j);
					int aux2 = ((MyMatrix) value).getMatrix().get(i).get(j);
					aux1 += aux2;
					// Update the matrix with the sum
					matrix.get(i).set(j, aux1);
				}
			}
		}
	}

	@Override
	public String toString() {
		return "MyMatrix{" +
				"matrix=" + matrix +
				'}';
	}
}
