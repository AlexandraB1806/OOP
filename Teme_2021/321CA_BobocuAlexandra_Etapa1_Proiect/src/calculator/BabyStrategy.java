package calculator;

import java.util.List;

public class BabyStrategy implements SantaClausStrategy {
    private List<Double> niceScores;

    public BabyStrategy(final List<Double> niceScores) {
        this.niceScores = niceScores;
    }

    /**
     * Method used to calculate the average score for
     * a child that is a baby.
     * @return the calculated average score
     */
    @Override
    public Double calculateAverageScore() {
        return 10.0;
    }
}
