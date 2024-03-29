package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static <T> String serialize(ArrayList<Map<String, T>> diffList) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(diffList);
    }

    public static <T> HashMap<String, T> unserialize(String fileString, String type) throws IOException {
        HashMap<String, T> fileMap = new HashMap<>();
        if (type.equals("json")) {
            fileMap = unserializeJSON(fileString);
        } else if (type.equals("yml")) {
            fileMap = unserializeYAML(fileString);
        }
        return fileMap;
    }

    public static <T> HashMap<String, T> unserializeJSON(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<HashMap<String, T>>() {
        });
    }

    public static <T> HashMap<String, T> unserializeYAML(String yaml) throws JsonProcessingException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(yaml, new TypeReference<HashMap<String, T>>() {
        });
    }

    /*public static void writeFile(String path, String content) throws IOException {
        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        Files.writeString(fullPath, content, StandardOpenOption.WRITE);
    }*/
}
