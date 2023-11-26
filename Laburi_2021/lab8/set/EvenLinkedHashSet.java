package lab8.set;

import java.util.LinkedHashSet;

public class EvenLinkedHashSet extends LinkedHashSet<Integer> {
	@Override
	public boolean add(Integer n) {
		if (n % 2 == 0) {
			return super.add(n);
		}
		return false;
	}
}
