package grades;

import entertainment.Season;
import entertainment.Show;

import java.util.List;

public final class ShowGrade {
    private ShowGrade() { }

    /**
     * Method used to calculate the average grade for each show from Database.
     * A show has a list of seasons. Firstly, the average grade for seasons is
     * calculated and retained in the variable averageSeason. sumGradesShow is
     * updated and the average grade for show is calculated and retained in the
     * variable averageShow.
     * @param shows the list of shows that are graded
     */
    public static void gradeForShow(final List<Show> shows) {
        // Traverse all the series
        for (Show show : shows) {
            double sumGradesShow = 0.0;
            // Traverse all the seasons from show
            for (Season season : show.getSeasons()) {
                double sumGradesSeason = 0.0;
                // Traverse all the grades given to a season
                for (int i = 0; i < season.getRatings().size(); i++) {
                    sumGradesSeason = sumGradesSeason + season.getRatings().get(i);
                }
                if (season.getRatings().size() > 0) {
                    // The medium rating of a season from a show
                    double averageSeason = sumGradesSeason / (season.getRatings().size());
                    // For every season, after calculating its average, it
                    // is included in the grades of the show
                    sumGradesShow = sumGradesShow + averageSeason;
                }
            }
            if (show.getSeasons().size() > 0) {
                // The medium rating
                double averageShow = sumGradesShow / show.getSeasons().size();
                show.setAverageGrade(averageShow);
            }
        }
    }
}
