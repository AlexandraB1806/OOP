package input;

import model.AnnualChanges;
import model.InitialData;

import java.util.List;

public class Input {
    private int numberOfYears;
    private Double santaBudget;
    private InitialData initialData;
    private List<AnnualChanges> annualChanges;

    public Input() {

    }

    public Input(final int numberOfYears, final Double santaBudget,
                 final InitialData initialData,
                 final List<AnnualChanges> annualChanges) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.initialData = initialData;
        this.annualChanges = annualChanges;
    }

    /**
     * Method used to obtain the number of years the simulation
     * is implemented
     * @return number of years
     */
    public int getNumberOfYears() {
        return numberOfYears;
    }

    /**
     * Method used to set the number of years the simulation
     * is implemented
     * @param numberOfYears given number of years
     */
    public void setNumberOfYears(final int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    /**
     * Method used to obtain the Santa's budget
     * @return Santa's budget
     */
    public Double getSantaBudget() {
        return santaBudget;
    }

    /**
     * Method used to set the Santa's budget
     * @param santaBudget given Santa's budget
     */
    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    /**
     * Method used to obtain the initial lists of children and gifts.
     * @return initial data
     */
    public InitialData getInitialData() {
        return initialData;
    }

    /**
     * Method used to set the initial lists of children and gifts.
     * @param initialData given initial data
     */
    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    /**
     * Method used to obtain the annual changes that occur.
     * @return annual changes
     */
    public List<AnnualChanges> getAnnualChanges() {
        return annualChanges;
    }

    /**
     * Method used to set the annual changes that occur.
     * @param annualChanges given annual changes
     */
    public void setAnnualChanges(final List<AnnualChanges> annualChanges) {
        this.annualChanges = annualChanges;
    }
}
