package output;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public final class JsonWriter {
    private JsonWriter() {

    }

    /**
     * Method used to write output files.
     * @param outputJson the filename
     * @param object object
     * @param <T> class
     * @throws IOException exception
     */
    public static <T> void  writeJson(final String outputJson, final T object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // Create the output directory named "output"
        File fileDir = new File("output/");
        fileDir.mkdir();
        // Create the output files
        File file = new File(outputJson);
        file.delete();
        file.createNewFile();
        // Update the output files with information
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, object);
    }
}
