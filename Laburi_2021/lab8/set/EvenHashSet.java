package lab8.set;

import java.util.HashSet;

public class EvenHashSet extends HashSet<Integer> {
	@Override
	public boolean add(Integer n) {
		if (n % 2 == 0) {
			return super.add(n);
		}
		return false;
	}
}
