package calculator;

/**
 * Defines the way average score is calculated. Each implementation uses
 * its own algorithm.
 */
public interface SantaClausStrategy {
    /**
     * Calculates the average grade for a child.
     * @return the average grade
     */
    Double calculateAverageScore();
}
