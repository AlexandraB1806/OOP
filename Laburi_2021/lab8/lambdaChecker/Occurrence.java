package lab8.lambdaChecker;

import java.util.LinkedHashMap;
import java.util.Scanner;

// Exercitiul 8
public class Occurrence {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
		int n = console.nextInt();

		for (int i = 0; i < n; i++) {
			int nr = console.nextInt();
			map.put(nr, map.getOrDefault(nr, 0) + 1);
		}

		for (var iter : map.entrySet()) {
			System.out.print(iter.getKey() + ": " + iter.getValue() + " ");
		}
	}
}
