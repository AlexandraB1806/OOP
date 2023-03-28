package actions;

import common.Constants;
import database.Database;
import entertainment.Video;
import fileio.ActionInputData;
import org.json.JSONObject;
import user.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static grades.MovieGrade.gradeForMovie;
import static grades.ShowGrade.gradeForShow;

public final class QueryVideo {
    private QueryVideo() { }

    /**
     * Method used to obtain the list of videos sorted by rating.
     * @param query from input
     * @return a JSON object that will be retained in
     * a result array of JSON objects
     */
    public static JSONObject rating(final ActionInputData query) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", query.getActionId());

        Database dtb = Database.getDatabase();

        List<Video> videos = new ArrayList<>();

        // Calculate the grades for movies and shows from Database
        gradeForMovie(dtb.getMovies());
        gradeForShow(dtb.getSeries());

        if (query.getObjectType().equals("movies")) {
            // Add all the movies in the new video list
            videos.addAll(dtb.getMovies());
        } else {
            // Add all the shows in the new video list
            videos.addAll(dtb.getSeries());
        }

        List<Video> sortedList = findAscDesRating(query, videos);

        ArrayList<String> result = new ArrayList<>();

        int numMaxVideos = 0;
        for (Video video : sortedList) {
            String videoTitle = video.getTitle();
            if (video.getAverageGrade() > 0) {
                // Obtain the released year
                String year = query.getFilters().get(0).get(0);
                // Decide if the filter should be applied or not
                boolean shouldFilter = false;
                // Applying filters:
                // Filter after the released year
                if (year != null && video.getReleasedYear() != Integer.parseInt(year)) {
                    shouldFilter = true;
                }
                // Obtain the genre
                String genre = query.getFilters().get(1).get(0);
                // Filter after the genre
                if (genre != null && !video.checkGenre(genre)) {
                    shouldFilter = true;
                }
                if (!shouldFilter && numMaxVideos < query.getNumber()) {
                    result.add(videoTitle);
                    numMaxVideos++;
                }
            }
        }
        jsonObject.put(Constants.MESSAGE, "Query result: " + result);
        return jsonObject;
    }

    /**
     * Method used to obtain the list of videos marked as favorite.
     * @param query from input
     * @return a JSON object that will be retained in
     * a result array of JSON objects
     */
    public static JSONObject favorite(final ActionInputData query) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", query.getActionId());

        Database dtb = Database.getDatabase();

        List<Video> videos = new ArrayList<>();

        // Add all the movies and shows in the new video list
        if (query.getObjectType().equals("movies")) {
            videos.addAll(dtb.getMovies());
        } else {
            videos.addAll(dtb.getSeries());
        }

        // For each video, initialize the number of favorites
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

        List<Video> sortedList = findAscDesFavorite(query, videos);

        ArrayList<String> result = new ArrayList<>();

        int numMaxVideos = 0;
        for (Video video : sortedList) {
            String videoTitle = video.getTitle();
            if (video.getTotalFavorites() > 0) {
                // Obtain the released year
                String year = query.getFilters().get(0).get(0);
                // Decide if the filter should be applied or not
                boolean shouldFilter = false;
                // Applying filters:
                // Filter after the released year
                if (year != null && video.getReleasedYear() != Integer.parseInt(year)) {
                    shouldFilter = true;
                }
                // Obtain the genre
                String genre = query.getFilters().get(1).get(0);
                // Filter after the genre
                if (genre != null && !video.checkGenre(genre)) {
                    shouldFilter = true;
                }
                if (!shouldFilter && numMaxVideos < query.getNumber()) {
                    result.add(videoTitle);
                    numMaxVideos++;
                }
            }
        }
        jsonObject.put(Constants.MESSAGE, "Query result: " + result);
        return jsonObject;
    }

    /**
     * Method used to obtain the list of videos which last longer.
     * @param query from input
     * @return a JSON object that will be retained in
     * a result array of JSON objects
     */
    public static JSONObject longest(final ActionInputData query) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", query.getActionId());

        Database dtb = Database.getDatabase();

        List<Video> videos = new ArrayList<>();

        if (query.getObjectType().equals("movies")) {
            // Add all the movies and shows in the new video list
            videos.addAll(dtb.getMovies());
        } else {
            videos.addAll(dtb.getSeries());
        }

        List<Video> sortedList = findAscDesLongest(query, videos);

        ArrayList<String> result = new ArrayList<>();

        int numMaxVideos = 0;
        for (Video video : sortedList) {
            String videoTitle = video.getTitle();
            // Obtain the released year
            String year = query.getFilters().get(0).get(0);
            // Decide if the filter should be applied or not
            boolean shouldFilter = false;
            // Applying filters:
            // Filter after the released year
            if (year != null && video.getReleasedYear() != Integer.parseInt(year)) {
                shouldFilter = true;
            }
            // Obtain the genre
            String genre = query.getFilters().get(1).get(0);
            // Filter after the genre
            if (genre != null && !video.checkGenre(genre)) {
                shouldFilter = true;
            }
            if (!shouldFilter && numMaxVideos < query.getNumber()) {
                result.add(videoTitle);
                numMaxVideos++;
            }
        }
        jsonObject.put(Constants.MESSAGE, "Query result: " + result);
        return jsonObject;
    }

    /**
     * Method used to obtain the list of videos sorted by the number of views.
     * @param query from input
     * @return a JSON object that will be retained in
     * a result array of JSON objects
     */
    public static JSONObject mostViewed(final ActionInputData query) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", query.getActionId());

        Database dtb = Database.getDatabase();

        List<Video> videos = new ArrayList<>();

        if (query.getObjectType().equals("movies")) {
            // Add all the movies and shows in the new video list
            videos.addAll(dtb.getMovies());
        } else {
            videos.addAll(dtb.getSeries());
        }

        List<Video> sortedList = findAscDesMostViewed(query, videos);

        ArrayList<String> result = new ArrayList<>();

        int numMaxVideos = 0;
        for (Video video : sortedList) {
            String videoTitle = video.getTitle();
            if (video.getTotalViews() > 0) {
                // Obtain the released year
                String year = query.getFilters().get(0).get(0);
                // Decide if the filter should be applied or not
                boolean shouldFilter = false;
                // Applying filters:
                // Filter after the released year
                if (year != null && video.getReleasedYear() != Integer.parseInt(year)) {
                    shouldFilter = true;
                }
                // Obtain the genre
                String genre = query.getFilters().get(1).get(0);
                // Filter after the genre
                if (genre != null && !video.checkGenre(genre)) {
                    shouldFilter = true;
                }
                if (!shouldFilter && numMaxVideos < query.getNumber()) {
                    result.add(videoTitle);
                    numMaxVideos++;
                }
            }
        }
        jsonObject.put(Constants.MESSAGE, "Query result: " + result);
        return jsonObject;
    }

    /**
     * Method used to sort the videos ascending/ descending, firstly by the
     * title, then by the number of total favorite videos.
     * @param query from input
     * @param videos list of videos that need to be sorted
     * @return the sorted list of videos
     */
    public static List<Video> findAscDesFavorite(final ActionInputData query,
                                                 final List<Video> videos) {
        List<Video> sortedList;

        if (query.getSortType().equals("asc")) {
            // Firstly, sort the list of videos ascendant by titles
            var storeStream = videos.stream();
            var compareVideo = Comparator.comparing(Video::getTitle);
            var alphabeticalList = storeStream.sorted(compareVideo).collect(Collectors.toList());
            // Secondly, sort the list of videos ascendant by the number of favorites
            var compareRating = Comparator.comparingDouble(Video::getTotalFavorites);
            sortedList = alphabeticalList.stream().sorted(compareRating)
                    .collect(Collectors.toList());
        } else {
            // Firstly, sort the list of videos descendant by titles
            var storeStream = videos.stream();
            var compareVideo = Comparator.comparing(Video::getTitle).reversed();
            var alphabeticalList = storeStream.sorted(compareVideo).collect(Collectors.toList());
            // Secondly, sort the list of videos descendant by the number of favorites
            var compareRating = Comparator.comparingDouble(Video::getTotalFavorites)
                    .reversed();
            sortedList = alphabeticalList.stream().sorted(compareRating)
                    .collect(Collectors.toList());
        }
        return sortedList;
    }

    /**
     * Method used to sort the videos ascending/ descending, firstly by the
     * title, then by the duration.
     * @param query from input
     * @param videos list of videos that need to be sorted
     * @return the sorted list of videos
     */
    public static List<Video> findAscDesLongest(final ActionInputData query,
                                                final List<Video> videos) {
        List<Video> sortedList;

        if (query.getSortType().equals("asc")) {
            // Firstly, sort the list of videos ascendant by titles
            var storeStream = videos.stream();
            var compareVideo = Comparator.comparing(Video::getTitle);
            var alphabeticalList = storeStream.sorted(compareVideo).collect(Collectors.toList());
            // Secondly, sort the list of videos ascendant by the duration
            var compareRating = Comparator.comparingDouble(Video::getDuration);
            sortedList = alphabeticalList.stream().sorted(compareRating)
                    .collect(Collectors.toList());
        } else {
            // Firstly, sort the list of videos descendant by titles
            var storeStream = videos.stream();
            var compareVideo = Comparator.comparing(Video::getTitle).reversed();
            var alphabeticalList = storeStream.sorted(compareVideo).collect(Collectors.toList());
            // Secondly, sort the list of videos descendant by the duration
            var compareRating = Comparator.comparingDouble(Video::getDuration).reversed();
            sortedList = alphabeticalList.stream().sorted(compareRating)
                    .collect(Collectors.toList());
        }
        return sortedList;
    }

    /**
     * Method used to sort the videos ascending/ descending, firstly by the
     * title, then by the average grade.
     * @param query from input
     * @param videos list of videos that need to be sorted
     * @return the sorted list of videos
     */
    public static List<Video> findAscDesRating(final ActionInputData query,
                                               final List<Video> videos) {
        List<Video> sortedList;

        if (query.getSortType().equals("asc")) {
            // Firstly, sort the list of videos ascendant by titles
            var storeStream = videos.stream();
            var compareVideo = Comparator.comparing(Video::getTitle);
            var alphabeticalList = storeStream.sorted(compareVideo).collect(Collectors.toList());
            // Secondly, sort the list of videos ascendant by the average grade
            var compareRating = Comparator.comparingDouble(Video::getAverageGrade);
            sortedList = alphabeticalList.stream().sorted(compareRating)
                    .collect(Collectors.toList());
        } else {
            // Firstly, sort the list of videos descendant by titles
            var storeStream = videos.stream();
            var compareVideo = Comparator.comparing(Video::getTitle).reversed();
            var alphabeticalList = storeStream.sorted(compareVideo).collect(Collectors.toList());
            // Secondly, sort the list of videos descendant by the average grade
            var compareRating = Comparator.comparingDouble(Video::getAverageGrade)
                    .reversed();
            sortedList = alphabeticalList.stream().sorted(compareRating)
                    .collect(Collectors.toList());
        }
        return sortedList;
    }

    /**
     * Method used to sort the videos ascending/ descending, firstly by the
     * title, then by the number of total viewed videos.
     * @param query from input
     * @param videos list of videos that need to be sorted
     * @return the sorted list of videos
     */
    public static List<Video> findAscDesMostViewed(final ActionInputData query,
                                                   final List<Video> videos) {
        List<Video> sortedList;

        if (query.getSortType().equals("asc")) {
            // Firstly, sort the list of videos ascendant by titles
            var storeStream = videos.stream();
            var compareVideo = Comparator.comparing(Video::getTitle);
            var alphabeticalList = storeStream.sorted(compareVideo).collect(Collectors.toList());
            // Secondly, sort the list of videos ascendant by the number of views
            var compareRating = Comparator.comparingDouble(Video::getTotalViews);
            sortedList = alphabeticalList.stream().sorted(compareRating)
                    .collect(Collectors.toList());
        } else {
            // Firstly, sort the list of videos descendant by titles
            var storeStream = videos.stream();
            var compareVideo = Comparator.comparing(Video::getTitle).reversed();
            var alphabeticalList = storeStream.sorted(compareVideo).collect(Collectors.toList());
            // Secondly, sort the list of videos descendant by the number of views
            var compareRating = Comparator.comparingDouble(Video::getTotalViews)
                    .reversed();
            sortedList = alphabeticalList.stream().sorted(compareRating)
                    .collect(Collectors.toList());
        }
        return sortedList;
    }
}
