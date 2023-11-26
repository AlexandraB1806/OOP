package lab11.task2;

import java.util.Collection;
import java.util.Iterator;

public interface Summable {
    void addValue(Summable value);

    static <T extends Summable> Summable sumOfElements(Collection<T> c) {
        Iterator<T> iterator = c.iterator();
        Summable sum = iterator.next();
        while(iterator.hasNext()) {
            sum.addValue(iterator.next());
        }
        return sum;
    }
}
