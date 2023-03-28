package actions;

import common.Constants;
import database.Database;
import entertainment.Movie;
import entertainment.Show;
import entertainment.Video;
import fileio.ActionInputData;
import org.json.JSONObject;
import user.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import static grades.MovieGrade.gradeForMovie;
import static grades.ShowGrade.gradeForShow;

public final class Recommendations {
    private Recommendations() { }

    /**
     * Method used to obtain the first video unseen by a user.
     * To do that, we search in the movies' and series' lists
     * and check if these videos appear in the users' history.
     * If not, we found the unseen videos.
     * @param action from input
     * @return a JSON object that will be retained in
     * a result array of JSON objects
     */
    public static JSONObject standard(final ActionInputData action) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", action.getActionId());

        Database dtb = Database.getDatabase();

        // Traverse the users' list
         for (User user : dtb.getUsers()) {
            String username = user.getUsername();
            if (username.equals(action.getUsername())) {
                // For the found user, traverse the movies' list
                for (Movie movie : dtb.getMovies()) {
                    // The movie was not seen because
                    // it does not appear in history
                    if (!user.getHistory().containsKey(movie.getTitle())) {
                        jsonObject.put(Constants.MESSAGE,
                                "StandardRecommendation result: "
                                + movie.getTitle());
                        return jsonObject;
                    }
                }
            }
        }

        // Traverse the users' list
        for (User user : dtb.getUsers()) {
            String username = user.getUsername();
            // Found the user
            if (username.equals(action.getUsername())) {
                // For the found user, traverse the movies' list
                for (Show show : dtb.getSeries()) {
                    // The show was not seen because
                    // it does not appear in history
                    if (!user.getHistory().containsKey(show.getTitle())) {
                        jsonObject.put(Constants.MESSAGE,
                                "StandardRecommendation result: "
                                + show.getTitle());
                        return jsonObject;
                    }
                }
            }
        }
        jsonObject.put(Constants.MESSAGE,
                "StandardRecommendation cannot be applied!");
        return jsonObject;
    }

    /**
     * Method used to obtain the first video unseen by a user,
     * which also has the best rating.
     * Firstly, sort the movies' and series' lists descendant
     * by received rating. Secondly, search in the sorted movies' and series'
     * lists and check if these videos appear in the users' history.
     * If not, we found the best unseen videos.
     * @param action from input
     * @return a JSON object that will be retained in
     * a result array of JSON objects
     */
    public static JSONObject bestUnseen(final ActionInputData action) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", action.getActionId());

        Database dtb = Database.getDatabase();

        List<Video> videos = new ArrayList<>();
        // Add all the movies and shows in the new video list
        videos.addAll(dtb.getMovies());
        videos.addAll(dtb.getSeries());

        // Calculate the grades for movies and shows from Database
        gradeForMovie(dtb.getMovies());
        gradeForShow(dtb.getSeries());

        var ratingVideoSort = Comparator.comparing(Video::getAverageGrade);
        var streamSortVideo = videos.stream().sorted(ratingVideoSort.reversed());

        // Retain the sorted movies and shows in new lists
        List<Video> cpyVideos = streamSortVideo.collect(Collectors.toList());

        // Traverse the users' list
        for (User user : dtb.getUsers()) {
            String videoUsername = user.getUsername();
            String actionUser = action.getUsername();
            // Found the user for movies
            if (videoUsername.equals(actionUser)) {
                var getHistoryUser = user.getHistory();
                // Traverse the sorted movies' list
                for (Video video : cpyVideos) {
                    String videoName = video.getTitle();
                    // Movie not found in the users' history
                    if (!getHistoryUser.containsKey(videoName)) {
                        jsonObject.put(Constants.MESSAGE,
                                "BestRatedUnseenRecommendation result: "
                                + videoName);
                        return jsonObject;
                    }
                }
            }
        }
        jsonObject.put(Constants.MESSAGE,
                "BestRatedUnseenRecommendation cannot be applied!");
        return jsonObject;
    }

    /**
     * Method used to obtain the first unseen video that has the most popular
     * genre. Create a map that contains the genre as key and total views as
     * value.
     * @param action from input
     * @return a JSON object that will be retained in
     * a result array of JSON objects
     */
    public static JSONObject popular(final ActionInputData action) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", action.getActionId());

        Database dtb = Database.getDatabase();

        List<Video> videos = new ArrayList<>();
        // Add all the movies and shows in the new video list
        videos.addAll(dtb.getMovies());
        videos.addAll(dtb.getSeries());

        Map<String, Integer> popGenre = new HashMap<>();

        // Traverse the videos' list and populate the map
        for (Video video : videos) {
            ArrayList<String> getVideoGenre = video.getGenres();
            int getVideoViews = video.getTotalViews();
            for (String genre : getVideoGenre) {
                if (popGenre.containsKey(genre)) {
                    popGenre.put(genre, popGenre.get(genre) + getVideoViews);
                } else {
                    popGenre.put(genre, getVideoViews);
                }
            }
        }

        List<Map.Entry<String, Integer>> popularGenreList =
                new LinkedList<>(popGenre.entrySet());

        // Sort the keys
        popularGenreList.sort((genre1, genre2) -> (genre2.getValue())
                .compareTo(genre1.getValue()));

        HashMap<String, Integer> result = new LinkedHashMap<>();
        // Populate the result map
        for (Map.Entry<String, Integer> genreIterator : popularGenreList) {
            result.put(genreIterator.getKey(), genreIterator.getValue());
        }

        // Traverse the users' list
        for (User user : dtb.getUsers()) {
            // Check the correct type of user
            if (user.getSubscriptionType().equals("PREMIUM")) {
                if (user.getUsername().equals(action.getUsername())) {
                    for (Map.Entry<String, Integer> entry : result.entrySet()) {
                        // Traverse the videos' list
                        for (Video video : videos) {
                            // Video not found in the premium users' history
                            if (!user.getHistory().containsKey(video.getTitle())) {
                                ArrayList<String> videoGenre = video.getGenres();
                                String key = entry.getKey();
                                if (videoGenre.contains(key)) {
                                    jsonObject.put(Constants.MESSAGE,
                                            "PopularRecommendation result: "
                                                    + video.getTitle());
                                    return jsonObject;
                                }
                            }
                        }
                    }
                }
            }
        }
        jsonObject.put(Constants.MESSAGE, "PopularRecommendation cannot be applied!");
        return jsonObject;
    }

    /**
     * Method used to obtain the first video that was the most added in the
     * list of favorite videos seen by users. To do that, it was created a new
     * video list where were added all the movies and series. Iterate through
     * the list of movies and series marked as favorite and update the number
     * of favorite videos. Sort the videos descendant by the updated number.
     * If there is a premium user, we are interested in finding the videos that
     * do not appear in the premium users' history.
     * @param action from input
     * @return a JSON object that will be retained in
     * a result array of JSON objects
     */
    public static JSONObject favorite(final ActionInputData action) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", action.getActionId());

        Database dtb = Database.getDatabase();

        List<Video> videos = new ArrayList<>();
        // Add all the movies and shows in the new video list
        videos.addAll(dtb.getMovies());
        videos.addAll(dtb.getSeries());

        for (Video video : videos) {
            video.resetTotalFavorites();
        }

        // Traverse the users' list from database
        for (User user : dtb.getUsers()) {
            // Access the list of favorites videos
            for (String favoriteName : user.getFavoriteVideo()) {
                // Traverse all the movies and shows
                for (Video video : videos) {
                    if (video.getTitle().equals(favoriteName)) {
                        // Update the number of favorites videos
                        video.incTotalFavorites();
                    }
                }
            }
        }

        // Sort the videos ascendant by the num of favorites
        var favoriteVideoSort = Comparator.comparing(Video::getTotalFavorites);
        var streamSortVideo = videos.stream().sorted(favoriteVideoSort.reversed());
        List<Video> cpyVideos = streamSortVideo.collect(Collectors.toList());

        // Traverse the users' list
        for (User user : dtb.getUsers()) {
            // Check the correct type of user
            if (user.getSubscriptionType().equals("PREMIUM")) {
                String videoUsername = user.getUsername();
                String actionUser = action.getUsername();
                // Found the user
                if (videoUsername.equals(actionUser)) {
                    // Traverse the sorted videos' list
                    for (Video video : cpyVideos) {
                        String videoTitle = video.getTitle();
                        var getHistoryUser = user.getHistory();
                        // Video not found in the users' history
                        if (!getHistoryUser.containsKey(videoTitle)
                                && video.getTotalFavorites() > 0) {
                            jsonObject.put(Constants.MESSAGE,
                                    "FavoriteRecommendation result: "
                                    + videoTitle);
                            return jsonObject;
                        }
                    }
                }
            }
        }
        jsonObject.put(Constants.MESSAGE,
                "FavoriteRecommendation cannot be applied!");
        return jsonObject;
    }

    /**
     * Method used to obtain a list of unseen videos that have a specific genre.
     * It was created a new video list where were added all the movies and
     * series and then removed the videos that do not respect the genre.
     * Sort the videos as requested. If there is a premium user, we are
     * interested in finding the videos that do not appear in the premium
     * users' history. Put the found videos in a new list named result.
     * @param action from input
     * @return a JSON object that will be retained in
     * a result array of JSON objects
     */
    public static JSONObject search(final ActionInputData action) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", action.getActionId());

        Database dtb = Database.getDatabase();

        // Calculate the grades for movies and shows from Database
        gradeForMovie(dtb.getMovies());
        gradeForShow(dtb.getSeries());

        List<Video> videos = new ArrayList<>();
        // Add all the movies and shows in the new video list
        videos.addAll(dtb.getMovies());
        videos.addAll(dtb.getSeries());

        // Eliminate the movies and series that do not have the genre
        // given in input
        videos.removeIf(video -> !video.getGenres().contains(action.getGenre()));

        // Firstly sort the videos alphabetical and then ascendant by rating
        var compareNameVideo = Comparator.comparing(Video::getTitle);
        var alphabeticalVideo = videos.stream().sorted(compareNameVideo)
                .collect(Collectors.toList());
        var compareGradeVideo = Comparator.comparingDouble(Video::getAverageGrade);
        List<Video> cpyVideos = alphabeticalVideo.stream().sorted(compareGradeVideo)
                .collect(Collectors.toList());

        ArrayList<String> result = new ArrayList<>();

        // Traverse the users' list
        for (User user : dtb.getUsers()) {
            // Check the correct type of user
            if (user.getSubscriptionType().equals("PREMIUM")) {
                String videoUsername = user.getUsername();
                String actionUser = action.getUsername();
                // Found the user
                if (videoUsername.equals(actionUser)) {
                    // Traverse the sorted videos' list
                    for (Video video : cpyVideos) {
                        String getVideo = video.getTitle();
                        // Video not found in the premium users' history
                        if (!user.getHistory().containsKey(getVideo)) {
                            result.add(getVideo);
                        }
                    }
                }
            }
        }
        if (result.size() <= 0) {
            jsonObject.put(Constants.MESSAGE,
                    "SearchRecommendation cannot be applied!");
        } else {
            jsonObject.put(Constants.MESSAGE,
                    "SearchRecommendation result: " + result);
        }

        return jsonObject;
    }
}
