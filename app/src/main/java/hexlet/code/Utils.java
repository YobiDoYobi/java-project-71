package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
import java.util.HashMap;
//import java.util.Map;

public class Utils {
    /*public static String serialize(Map<String, String> map) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(map);
    }*/

    public static <T> HashMap<String, T> unserialize(String path) throws IOException {
        String fileString = readFile(path);
        HashMap<String, T> fileMap = new HashMap<>();
        if (path.endsWith(".json")) {
            fileMap = unserializeJSON(fileString);
        } else if (path.endsWith(".yml")) {
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

    private static String readFile(String path) throws IOException {
        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        return Files.readString(fullPath);
    }

    /*public static void writeFile(String path, String content) throws IOException {
        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        Files.writeString(fullPath, content, StandardOpenOption.WRITE);
    }*/
}
