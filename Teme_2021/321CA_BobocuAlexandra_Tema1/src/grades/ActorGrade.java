package grades;

import actor.Actor;
import entertainment.Movie;
import entertainment.Show;

import java.util.ArrayList;
import java.util.List;

public final class ActorGrade {
    private ActorGrade() { }

    /**
     * Method used to calculate the average grade for each actor from Database.
     * Every time we find a movie, a show where the actor that needs grading
     * played, update how many ratings the actor received and variable
     * sumGradesActor, which retains a sum of grades given to an actor
     * that played in a video (movie or show). The average grade for an actor
     * is calculated and retained in the variable averageActor.
     * @param actors the list of actors that are graded
     * @param movies the list of movies where the actor that is graded plays
     * @param shows the list of series where the actor that is graded plays
     */
    public static void gradeForActor(final List<Actor> actors,
                                     final List<Movie> movies,
                                     final List<Show> shows) {
        // Traverse the actors' list
        for (Actor actor : actors) {
            // The number of ratings received by an actor
            int numOfRatings = 0;
            // The grades received by an actor
            double sumGradesActor = 0.0;
            ArrayList<String> filmography = actor.getFilmography();

            // Search in the filmography
            for (String videoName : filmography) {
                // Traverse the movies' list
                for (Movie movie : movies) {
                    String getEachTitle = movie.getTitle();
                    // Found the movie where the actor played
                    if (getEachTitle.equals(videoName)) {
                        // Obtain the grade given to the movie
                        double getGrade = movie.getAverageGrade();
                        if (getGrade > 0) {
                            sumGradesActor += getGrade;
                            numOfRatings++;
                        }
                    }
                }
                // Traverse the shows' list
                for (Show show : shows) {
                    String getEachTitle = show.getTitle();
                    // Found the show where the actor played
                    if (getEachTitle.equals(videoName)) {
                        // Obtain the grade given to the show
                        double getGrade = show.getAverageGrade();
                        if (getGrade > 0) {
                            sumGradesActor += getGrade;
                            numOfRatings++;
                        }
                    }
                }
            }
            if (numOfRatings > 0) {
                // The medium rating
                double averageActor = sumGradesActor / numOfRatings;
                actor.setRating(averageActor);
            }
        }
    }
}
