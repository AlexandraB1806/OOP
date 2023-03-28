package actions;

import common.Constants;
import database.Database;
import entertainment.Movie;
import entertainment.Show;
import fileio.ActionInputData;
import org.json.JSONObject;
import user.User;

public final class Commands {
    private Commands() { }

    /**
     * Method used to add videos: movies/shows in the user's favorite list.
     * @param action from input
     * @return a JSON object that will be retained in
     * a result array of JSON objects
     */
    public static JSONObject favorite(final ActionInputData action) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", action.getActionId());

        Database dtb = Database.getDatabase();

        // Traverse the users' list
        for (User user : dtb.getUsers()) {
            // Found user who gave the command "favorite"
            if (user.getUsername().equals(action.getUsername())) {
                // Check if the movie is already in the favorite list
                if (user.getFavoriteVideo().contains(action.getTitle())) {
                    // If so, put a message in JSON object
                    jsonObject.put(Constants.MESSAGE, "error -> " + action.getTitle()
                    + " is already in favourite list");

                    // Traverse the movies' list
                    for (Movie movie : dtb.getMovies()) {
                        if (movie.getTitle().equals(action.getTitle())) {
                            // Increment the number of users who marked the seen film as favorite
                            movie.incTotalFavorites();
                        }
                    }

                    // Traverse the series' list
                    for (Show show : dtb.getSeries()) {
                        if (show.getTitle().equals(action.getTitle())) {
                            // Increment the number of users who marked the seen show as favorite
                            show.incTotalFavorites();
                        }
                    }
                } else {
                    // If not, check if the movie was seen in history list
                    if (user.getHistory().containsKey(action.getTitle())) {
                        // Add in the user's favorite movies list
                        user.getFavoriteVideo().add(action.getTitle());
                        // Put a message in JSON object
                        jsonObject.put(Constants.MESSAGE, "success -> " + action.getTitle()
                                + " was added as favourite");
                    } else {
                        jsonObject.put(Constants.MESSAGE, "error -> " + action.getTitle()
                                + " is not seen");
                    }
                }
            }
        }
       return jsonObject;
    }

    /**
     * Method used to mark videos: movies/shows
     * as seen. It updates the number of views for videos.
     * @param action from input
     * @return a JSON object that will be retained in
     * a result array of JSON objects
     */
    public static JSONObject view(final ActionInputData action) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", action.getActionId());

        Database dtb = Database.getDatabase();

        // Traverse the users' list
        for (User user : dtb.getUsers()) {
            // Found user who gave the command "view"
            if (user.getUsername().equals(action.getUsername())) {
                // If the video was not seen, mark it as seen with 1 view,
                // but if the video was seen, update the number of views.
                // Put the seen video in history
                if (!user.getHistory().containsKey(action.getTitle())) {
                    user.getHistory().put(action.getTitle(), 1);
                } else {
                    user.getHistory().replace(action.getTitle(),
                            user.getHistory().get(action.getTitle()),
                            user.getHistory().get(action.getTitle()) + 1);
                }
                // Put a message in JSON object
                jsonObject.put(Constants.MESSAGE, "success -> "
                        + action.getTitle()
                        + " was viewed with total views of "
                        + user.getHistory().get(action.getTitle()));
            }
        }

        boolean ok = false;
        // Traverse the movies' list
        for (Movie movie : dtb.getMovies()) {
            if (movie.getTitle().equals(action.getTitle())) {
                // Increment the number of users who marked the film as viewed
                movie.incTotalViews();
                ok = true;
                break;
            }
        }

        if (!ok) {
            // Traverse the series' list
            for (Show show : dtb.getSeries()) {
                if (show.getTitle().equals(action.getTitle())) {
                    // Increment the number of users who marked the show as viewed
                    show.incTotalViews();
                    ok = true;
                    break;
                }
            }
        }
        return jsonObject;
    }

    /**
     * Method used to grade videos: movies/shows.
     * @param action from input
     * @return a JSON object that will be retained in
     * a result array of JSON objects
     */
    public static JSONObject rating(final ActionInputData action) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", action.getActionId());

        Database dtb = Database.getDatabase();

        // Check if the video is a movie
        // A movie does not have seasons
        if (action.getSeasonNumber() == 0) {
            // Traverse the users' list
            for (User user : dtb.getUsers()) {
                // Found user who gave the command "rating"
                if (user.getUsername().equals(action.getUsername())) {
                    // The movie was not seen
                    if (!user.getHistory().containsKey(action.getTitle())) {
                        // Put the message in the JSON object
                        jsonObject.put(Constants.MESSAGE, "error -> " + action.getTitle()
                                + " is not seen");
                    } else {
                        // Traverse the movies' list
                        for (Movie movie: dtb.getMovies()) {
                            // Movie to be graded was found
                            if (movie.getTitle().equals(action.getTitle())) {
                                // Movie was not graded
                                if (!movie.hasUserGraded(user)) {
                                    // Add grade in the list of movies' grades
                                    movie.addGrade(action.getGrade());
                                    // Add user in the list of users who graded the movie
                                    movie.addUserGraded(user);
                                    // Put a message in the JSON object
                                    jsonObject.put(Constants.MESSAGE, "success -> "
                                            + action.getTitle()
                                            + " was rated with " + action.getGrade() + " by "
                                            + action.getUsername());
                                } else {
                                    jsonObject.put(Constants.MESSAGE, "error -> "
                                            + action.getTitle()
                                            + " has been already rated");
                                }
                            }
                        }
                    }
                }
            }
        } else {
            // The number of seasons is not 0, so the video is a show
            // Traverse the users' list
            for (User user : dtb.getUsers()) {
                // Found user who gave the command "rating"
                if (user.getUsername().equals(action.getUsername())) {
                    // The show was not seen
                    if (!user.getHistory().containsKey(action.getTitle())) {
                        // Put the message in the JSON object
                        jsonObject.put(Constants.MESSAGE, "error -> " + action.getTitle()
                                + " is not seen");
                    } else {
                        // Traverse the show's list
                        for (Show show : dtb.getSeries()) {
                            // Show to be graded was found
                            if (show.getTitle().equals(action.getTitle())) {
                                // Season from show was not graded
                                if (!show.hasUserGraded(user, action.getSeasonNumber())) {
                                    // Add grade in the list of seasons' grades
                                    show.addGrade(action.getGrade(), action.getSeasonNumber());
                                    // Add user in the list of users who graded the season
                                    show.addUserGraded(user, action.getSeasonNumber());
                                    // Put a message in the JSON object
                                    jsonObject.put(Constants.MESSAGE, "success -> "
                                            + action.getTitle()
                                            + " was rated with " + action.getGrade()
                                            + " by " + action.getUsername());
                                } else {
                                    jsonObject.put(Constants.MESSAGE, "error -> "
                                            + action.getTitle()
                                            + " has been already rated");
                                }
                            }
                        }
                    }
                }
            }
        }
        return jsonObject;
    }
}
