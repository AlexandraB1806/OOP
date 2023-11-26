package main;

import actions.Commands;
import actions.QueryActor;
import actions.QueryUser;
import actions.QueryVideo;
import actions.Recommendations;
import checker.Checkstyle;
import checker.Checker;
import common.Constants;
import database.Helpers;

import fileio.ActionInputData;
import fileio.Input;
import fileio.InputLoader;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * The entry point to this homework. It runs the checker that tests your implementation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * Call the main checker and the coding style checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH, Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();

        // Adding elements to Database
        Helpers.addMovies(input.getMovies());
        Helpers.addShows(input.getSerials());
        Helpers.addActors(input.getActors());
        Helpers.addUsers(input.getUsers());

        // Check the type of action: command, query or recommendation
        for (ActionInputData action : input.getCommands()) {
            // Check the type of command: favorite, view, rating
            if (action.getActionType().equals(Constants.COMMAND)) {
                if (action.getType().equals("favorite")) {
                    arrayResult.add(Commands.favorite(action));
                } else if (action.getType().equals("view")) {
                    arrayResult.add(Commands.view(action));
                } else if (action.getType().equals("rating")) {
                    arrayResult.add(Commands.rating(action));
                }
            }
            if (action.getActionType().equals(Constants.QUERY)) {
                if (action.getObjectType().equals(Constants.ACTORS)) {
                    if (action.getCriteria().equals("average")) {
                        arrayResult.add(QueryActor.average(action));
                    }
                    if (action.getCriteria().equals("awards")) {
                        arrayResult.add(QueryActor.awards(action));
                    }
                    if (action.getCriteria().equals("filter_description")) {
                        arrayResult.add(QueryActor.filterDescription(action));
                    }
                } else if (action.getObjectType().equals("users")) {
                    if (action.getCriteria().equals("num_ratings")) {
                        arrayResult.add(QueryUser.numOfRatings(action));
                    }
                } else {
                    if (action.getCriteria().equals("favorite")) {
                        arrayResult.add(QueryVideo.favorite(action));
                    }
                    if (action.getCriteria().equals("longest")) {
                        arrayResult.add(QueryVideo.longest(action));
                    }
                    if (action.getCriteria().equals("ratings")) {
                        arrayResult.add(QueryVideo.rating(action));
                    }
                    if (action.getCriteria().equals("most_viewed")) {
                        arrayResult.add(QueryVideo.mostViewed(action));
                    }
                }
            }
            if (action.getActionType().equals(Constants.RECOMMENDATION)) {
                if (action.getType().equals("standard")) {
                    arrayResult.add(Recommendations.standard(action));
                }
                if (action.getType().equals("best_unseen")) {
                    arrayResult.add(Recommendations.bestUnseen(action));
                }
                if (action.getType().equals("favorite")) {
                    arrayResult.add(Recommendations.favorite(action));
                }
                if (action.getType().equals("search")) {
                    arrayResult.add(Recommendations.search(action));
                }
                if (action.getType().equals("popular")) {
                    arrayResult.add(Recommendations.popular(action));
                }
            }
        }

        // Clear the database
        Helpers.clear();
        fileWriter.closeJSON(arrayResult);
    }
}
