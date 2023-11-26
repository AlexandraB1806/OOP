package lab8.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

// Exercitiul 7
public class TestSet {
	public static void main(String[] args) {
		LinkedHashSet<Integer> set1 = new EvenLinkedHashSet();
		set1.add(12);
		set1.add(2);
		set1.add(25);
		set1.add(90);
		set1.add(4);
		set1.add(31);
		System.out.println("Linked Hash Set:");
		// Elementele pare sunt afisate in ordinea in care au fost puse
		for (int current : set1) {
			System.out.println(current);
		}

		System.out.println("===========");

		HashSet<Integer> set2 = new EvenHashSet();
		set2.add(12);
		set2.add(2);
		set2.add(25);
		set2.add(90);
		set2.add(4);
		set2.add(31);
		System.out.println("Hash Set:");
		// Elementele pare sunt nesortate, neordonate
		for (int current : set2) {
			System.out.println(current);
		}

		System.out.println("===========");

		TreeSet<Integer> set3 = new EvenTreeSet();
		set3.add(12);
		set3.add(2);
		set3.add(25);
		set3.add(90);
		set3.add(4);
		set3.add(31);
		System.out.println("Tree Set:");
		// Elementele pare sunt sortate
		for (int current : set3) {
			System.out.println(current);
		}
	}
}
