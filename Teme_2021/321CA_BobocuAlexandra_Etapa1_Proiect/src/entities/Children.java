package entities;

import enums.Category;
import enums.Cities;

import java.util.List;

public class Children {
    private int id;
    private String lastName;
    private String firstName;
    private int age;
    private Cities city;
    private Double niceScore;
    private List<Category> giftsPreferences;

    public Children() {

    }

    public Children(final int id, final String lastName,
                    final String firstName, final int age,
                    final Cities city, final Double niceScore,
                    final List<Category> giftsPreferences) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
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
     * @param id that needs to be given to a child
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
     * @param lastName that needs to be given to a child
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
     * @param firstName that needs to be given to a child
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
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
     * @param age that needs to be given to a child
     */
    public void setAge(final int age) {
        this.age = age;
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
     * @param city that needs to be associated to a child
     */
    public void setCity(final Cities city) {
        this.city = city;
    }

    /**
     * Method used to obtain the nice score of a child.
     * @return nice score
     */
    public Double getNiceScore() {
        return niceScore;
    }

    /**
     * Method used to set the nice score of a child.
     * @param niceScore that needs to be associated to a child
     */
    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    /**
     * Method used to obtain the child's preferences for a gift.
     * @return child's gift preferences
     */
    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    /**
     * Method used to set the child's preferences for a gift.
     * @param giftsPreferences child's gift preferences
     */
    public void setGiftsPreferences(final List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }
}
