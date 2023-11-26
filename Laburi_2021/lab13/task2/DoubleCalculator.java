package lab13.task2;

import java.util.Collection;

public class DoubleCalculator implements Calculator {
    @Override
    public Double add(Double nr1, Double nr2) {
        if (nr1 == null || nr2 == null) {
            throw new NullParameterException();
        }
        if (nr1 + nr2 == Double.POSITIVE_INFINITY) {
            throw new OverflowException();
        } else if (nr1 + nr2 == Double.NEGATIVE_INFINITY) {
            throw new UnderflowException();
        } else {
            return nr1 + nr2;
        }
    }

    @Override
    public Double divide(Double nr1, Double nr2) {
        if (nr1 == null || nr2 == null) {
            throw new NullParameterException();
        }
        if (nr2 == 0d) {
            if (nr1 > 0) {
                throw new OverflowException();
            } else if (nr1 < 0) {
                throw new UnderflowException();
            } else throw new NotANumberException();
        }
        if (nr1 + nr2 == Double.POSITIVE_INFINITY) {
            throw new OverflowException();
        } else if (nr1 + nr2 == Double.NEGATIVE_INFINITY) {
            throw new UnderflowException();
        }
        return nr1 / nr2;
    }

    @Override
    public Double average(Collection<Double> numbers) {
        double sum = 0d;
        if (numbers.isEmpty()) {
            throw new NotANumberException();
        }
        for(Double number : numbers) {
            sum = add(sum, number);
        }
        return divide(sum, (double)numbers.size());
    }
}
