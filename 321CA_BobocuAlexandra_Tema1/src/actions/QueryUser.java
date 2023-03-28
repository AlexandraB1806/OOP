package actions;

import common.Constants;
import database.Database;
import fileio.ActionInputData;
import org.json.JSONObject;
import user.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class QueryUser {
    private QueryUser() { }

    /**
     * Method used to obtain the sorted list of users who gave rating.
     * @param query from input
     * @return a JSON object that will be retained in
     * a result array of JSON objects
     */
    public static JSONObject numOfRatings(final ActionInputData query) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", query.getActionId());

        Database dtb = Database.getDatabase();

        List<User> sortedList = findAscDesNumOfRatings(query, dtb.getUsers());

        ArrayList<String> result = new ArrayList<>();

        int numMaxUsers = 0;
        for (User user : sortedList) {
            if (user.getNumRatings() != 0) {
                // Add the first users' names in result
                if (numMaxUsers < query.getNumber()) {
                    result.add(user.getUsername());
                    numMaxUsers++;
                }
            }
        }
        jsonObject.put(Constants.MESSAGE, "Query result: " + result);
        return jsonObject;
    }

    /**
     * Method used to sort the users ascending/ descending, firstly by the
     * name, then by the duration.
     * @param query from input
     * @param users list of videos that need to be sorted
     * @return the sorted list of videos
     */
    public static List<User> findAscDesNumOfRatings(final ActionInputData query,
                                                    final List<User> users) {
        List<User> sortedList;

        if (query.getSortType().equals("asc")) {
            // Firstly, sort the list of users ascendant by names
            var storeStream = users.stream();
            var compareUser = Comparator.comparing(User::getUsername);
            var alphabeticalList = storeStream.sorted(compareUser).collect(Collectors.toList());
            // Secondly, sort the list of users ascendant by the number of ratings
            var compareRating = Comparator.comparingInt(User::getNumRatings);
            sortedList = alphabeticalList.stream().sorted(compareRating)
                    .collect(Collectors.toList());
        } else {
            // Firstly, sort the list of users descendant by names
            var storeStream = users.stream();
            var compareUser = Comparator.comparing(User::getUsername).reversed();
            var alphabeticalList = storeStream.sorted(compareUser).collect(Collectors.toList());
            // Secondly, sort the list of users descendant by the number of ratings
            var compareRating = Comparator.comparingInt(User::getNumRatings).reversed();
            sortedList = alphabeticalList.stream().sorted(compareRating)
                    .collect(Collectors.toList());
        }
        return sortedList;
    }
}
