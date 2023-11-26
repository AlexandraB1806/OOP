package grades;

import entertainment.Movie;

import java.util.List;

public final class MovieGrade {
    private MovieGrade() { }

    /**
     * Method used to calculate the average grade for each movie
     * from the Database. The average grade for movie is calculated and
     * retained in the variable averageMovie.
     * @param movies the list of movies that are graded
     */
    public static void gradeForMovie(final List<Movie> movies) {
        double averageMovie = 0.0;
        // Traverse the list of movies
        for (Movie movie : movies) {
            double sumGradesMovie = 0.0;
            // Traverse the list of grades given to a movie
            for (double grade : movie.getGrades()) {
                sumGradesMovie += grade;
            }
            if (movie.getGrades().size() > 0) {
                // The medium rating
                averageMovie = sumGradesMovie / movie.getGrades().size();
                movie.setAverageGrade(averageMovie);
            }
        }
    }
}
