package actor;

import java.util.ArrayList;
import java.util.Map;

public class Actor {
    private final String name;
    private final String careerDescription;
    private final ArrayList<String> filmography;
    private Map<ActorsAwards, Integer> awards;
    private double rating;
    private int numAwards;

    public Actor(final String name,
                 final String careerDescription,
                 final ArrayList<String> filmography,
                 final Map<ActorsAwards, Integer> awards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.filmography = filmography;
        this.awards = awards;
        this.rating = 0.0;
    }

    /**
     * Method used to obtain the name of the actor.
     * @return name of the actor
     */
    public String getName() {
        return name;
    }

    /**
     * Method used to obtain the rating given to an actor.
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Method used to set the rating for an actor.
     * @param rating given to the actor
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Method used to obtain the actor's career description.
     * @return actor's career description
     */
    public String getCareerDescription() {
        return careerDescription;
    }

    /**
     * Method used to obtain the list of videos
     * where the actor performed.
     * @return list of movies
     */
    public ArrayList<String> getFilmography() {
        return filmography;
    }

    /**
     * Method used to obtain the number of awards won by an actor.
     * @return the number of awards
     */
    public int getNumAwards() {
        return numAwards;
    }

    /**
     * Method used to set the number of awards won by an actor.
     * @param numAwards the number of awards
     */
    public void setNumAwards(int numAwards) {
        this.numAwards = numAwards;
    }

    /**
     * Method used to get the awards obtained by actor.
     * @return awards of actor
     */
    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    /**
     * Method used to set the awards for an actor.
     * @param awards given awards for actor
     */
    public void setAwards(final Map<ActorsAwards, Integer> awards) {
        this.awards = awards;
    }
}
