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
import java.util.Map;

public class Utils {
    static TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
    };

    public static String serialize(Map<String, String> map) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        json = mapper.writeValueAsString(map);
        return json;
    }

    public static HashMap<String, String> unserialize(String path) throws IOException {
        String fileString = readFile(path);
        HashMap<String, String> fileMap = new HashMap<>();
        if (path.endsWith(".json")) {
            fileMap = unserializeJSON(fileString);
        } else if (path.endsWith(".yml")) {
            fileMap = unserializeYAML(fileString);
        }
        return fileMap;
    }

    public static HashMap<String, String> unserializeJSON(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, typeRef);
    }

    public static HashMap<String, String> unserializeYAML(String yaml) throws JsonProcessingException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(yaml, typeRef);
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
