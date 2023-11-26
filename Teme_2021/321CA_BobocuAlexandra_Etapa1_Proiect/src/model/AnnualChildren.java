package model;

import entities.SantaGifts;
import enums.Category;
import enums.Cities;

import java.util.ArrayList;
import java.util.List;

public class AnnualChildren {
    private int id;
    private String lastName;
    private String firstName;
    private Cities city;
    private int age;
    private List<Category> giftsPreferences;
    private Double averageScore;
    private List<Double> niceScoreHistory;
    private Double assignedBudget;
    private List<SantaGifts> receivedGifts;

    public AnnualChildren() {

    }

    public AnnualChildren(final int id, final String lastName,
                          final String firstName, final Cities city,
                          final int age, final List<Category> giftsPreferences,
                          final Double averageScore,
                          final List<Double> niceScoreHistory,
                          final Double assignedBudget,
                          final List<SantaGifts> receivedGifts) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.age = age;
        this.giftsPreferences = giftsPreferences;
        this.averageScore = averageScore;
        this.niceScoreHistory = niceScoreHistory;
        this.assignedBudget = assignedBudget;
        this.receivedGifts = receivedGifts;
    }

    public AnnualChildren(final AnnualChildren annualChildren) {
        this.id = annualChildren.getId();
        this.lastName = annualChildren.getLastName();
        this.firstName = annualChildren.getFirstName();
        this.age = annualChildren.getAge();
        this.city = annualChildren.getCity();
        this.giftsPreferences = new ArrayList<>(annualChildren.getGiftsPreferences());
        this.averageScore = annualChildren.getAverageScore();
        this.niceScoreHistory = new ArrayList<>(annualChildren.getNiceScoreHistory());
        this.assignedBudget = annualChildren.getAssignedBudget();
        this.receivedGifts = new ArrayList<>(annualChildren.getReceivedGifts());
    }

    /**
     * Method used to obtain the id of a child.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Method used to set the id of a child.
     * @param id given id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Method used to obtain the last name of a child.
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Method used to set the last name of a child.
     * @param lastName given last name
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * Method used to obtain the first name of a child.
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method used to set the first name of a child.
     * @param firstName given first name
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * Method used to obtain the city of a child.
     * @return city
     */
    public Cities getCity() {
        return city;
    }

    /**
     * Method used to set the city of a child.
     * @param city given city
     */
    public void setCity(final Cities city) {
        this.city = city;
    }

    /**
     * Method used to obtain the age of a child.
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Method used to set the age of a child.
     * @param age given age
     */
    public void setAge(final int age) {
        this.age = age;
    }

    /**
     * Method used to obtain the child's preferences for a gift.
     * @return preferences for a gift
     */
    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    /**
     * Method used to set the child's preferences for a gift.
     * @param giftsPreferences given preferences for a gift
     */
    public void setGiftsPreferences(final List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    /**
     * Method used to obtain the child's average score.
     * @return average score
     */
    public Double getAverageScore() {
        return averageScore;
    }

    /**
     * Method used to set the child's average score.
     * @param averageScore given average score
     */
    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    /**
     * Method used to obtain the nice scores during years.
     * @return the history of nice scores
     */
    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    /**
     * Method used to set the nice scores during years.
     * @param niceScoreHistory the given history of nice scores
     */
    public void setNiceScoreHistory(final List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    /**
     * Method used to obtain the assigned budget for a certain year.
     * @return the assigned budget
     */
    public Double getAssignedBudget() {
        return assignedBudget;
    }

    /**
     * Method used to set the assigned budget for a certain year.
     * @param assignedBudget given assigned budget
     */
    public void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    /**
     * Method used to obtain the gifts received by a child.
     * @return the gifts that  a child has received
     */
    public List<SantaGifts> getReceivedGifts() {
        return receivedGifts;
    }

    /**
     * Method used to set the gifts received by a child.
     * @param receivedGifts the given gifts that a child has received
     */
    public void setReceivedGifts(final List<SantaGifts> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }
}
