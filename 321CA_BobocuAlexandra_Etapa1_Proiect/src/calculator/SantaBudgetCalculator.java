package calculator;

public final class SantaBudgetCalculator {

    private SantaBudgetCalculator() {

    }

    /**
     * Method used to calculate the assigned budget for a child.
     * @param santaBudget the available Santa's budget
     * @param sumAverageScore the sum of average scores of all children
     * @param averageScore the average score of a specific child
     * @return the calculated budget
     */
    public static Double calculateSantaBudgetForChild(final Double santaBudget,
                                                      final Double sumAverageScore,
                                                      final Double averageScore) {
        Double budgetUnit = calculateBudgetUnit(santaBudget, sumAverageScore);
        return averageScore * budgetUnit;
    }

    private static Double calculateBudgetUnit(final Double santaBudget,
                                              final Double sumAverageScore) {
        return santaBudget / sumAverageScore;
    }
}
