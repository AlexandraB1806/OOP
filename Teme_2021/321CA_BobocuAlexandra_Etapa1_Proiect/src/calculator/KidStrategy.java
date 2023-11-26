package calculator;

import java.util.List;

public class KidStrategy implements SantaClausStrategy {
    private List<Double> niceScores;

    public KidStrategy(final List<Double> niceScores) {
        this.niceScores = niceScores;
    }

    /**
     * Method used to calculate the average score for
     * a child that is a kid.
     * @return the calculated average score
     */
    @Override
    public Double calculateAverageScore() {
        double sumNiceScores = niceScores.stream()
                .mapToDouble(Double::doubleValue).sum();
        int sizeNiceScores = niceScores.size();

        return sumNiceScores / sizeNiceScores;
    }
}
