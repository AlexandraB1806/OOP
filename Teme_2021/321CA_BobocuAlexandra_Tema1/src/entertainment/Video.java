package entertainment;

import database.Database;
import user.User;

import java.util.ArrayList;

/**
 * Class for video
 * Classes Movie and Show inheritance this class
 */
public class Video {
    private String title;
    private final int releasedYear;
    private ArrayList<String> genres;
    private final ArrayList<String> actors;

    private int totalFavorites = 0;
    private int totalViews = 0;
    private double averageGrade;

    public Video(final String title, final int releasedYear,
                 final ArrayList<String> genres,
                 final ArrayList<String> actors) {
        this.title = title;
        this.releasedYear = releasedYear;
        this.genres = genres;
        this.actors = actors;
        this.averageGrade = 0.0;
    }

    /**
     * Method used to verify the genre of a video.
     * @param genreName the given genre
     * @return true if the genres are identical, or false if not
     */
    public boolean checkGenre(final String genreName) {
        // Traverse the genres' list
        for (String genre : genres) {
            if (genre.equals(genreName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method used to get the duration of a video.
     * It is overridden in the classes for movie and show.
     * @return 0
     */
    public int getDuration() {
        return 0;
    }

    /**
     * Method used to obtain all the favorite videos.
     * @return total number of favorite videos
     */
    public int getTotalFavorites() {
        return totalFavorites;
    }

    /**
     * Method used to reset the number of favorite videos
     * to zero.
     */
    public void resetTotalFavorites() {
        totalFavorites = 0;
    }

    /**
     * Method used to increment the total number of
     * favorites videos
     */
    public void incTotalFavorites() {
        this.totalFavorites++;
    }

    /**
     * Method used to obtain all the seen videos.
     * @return total number of seen videos
     */
    public int getTotalViews() {
        int views = 0;
        Database dtb = Database.getDatabase();
        // Traverse the users' list from Database
        for (User user : dtb.getUsers()) {
            // Found the video
            if (user.getHistory().containsKey(title)) {
                views += user.getHistory().get(title);
            }
        }
        return views;
    }

    /**
     * Method used to increment the total number of
     * seen videos.
     */
    public void incTotalViews() {
        this.totalViews++;
    }

    /**
     * Method used to obtain the grade given to a film/show.
     * @return the grade
     */
    public double getAverageGrade() {
        return averageGrade;
    }

    /**
     * Method used to set the grade given to a film/show.
     * @param averageGrade the given grade
     */
    public void setAverageGrade(final double averageGrade) {
        this.averageGrade = averageGrade;
    }

    /**
     * Method used to obtain the title of the video.
     * @return name of the video
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method used to name a video.
     * @param title name of the video
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Method used to obtain the year when a video
     * was released.
     * @return released year
     */
    public int getReleasedYear() {
        return releasedYear;
    }

    /**
     * Method used to obtain the genres of videos.
     * @return genres of the videos
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * Method used to set the genres for videos.
     * @param genres given list of genres
     */
    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    /**
     * Method used to obtain the actors that performed
     * in videos.
     * @return actors that performed
     */
    public ArrayList<String> getActors() {
        return actors;
    }
}
