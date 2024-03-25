package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static String serialize(Map<String, String> map) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        json = mapper.writeValueAsString(map);
        return json;
    }

    public static HashMap<String, String> unserialize(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
        };
        return mapper.readValue(json, typeRef);
    }

    public static String readFile(String path) throws IOException {
        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        String content = "";
        content = Files.readString(fullPath);
        return content;
    }

    /*public static void writeFile(String path, String content) throws IOException {
        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        Files.writeString(fullPath, content, StandardOpenOption.WRITE);
    }*/
}
