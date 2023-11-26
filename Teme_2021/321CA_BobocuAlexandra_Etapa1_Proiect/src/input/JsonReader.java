package input;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public final class JsonReader {
    private JsonReader() {

    }

    /**
     * Method used to read and convert the json input tests.
     * @param json the filename
     * @param objectType the object type
     * @param <T> class
     * @return converted json file into object type
     * @throws IOException exception
     */
    public static <T> T readInputJson(final String json, final Class<T> objectType)
            throws IOException {
        ObjectMapper mapper = null;
        JsonNode jsonNode = null;
        try {
            mapper = new ObjectMapper();
            mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
            File inputFile = new File(json);

            jsonNode = mapper.readTree(inputFile);
            return mapper.convertValue(jsonNode, objectType);
        } catch (Error e) {
            System.out.println("Error");
        }
        assert mapper != null;
        return mapper.convertValue(jsonNode, objectType);
    }
}
