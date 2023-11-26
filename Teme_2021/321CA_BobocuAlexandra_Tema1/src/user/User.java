package user;

import database.Database;
import entertainment.Movie;
import entertainment.Show;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User {
    private final String username;
    private final Map<String, Integer> history;
    private final String subscriptionType;
    private final ArrayList<String> favoriteVideo;

    public User(final String username, final Map<String, Integer> history,
                final String subscriptionType,
                final ArrayList<String> favoriteVideo) {
        this.username = username;
        this.history = history;
        this.subscriptionType = subscriptionType;
        this.favoriteVideo = favoriteVideo;
    }

    /**
     * Method used to obtain the name of a user.
     * @return user's name
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method used to obtain a list of seen videos.
     * It retains the name of the seen video and a number which indicates
     * how many times the video was seen.
     * @return name of the seen video and number of the views
     */
    public Map<String, Integer> getHistory() {
        return history;
    }

    /**
     * Method used to obtain the type of user.
     * @return type
     */
    public String getSubscriptionType() {
        return subscriptionType;
    }

    /**
     * Method used to obtain the list of user's favorite videos.
     * @return list of user's favorite videos
     */
    public ArrayList<String> getFavoriteVideo() {
        return favoriteVideo;
    }

    /**
     * Method used to obtain the number of ratings
     * given to movies and series by a user.
     * @return total number of ratings
     */
    public int getNumRatings() {
        Database dtb = Database.getDatabase();
        List<Movie> movies = dtb.getMovies();
        List<Show> shows = dtb.getSeries();

        int currNumOfRatings = 0;
        // Traverse the movies' list
        for (Movie movie : movies) {
            if (movie.hasUserGraded(this)) {
                currNumOfRatings++;
            }
        }
        // Traverse the shows' list
        for (Show show : shows) {
            currNumOfRatings += show.userNumOfRatings(this);
        }
        return currNumOfRatings;
    }
}
