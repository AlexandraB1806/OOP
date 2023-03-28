package simulation;

import calculator.AgeCalculator;
import calculator.SantaClausStrategy;
import calculator.SantaClausStrategyFactory;
import calculator.SantaBudgetCalculator;
import calculator.SantaGiftsDelivery;

import database.Database;
import database.Helpers;
import entities.Children;
import enums.AgeCategory;
import enums.Category;
import exception.NoAverageScoreException;
import input.Input;
import model.AnnualChanges;
import model.AnnualChildren;
import model.AnnualChildrenWrapper;
import model.ChildUpdate;
import output.Output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;


public class Simulation {
    private final Output output;
    private final List<AnnualChildren> annualChildrenList;

    public Simulation() {
        output = new Output();
        output.setAnnualChildren(new ArrayList<>());
        annualChildrenList = new ArrayList<>();
    }

    /**
     * Method used to simulate the Santa Claus's scenario.
     *
     * @param input the input data
     * @return result of simulation in output
     */
    public Output startSimulation(final Input input) {
        // Adding elements to Database
        Helpers.addChildren(input.getInitialData().getChildren());
        Helpers.addGifts(input.getInitialData().getSantaGiftsList());

        // Simulate the rounds
        round0(input);
        roundNumberOfYears(input);

        return output;
    }

    /**
     * Initial round (Round 0)
     * @param input the input
     */
    private void round0(final Input input) {
        SantaClausStrategyFactory factory = new SantaClausStrategyFactory();
        // Obtain the database where initial data are kept
        Database dtb = Database.getDatabase();
        // Traverse the children' list from database in order to determine
        // the age category for each child
        // If the child is actually a young adult, an exception is thrown
        // and continue searches
        for (Children child : dtb.getChildren()) {
            try {
                // Create a list that retains the nice score for every child
                List<Double> niceScoreHistory = new ArrayList<>();
                // The list has one element: the initial nice score
                niceScoreHistory.add(child.getNiceScore());

                // Calculate the average score for every child depending on the age
                SantaClausStrategy strategy = factory.createStrategy(child.getAge(),
                        niceScoreHistory);
                annualChildrenList.add(buildAnnualChildren(child,
                        niceScoreHistory, strategy.calculateAverageScore()));
            } catch (NoAverageScoreException e) {
                continue;
            }
        }

        // Prepare the output
        AnnualChildrenWrapper annualChildrenWrapper = new AnnualChildrenWrapper();
        annualChildrenWrapper.setChildren(new ArrayList<>());
        output.getAnnualChildren().add(annualChildrenWrapper);

        // Traverse the list of children whose information changes annually
        for (AnnualChildren annualChild : annualChildrenList) {
            // Calculate the budget that Santa Claus allocates for every child,
            // knowing the initial budget of Santa, the sum of average nice
            // scores (the sum of average score from all children in the list)
            // and the average score of the child we want to establish the budget
            Double santaBudgetForChild = SantaBudgetCalculator.
                    calculateSantaBudgetForChild(input.getSantaBudget(),
                            getAverageSum(annualChildrenList),
                            annualChild.getAverageScore());

            // Set the calculated budget
            annualChild.setAssignedBudget(santaBudgetForChild);
            // For every child we have a new delivery
            SantaGiftsDelivery santaGiftsDelivery = new SantaGiftsDelivery(
                    dtb.getGifts(), annualChild.getAssignedBudget(),
                    annualChild.getGiftsPreferences());
            // Set the list of received gifts for the child
            annualChild.setReceivedGifts(santaGiftsDelivery.getSantaGifts());

            output.getAnnualChildren().get(0).getChildren().add(annualChild);
        }
    }

    /**
     * Rounds that occur over number of years
     * @param input the input
     */
    private void roundNumberOfYears(final Input input) {
        SantaClausStrategyFactory factory = new SantaClausStrategyFactory();
        // Obtain the database where initial data are kept
        Database dtb = Database.getDatabase();

        for (int i = 0; i < input.getNumberOfYears(); i++) {
            rewriteAnnualChildrenList();
            // For every child that remains in the Santa's list (did not become
            // a young adult), update the age
            incrementAge();

            // Santa Claus has access to the list of updates
            // that occur every year
            AnnualChanges annualChanges = input.getAnnualChanges().get(i);

            updateChildren(annualChanges);

            // Update the new budget and the new list of gifts in input
            input.setSantaBudget(annualChanges.getNewSantaBudget());
            dtb.getGifts().addAll(annualChanges.getNewGifts());
            // Traverse the list of new children and add them in the list
            for (Children child : annualChanges.getNewChildren()) {
                // Create the children with the new information
                AnnualChildren annualChildren = new AnnualChildren(child.getId(),
                        child.getLastName(), child.getFirstName(),
                        child.getCity(), child.getAge(),
                        child.getGiftsPreferences(),
                        child.getNiceScore(), new ArrayList<>(),
                        (double) 0, new ArrayList<>());

                // Update the history of nice scores
                annualChildren.getNiceScoreHistory().add(annualChildren.getAverageScore());
                // Update the new list of children
                annualChildrenList.add(annualChildren);
            }

            for (AnnualChildren annualChildren : annualChildrenList) {
                try {
                    SantaClausStrategy strategy = factory.createStrategy(annualChildren.getAge(),
                            annualChildren.getNiceScoreHistory());
                    annualChildren.setAverageScore(strategy.calculateAverageScore());
                } catch (NoAverageScoreException e) {
                    continue;
                }
            }

            // Prepare the output
            AnnualChildrenWrapper annualChildrenWrapper = new AnnualChildrenWrapper();
            annualChildrenWrapper.setChildren(new ArrayList<>());
            output.getAnnualChildren().add(new AnnualChildrenWrapper());

            // Traverse the list of children whose information changes annually
            for (AnnualChildren annualChild : annualChildrenList) {
                if (annualChild.getAge() <= 18) {
                    // Calculate the budget that Santa Claus allocates for every child,
                    // knowing the initial budget of Santa, the sum of average nice
                    // scores (the sum of average score from all children in the list)
                    // and the average score of the child we want to establish the budget
                    Double santaBudgetForChild = SantaBudgetCalculator.
                            calculateSantaBudgetForChild(input.getSantaBudget(),
                                    getAverageSum(annualChildrenList),
                                    annualChild.getAverageScore());

                    // Set the calculated budget
                    annualChild.setAssignedBudget(santaBudgetForChild);
                    // For every child we have a new delivery
                    SantaGiftsDelivery santaGiftsDelivery = new SantaGiftsDelivery(
                            dtb.getGifts(), annualChild.getAssignedBudget(),
                            annualChild.getGiftsPreferences());
                    // Set the list of received gifts for the child
                    annualChild.setReceivedGifts(santaGiftsDelivery.getSantaGifts());

                    output.getAnnualChildren().get(i + 1).getChildren().add(annualChild);
                }
            }
        }
        // Clear the database
        Helpers.clear();
    }

    private AnnualChildren buildAnnualChildren(final Children children,
                                               final List<Double> niceScoreHistory,
                                               final Double averageScore) {
        AnnualChildren annualChildren = new AnnualChildren();
        annualChildren.setId(children.getId());
        annualChildren.setLastName(children.getLastName());
        annualChildren.setFirstName(children.getFirstName());
        annualChildren.setAge(children.getAge());
        annualChildren.setCity(children.getCity());
        annualChildren.setGiftsPreferences(children.getGiftsPreferences());
        annualChildren.setAverageScore(averageScore);
        annualChildren.setNiceScoreHistory(niceScoreHistory);
        annualChildren.setReceivedGifts(Collections.emptyList());
        return annualChildren;
    }

    /**
     * Method used to calculate the sum of the average nice scores from all
     * the children in the list that are not young adults.
     * @param annualList the list of children
     * @return sum
     */
    private double getAverageSum(final List<AnnualChildren> annualList) {
        Double sum = 0.0;
        // Traverse the annual children' list
        for (AnnualChildren annualChild : annualList) {
            if (annualChild.getAge() <= 18) {
                sum += annualChild.getAverageScore();
            }
        }
        return sum;
    }

    /**
     * Method used to update information about children.
     */
    private void rewriteAnnualChildrenList() {
        // Create a copy list of annual children
        List<AnnualChildren> annualChildrenListCopy = new ArrayList<>(annualChildrenList);
        // Traverse the copy list
        for (AnnualChildren annualChildCopy : annualChildrenListCopy) {
            // Create a new child, remove the copy child and add the newly
            // created child in the original list
            AnnualChildren annualChild = new AnnualChildren(annualChildCopy);
            annualChildrenList.remove(annualChildCopy);
            annualChildrenList.add(annualChild);
        }
    }

    /**
     * Method used to update the age of a child.
     */
    private void incrementAge() {
        // Create a copy list of annual children
        List<AnnualChildren> annualChildrenListCopy = new ArrayList<>(annualChildrenList);
        // Traverse the copy list
        for (AnnualChildren annualChildCopy : annualChildrenListCopy) {
            // The child becomes young adult
            if (AgeCalculator.getAgeCategory(annualChildCopy.getAge()).
                    equals(AgeCategory.YOUNG_ADULT)) {
                // Eliminate the child from the original list
                annualChildrenList.remove(annualChildCopy);
            } else {
                // Keep the child in the original list, but increment age
                // Search the position to find annualChildCopy in the original list
                int index = annualChildrenList.indexOf(annualChildCopy);
                // Obtain the child from the original list
                AnnualChildren annualChild = annualChildrenList.get(index);
                // Update the age for the child
                annualChild.setAge(annualChild.getAge() + 1);
            }
        }
    }

    /**
     * Method used to update information about existing children.
     * @param annualChanges the changes that occur every year
     */
    private void updateChildren(final AnnualChanges annualChanges) {
        // Traverse the list of children updates
        for (ChildUpdate childUpdate : annualChanges.getChildrenUpdates()) {
            // check if the id of the child for whom
            // we have an update is still in Santa's list
            AnnualChildren annualChild = findAnnualChildrenById(childUpdate.getId());
            // Found the child
            if (annualChild != null) {
                // Add the new nice score in the history of nice scores
                // only if the new nice score is valid
                if (childUpdate.getNiceScore() != null) {
                    annualChild.getNiceScoreHistory().
                            add(childUpdate.getNiceScore());
                }

                // Eliminate the duplicated gift preferences
                Set<Category> gifts = new LinkedHashSet<>(childUpdate.
                        getGiftsPreferences());
                childUpdate.getGiftsPreferences().clear();
                childUpdate.setGiftsPreferences(gifts.stream().toList());

                // Traverse the list of child's new preferences
                for (Category category : childUpdate.getGiftsPreferences()) {
                    // If existing categories are added to the list of
                    // preferences in an update, the old occurrence of that
                    // category will be deleted
                    annualChild.getGiftsPreferences().remove(category);
                    // Add the new preferences in the existing list of preferences
                    annualChild.getGiftsPreferences().add(childUpdate.
                            getGiftsPreferences().indexOf(category), category);
                }
            }
        }
    }

    /**
     * Method used to find the annual child by id in the Santa's list.
     * @param id the id of the searched child
     * @return the child if was found, or null contrary
     */
    private AnnualChildren findAnnualChildrenById(final int id) {
        return annualChildrenList.stream().
                filter(annualChildren -> annualChildren.getId() == id)
                .findAny().orElse(null);
    }
}
