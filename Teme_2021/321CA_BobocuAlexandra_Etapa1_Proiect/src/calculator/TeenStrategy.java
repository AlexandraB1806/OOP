package calculator;

import java.util.List;

public class TeenStrategy implements SantaClausStrategy {
    private List<Double> niceScores;

    public TeenStrategy(final List<Double> niceScores) {
        this.niceScores = niceScores;
    }

    /**
     * Method used to calculate the average score for
     * a child that is a teenager.
     * @return the calculated average score
     */
    @Override
    public Double calculateAverageScore() {
        double sum = 0.0;
        for (int i = 0; i < niceScores.size(); i++) {
            sum += niceScores.get(i) * (i + 1);
        }
        // Gauss sum formula: 1+2+3+..+n = n*(n+1)/2
        double sumOfFirstN = (double) (niceScores.size()
                * (niceScores.size() + 1)) / 2;

        return sum / sumOfFirstN;
    }
}
