package entertainment;

import user.User;

import java.util.ArrayList;
import java.util.List;

public class Movie extends Video {
    private final int duration;
    // List of grades given for a movie
    private final List<Double> grades;
    // List of names of the users who graded a movie
    private final ArrayList<String> userGraded;

    public Movie(final String title, final int releasedYear,
                 final ArrayList<String> genres,
                 final ArrayList<String> actors, final int duration) {
        super(title, releasedYear, genres, actors);
        this.duration = duration;
        grades = new ArrayList<>();
        userGraded = new ArrayList<>();
    }

    /**
     * Method used to obtain the duration of a movie.
     * @return duration
     */
    @Override
    public int getDuration() {
        return duration;
    }

    /**
     * Method used to obtain the list of the grades given to a movie.
     * @return the list of grades
     */
    public List<Double> getGrades() {
        return grades;
    }

    /**
     * Method used to populate the list of the grades
     * given to a film.
     * @param newGrade the grade that needs to be added in the list
     */
    public void addGrade(final double newGrade) {
        grades.add(newGrade);
    }

    /**
     * Method used to populate the list of the users who graded
     * a movie. The list retains the usernames.
     * @param user who graded the movie
     */
    public void addUserGraded(final User user) {
        userGraded.add(user.getUsername());
    }

    /**
     * Check if the name of the user exists
     * in the list of usernames who graded a movie.
     * @param user that is searched
     * @return true if the user graded a movie, or false if not
     */
    public boolean hasUserGraded(final User user) {
        // None of the users has graded the movie, so
        // the list of names is empty
        if (userGraded.isEmpty()) {
            return false;
        }

        // Traverse the list of names of the users who graded the movie
        for (String usernameGraded : userGraded) {
            // Found user who graded the movie
            if (usernameGraded.equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }
}
