package actions;

import actor.Actor;
import common.Constants;
import database.Database;
import fileio.ActionInputData;
import org.json.JSONObject;
import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static grades.MovieGrade.gradeForMovie;
import static grades.ShowGrade.gradeForShow;
import static grades.ActorGrade.gradeForActor;

public final class QueryActor {
    private QueryActor() { }

    /**
     * Method used to obtain the sorted list of actors by average grade.
     * @param query from input
     * @return  a JSON object that will be retained in
     * a result array of JSON objects
     */
    public static JSONObject average(final ActionInputData query) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", query.getActionId());

        Database dtb = Database.getDatabase();

        // Calculate the rating for: movie, show, actor
        gradeForMovie(dtb.getMovies());
        gradeForShow(dtb.getSeries());
        gradeForActor(dtb.getActors(), dtb.getMovies(), dtb.getSeries());

        List<Actor> sortedList = findAscDesAverage(query, dtb.getActors());

        ArrayList<String> result = new ArrayList<>();

        int numMaxActors = 0;
        for (Actor actor : sortedList) {
            if (actor.getRating() != 0) {
                // Add the first actors' names in result
                if (numMaxActors < query.getNumber()) {
                    result.add(actor.getName());
                    numMaxActors++;
                }
            }
        }
        jsonObject.put(Constants.MESSAGE, "Query result: " + result);
        return jsonObject;
    }

    /**
     * Method used to obtain the sorted list of actors by awards.
     * @param query from input
     * @return  a JSON object that will be retained in
     * a result array of JSON objects
     */
    public static JSONObject awards(final ActionInputData query) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", query.getActionId());

        Database dtb = Database.getDatabase();

        ArrayList<String> result = new ArrayList<>();
        List<Actor> actors = dtb.getActors();
        // Traverse all the awards from input
        for (String award : query.getFilters().get(3)) {
            // Delete the actors that don't have the awards
            actors = actors.stream().filter(actor -> actor.getAwards().containsKey(
                    Utils.stringToAwards(award))).toList();
        }
        // Traverse the actors' list
        for (Actor actor : actors) {
            int sum = 0;
            for (Integer award : actor.getAwards().values()) {
                sum += award;
            }
            // Update the number of awards for actor
            actor.setNumAwards(sum);
        }
        List<Actor> sortedList = findAscDesAwards(query, actors);

        if (sortedList.size() < query.getNumber()) {
            // Add the first actors' names in result
            for (int i = 0; i < sortedList.size(); i++) {
                result.add(sortedList.get(i).getName());
            }
            jsonObject.put(Constants.MESSAGE, "Query result: " + result);
            return jsonObject;
        } else {
            // Add all the actors' names in result
            for (int i = 0; i < query.getNumber(); i++) {
                result.add(sortedList.get(i).getName());
            }
            jsonObject.put(Constants.MESSAGE, "Query result: " + result);
            return jsonObject;
        }
    }

    /**
     * Method used to obtain the sorted list of actors, who have
     * in description specific keywords.
     * @param query from input
     * @return  a JSON object that will be retained in
     * a result array of JSON objects
     */
    public static JSONObject filterDescription(final ActionInputData query) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", query.getActionId());

        Database dtb = Database.getDatabase();

        ArrayList<String> result = new ArrayList<>();

        List<Actor> actors = dtb.getActors();

        int ct;
        // Traverse the actors' list
        for (Actor act : dtb.getActors()) {
            ct = 0;
            // Applying filters
            // Traverse the list of keywords
            for (String keyword : query.getFilters().get(2)) {
                String descriptionActor = act.getCareerDescription();
                String toLow = descriptionActor.toLowerCase();
                var split = toLow.split("[ -.,]");
                if (Arrays.asList(split).contains(keyword)) {
                    // Found keywords in description
                    ct++;
                }
            }
            var getFilQuery = query.getFilters().get(2);
            // Check if the actor has all the keywords in description.
            // If not, remove the actor from list
            if (ct != getFilQuery.size()) {
                actors = actors.stream().filter(actor -> actor != act).toList();
            }
        }

        var nameSortAsc = Comparator.comparing(Actor::getName);
        // Sort ascendant
        var streamSortAsc = actors.stream().sorted(nameSortAsc);
        // Sort descendant
        var streamSortDesc = actors.stream().sorted(nameSortAsc.reversed());

        // Retains the sorted list
        List<Actor> cpyActor;

        if (query.getSortType().equals("asc")) {
            cpyActor = streamSortAsc.collect(Collectors.toList());
        } else {
            cpyActor = streamSortDesc.collect(Collectors.toList());
        }

        // Populate the result list
        for (Actor act : cpyActor) {
            result.add(act.getName());
        }
        jsonObject.put(Constants.MESSAGE, "Query result: " + result);
        return jsonObject;
    }

    /**
     * Method used to sort the actors ascending/ descending, firstly by the
     * name, then by the rating.
     * @param query from input
     * @param actors list of videos that need to be sorted
     * @return the sorted list of videos
     */
    public static List<Actor> findAscDesAverage(final ActionInputData query,
                                                final List<Actor> actors) {
        List<Actor> sortedList;

        if (query.getSortType().equals("asc")) {
            // Firstly, sort the list of actors ascendant by names
            var storeStream = actors.stream();
            var compareActor = Comparator.comparing(Actor::getName);
            var alphabeticalList = storeStream.sorted(compareActor).collect(Collectors.toList());
            // Secondly, sort the list of actors ascendant by rating
            var compareRating = Comparator.comparingDouble(Actor::getRating);
            sortedList = alphabeticalList.stream().sorted(compareRating)
                    .collect(Collectors.toList());
        } else {
            // Firstly, sort the list of actors descendant by names
            var storeStream = actors.stream();
            var compareActor = Comparator.comparing(Actor::getName).reversed();
            var alphabeticalList = storeStream.sorted(compareActor)
                    .collect(Collectors.toList());
            // Secondly, sort the list of actors descendant by rating
            var compareRating = Comparator.comparingDouble(Actor::getRating).reversed();
            sortedList = alphabeticalList.stream().sorted(compareRating)
                    .collect(Collectors.toList());
        }
        return sortedList;
    }

    /**
     * Method used to sort the actors ascending/ descending, firstly by the
     * name, then by the number of awards.
     * @param query from input
     * @param actors list of videos that need to be sorted
     * @return the sorted list of videos
     */
    public static List<Actor> findAscDesAwards(final ActionInputData query,
                                               final List<Actor> actors) {
        List<Actor> sortedList;

        if (query.getSortType().equals("asc")) {
            // Firstly, sort the list of actors ascendant by names
            var storeStream = actors.stream();
            var compareActor = Comparator.comparing(Actor::getName);
            var alphabeticalList = storeStream.sorted(compareActor)
                    .collect(Collectors.toList());
            // Secondly, sort the list of actors ascendant by awards
            var compareAwards = Comparator.comparing(Actor::getNumAwards);
            sortedList = alphabeticalList.stream().sorted(compareAwards)
                    .collect(Collectors.toList());
        } else {
            // Firstly, sort the list of actors descendant by names
            var storeStream = actors.stream();
            var compareActor = Comparator.comparing(Actor::getName).reversed();
            var alphabeticalList = storeStream.sorted(compareActor)
                    .collect(Collectors.toList());
            // Secondly, sort the list of actors descendant by awards
            var compareAwards = Comparator.comparing(Actor::getNumAwards).reversed();
            sortedList = alphabeticalList.stream().sorted(compareAwards)
                    .collect(Collectors.toList());
        }
        return sortedList;
    }
}
