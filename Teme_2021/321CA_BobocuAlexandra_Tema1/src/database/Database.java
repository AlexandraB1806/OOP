package database;

import actor.Actor;
import entertainment.Movie;
import entertainment.Show;
import user.User;
import java.util.ArrayList;
import java.util.List;

public final class Database {
    private final List<Movie> movies = new ArrayList<>();
    private final List<Show> series = new ArrayList<>();
    private final List<Actor> actors = new ArrayList<>();
    private final List<User> users = new ArrayList<>();

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Show> getSeries() {
        return series;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public List<User> getUsers() {
        return users;
    }

    private static Database instance = null;
    private Database() { }

    /**
     * Singleton lazy instantiation.
     * @return instance of Database
     */
    public static Database getDatabase() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Make a copy from MovieInputData
     * in my Database.
     * @param listMovies that will be added
     */
    public void addMovies(final List<Movie> listMovies) {
        this.movies.addAll(listMovies);
    }

    /**
     * Make a copy from SerialInputData
     * in my Database.
     * @param listSeries that will be added
     */
    public void addShows(final List<Show> listSeries) {
        this.series.addAll(listSeries);
    }

    /**
     * Make a copy from ActorInputData
     * in my Database.
     * @param listActors that will be added
     */
    public void addActors(final List<Actor> listActors) {
        this.actors.addAll(listActors);
    }

    /**
     * Make a copy from UserInputData
     * in my Database.
     * @param listUsers that will be added
     */
    public void addUsers(final List<User> listUsers) {
        this.users.addAll(listUsers);
    }

    /**
     * Clear the lists of movies, series, actors and users from
     * the Database.
     */
    public void clearDatabase() {
        this.movies.clear();
        this.series.clear();
        this.actors.clear();
        this.users.clear();
    }
}
