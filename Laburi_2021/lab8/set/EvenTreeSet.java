package lab8.set;

import java.util.TreeSet;

public class EvenTreeSet extends TreeSet<Integer> {
	@Override
	public boolean add(Integer n) {
		if (n % 2 == 0) {
			return super.add(n);
		}
		return false;
	}
}
