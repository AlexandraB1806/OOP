package database;

import actor.Actor;
import entertainment.Movie;
import entertainment.Show;
import fileio.ActorInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import user.User;

import java.util.Arrays;
import java.util.List;

public final class Helpers {
    private Helpers() { }
    /**
     * Method used to add the information about movies
     * in my Database
     * @param moviesData movies from input that will be added
     */
    public static void addMovies(final List<MovieInputData> moviesData) {
        for (int i = 0; i < moviesData.size(); i++) {
            Database.getDatabase().addMovies(Arrays.asList(
                    new Movie(moviesData.get(i).getTitle(),
                            moviesData.get(i).getYear(),
                            moviesData.get(i).getGenres(),
                            moviesData.get(i).getCast(),
                            moviesData.get(i).getDuration())
            ));
        }
    }

    /**
     * Method used to add the information about shows
     * in my Database
     * @param seriesData shows from input that will be added
     */
    public static void addShows(final List<SerialInputData> seriesData) {
        for (int i = 0; i < seriesData.size(); i++) {
            Database.getDatabase().addShows(Arrays.asList(
                    new Show(seriesData.get(i).getTitle(),
                            seriesData.get(i).getYear(),
                            seriesData.get(i).getGenres(),
                            seriesData.get(i).getCast(),
                            seriesData.get(i).getNumberSeason(),
                            seriesData.get(i).getSeasons())
            ));
        }
    }

    /**
     * Method used to add information about actors
     * in my Database
     * @param actorsData the actors from input that will be added
     */
    public static void addActors(final List<ActorInputData> actorsData) {
        for (int i = 0; i < actorsData.size(); i++) {
            Database.getDatabase().addActors(Arrays.asList(
                    new Actor(actorsData.get(i).getName(),
                            actorsData.get(i).getCareerDescription(),
                            actorsData.get(i).getFilmography(),
                            actorsData.get(i).getAwards())
            ));
        }
    }

    /**
     * Method used to add information about users
     * in my Database
     * @param usersData the users from input that will be added
     */
    public static void addUsers(final List<UserInputData> usersData) {
        for (int i = 0; i < usersData.size(); i++) {
            Database.getDatabase().addUsers(Arrays.asList(
                    new User(usersData.get(i).getUsername(),
                            usersData.get(i).getHistory(),
                            usersData.get(i).getSubscriptionType(),
                            usersData.get(i).getFavoriteMovies())
            ));
        }
    }

    /**
     * Method used to reset the Database.
     */
    public static void clear() {
        Database.getDatabase().clearDatabase();
    }
}
