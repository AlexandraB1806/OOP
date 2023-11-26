package main;

import checker.Checker;
import common.Constants;
import input.Input;
import input.JsonReader;
import output.JsonWriter;
import output.Output;
import simulation.Simulation;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) {
        try {
            startSimulation();
            Checker.calculateScore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void startSimulation() throws IOException {
        final File folder = new File(Constants.INPUT_PATH);
        int counter = 1;
        File[] files = folder.listFiles();
        // Sort input files after the number of test
        assert files != null;
        Arrays.sort(files, new Comparator<>() {
            @Override
            public int compare(final File o1, final File o2) {
                int n1 = extractNumber(o1.getName());
                int n2 = extractNumber(o2.getName());
                return n1 - n2;
            }

            private int extractNumber(final String name) {
                int index = 0;
                try {
                    // number retains _index, where index = {1,2,...,25}
                    String number = name.substring(name.lastIndexOf('t') + 1,
                            name.lastIndexOf('.'));
                    index = Integer.parseInt(number);
                } catch (Exception e) {
                    // If filename does not match the format,
                    // then default to 0
                    index = 0;
                }
                return index;
            }
        });

        for (final File fileEntry : files) {
            if (!fileEntry.getName().contains("test")) {
                continue;
            }
            Simulation simulation = new Simulation();
            Output output = simulation.startSimulation(JsonReader.readInputJson(
                    fileEntry.getAbsolutePath(), Input.class));
            JsonWriter.writeJson(Constants.OUTPUT_PATH + counter
                    + Constants.FILE_EXTENSION, output);
            counter++;
        }
    }
}
