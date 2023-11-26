package entertainment;

import user.User;

import java.util.ArrayList;

public class Show extends Video {
    private final int numberOfSeason;
    private final ArrayList<Season> seasons;
    // List of lists of usernames who graded a show.
    // On the first position, there are the usernames that rated
    // the first season from the show
    // ...
    // On the last position, there are the usernames that rated
    // the last season from the show
    private final ArrayList<ArrayList<String>> userGraded;

    public Show(final String title, final int releasedYear,
                final ArrayList<String> genres, final ArrayList<String> actors,
                final int numberOfSeason, final ArrayList<Season> seasons) {
        super(title, releasedYear, genres, actors);
        this.numberOfSeason = numberOfSeason;
        this.seasons = seasons;
        userGraded = new ArrayList<>(numberOfSeason);
        for (int i = 0; i < numberOfSeason; i++) {
            userGraded.add(new ArrayList<>());
        }
    }

    /**
     * Method used to get total duration of a show,
     * calculating a sum of durations for each season.
     * @return the total duration
     */
    @Override
    public int getDuration() {
        int totalDuration = 0;
        // Traverse the seasons' list
        for (Season season : seasons) {
            totalDuration += season.getDuration();
        }
        return totalDuration;
    }

    /**
     * Method used to populate the list of the grades
     * given to a season from a show.
     * @param newGrade the grade that needs to be added in the list
     * @param numOfSeason the season which is graded
     */
    public void addGrade(final double newGrade, final int numOfSeason) {
        seasons.get(numOfSeason - 1).getRatings().add(newGrade);
    }

    /**
     * Method used to populate the list of the users who graded
     * a season from a show. The list retains the usernames.
     * @param user who graded the season from a show
     * @param numOfSeason the season which was graded
     */
    public void addUserGraded(final User user, final int numOfSeason) {
        userGraded.get(numOfSeason - 1).add(user.getUsername());
    }

    /**
     * Check if the name of the user exists
     * in the list of usernames who graded a season.
     * @param user that is searched
     * @param numOfSeason the season which was graded
     * @return true if the user graded a season, or false if not
     */
    public boolean hasUserGraded(final User user, final int numOfSeason) {
        // None of the users has graded the movie, so
        // the list of names is empty
        if (userGraded.get(numOfSeason - 1).isEmpty()) {
            return false;
        }

        // Traverse the list of names of the users who graded the season
        for (String usernameGraded : userGraded.get(numOfSeason - 1)) {
            // Found user who graded the season
            if (usernameGraded.equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method used to calculate how many times a user has graded a show.
     * @param user that evaluated the show
     * @return the number of ratings
     */
    public int userNumOfRatings(final User user) {
        int counter = 0;

        for (int i = 1; i <= getNumberOfSeason(); i++) {
            if (hasUserGraded(user, i)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Method used to obtain how many seasons a show has.
     * @return number of seasons
     */
    public int getNumberOfSeason() {
        return numberOfSeason;
    }

    /**
     * Method used to obtain the list of the seasons
     * of a show.
     * @return seasons of the show
     */
    public ArrayList<Season> getSeasons() {
        return seasons;
    }
}
