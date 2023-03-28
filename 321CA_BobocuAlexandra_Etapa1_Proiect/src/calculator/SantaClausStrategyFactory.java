package calculator;

import exception.NoAverageScoreException;

import java.util.List;

public class SantaClausStrategyFactory {
    /**
     * Method used to create a strategy for calculating the average
     * score for a child, depending on the child's age.
     * @param age of a child
     * @param niceScores list of child's nice scores
     * @return the strategy adapted for each case of child's age
     * @throws NoAverageScoreException exception if a child is actually
     * a young adult
     */
    public SantaClausStrategy createStrategy(final int age,
                                             final List<Double> niceScores)
            throws NoAverageScoreException {
        switch (AgeCalculator.getAgeCategory(age)) {
            case BABY -> {
                return new BabyStrategy(niceScores);
            }
            case KID -> {
                return new KidStrategy(niceScores);
            }
            case TEEN -> {
                return new TeenStrategy(niceScores);
            }
            default -> {
                throw new NoAverageScoreException(
                        "Average Score is not calculated for Young Adult");
            }
        }
    }
}
