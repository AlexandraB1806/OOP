package model;

import enums.Category;

import java.util.List;

public class ChildUpdate {
    private int id;
    private Double niceScore;
    private List<Category> giftsPreferences;

    /**
     * Method used to obtain the id of a child where information
     * is modified.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Method used to set the id of a child where information
     * is modified.
     * @param id given id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Method used to obtain the nice score of a child.
     * @return the nice score
     */
    public Double getNiceScore() {
        return niceScore;
    }

    /**
     * Method used to set a new nice score for a child.
     * @param niceScore given nice score
     */
    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    /**
     * Method used to obtain the child's gift preferences.
     * @return preferences for a gift
     */
    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    /**
     * Method used to set the child's gift preferences.
     * @param giftsPreferences given preferences for a gift
     */
    public void setGiftsPreferences(final List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }
}
